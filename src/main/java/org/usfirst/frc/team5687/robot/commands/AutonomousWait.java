package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import java.util.Date;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous commandgroup to move forward for a set time, then lift stacker to a setpoint.
 */
public class AutonomousWait extends CommandGroup {
    
	private Calendar end = null;
	
    public  AutonomousWait(int milliseconds) {
    	    	
    	addSequential(new AutoWait(milliseconds));
    
    }
    
}
