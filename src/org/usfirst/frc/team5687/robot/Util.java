package org.usfirst.frc.team5687.robot;

import edu.wpi.first.wpilibj.DriverStation;

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
	
	public static void LogAction(String message) {
		// Log the action
		DriverStation.reportError(message, false);
	}
}

