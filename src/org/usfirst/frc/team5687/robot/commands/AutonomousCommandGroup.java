package org.usfirst.frc.team5687.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Original autonomous command
 */
public class AutonomousCommandGroup extends CommandGroup {
    
    public  AutonomousCommandGroup() throws InterruptedException {
    	// Reset the stacker
    	addSequential(new ResetStacker());
    	
    }
}
