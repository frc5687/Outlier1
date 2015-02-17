package org.usfirst.frc.team5687.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Handle user inputs from a gamepad connected to the Driver Station
 */
public class Gamepad extends Joystick{
	
	/*
	 * Enumeration for the various analog axes
	 */
	public static enum Axes {
		LEFT_X,
		LEFT_Y,
		LEFT_TRIGGER,
		RIGHT_TRIGGER,
		RIGHT_X,
		RIGHT_Y,
		D_PAD_HORIZ
	}
	
	/*
	 * Enumeration for the various buttons
	 */
	public static enum Buttons {
		A,
		B,
		X,
		Y,
		LB,
		RB,
		BACK,
		START,
		LEFT_STICK,
		RIGHT_STICK
	}

	/*
	 * Constructor
	 */
	public Gamepad(int port) {
		super(port);
	}
	
	/*
	 * Gets the raw value for the specified axis
	 * @param Axes the desired gamepad axis
	 * @return double the analog value for the axis
	 */
	public double getRawAxis(Axes axis) {
		return super.getRawAxis(axis.ordinal());
	}
	
	/*
	 * Checks if the specified button is pressed
	 * @param Buttons the desired gamepad button
	 * @return bool true if the button is pressed
	 */
	public boolean getRawButton(Buttons button) {
		return super.getRawButton(button.ordinal() + 1);
	}
}
