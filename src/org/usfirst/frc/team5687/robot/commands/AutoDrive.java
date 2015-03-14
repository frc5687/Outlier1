package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import java.util.Date;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {

	DriveTrain drive = Robot.driveTrain;
	private Calendar end = null;
	private int timeToDrive = 0;
	private double speed = 0;
	
    
	public AutoDrive(double speed, int timeToDrive) {
        requires(drive);
        this.speed = speed;
        this.timeToDrive = timeToDrive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	end = Calendar.getInstance();
    	end.setTime(new Date());
    	end.add(Calendar.MILLISECOND, timeToDrive);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		drive.tankDrive(speed, speed, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Calendar now = Calendar.getInstance();
    	now.setTime(new Date());
    	return !now.before(end);
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.tankDrive(0,0,false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
