package frc.robot.spindexer;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpindexerSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(SpindexerConst.MOTOR_ID);

    public SpindexerSubsystem() {
        motor.getConfigurator().apply(SpindexerConfig.motorConfiguration);
    }

    public void moveMotorSpeed(double speed) {
        motor.set(speed);
    }

    public void start() {
        moveMotorSpeed(SpindexerConfig.MOTOR_SPEED);
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
