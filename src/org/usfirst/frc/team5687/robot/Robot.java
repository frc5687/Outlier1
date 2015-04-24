package org.usfirst.frc.team5687.robot;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team5687.robot.Constants.CalibrationDefaults;
import org.usfirst.frc.team5687.robot.commands.AutonomousDoNothing;
import org.usfirst.frc.team5687.robot.commands.AutonomousDriveOnly;
import org.usfirst.frc.team5687.robot.commands.AutonomousLiftAndDrive;
import org.usfirst.frc.team5687.robot.commands.AutonomousResetAndDrive;
import org.usfirst.frc.team5687.robot.commands.AutonomousResetLiftAndDrive;
import org.usfirst.frc.team5687.robot.commands.AutonomousResetOnly;
import org.usfirst.frc.team5687.robot.commands.AutonomousScript;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5687.robot.subsystems.Guides;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Main robot class
 */
public class Robot extends IterativeRobot {
	Preferences prefs;
	
	double testpref = 0;
	
	public static DriveTrain driveTrain;
	public static Stacker stacker;
	public static Guides guides;
	public static OI oi;
	
    Command autonomousCommand;
    SendableChooser autoChooser;
    
    CustomCameraServer server;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	initPrefs();
    	
    	driveTrain = new DriveTrain();
		stacker = new Stacker();
		guides = new Guides();
		oi = new OI();
    	
		// Load the auto scripts from filesystem...
		List<AutonomousScript> autoScripts = loadAutoScripts();
		
    	// Set up the autonomous choices...
    	autoChooser = new SendableChooser();
    	
    	// By default, do nothing at all.  This is the safest choice :P
    	autoChooser.addDefault("Default - None", new AutonomousDoNothing());
        
    	// Options are displayed in increasing order of risk
		autoChooser.addObject("Reset Stacker ONLY", new AutonomousResetOnly());
		autoChooser.addObject("Reset and Drive ONLY", new AutonomousResetAndDrive());
		autoChooser.addObject("Lift and Drive ONLY", new AutonomousLiftAndDrive());
		autoChooser.addObject("Drive ONLY", new AutonomousDriveOnly());
		autoChooser.addObject("Reset, Drive and Lift", new AutonomousResetLiftAndDrive());

		// Add autonomous script commands to the autochooser
		for (AutonomousScript script : autoScripts) {
			autoChooser.addObject(script.getDislayName(), script);
		}
		
    	// Add the chooser to the dashboard, tested and working
		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
		
    	// Setup camera streaming, working sometimes
        try {
    		server = CustomCameraServer.getInstance();
    		server.setQuality(50);
    		server.startAutomaticCapture("cam2"); 
    		server.setSize(1); // force 320x240
    				
    	} catch (Exception e) {
    		DriverStation.reportError("Failed to setup camera server", true);
    	}
        
		updateDashboard();
    }
	
    private void initPrefs() {
		prefs = Preferences.getInstance();
//		Calibration.Drive.ROTATION = prefs.getDouble("DriveRotation", Constants.CalibrationDefaults.ROTATION);
		Calibration.Drive.STRAIGHT = prefs.getDouble("DriveStraigt", Constants.CalibrationDefaults.STRAIGHT);
		Calibration.Drive.SIDEWAYS = prefs.getDouble("DriveSideways", Constants.CalibrationDefaults.SIDEWAYS);
		
		Calibration.Guides.LEFT_IN = prefs.getDouble("LeftGuideIn", CalibrationDefaults.LEFT_IN);
		Calibration.Guides.LEFT_OUT = prefs.getDouble("LeftGuideOut", CalibrationDefaults.LEFT_OUT);
		Calibration.Guides.RIGHT_IN = prefs.getDouble("RightGuideIn", CalibrationDefaults.RIGHT_IN);
		Calibration.Guides.RIGHT_OUT = prefs.getDouble("RightGuideOut", CalibrationDefaults.RIGHT_OUT);
		prefs.save();
    }
	private List<AutonomousScript> loadAutoScripts() {
		List<AutonomousScript> scripts = new LinkedList<AutonomousScript>();
		
		// 1) List files in scripts folder...
		File folder = new File(Constants.SCRIPTS_PATH);
	    for (final File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
        		// 2) For each file, add a new AutonomousScript(fileName)  to list
	        	AutonomousScript script = null;
	        	try {
	        		script = new AutonomousScript(fileEntry.getCanonicalPath(), fileEntry.getName());
	        		scripts.add(script);
	        	} catch (IOException ioe) {
	        		DriverStation.reportError("Unable to process script file: " + fileEntry.getName(), false);
	        		DriverStation.reportError(ioe.getMessage(), false);
	        	}
	        }
	    }
		return scripts;
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command)autoChooser.getSelected();
    	if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    public void teleopInit() {
		// Stop autonomous commands when teleop starts
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void updateDashboard()
    {
    	SmartDashboard.putData(Robot.driveTrain);
    	SmartDashboard.putData(Robot.stacker);	
    	SmartDashboard.putData(Robot.guides);	
    }
}
