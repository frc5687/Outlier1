package org.usfirst.frc.team5687.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5687.robot.Gamepad.Buttons;
import org.usfirst.frc.team5687.robot.commands.*;

/**
 * Operator interface for the robot, tying user controls to robot commands
 */
public class OI {
	private Gamepad gamepad;
	private Joystick joystick;
	
	public static final int RESET = 6;
	public static final int CLEAR_2 = 7;
	public static final int CLEAR_4 = 9;
	public static final int DEPOSIT_2 = 8;
	public static final int DEPOSIT_4 = 10;
	public static final int CHUTE = 3;

	public static final int JUMP_LEFT = Buttons.X.ordinal()+1;
	public static final int JUMP_RIGHT = Buttons.B.ordinal()+1;

	public static final int GUIDES_IN = 5;
	public static final int GUIDES_OUT = 6;
	
	
	public static Buttons boostButton = Buttons.RB;
	
	/*
	 * Constructor
	 */
	public OI() {
		gamepad = new Gamepad(0);
		joystick = new Joystick(1);
		
		// Create buttons
		JoystickButton resetButton = new JoystickButton(joystick, RESET);
		JoystickButton clear2Button = new JoystickButton(joystick, CLEAR_2);
		JoystickButton clear4Button = new JoystickButton(joystick, CLEAR_4);
		JoystickButton deposit2Button = new JoystickButton(joystick, DEPOSIT_2);
		JoystickButton deposit4Button = new JoystickButton(joystick, DEPOSIT_4);
		JoystickButton chuteButton = new JoystickButton(joystick, CHUTE);

		JoystickButton guidesInButton = new JoystickButton(joystick, GUIDES_IN);
		JoystickButton guidesOutButton = new JoystickButton(joystick, GUIDES_OUT);
		
		
		JoystickButton jumpLeftButton = new JoystickButton(gamepad, JUMP_LEFT);
		JoystickButton jumpRightButton = new JoystickButton(gamepad, JUMP_RIGHT);
		
		
		// Link buttons to commands
		resetButton.whenPressed(new ResetStacker());
		clear2Button.whenPressed(new MoveStackerToSetpoint(Constants.StackerHeights.CLEAR_FIRST));
		clear4Button.whenPressed(new MoveStackerToSetpoint(Constants.StackerHeights.CLEAR_SECOND));
		deposit2Button.whenPressed(new MoveStackerToSetpoint(Constants.StackerHeights.DEPOSIT_2_HEIGHT));
		deposit4Button.whenPressed(new MoveStackerToSetpoint(Constants.StackerHeights.DEPOSIT_4_HEIGHT));
		chuteButton.whenPressed(new MoveStackerToSetpoint(Constants.StackerHeights.CHUTE_HEIGHT));

		guidesInButton.whenPressed(new MoveGuides(Constants.Guides.IN));
		guidesOutButton.whenPressed(new MoveGuides(Constants.Guides.OUT));

		jumpLeftButton.whenPressed(new MoveSideways(MoveSideways.LEFT, 1));
		jumpLeftButton.whenPressed(new MoveSideways(MoveSideways.RIGHT, 1));
		
		// Add commands to dashboard
		SmartDashboard.putData("Reset Stacker", new ResetStacker());
	}
	
	/*
	 * Returns the control value for the left drive motors
	 * @return double the desired speed value for the left drive motors
	 */
	public double getLeftDriveValue() {
		// Return the vertical left-stick axis value from the gamepad
		double raw = gamepad.getRawAxis(Gamepad.Axes.LEFT_Y);
		return Util.applyDeadband(raw, Constants.Deadbands.DRIVE_STICK);
	}
	
	/*
	 * Returns the control value for the right drive motors
	 * @return double the desired speed value for the right drive motors
	 */
	public double getRightDriveValue() {
		// Return the vertical right-stick axis value for the gamepad
		double raw = gamepad.getRawAxis(Gamepad.Axes.RIGHT_Y);
		return Util.applyDeadband(raw, Constants.Deadbands.DRIVE_STICK);
	}
	
	/*
	 * Checks if the driver wants to override the internal speed limit
	 * @return boolean true if the driver wants to override the speed limit
	 */
	public boolean getOverrideButtonValue() {
		return gamepad.getRawButton(boostButton);
	}
	
	/*
	 * Returns the control value for the stacker elevation
	 * @return double the desired speed for the stacker motor
	 */
	public double getStackerValue() {
		return Util.applyDeadband(joystick.getRawAxis(1), Constants.Deadbands.LIFT_STICK);
	}
	
	/*
	 * Returns the control value for the left flipper
	 * @return double the desired speed for the flipper motor
	 */
	

	
	/*
	 * Returns the control value for the left flipper
	 * @return double the desired speed for the flipper motor
	 */
	
}

