package org.usfirst.frc.team5687.robot;

public class Calibration {
	public static class Drive {
		/**
		 * Calibration factor to produce one inch of sideways motion when the left or right buttons are pressed.
		 */
		public static double SIDEWAYS = 100; // TODO: These values are fictional.  Need real values!!
		
		/**
		 * Calibrates the Rotate command to accommodate input in degrees. 
		 */
		public static double ROTATION = .5; // TODO: These values are fictional.  Need real values!!
		
		/**
		 * Calibrates the AutoDrive command to accommodate input in inches. 
		 */
		public static double STRAIGHT = .1; // TODO: These values are fictional.  Need real values!!
	}
	
	public static class Guides {
		public static double LEFT_IN = .1; // TODO: These values are fictional.  Need real values!!
		public static double LEFT_OUT = .55; // TODO: These values are fictional.  Need real values!!
		public static double RIGHT_IN = .55; // TODO: These values are fictional.  Need real values!!
		public static double RIGHT_OUT = 0.1; // TODO: These values are fictional.  Need real values!!
	}

}
