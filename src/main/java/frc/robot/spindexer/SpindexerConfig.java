package frc.robot.spindexer;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class SpindexerConfig {
    public static final TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();
    public static final double MOTOR_SPEED = 0.5;

    static {
        motorConfiguration.CurrentLimits.StatorCurrentLimit = 80;
        motorConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        motorConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    }
}
