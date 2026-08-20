package frc.robot.launcher.hood;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class HoodConfig {
    public static final TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();

    static {
        motorConfiguration.CurrentLimits.StatorCurrentLimit = 80;
        motorConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        motorConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        motorConfiguration.Feedback.SensorToMechanismRatio = HoodConst.GEAR_RATIO;

        motorConfiguration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        motorConfiguration.SoftwareLimitSwitch.ForwardSoftLimitThreshold =
                HoodConst.MAX_PITCH.in(Degrees);
        motorConfiguration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        motorConfiguration.SoftwareLimitSwitch.ReverseSoftLimitThreshold =
                HoodConst.MIN_PITCH.in(Degrees);
    }
}
