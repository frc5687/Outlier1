package org.usfirst.frc.team5687.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    
	// Drive Train Ports
	public static int leftFrontMotor = 0;
    public static int rightFrontMotor = 2;
    public static int leftBackMotor = 1;
    public static int rightBackMotor = 3;
    
    // Lift Motor Ports
    public static int lift1 = 4;
    public static int lift2 = 5;
    
    // Encoders
    public static int encoderA = 6;
    public static int encoderB = 7;
    
    // halleffect
    public static int hallTop = 8;
    public static int hallBottom = 9;
}
