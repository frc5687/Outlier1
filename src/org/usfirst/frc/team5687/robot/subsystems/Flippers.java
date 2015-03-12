package org.usfirst.frc.team5687.robot.subsystems;

import org.usfirst.frc.team5687.robot.RobotMap;
import org.usfirst.frc.team5687.robot.commands.MoveFlippersManually;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flippers extends Subsystem {
	
	private Victor leftMotor;
	private Victor rightMotor;

    // Initialize your subsystem here
    public Flippers() {
        leftMotor = new Victor(RobotMap.flapLeft);
        rightMotor = new Victor(RobotMap.flapRight);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MoveFlippersManually());
    }
    
    public void moveFlippers(double leftSpeed, double rightSpeed) {
    	// TODO make sure the motors go the correct way with respect to the sticks, 
    	// the negative sign may need to be switched from rightSpeed to leftSpeed
    	leftMotor.set(leftSpeed);
    	rightMotor.set(-rightSpeed);
    }
}
