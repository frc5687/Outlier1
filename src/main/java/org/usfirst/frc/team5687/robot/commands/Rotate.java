package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import java.util.Date;

import org.usfirst.frc.team5687.robot.Calibration;
import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.Constants.CalibrationDefaults;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to rotate left or right for a particular time or for a specific degrees
 */
public class Rotate extends OutlierCommand {
	public static int LEFT = -1;
	public static int RIGHT = 1;

	DriveTrain drive = Robot.driveTrain;
	private long end = 0;
	private int timeToRotate = 0;
	private double rightSpeed = 0;
	private double leftSpeed = 0;
    
	/**
	 * Rotate left or right the specified number of degrees.
	 * @param direction
	 * @param degrees
	 */
	public Rotate(int direction, double degrees) {
        requires(drive);

		this.leftSpeed = Constants.AutonomousSettings.DRIVE_SPEED * (direction == LEFT ? 1 : -1);
        this.rightSpeed = Constants.AutonomousSettings.DRIVE_SPEED * (direction == LEFT ? -1 : 1);

        double degreesPerSecond = 0;

		if (direction==LEFT){
	        // Calculate the settings
	        degreesPerSecond = Constants.AutonomousSettings.ROTATION_SPEED * Constants.CalibrationDefaults.LEFT_ROTATION;
		} else {
	        degreesPerSecond = Constants.AutonomousSettings.ROTATION_SPEED * Constants.CalibrationDefaults.LEFT_ROTATION;
		}
		double degreesPerMillisecond = degreesPerSecond/1000;
		int milliseconds = (int)Math.round(degrees / degreesPerMillisecond);
        this.timeToRotate = milliseconds;
    }

	/**
	 * Rotate left or right for the specified number of milliseconds. 
	 * @param direction
	 * @param milliseconds
	 */
	public Rotate(int direction, int milliseconds) {
        requires(drive);
        // Calculate the settings
        this.leftSpeed = Constants.AutonomousSettings.DRIVE_SPEED * (direction == LEFT ? -1 : 1);
        this.rightSpeed = Constants.AutonomousSettings.DRIVE_SPEED * (direction == LEFT ? 1 : -1);
        
        this.timeToRotate = milliseconds;
    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	end = (new Date()).getTime() + timeToRotate;
    	if (leftSpeed<0) {
    		LogAction(String.format("Turning left for %1$d milliseconds", timeToRotate));
    	} else {
    		LogAction(String.format("Turning right for %1$d milliseconds", timeToRotate));
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		drive.tankDrive(leftSpeed, rightSpeed, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (new Date()).getTime() > end;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.tankDrive(0,0,false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
