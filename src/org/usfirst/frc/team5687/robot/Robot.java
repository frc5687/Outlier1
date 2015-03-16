package org.usfirst.frc.team5687.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5687.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team5687.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5687.robot.subsystems.Flippers;
import org.usfirst.frc.team5687.robot.subsystems.Stacker;

/**
 * Main robot class
 */
public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain;
	public static Stacker stacker;
	public static OI oi;
	public static Flippers flippers;

    Command autonomousCommand;
    
    CameraServer server;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
// Setup camera streaming, not tested yet
        
        try {
    		server = CameraServer.getInstance();
    		server.setQuality(50);
    		server.startAutomaticCapture("cam2"); 
    				
    	} catch (Exception e) {
    		
    	}
        
        // end of camera stuff. 
    	
		driveTrain = new DriveTrain();
		stacker = new Stacker();
		oi = new OI();
		
		updateDashboard();
		
		try {
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommandGroup();
		} catch (Exception e) {
		}

		
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    public void teleopInit() {
		// Stop autonomous commands when teleop starts
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void updateDashboard()
    {
    	SmartDashboard.putData(this.driveTrain);
    	SmartDashboard.putData(this.stacker);
    	SmartDashboard.putData(this.flippers);
    }
}
