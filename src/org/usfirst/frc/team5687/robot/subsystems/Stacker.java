package org.usfirst.frc.team5687.robot.subsystems;

import org.usfirst.frc.team5687.robot.RobotMap;
import org.usfirst.frc.team5687.robot.commands.MoveStackerManually;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the tote and can stacker
 */
public class Stacker extends Subsystem {
    
    private SpeedController stackerMotor;
    private DigitalInput lowerSensor;
    private DigitalInput upperSensor;
    
    /*
     * Constructor
     */
    public Stacker() {
    	stackerMotor = new Victor(RobotMap.stackerMotor);
    	lowerSensor = new DigitalInput(RobotMap.hallBottom);
    	upperSensor = new DigitalInput(RobotMap.hallTop);
    }

    /*
     * Sets the initial command for the stacker subsystem
     * (non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Subsystem#initDefaultCommand()
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MoveStackerManually());
    }
    
    /*
     * Sets the lift motor speed
     */
    public void moveStacker(double speed) {
    	boolean movingDown = speed < 0;
    	boolean atLowerLimit = !lowerSensor.get();
    	boolean atUpperLimit = !upperSensor.get();
    	
    	// Don't allow movement if lift is already at the limit for the chosen direction
    	if((movingDown && atLowerLimit) || (!movingDown && atUpperLimit)) {
    		stackerMotor.set(0);
    	} else {
    		stackerMotor.set(speed);
    	}
    }
}

