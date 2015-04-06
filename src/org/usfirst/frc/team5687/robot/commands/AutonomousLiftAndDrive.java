package org.usfirst.frc.team5687.robot.commands;

import org.usfirst.frc.team5687.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous commandgroup to move forward for a set time, then lift stacker to a setpoint.
 */
public class AutonomousLiftAndDrive extends CommandGroup {
	
    public  AutonomousLiftAndDrive() {
    	    	
    	
    	// Lift to set point
    	addSequential(new LiftStackerForTime(Constants.AutonomousSettings.LIFT_TIME));

    	// Drive forward at set speed for set milliseconds 
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED, Constants.AutonomousSettings.DRIVE_TIME));
    
    }
    
}
