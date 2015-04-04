package org.usfirst.frc.team5687.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class OutlierCommandGroup extends CommandGroup {
	protected CommandGroup LogAction(String message) {
		// Log action
		DriverStation.reportError(message + "\r\n", false);
		return null;
	}
	
}