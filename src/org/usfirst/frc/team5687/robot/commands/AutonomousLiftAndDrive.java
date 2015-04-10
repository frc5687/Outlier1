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
public class AutonomousLiftAndDrive extends CommandGroup {
    
	private Calendar end = null;
	
    public  AutonomousLiftAndDrive() {
    	    	
    	addSequential(new MoveGuides(Constants.Guides.OUT));
    	
    	// Lift to set point
    	addSequential(new LiftStackerForTime(Constants.AutonomousSettings.LIFT_TIME));

    	// Drive forward at set speed for set milliseconds 
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED, Constants.AutonomousSettings.DRIVE_TIME));
    
    }
    
}
