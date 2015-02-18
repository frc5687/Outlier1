package org.usfirst.frc.team5687.robot.subsystems;

import org.usfirst.frc.team5687.robot.RobotFactors;
import org.usfirst.frc.team5687.robot.RobotMap;
import org.usfirst.frc.team5687.robot.Util;
import org.usfirst.frc.team5687.robot.commands.MoveStackerManually;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Subsystem for the tote and can stacker
 */
public class Stacker extends PIDSubsystem {
    
    private SpeedController stackerMotor;
    private DigitalInput lowerSensor;
    private DigitalInput upperSensor;
    private Encoder encoder;
    
    /*
     * Constructor
     */
    public Stacker() {
    	super(RobotFactors.PID.kP, RobotFactors.PID.kI, RobotFactors.PID.kD);
    	
    	// Setup the motor
    	stackerMotor = new Victor(RobotMap.stackerMotor);
    	
    	// Setup the limit switches
    	lowerSensor = new DigitalInput(RobotMap.hallBottom);
    	upperSensor = new DigitalInput(RobotMap.hallTop);
    	
    	this.setAbsoluteTolerance(0.25);
    	
    	// Initialize the encoder
    	encoder = new Encoder(RobotMap.encoderA, RobotMap.encoderB);
    	encoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
    	encoder.setDistancePerPulse(RobotFactors.DISTANCE_PER_PULSE);
    	
    	LiveWindow.addActuator("Stacker", "PID Controller", this.getPIDController());
    }

    /*
     * Sets the initial command for the stacker subsystem
     * (non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Subsystem#initDefaultCommand()
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MoveStackerManually());
    }
    
    /*
     * Sets the lift motor speed
     */
    public void moveStacker(double speed) {
    	boolean movingDown = speed < 0;
    	
    	// Don't allow movement if lift is already at the limit for the chosen direction
    	if((movingDown && isAtLowerLimit()) || (!movingDown && isAtUpperLimit())) {
    		stackerMotor.set(0);
    	} else {
    		stackerMotor.set(speed);
    	}
    }
    
    @Override
	protected double returnPIDInput() {
		return encoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		moveStacker(output);
	}
	
	/*
	 * Get the height for the next level for the stacker
	 */
	public double getNextHeight() {
		double current = encoder.getDistance();
		if(current < RobotFactors.StackerHeights.HOVER_CAN)
			return RobotFactors.StackerHeights.HOVER_CAN;
		else if(current < RobotFactors.StackerHeights.CLEAR_FIRST)
			return RobotFactors.StackerHeights.CLEAR_FIRST;
		else if(current < RobotFactors.StackerHeights.HOVER_FIRST)
			return RobotFactors.StackerHeights.HOVER_FIRST;
		else if(current < RobotFactors.StackerHeights.CLEAR_SECOND)
			return RobotFactors.StackerHeights.CLEAR_SECOND;
		else return RobotFactors.StackerHeights.HOVER_SECOND;
	}
	
	/*
	 * Get the height for the previous level of the stacker
	 */
	public double getPreviousHeight() {
		double current = encoder.getDistance();
		if(current > RobotFactors.StackerHeights.HOVER_SECOND)
			return RobotFactors.StackerHeights.HOVER_SECOND;
		else if(current > RobotFactors.StackerHeights.CLEAR_SECOND)
			return RobotFactors.StackerHeights.CLEAR_SECOND;
		else if(current > RobotFactors.StackerHeights.HOVER_FIRST)
			return RobotFactors.StackerHeights.HOVER_FIRST;
		else if(current > RobotFactors.StackerHeights.CLEAR_FIRST)
			return RobotFactors.StackerHeights.CLEAR_FIRST;
		else if(current > RobotFactors.StackerHeights.HOVER_CAN)
			return RobotFactors.StackerHeights.HOVER_CAN;
		else return RobotFactors.StackerHeights.GROUND;
		
	}
    
	/*
	 * Check if the stacker is at the lower limit
	 */
    public boolean isAtLowerLimit() {
    	return !lowerSensor.get();
    }
    
    /*
     * Check if the stacker is at the upper limit
     */
    public boolean isAtUpperLimit() {
    	return !upperSensor.get();
    }
    
    /*
     * Reset the stacker encoder
     */
    public void resetEncoder() {
    	encoder.reset();
    }
}

