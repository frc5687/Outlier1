package org.usfirst.frc.team5687.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Pneumatics {
	
		DoubleSolenoid valve;
		
		public Pneumatics(){
			valve = new DoubleSolenoid(0, 1);
		}
		
		public void expandValve(){
			valve.set(DoubleSolenoid.Value.kForward);
		}
		
		public void retractValve(){
			valve.set(DoubleSolenoid.Value.kReverse);
		}
	
	
	
}
