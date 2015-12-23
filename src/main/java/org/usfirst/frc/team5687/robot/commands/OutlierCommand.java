package org.usfirst.frc.team5687.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public abstract class OutlierCommand extends Command {
	protected Command LogAction(String message) {
		// Log action
		DriverStation.reportError(message + "\r\n", false);
		return null;
	}
	
}