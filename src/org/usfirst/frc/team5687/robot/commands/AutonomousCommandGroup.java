package org.usfirst.frc.team5687.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command
 */
public class AutonomousCommandGroup extends CommandGroup {
    
    public  AutonomousCommandGroup() {
    	// Reset the stacker
    	addSequential(new ResetStacker());
    }
}
