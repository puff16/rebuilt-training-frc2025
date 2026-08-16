package frc.robot.launcher.feeder;

import com.ctre.phoenix6.controls.CoastOut;
import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(FeederConst.ID, FeederConst.CAN_BUS);

    public FeederSubsystem() {
        motor.getConfigurator().apply(FeederConfig.motorConfiguration);
    }

    /**
     * change the motor speed
     *
     * @param speed input speed
     */
    public void moveMotorSpeed(double speed) {
        motor.set(speed);
    }

    /** starts the motor */
    public void start() {
        moveMotorSpeed(FeederConfig.MOTOR_SPEED);
    }

    /** stops the motor but lets it coast */
    public void coast() {
        motor.setControl(new CoastOut());
    }

    /** stops the motor completely */
    public void brake() {
        motor.setControl(new StaticBrake());
    }

    /** reverses the motor */
    public void reverse() {
        moveMotorSpeed(-FeederConfig.MOTOR_SPEED);
    }

    /**
     * gets the motor speed
     *
     * @return returns the motor speed
     */
    public double getMotorSpeed() {
        return motor.get();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty(
                "motor speed (fraction)", () -> getMotorSpeed(), (speed) -> moveMotorSpeed(speed));
        super.initSendable(builder);
    }
}
