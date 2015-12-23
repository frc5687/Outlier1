package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import java.util.Date;

import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command
 */
public class AutonomousResetOnly extends CommandGroup {
    
	private Calendar end = null;
	
    public  AutonomousResetOnly() {
    	
    	// Reset the stacker
    	addSequential(new ResetStacker());
    	
    }
}   
 
