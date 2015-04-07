package org.usfirst.frc.team5687.robot.subsystems;

import org.usfirst.frc.team5687.robot.Calibration;
import org.usfirst.frc.team5687.robot.Constants.CalibrationDefaults;
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

	public void MoveIn() {
		leftServo.set(Calibration.Guides.LEFT_IN);
		rightServo.set(Calibration.Guides.RIGHT_IN);
	}

	public void MoveOut() {
		leftServo.set(Calibration.Guides.LEFT_OUT);
		rightServo.set(Calibration.Guides.RIGHT_OUT);
	}

	@Override
	protected void initDefaultCommand() {
	}

}
