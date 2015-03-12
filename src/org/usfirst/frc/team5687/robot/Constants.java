package org.usfirst.frc.team5687.robot;

/*
 * Class containing constants and coefficients for the robot
 */
public class Constants {
	public class Deadbands {
		public static final double DRIVE_STICK = 0.1;
		public static final double LIFT_STICK = 0.1;
		public static final double FLIPPER_STICK = 0.1;
	}
	
	public class SpeedLimits {
		public static final double PRIMARY = 0.6;
		public static final double BOOST = 0.8;
		public static final double ACCELERATION_CAP = 0.02; // Maximum change in speed over 1/50 sec.
		public static final double STACKER_DOWN = 0.6;
		public static final double STACKER_UP = 1.0;
	}
	
	public class PID { 
		// Proportional constant
		public static final double kP = 1.0;
		
		// Integral constant
		public static final double kI = 0.0;
		
		// Derivative constant
		public static final double kD = 0.0;
		
		// Tolerance for PID onTarget() method, in inches
		public static final double TOLERANCE = 0.25;
	}
	
	public class StackerHeights {
		// platform starts at 21.125in form floor
		private static final double HEIGHT_OFFSET = 21.125;
		
		public static final double GROUND = 0;
		public static final double CLEAR_FIRST = 45.75 - HEIGHT_OFFSET;
		public static final double CLEAR_SECOND = 70.875 - HEIGHT_OFFSET;
		public static final double DEPOSIT_HEIGHT = 64 - HEIGHT_OFFSET;
		public static final double DEPOSIT_2_HEIGHT = 37.5 - HEIGHT_OFFSET;
		
		// TODO get the actual measurement
		public static final double CHUTE_HEIGHT = 53.5 - HEIGHT_OFFSET;
		
		/*
		public static final double HOVER_CAN = 26.5 - HEIGHT_OFFSET;
		public static final double HOVER_FIRST = 52 - HEIGHT_OFFSET;
		public static final double HOVER_SECOND = 75.375 - HEIGHT_OFFSET;
		*/
	}
	
	// Ball-screw is 0.5in per revolution
	// Encoder is 250 cycles-per-revolution, which is 1000 pulses-per-revolution
	// 1/2000 inches per pulse in 1X mode, 1/8000 in 4X mode
	public static final double DISTANCE_PER_PULSE = 0.002;
}
