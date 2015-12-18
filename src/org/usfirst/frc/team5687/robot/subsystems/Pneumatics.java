package org.usfirst.frc.team5687.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem{
	
	private DoubleSolenoid valve;
		
	public Pneumatics(){
		valve = new DoubleSolenoid(0, 1);
	}
	
	public void disableValve(){
		valve.set(DoubleSolenoid.Value.kOff);
	}
	
	public void expandValve(){
		valve.set(DoubleSolenoid.Value.kForward);
	}
		
	public void retractValve(){
		valve.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {
	}

}
