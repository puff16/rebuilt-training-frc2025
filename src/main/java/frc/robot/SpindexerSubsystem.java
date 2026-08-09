package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpindexerSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(-1);

    private static final TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();

    static {
        motorConfiguration.CurrentLimits.StatorCurrentLimit = 80;
        motorConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        motorConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    }

    public SpindexerSubsystem() {
        motor.getConfigurator().apply(motorConfiguration);
    }

    public void moveMotorSpeed(double speed) {
        motor.set(speed);
    }

    public void start() {
        moveMotorSpeed(0.5);
    }

    public void stop() {
        moveMotorSpeed(0);
    }

    public double getMotorSpeed() {
        return motor.get();
    }

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
