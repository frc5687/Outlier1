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
public class MoveGuides extends OutlierCommand {
	
	Guides guides = Robot.guides;
	private Double target; 
	private long endTime;
	
    public MoveGuides(double target) {
    	super();
    	LogAction("Instantiating guides.");
        this.target = target;
    	requires(guides);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	endTime = (new Date()).getTime() + Constants.Guides.TIMEOUT;
    	LogAction(String.format("Moving guides to " + target.toString()));
    	guides.MoveTo(target);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((new Date()).getTime() > endTime) { 
        	LogAction(String.format("Guide timeout reached."));
    		return true; 
    	}
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
