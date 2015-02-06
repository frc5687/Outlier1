package org.usfirst.frc.team5687.robot;

/**
 * Operator interface for the robot, tying user controls to robot commands
 */
public class OI {
	private Gamepad gamepad;
	
	/*
	 * Constructor
	 */
	public OI() {
		gamepad = new Gamepad(0);
	}
	
	/*
	 * Returns the control value for the left drive motors
	 * @return double the desired speed value for the left drive motors
	 */
	public double getLeftDriveValue() {
		
		// Return the vertical left-stick axis value from the gamepad
		return gamepad.getRawAxis(Gamepad.Axes.LEFT_Y);
	}
	
	/*
	 * Returns the control value for the right drive motors
	 * @return double the desired speed value for the rught drive motors
	 */
	public double getRightDriveValue() {
		
		// Return the vertical right-stick axis value for the gamepad
		return gamepad.getRawAxis(Gamepad.Axes.RIGHT_Y);
	}
}

