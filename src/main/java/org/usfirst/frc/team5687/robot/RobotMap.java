package org.usfirst.frc.team5687.robot;

/**
 * Contains a mapping of the various devices connected to the RoboRIO 
 * and the ports they connect to
 */
public class RobotMap {
    
	// Drive Train Ports
	public static int leftMotor = 0;
    public static int rightMotor = 3;
    
    // Lift Motor Ports
    public static int stackerMotor = 4;
    
    //servo motors
    public static int leftGuideServo = 1; //TODO: where do these plugin?
    public static int rightGuideServo = 2; //TODO: where do these plugin?
    
    // Encoders
    public static int encoderA = 0;
    public static int encoderB = 1;
    
    // hall effect sensors
    public static int hallTop = 2;
    public static int hallBottom = 3;
}
