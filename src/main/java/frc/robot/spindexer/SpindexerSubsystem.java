package frc.robot.spindexer;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpindexerSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(SpindexerConst.MOTOR_ID);

    public SpindexerSubsystem() {
        motor.getConfigurator().apply(SpindexerConfig.motorConfiguration);
    }

    /**
     * Changes the motor speed
     *
     * @param speed input speed
     */
    public void moveMotorSpeed(double speed) {
        motor.set(speed);
    }

    /** Starts the motor */
    public void start() {
        moveMotorSpeed(SpindexerConfig.MOTOR_SPEED);
    }

    /** Stops the motor */
    public void stop() {
        moveMotorSpeed(0);
    }

    /**
     * Gets the current motor speed
     *
     * @return the motor speed
     */
    public double getMotorSpeed() {
        return motor.get();
    }

    /**
     * Gets the angular velocity
     *
     * @return the angular velocity as a double
     */
    public double getAngularVelocity() {
        return motor.getVelocity().getValueAsDouble();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        builder.addDoubleProperty(
                "motor speed(fraction)", this::getMotorSpeed, this::moveMotorSpeed);
        builder.addDoubleProperty("angular velocity", this::getAngularVelocity, null);
        super.initSendable(builder);
    }
}
