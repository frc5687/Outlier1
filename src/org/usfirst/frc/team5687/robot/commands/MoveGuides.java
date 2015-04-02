package org.usfirst.frc.team5687.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.OI;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.Guides;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for moving the stacker based on raw OI axis input
 */
public class MoveGuides extends Command {
	
	Guides guides = Robot.guides;
	OI oi = Robot.oi;
	private Double target; 
	private long endTime;
	
    public MoveGuides(double target) {
    	super();
        this.target = target;
    	requires(guides);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	endTime = (new Date()).getTime() + Constants.Guides.TIMEOUT;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	guides.MoveTo(target);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Option 1: see if the guides have reached the target...
        if (guides.AreAt(target)) { return true; }
        // Option 2: 
    	if ((new Date()).getTime() > endTime) { return true; }
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	endTime = 0;
    }
}
