package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import java.util.Date;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command
 */
public class AutonomousResetAndDrive extends CommandGroup {
    
	private Calendar end = null;
	
    public  AutonomousResetAndDrive() {
    	
    	// Reset the stacker
    	addSequential(new ResetStacker());
    	
    	// Drive forward at .2 speed for 1000 milliseconds 
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED, Constants.AutonomousSettings.DRIVE_TIME));
    	
    }
    
}
