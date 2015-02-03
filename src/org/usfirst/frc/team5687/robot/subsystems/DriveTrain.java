
package org.usfirst.frc.team5687.robot.subsystems;

import org.usfirst.frc.team5687.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	RobotDrive drive; 
	
	public DriveTrain() {
		SpeedController leftFront = new Talon(RobotMap.leftFrontMotor);
		SpeedController leftBack = new Talon(RobotMap.leftBackMotor);
		SpeedController rightFront = new Talon(RobotMap.rightFrontMotor);
		SpeedController rightBack = new Talon(RobotMap.rightBackMotor);
		
		drive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
		
		drive.setSafetyEnabled(true);
		
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

