package org.usfirst.frc.team5687.robot.commands;

import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetStacker extends OutlierCommand {

	Stacker stacker = Robot.stacker;
	
    public ResetStacker() {
        requires(stacker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// disable the PID (it might already be disabled, but just in case)
    	LogAction("Resetting stacker.");
    	stacker.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	stacker.move(-0.8);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return stacker.isAtLowerLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	stacker.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
