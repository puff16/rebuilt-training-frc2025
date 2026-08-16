package frc.robot.intake;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class IntakeConfig {
    public static final TalonFXConfiguration deployMotorConfiguration = new TalonFXConfiguration();
    public static final TalonFXConfiguration rollerMotorConfiguration = new TalonFXConfiguration();
    public static final double ROLLER_MOTOR_SPEED = 0.5;

    static {
        deployMotorConfiguration.CurrentLimits.StatorCurrentLimit = 80;
        deployMotorConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        // TODO: either clockwise or counterclockwise
        deployMotorConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        deployMotorConfiguration.Feedback.SensorToMechanismRatio = IntakeConst.GEAR_RATIO;
        deployMotorConfiguration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        deployMotorConfiguration.SoftwareLimitSwitch.ForwardSoftLimitThreshold =
                IntakeConst.MAX_ANGLE.in(Degrees);
        deployMotorConfiguration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        deployMotorConfiguration.SoftwareLimitSwitch.ReverseSoftLimitThreshold =
                IntakeConst.MIN_ANGLE.in(Degrees);

        rollerMotorConfiguration.CurrentLimits.StatorCurrentLimit = 80;
        rollerMotorConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        // TODO: either clockwise or counterclockwise
        rollerMotorConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    }
}
