package org.usfirst.frc.team5687.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5687.robot.Calibration;
import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to drive the robot at specified speeds for specified times or distances. 
 */
public class AutoDrive extends OutlierCommand {

	DriveTrain drive = Robot.driveTrain;
	private long end = 0;
	private int timeToDrive = 0;
	private double rightSpeed = 0;
	private double leftSpeed = 0;

	
	/**
	 * Drive at the specified speed for the specified distance in inches.
	 * To move backwards, pass negative inches.
	 * @param speed
	 * @param inches
	 */
	public AutoDrive(double speed, double inches) {
		requires(drive);
		double inchesPerSecond = Math.abs(speed) * Constants.CalibrationDefaults.STRAIGHT;
		double inchesPerMillisecond = inchesPerSecond / 1000;
		int direction = (inches<0?-1:1);
		double distanceInInches = Math.abs(inches);
		double milliseconds = distanceInInches / inchesPerMillisecond;

        this.leftSpeed = Math.abs(speed) * direction;
        this.rightSpeed = Math.abs(speed) * direction;
        this.timeToDrive = (int)Math.round(milliseconds);
    }
	
	/**
	 * Drive at the specified speed for the specified time in milliseconds.
	 * @param speed
	 * @param timeToDrive
	 */
	public AutoDrive(double speed, int timeToDrive) {
        requires(drive);
        this.leftSpeed = speed;
        this.rightSpeed = speed;
        this.timeToDrive = timeToDrive;
    }
	
	/**
	 * Drive the two sides at different speeds for the specified time in milliseconds.  Intended mainly as a helper for other motions. 
	 * @param leftSpeed
	 * @param rightSpeed
	 * @param timeToDrive
	 */
	public AutoDrive(double leftSpeed, double rightSpeed, int timeToDrive) {
        requires(drive);
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.timeToDrive = timeToDrive<0?timeToDrive:timeToDrive;
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    	end = (new Date()).getTime() + timeToDrive;
    	
    	LogAction(String.format("Driving left=%1$f right=%2$f for %3$d milliseconds", leftSpeed, rightSpeed, timeToDrive));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
		drive.tankDrive(leftSpeed, rightSpeed, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	long now = (new Date()).getTime();
    	return now > end;
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
