package org.usfirst.frc.team5687.robot.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;

import org.usfirst.frc.team5687.robot.Constants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command group driven by a script uploaded to the roborio
 */
public class AutonomousScript extends CommandGroup {
	private String scriptName = null;
	private boolean isValid = false;

	/**
	 * Parses the supplied script file and builds an AutonomousScript from it.
	 * Logs any parsing errors.
	 * If the script parses successfully, the DisplayName property will include the OK suffix and IsValid will return true.
	 * Otherwise, DisplayName will include the Error suffix and IsValid will return false.
	 *    
	 * @param fullPath The full path to the script.  Typically this should be /home/lvuser/scripts. 
	 * @param scriptName The name of the script file.
	 */
	public AutonomousScript(String fullPath, String scriptName) {
		isValid = true;
		this.scriptName = scriptName;
		
		// Open the file...
		try {
			logMessage(String.format("Parsing script file: %1$s", scriptName));

			// Get a BufferedReader
			File file = new File(fullPath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String scriptLine;
			
			// For each line in file...
			int line = 1;
			while ((scriptLine = bufferedReader.readLine()) != null) {
				// Prune off start and end whitespace and convert to lowercase to make matching easier
				scriptLine = scriptLine.trim().toLowerCase().replaceAll("\\s+", " ");
				

				// If the line is not blank and does not start with a #, parse a command
				if (scriptLine.length() > 0 && scriptLine.charAt(0) != '#') {
					// Add the command to the sequence
					try {
						Command command = parseLine(scriptLine);
						if (command != null) {
							logMessage(String.format("Adding %1$s command for line %2$d", command.getClass().getSimpleName(), line));
							
							addSequential(command);
						} else {
							// Log the bad line to RoboRio
							logMessage(String.format("Parse error on line %1$d: %2$s", line, scriptLine));
							isValid = false;
						}
					} catch (ScriptParseException spe) {
						logMessage(String.format("Parse error on line %1$d: %2$s", line, scriptLine));
						logMessage(spe.getMessage());
						isValid = false;
					} catch (Exception e) {
						logMessage(String.format("Unexpected error on line %1$d: %2$s", line, scriptLine));
						logMessage(e.getMessage());
						isValid = false;
					}
				}
				
				line++;
			}
			
			fileReader.close();
			logMessage(String.format("Successfully parsed %1$s", scriptName));
			
		} catch (IOException e) {
			logMessage(String.format("IO error parsing %1$s: %2$s", scriptName, e.getMessage()));
		}
	}

	/**
	 * Parses a line of the script and generates a Command.
	 * 
	 * @param scriptLine
	 * @return
	 */
	private Command parseLine(String scriptLine) throws ScriptParseException {
		// Guard for null
		if (scriptLine == null) return null;

		// Guard for empty lines
		if (scriptLine.length() == 0) { return null; }

		// Grab the tokens
		String[] tokens = scriptLine.split(" ");

		switch(tokens[0]) {
			case "drive":
				return parseDriveCommand(tokens);
			case "move":
				return parseMoveCommand(tokens);
			case "turn":
			case "rotate":
				return parseTurnCommand(tokens);
			case "wait":
			case "pause":
				return parseWaitCommand(tokens);
			case "reset":
				return parseResetCommand(tokens);
		}
			
		throw new ScriptParseException("Unrecognized token: '%1$s'", tokens[0]);
	}
	
	/**
	 * Parses a drive command.
	 * @param tokens
	 * @return
	 */
	private Command parseDriveCommand(String[] tokens) {
		// drive direction distance [units [at speed]]
		int direction = 0;
		double inches = 0;
		double speed = 0;

		int tokenCheck = 3;

		// Get direction
		switch (tokens[1]) {
			case "forward":
			case "forwards":
			case "ahead":
				direction = -1;
				break;
			case "back":
			case "backwards":
				direction = 1;
				break;
			default:
				throw new ScriptParseException("Invalid direction passed to drive command: '%1$s'.  Expected 'forward' or 'backward'.", tokens[1]);
		}

		// Get distance
		try {
			inches = Double.parseDouble(tokens[2]);
		} catch (NumberFormatException nfe) {
			throw new ScriptParseException("Invalid distance passed to drive command: '%1$s'.", tokens[2]);
		}

		if (tokenCheck<tokens.length && "inches".equals(tokens[tokenCheck])) {
			tokenCheck ++;
		}

		// Separator
		if (tokenCheck<tokens.length && "at".equals(tokens[tokenCheck])) {
			tokenCheck ++;
		}

		// Get speed
		if (tokenCheck<tokens.length && "speed".equals(tokens[tokenCheck])) {
			tokenCheck ++;
		}

		if (tokenCheck<tokens.length){
			try {
				speed = Double.parseDouble(tokens[tokenCheck]);
			} catch (NumberFormatException nfe) {
				throw new ScriptParseException("Invalid speed passed to drive command: '%1$s'.", tokens[tokenCheck]);
			}
		}
		else {
			speed = Constants.AutonomousSettings.DRIVE_SPEED;
		}

		return new AutoDrive(speed, inches * (double)direction);
	}

	/**
	 * Parses a 'move stacker' or 'move guides' command. 
	 * @param tokens
	 * @return
	 */
	private Command parseMoveCommand(String[] tokens) {
		// move stacker to setpoint 
		// move stacker to inches
		if (tokens.length<2) {
			throw new ScriptParseException("Move command requires either 'stacker' or 'guides' next.");
		}
		switch(tokens[1]) {
			case "stacker":
				return parseStackerCommand(tokens);
			case "guides":
			case "bumpers":
			case "guide":
			case "bumper":
				return parseGuidesCommand(tokens);
		}
		
		throw new ScriptParseException("Move command requires either 'stacker' or 'guides' next.");
	}

	/**
	 * Parses a 'move stacker' command.
	 * @param tokens
	 * @return
	 */
	private Command parseStackerCommand(String[] tokens) {
		if (tokens.length<3) {
			throw new ScriptParseException("Move command requires the phrase 'move stacker to'."); 
		}

		if (!"to".equals(tokens[2])) {
			throw new ScriptParseException("Move command requires the phrase 'move stacker to'.  Found '%1$s'.", tokens[2]); 
		}
		
		
		if (tokens.length<4) {
			throw new ScriptParseException("Move command requires a setpoint or height."); 
		}

		// Special handling for 'move stacker to GROUND'.
		// We want to translate this into a ResetStacker() call. 
		if ("ground".equals(tokens[3])) {
			return new ResetStacker();
		}
		
		// Otherwise, try to parse it as a double...
		double setpoint = -1;
		try {
			setpoint = Double.parseDouble(tokens[3]) - Constants.StackerHeights.HEIGHT_OFFSET;
		} catch (NumberFormatException nfe) {
			setpoint = -1;
		}

		if (setpoint < 0) {
			// And if that fails, try to parse it as a double constant in the StackerHeights class
			try {
				setpoint = parseDoubleConstant(Constants.StackerHeights.class, tokens[3]);
			} catch (ParseException pe) {
				setpoint = -1;
			}
		}

		if (setpoint < 0) {
			// Still no dice.  Report it!
			throw new ScriptParseException("Unable to determine height or setpoint for move stacker command: '%1$s'.",tokens[3]);
		}
		return new MoveStackerToSetpoint(setpoint); 
	}

	/**
	 * Parses a 'move guides' command.
	 * @param tokens
	 * @return
	 */
	private Command parseGuidesCommand(String[] tokens) {
		if (tokens.length<3) {
			throw new ScriptParseException("Move guides command requires 'in' or 'out' next."); 
		} 
		
		if ("in".equals(tokens[2])) {
			return new MoveGuides(Constants.Guides.IN); 
		} else if ("out".equals(tokens[2])) {
			return new MoveGuides(Constants.Guides.OUT); 
		}

		throw new ScriptParseException("Move guides command requires 'in' or 'out' next. Found: '%1$s'.", tokens[2]); 
	}

	
	/**
	 * Parse a 'reset stacker' command
	 * @param tokens
	 * @return
	 */
	private Command parseResetCommand(String[] tokens) {
		if (tokens.length<2) {
			throw new ScriptParseException("Expected token 'stacker' after reset.");
		}
		
		if (!"stacker".equals(tokens[1])) {
			throw new ScriptParseException("Expected token 'stacker' after reset.  Found '%1$s'.",tokens[1]);
		}
		
		return new ResetStacker();
	}
	
	/**
	 * Parse a turn command.
	 * @param tokens
	 * @return
	 */
	private Command parseTurnCommand(String[] tokens) {
		// turn direction degrees [units]
		int direction = 0;
		
		if (tokens.length<2) { 
			throw new ScriptParseException("No direction passed to turn command."); 
		}
		
		switch (tokens[1]) {
			case "left":
			case "counter":
			case "counterclock":
			case "counterclockwise":
				direction = Rotate.LEFT;
				break;
			case "right":
			case "clockwise":
				direction = Rotate.RIGHT;
				break;
			default:
				throw new ScriptParseException("Invalid direction passed to turn command: '%1$s'.", tokens[1]);
		}

		if (tokens.length < 3) { 
			throw new ScriptParseException("No degrees passed to turn command."); 
		}

		double degrees = 0;
		try {
			degrees = Double.parseDouble(tokens[2]);
		} catch (NumberFormatException nfe) {
			throw new ScriptParseException("Invalid degrees passed to turn command: '%1$s'.", tokens[2]);
		}

		return new Rotate(direction, degrees);
	}

	/**
	 * Parse a wait command.
	 * @param tokens
	 * @return
	 */
	private Command parseWaitCommand(String[] tokens) {
		// wait seconds
		double waitTime = 0;
		int tokenCheck = 1;

		if (tokens.length<2) { 
			throw new ScriptParseException("Wait command requires a time to wait"); 
		}	
		// optional addition of phrase "for" (wait FOR 3)
		if ("for".equals(tokens[tokenCheck])){
			tokenCheck ++;
		}
		//is it a number?
		try {
			waitTime = Double.parseDouble(tokens[tokenCheck]);
		} catch (NumberFormatException nfe) {
			throw new ScriptParseException("Invalid time passed to wait command: '%1$s'.", tokens[tokenCheck]);
		}
		
		if (tokens.length > tokenCheck+1) {
			tokenCheck++;
		
			// if the unit is seconds, 
			//multiply by 1000 to reach the milliseconds unit required
			if ("seconds".equals(tokens[tokenCheck])||"second".equals(tokens[tokenCheck]) ) {
				waitTime = waitTime * 1000;
			} else if (!"milliseconds".equals(tokens[tokenCheck])) {
				//no units besides seconds/milliseconds accepted!
				throw new ScriptParseException("Only units milliseconds/seconds accepted in wait command.  Found: '%1$s'.", tokens[tokenCheck+1]);
			}
		}
		return new AutonomousWait((int) waitTime);

	}

	/**
	 * Sends a message to the driverstation logs.
	 * @param message
	 */
	private void logMessage(String message) {
		// Log error
		DriverStation.reportError(message + "\r\n", false);
	}

	/**
	 * Parses a constant from a script file in the context of a specific class, returning the double value of the constant.
	 * For example, the script command 'move stacker to CLEAR_FIRST' would result in the call call to this function with
	 * ParseDoubleConstant(StackerHeights, "CLEAR_FIRST") which would return 45.75
	 * 
	 * @param constantClass
	 * @param constantName
	 * @return
	 * @throws ParseException
	 */
	private double parseDoubleConstant(Class constantClass, String constantName) throws ParseException {
		try {
			Field field = constantClass.getField(constantName.toUpperCase());
			if ((field.getModifiers() & (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL)) > 0) {
				Object val = field.get(null);
				if (val instanceof Double) {
					return ((Double)val).doubleValue();
				} else {
					throw new ParseException("Unable to convert " + constantName + " to a value in class " + constantClass.getName(), 0);
				}
			} else {
				throw new ParseException("Unable to convert " + constantName + " to a value in class " + constantClass.getName(), 0);
			}
		} catch (NoSuchFieldException nsfe) {
			throw new ParseException("Unable to convert " + constantName + " to a value in class " + constantClass.getName(), 0);
		} catch (IllegalAccessException iae) {
			throw new ParseException("Unable to convert " + constantName + " to a value in class " + constantClass.getName(), 0);
		}
	}

	private int parseIntConstant(Class constantClass, String constantName) throws ParseException {
		try {
			Field field = constantClass.getField(constantName.toUpperCase());
			if ((field.getModifiers() & (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL)) > 0) {
				Object val = field.get(null);
				if (val instanceof Integer) {
					return ((Integer)val).intValue();
				} else {
					throw new ParseException("Unable to convert " + constantName + " to a value in class " + constantClass.getName(), 0);
				}
			} else {
				throw new ParseException("Unable to convert " + constantName + " to a value in class " + constantClass.getName(), 0);
			}
		} catch (NoSuchFieldException nsfe) {
			throw new ParseException("Unable to convert " + constantName + " to a value in class " + constantClass.getName(), 0);
		} catch (IllegalAccessException iae) {
			throw new ParseException("Unable to convert " + constantName + " to a value in class " + constantClass.getName(), 0);
		}
	}

	/**
	 * Returns the display name of the script.
	 * This is the script name with a suffix of OK or ERROR to reflect it parsing status.
	 * @return the script name with a suffix of OF if the script parsed successfully and ERROR if it did not.
	 */
	public String getDislayName() {
		return scriptName + (isValid ? " - OK" : " - ERROR");
	}
	
	/**
	 * The parsing status of the script.
	 * @return True if the script parsed successfully and false otherwise.
	 */
	public boolean isValid() {
		return this.isValid;
	}
}
