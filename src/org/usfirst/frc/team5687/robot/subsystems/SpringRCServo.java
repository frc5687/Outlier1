package org.usfirst.frc.team5687.robot.subsystems;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.PWM.PeriodMultiplier;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tResourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Custom Servo subclass to provide bounds specific to the SpringRC 8466 servos.
 * @author Ben Bernard
 *
 */
public class SpringRCServo extends Servo {
    protected static final double kDefaultMaxServoPWM = 2.2;
    protected static final double kDefaultMinServoPWM = .9;
	
    private void initServo() {
        setBounds(kDefaultMaxServoPWM, 0, 0, 0, kDefaultMinServoPWM);
        setPeriodMultiplier(PeriodMultiplier.k4X);

        LiveWindow.addActuator("Servo", getChannel(), this);
        UsageReporting.report(tResourceType.kResourceType_Servo, getChannel());
    }
	
	public SpringRCServo(int channel) {
		super(channel);
		initServo();
	}

}