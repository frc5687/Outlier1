package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import java.util.Date;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.OI;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftStackerForTime extends Command {

	private Stacker stacker = Robot.stacker;
	private long end = 0;
	private int timeToLift = 0;
	
    public LiftStackerForTime(int timeToLift) {
        requires(stacker);
        this.timeToLift = timeToLift;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	stacker.disable();
    	end = (new Date()).getTime() +  timeToLift;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	stacker.move(Constants.AutonomousSettings.STACKER_SPEED);
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
