package org.usfirst.frc.team5687.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command
 */
public class AutonomousResetOnly extends CommandGroup {
    
	public  AutonomousResetOnly() {
    	
    	// Reset the stacker
    	addSequential(new ResetStacker());
    	
    }
}   
 
