package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import org.usfirst.frc.team5687.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous commandgroup to reset the stacker to bottom, 
 * move forward for a set time, then lift stacker to a setpoint.
 */
public class AutonomousResetLiftAndDrive extends CommandGroup {
	
	private Calendar end = null;
	
    public  AutonomousResetLiftAndDrive() {

    	addSequential(new MoveGuides(Constants.Guides.OUT));

    	// Drive forward at set speed for set milliseconds 
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED, Constants.AutonomousSettings.CLEAR_TIME));

    	addSequential(new ResetStacker());
 
       	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED*-1, Constants.AutonomousSettings.CLEAR_TIME));
   	
    	// Lift to set point
    	addSequential(new LiftStackerForTime(Constants.AutonomousSettings.LIFT_TIME));

    	// Drive forward at set speed for set milliseconds 
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED, Constants.AutonomousSettings.DRIVE_TIME));
    }
    
}
