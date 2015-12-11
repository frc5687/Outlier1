package org.usfirst.frc.team5687.robot.commands;

import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.command.Command;

public class ExpandValve extends Command{
	
	Pneumatics solenoid = Robot.pneumatics;
	
	public ExpandValve(){
		requires(solenoid);
	}
	
	//Sets up the command
	//Called just before this Command runs the first time
    protected void initialize() {
    }

    //Executes the command
    //Called repeatedly when this Command is scheduled to run    
    protected void execute() {
    	solenoid.expandValve();
    }

    //Check if this command is finished running
    //Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    //Command execution clean-up
    //Called once after isFinished returns true
    protected void end() {
    }

    // Handler for when command is interrupted
    // Called when another command which requires one or more of the same
    protected void interrupted() {
    }
}
