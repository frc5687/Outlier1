package org.usfirst.frc.team5687.robot;

/**
 * Contains a mapping of the various devices connected to the RoboRIO 
 * and the ports they connect to
 */
public class RobotMap {
    
	// Drive Train Ports
	public static int leftFrontMotor = 0;
    public static int rightFrontMotor = 2;
    public static int leftBackMotor = 1;
    public static int rightBackMotor = 3;
    
    // Lift Motor Ports
    public static int lift1 = 4;
    public static int lift2 = 5;
    
    // Encoders
    public static int encoderA = 0;
    public static int encoderB = 1;
    
    // hall effect sensors
    public static int hallTop = 2;
    public static int hallBottom = 3;
}
