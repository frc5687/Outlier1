package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import java.util.Date;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.Constants.Calibration;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous commandgroup to move forward for a set time, then lift stacker to a setpoint.
 */
public class MoveSideways extends CommandGroup {
	public static int LEFT = -1;
	public static int RIGHT = 1;
	
	
    public  MoveSideways(int direction, double distance) {
    	int runTime =  (int)Math.round(distance / Constants.AutonomousSettings.DRIVE_SPEED * Calibration.SIDEWAYS);
    	
    	// Drive forward at set speed for set milliseconds 
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED  *(direction == RIGHT?-1:0),Constants.AutonomousSettings.DRIVE_SPEED *(direction == LEFT?-1:0), runTime));
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED  *(direction == RIGHT?0:-1),Constants.AutonomousSettings.DRIVE_SPEED *(direction == LEFT?0:-1), runTime));
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED  *(direction == RIGHT?1:0),Constants.AutonomousSettings.DRIVE_SPEED *(direction == LEFT?1:0), runTime));
    	addSequential(new AutoDrive(Constants.AutonomousSettings.DRIVE_SPEED  *(direction == RIGHT?0:1),Constants.AutonomousSettings.DRIVE_SPEED *(direction == LEFT?0:1), runTime));
    }
    
}
