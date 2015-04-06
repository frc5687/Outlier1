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
	private int target; 
	private long end = 0;
	
    public MoveGuides(int target) {
    	super();
    	LogAction("Instantiating guides.");
        this.target = target;
    	requires(guides);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	end = (new Date()).getTime() + Constants.Guides.TIMEOUT;
    	switch(target) {
    		case Constants.Guides.IN:
    	    	LogAction(String.format("Moving guides IN"));
    	    	guides.MoveIn();
    	    	break;
    		case Constants.Guides.OUT:
    	    	LogAction(String.format("Moving guides OUT"));
    	    	guides.MoveOut();
    	    	break;
    	    default:
    	    	LogAction(String.format("Unrecognized guides target: '%1$n'", target));
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (new Date()).getTime() > end;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
