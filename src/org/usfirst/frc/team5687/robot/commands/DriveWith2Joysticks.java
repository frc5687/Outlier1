package org.usfirst.frc.team5687.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5687.robot.OI;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;

/**
 * Command for basic driver-control of the robot chassis
 */
public class DriveWith2Joysticks extends Command {

	DriveTrain drive = Robot.driveTrain;
	OI oi = Robot.oi;
	
	/*
	 * Constructor
	 */
    public DriveWith2Joysticks() {
        requires(drive);
    }

    /*
     * Sets up the command
     * Called just before this Command runs the first time(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#initialize()
     */
    protected void initialize() {
    }

    /*
     * Executes the command
     * Called repeatedly when this Command is scheduled to run(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#execute()
     */
    protected void execute() {
    	drive.tankDrive(oi.getLeftDriveValue(), 
    					oi.getRightDriveValue(), 
    					oi.getOverrideButtonValue());
    }

    /* 
     * Check if this command is finished running
     * Make this return true when this Command no longer needs to run execute()(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#isFinished()
     */
    protected boolean isFinished() {
        return false;
    }

    /* 
     * Command execution clean-up
     * Called once after isFinished returns true(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#end()
     */
    protected void end() {
    }

    /*
     * Handler for when command is interrupted
     * Called when another command which requires one or more of the same(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#interrupted()
     */
    protected void interrupted() {
    }
}
