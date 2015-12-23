package org.usfirst.frc.team5687.robot.commands;

import org.usfirst.frc.team5687.robot.OI;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for moving the stacker based on raw OI axis input
 */
public class MoveStackerManually extends Command {
	
	Stacker stacker = Robot.stacker;
	OI oi = Robot.oi;

    public MoveStackerManually() {
        requires(stacker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	stacker.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	stacker.move(oi.getStackerValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
