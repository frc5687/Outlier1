package org.usfirst.frc.team5687.robot.subsystems;

import org.usfirst.frc.team5687.robot.RobotMap;
import org.usfirst.frc.team5687.robot.commands.DriveWith2Joysticks;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Subsystem for the robot drive chassis
 */
public class DriveTrain extends Subsystem {
    
	private RobotDrive drive; 
	private final double LOWER_LIMIT = 0.6;
	private final double UPPER_LIMIT = 0.8;
	
	/*
	 * Constructor
	 */
	public DriveTrain() {
		// Setup speed controllers
		SpeedController leftMotor = new Talon(RobotMap.leftMotor);
		SpeedController rightMotor = new Talon(RobotMap.rightMotor);
		
		// Initialize the drive object
		drive = new RobotDrive(leftMotor, rightMotor);
		drive.setSafetyEnabled(true);
		
		// Inverting the drive motors
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
	}

	/*
	 * Sets the default command for the DriveTrain subsystem
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWith2Joysticks());
    }
    
    /*
     * Drives the robot in tank mode, with internal speed limits imposed when going forward/ reverse
     * Speed limit isn't imposed when turning
     * @param leftSpeed the speed value to use for the left motors
     * @param rightSpeed the speed value to use for the right motors
     * @param speedOverride whether or not to override the speed limit and allow for a high speed limit
     */
    public void tankDrive(double leftSpeed, double rightSpeed, boolean speedOverride)
    {
    	// Determine which internal speed limit to use
    	double speedLimit = speedOverride ? UPPER_LIMIT : LOWER_LIMIT;
    	
    	if(leftSpeed > speedLimit && rightSpeed > speedLimit) {
    		drive.tankDrive(speedLimit, speedLimit);
    	}
    	else if (leftSpeed < -speedLimit && rightSpeed < -speedLimit) {
    		drive.tankDrive(-speedLimit, -speedLimit);
    	}
    	else {
    		drive.tankDrive(leftSpeed, rightSpeed, false);
    	}
    }
}

