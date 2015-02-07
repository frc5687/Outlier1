package org.usfirst.frc.team5687.robot;

public class Util {

	/*
	 * This applies a deadband value  
	 * @param input raw value from joystick
	 * @param deadband the deadband threshold 
	 * @return double the adjusted value
	 */
	public static double applyDeadband(double input, double deadband){ 
		return (Math.abs(input) >= Math.abs(deadband)) ? input : 0;
	}
}

