package frc.robot.launcher.hood;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HoodSubsystem extends SubsystemBase {
    private final TalonFX motor = new TalonFX(HoodConst.ID, HoodConst.CAN_BUS);
    private Angle targetPitch;

    public HoodSubsystem() {
        motor.getConfigurator().apply(HoodConfig.motorConfiguration);
    }

    /**
     * changes pitch
     *
     * @param angle input angle
     */
    public void movePitch(Angle angle) {
        targetPitch =
                Rotations.of(
                        MathUtil.clamp(
                                angle.in(Rotations),
                                HoodConst.MIN_PITCH.in(Rotations),
                                HoodConst.MAX_PITCH.in(Rotations)));
        motor.setControl(new MotionMagicVoltage(targetPitch));
    }

    /** stows the hood */
    public void stow() {
        movePitch(HoodConst.MAX_PITCH);
    }

    /**
     * gets the current pitch
     *
     * @return the pitch angle
     */
    public Angle getPitch() {
        return motor.getPosition().getValue();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        builder.addDoubleProperty("pitch (deg)", () -> getPitch().in(Degrees), null);
        builder.addDoubleProperty(
                "target pitch (deg)",
                () -> targetPitch.in(Degrees),
                (angle) -> movePitch(Degrees.of(angle)));
        super.initSendable(builder);
    }
}
