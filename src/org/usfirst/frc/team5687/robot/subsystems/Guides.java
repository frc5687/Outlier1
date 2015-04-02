package org.usfirst.frc.team5687.robot.subsystems;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.RobotMap;
import org.usfirst.frc.team5687.robot.commands.MoveGuides;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Guides extends Subsystem {
	
	private Servo leftServo;
	private Servo rightServo;
	
	public Guides() {
		leftServo = new Servo(RobotMap.leftGuideServo);
		rightServo = new Servo(RobotMap.rightGuideServo);
		
		//LiveWindow.addActuator("Guides", "Left Guide", leftServo);
		//LiveWindow.addActuator("Guides", "Right Guide", rightServo);
		//LiveWindow.addSensor("Guides", "Left Guide", leftServo);
		//LiveWindow.addSensor("Guides", "Right Guide", rightServo);
	}

	public void MoveTo(double position) {
		leftServo.set(position);
		rightServo.set(position);
	}

	public double getLeft() {
		return leftServo.get();
	}
	
	public double getRight() {
		return rightServo.get();
	}
	
	public boolean AreAt(double position) {
		return (Math.abs(position - leftServo.get()) < Constants.Guides.FUDGE_FACTOR)
				&& (Math.abs(position - rightServo.get()) < Constants.Guides.FUDGE_FACTOR);
	}
	
	@Override
	protected void initDefaultCommand() {
        // setDefaultCommand(new MoveGuides(Constants.Guides.OUT));
	}

}
