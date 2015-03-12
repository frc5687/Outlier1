package org.usfirst.frc.team5687.robot.commands;

import org.usfirst.frc.team5687.robot.OI;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.Flippers;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for basic manual control of the flippers
 */
public class MoveFlippersManually extends Command {

	Flippers flippers = Robot.flippers;
	OI oi = Robot.oi;
	
    public MoveFlippersManually() {
        requires(flippers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	flippers.moveFlippers(oi.getLeftFlipperValue(), oi.getLRightFlipperValue());
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
