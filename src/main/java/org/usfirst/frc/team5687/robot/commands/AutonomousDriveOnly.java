package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import java.util.Date;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous commandgroup to move forward for a set time, then lift stacker to a setpoint.
 */
public class AutonomousDriveOnly extends CommandGroup {
    
	private Calendar end = null;
	
    public  AutonomousDriveOnly() {
    	    	
    	// Drive forward at set speed for set milliseconds 
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED, Constants.AutonomousSettings.DRIVE_TIME));
    
    }
    
}
