package org.usfirst.frc.team5687.robot;

/*
 * Class containing constants and coefficients for the robot
 */
public class RobotFactors {
	public class Deadbands {
		public static final double DRIVE_STICK = 0.1;
		public static final double LIFT_STICK = 0.1;
	}
	
	public class SpeedLimits {
		public static final double PRIMARY = 0.6;
		public static final double BOOST = 0.8;
	}
	
	public class PID {
		public static final double kP = 1.0;
		public static final double kI = 0.0;
		public static final double kD = 0.0;
	}
	
	public class StackerHeights {
		// platform starts at 21.125in form floor
		public static final double GROUND = 0.0;
		public static final double HOVER_CAN = 5.375;
		public static final double CLEAR_FIRST = 24.625;
		public static final double HOVER_FIRST = 30.875;
		public static final double CLEAR_SECOND = 49.75;
		public static final double HOVER_SECOND = 54.25;
		public static final double DEPOSIT_HEIGHT = 42.875;
	}
	
	public static final double DISTANCE_PER_PULSE = 0.000125;
}
