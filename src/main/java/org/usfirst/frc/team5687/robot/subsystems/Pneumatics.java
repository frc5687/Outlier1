package org.usfirst.frc.team5687.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for pneumatics
 */
public class Pneumatics extends Subsystem {

    private DoubleSolenoid doubleSolenoid;

    /**
     * Constructor
     */
    public Pneumatics() {
        // Sets the double solenoid to channels 0 and 1 of PCM
        doubleSolenoid = new DoubleSolenoid(0, 1);
    }

    /**
     * Expands the cylinder of double solenoid
     */
    public void expandPiston() {
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * Retracts the cylinder of double solenoid
     */
    public void retractPiston() {
        doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    /**
     * Disables the double solenoid
     */
    public void disablePiston() {
        doubleSolenoid.set(DoubleSolenoid.Value.kOff);
    }

    /**
     * Sets the default command for the pneumatics subsystem
     */
    @Override
    protected void initDefaultCommand() {
    }
}
