package org.usfirst.frc.team5687.robot;

/*
 * Class containing constants and coefficients for the robot
 */
public class Constants {
	public class Deadbands {
		public static final double DRIVE_STICK = 0.1;
		public static final double LIFT_STICK = 0.1;
	}
	
	public class SpeedLimits {
		public static final double PRIMARY = 0.6;
		public static final double BOOST = 0.8;
		public static final double ACCELERATION_CAP = 0.30; // Maximum change in speed over 1/50 sec. (0 = no moving: .30 seems good)
		public static final double STACKER_DOWN = 0.6;
		public static final double STACKER_UP = 1.0;
	}

	public class AutonomousSettings {
		public static final double DRIVE_SPEED = 0.4;
		public static final double SIDEWAYS_SPEED = 0.6;
		public static final double ROTATION_SPEED = 0.6;
		public static final int DRIVE_TIME = 2800; // In milliseconds
		public static final int CLEAR_TIME = 600; // In milliseconds
		public static final double STACKER_SPEED = 1.0;
		public static final int LIFT_TIME = 1000; // In milliseconds
	}
	// 140, 100
	
	public class CalibrationDefaults {
		public static final double SIDEWAYS = 200; // Tested at WPI
		public static final double LEFT_ROTATION = 10; // TODO: This values are pretty good
		public static final double RIGHT_ROTATION = 10; // TODO: This values are pretty good
		public static final double STRAIGHT = 100; // TODO: These values are fictional.  Need real values!!
		
	//Bumpers
		public static final double LEFT_IN = 1.0; // TODO: These values are tuned and GOOD
		public static final double LEFT_OUT = 0.4; // TODO: These values are tuned and acceptable
		public static final double RIGHT_OUT = 0.6; // TODO: These values are tuned and acceptable
		public static final double RIGHT_IN = 0.00; // TODO: These values are tuned and GOOD
	}
	
	public class Guides {
		public static final int IN=-1;
		public static final int OUT=1;
		public static final int TIMEOUT = 250; // Time in millesconds to allow for deploying or retracting guides.
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
		// platform starts at 21.125in from floor
		public static final double HEIGHT_OFFSET = 21.125;
		
		public static final double GROUND = 0;
		public static final double CLEAR_FIRST = 45.75 - HEIGHT_OFFSET;
		public static final double CLEAR_SECOND = 70.875 - HEIGHT_OFFSET;
		public static final double DEPOSIT_4_HEIGHT = 64 - HEIGHT_OFFSET;
		public static final double DEPOSIT_2_HEIGHT = 37.5 - HEIGHT_OFFSET;
		public static final double CHUTE_HEIGHT = 53.825 - HEIGHT_OFFSET;
		
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
	public static final String SCRIPTS_PATH = "/home/lvuser/scripts";
}
