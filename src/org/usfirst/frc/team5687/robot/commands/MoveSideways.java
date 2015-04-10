package org.usfirst.frc.team5687.robot.commands;

import java.util.Calendar;
import java.util.Date;

import org.usfirst.frc.team5687.robot.Calibration;
import org.usfirst.frc.team5687.robot.Constants;
import org.usfirst.frc.team5687.robot.Constants.CalibrationDefaults;
import org.usfirst.frc.team5687.robot.Constants.SpeedLimits;
import org.usfirst.frc.team5687.robot.Robot;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Commandgroup to move sideways a set direction and distance.
 */
public class MoveSideways extends OutlierCommandGroup {
	public static int LEFT = -1;
	public static int RIGHT = 1;
	
	
    /**
     * CommandGroup to move the robot sideways for small distances.  Distance shouldn't be more than a couple of inches.
     * This works by moving one side at a time.  To move left, we run the right wheels reverse, then the left wheels reverse, then the right wheels forward, then the left wheels forward.
     * Julian is concerned there will be some slippage because of the omni-wheels, so we may have to compensate for that.  
     * @param direction
     * @param distance
     */
	public  MoveSideways(int direction, double distance) {
		LogAction(String.format("Moving %1$d for %2$f inches", direction, distance));
    	int runTime =  (int)Math.round(distance / Constants.AutonomousSettings.SIDEWAYS_SPEED * Constants.CalibrationDefaults.SIDEWAYS);

    	// Run one side back for runTime milliseconds...if moving left, start with right wheels.  If moving right, start with left wheels
    	addSequential(new AutoDrive(Constants.AutonomousSettings.SIDEWAYS_SPEED  *(direction == RIGHT?1:0),Constants.AutonomousSettings.SIDEWAYS_SPEED *(direction == LEFT?1:0), runTime));

    	// Run the other side back for runTime milliseconds...if moving left, finish with left wheels.  If moving right, finish with right wheels
    	addSequential(new AutoDrive(Constants.AutonomousSettings.SIDEWAYS_SPEED  *(direction == RIGHT?0:1),Constants.AutonomousSettings.SIDEWAYS_SPEED *(direction == LEFT?0:1), runTime));

    	// Run one side forward for runTime milliseconds...if moving left, start with right wheels.  If moving right, start with left wheels
    	addSequential(new AutoDrive(Constants.AutonomousSettings.SIDEWAYS_SPEED  *(direction == RIGHT?-1:0),Constants.AutonomousSettings.SIDEWAYS_SPEED *(direction == LEFT?-1:0), runTime));

    	// Run the other side forward for runTime milliseconds...if moving left, finish with left wheels.  If moving right, finish with right wheels
    	addSequential(new AutoDrive(Constants.AutonomousSettings.SIDEWAYS_SPEED  *(direction == RIGHT?0:-1),Constants.AutonomousSettings.SIDEWAYS_SPEED *(direction == LEFT?0:-1), runTime));
    }
    
}
