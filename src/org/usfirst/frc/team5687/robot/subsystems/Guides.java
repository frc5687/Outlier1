package org.usfirst.frc.team5687.robot.subsystems;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Guides extends Subsystem {
	
	private Servo leftServo;
	private Servo rightServo;
	
	public Guides() {
		leftServo = new SpringRCServo(RobotMap.leftGuideServo);
		rightServo = new SpringRCServo(RobotMap.rightGuideServo);
	}

	public void MoveTo(double position) {
		leftServo.set(position);
		rightServo.set(Constants.Guides.OUT-position);
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
