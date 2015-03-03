package org.usfirst.frc.team5687.robot.commands;

import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveToChuteHeight extends Command {

	private Stacker stacker = Robot.stacker;
	
    public MoveToChuteHeight() {
    	requires(stacker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	stacker.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	stacker.setSetpoint(Constants.StackerHeights.CHUTE_HEIGHT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return stacker.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	stacker.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}