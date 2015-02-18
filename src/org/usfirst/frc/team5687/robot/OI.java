package org.usfirst.frc.team5687.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5687.robot.commands.*;

/**
 * Operator interface for the robot, tying user controls to robot commands
 */
public class OI {
	private Gamepad gamepad;
	private Joystick joystick;
	
	/*
	 * Constructor
	 */
	public OI() {
		gamepad = new Gamepad(0);
		joystick = new Joystick(1);
		
		JoystickButton upButton = new JoystickButton(joystick, 1);
		JoystickButton downButton = new JoystickButton(joystick, 2);
		JoystickButton resetButton = new JoystickButton(joystick, 7);
		JoystickButton depositButton = new JoystickButton(joystick, 8);
		
		upButton.whenPressed(new MoveStackerUp());
		downButton.whenPressed(new MoveStackerDown());
		resetButton.whenPressed(new ResetStacker());
		depositButton.whenPressed(new DepositStack());
		
		// Add commands to dashboard
		SmartDashboard.putData("Reset Stacker", new ResetStacker());
		SmartDashboard.putData("Move Stacker Up", new MoveStackerUp());
		SmartDashboard.putData("Move Stacker Down", new MoveStackerDown());
		SmartDashboard.putData("Deposit Stack", new DepositStack());
	}
	
	/*
	 * Returns the control value for the left drive motors
	 * @return double the desired speed value for the left drive motors
	 */
	public double getLeftDriveValue() {
		// Return the vertical left-stick axis value from the gamepad
		double raw = gamepad.getRawAxis(Gamepad.Axes.LEFT_Y);
		return Util.applyDeadband(raw, RobotFactors.Deadbands.DRIVE_STICK);
	}
	
	/*
	 * Returns the control value for the right drive motors
	 * @return double the desired speed value for the right drive motors
	 */
	public double getRightDriveValue() {
		// Return the vertical right-stick axis value for the gamepad
		double raw = gamepad.getRawAxis(Gamepad.Axes.RIGHT_Y);
		return Util.applyDeadband(raw, RobotFactors.Deadbands.DRIVE_STICK);
	}
	
	/*
	 * Checks if the driver wants to override the internal speed limit
	 * @return boolean true if the driver wants to override the speed limit
	 */
	public boolean getOverrideButtonValue() {
		return gamepad.getRawButton(Gamepad.Buttons.RB);
	}
	
	/*
	 * Returns the control value for the stacker elevation
	 * @return double the desired speed for the stacker motor
	 */
	public double getStackerValue() {
		return Util.applyDeadband(joystick.getRawAxis(1), RobotFactors.Deadbands.LIFT_STICK);
	}
}

