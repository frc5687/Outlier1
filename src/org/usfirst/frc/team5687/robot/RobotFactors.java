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
		public static final double kI = 0.5;
		public static final double kD = 0.2;
	}
}
