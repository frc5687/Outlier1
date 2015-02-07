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
	
	/*
	 * Constructor
	 */
	public DriveTrain() {
		// Setup speed controllers
		SpeedController leftFront = new Talon(RobotMap.leftFrontMotor);
		SpeedController leftBack = new Talon(RobotMap.leftBackMotor);
		SpeedController rightFront = new Talon(RobotMap.rightFrontMotor);
		SpeedController rightBack = new Talon(RobotMap.rightBackMotor);
		
		// Initialize the drive object
		drive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
		drive.setSafetyEnabled(true);
		
		// Invert the left motors
		//drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		//drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
	}
	

	/*
	 * Sets the default command for the DriveTrain subsystem
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWith2Joysticks());
    }
    
    /*
     * Drives the robot in tank mode
     * @param leftSpeed the speed value to use for the left motors
     * @param rightSpeed the speed value to use for the right motors
     */
    public void tankDrive(double leftSpeed, double rightSpeed)
    {
    	drive.tankDrive(leftSpeed, rightSpeed, false);
    }
}

