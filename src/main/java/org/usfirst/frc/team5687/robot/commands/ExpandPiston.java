package org.usfirst.frc.team5687.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.Pneumatics;

/**
 * Command for expanding piston of double solenoid
 */
public class ExpandPiston extends Command{

    Pneumatics doubleSolenoid  = Robot.pneumatics;

    /**
     * Sets up the command
     * Called just before this Command runs the first time
     */
    @Override
    protected void initialize() {
        requires(doubleSolenoid);
    }

    /**
     * Executes the command
     * Called repeatedly when this Command is scheduled to run
     */
    @Override
    protected void execute() {
        doubleSolenoid.expandPiston();
    }

    /**
     * Check if this command is finished running
     * Make this return true when this Command no longer needs to run execute()
     * @return true if Command is stopped, false otherwise
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /**
     * Command execution clean-up
     * Called once after isFinished returns true
     */
    @Override
    protected void end() {

    }

    /**
     * Handler for when command is interrupted
     * Called when another command which requires one or more of the same
     */
    @Override
    protected void interrupted() {

    }
}
