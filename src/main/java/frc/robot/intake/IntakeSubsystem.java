package frc.robot.intake;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final TalonFX deployMotor = new TalonFX(IntakeConst.DEPLOY_MOTOR_ID);
    private final TalonFX rollerMotor = new TalonFX(IntakeConst.ROLLER_MOTOR_ID);

    public IntakeSubsystem() {
        deployMotor.getConfigurator().apply(IntakeConfig.deployMotorConfiguration);
        rollerMotor.getConfigurator().apply(IntakeConfig.rollerMotorConfiguration);
        deployMotor.setPosition(IntakeConst.MAX_ANGLE.in(Rotations));
    }

    /**
     * sets the roller speed
     *
     * @param speed input speed
     */
    public void moveRollerSpeed(double speed) {
        rollerMotor.set(speed);
    }

    /** turn the rollers on */
    public void rollersOn() {
        moveRollerSpeed(IntakeConfig.ROLLER_MOTOR_SPEED);
    }

    /** turn the rollers off */
    public void rollersOff() {
        moveRollerSpeed(0);
    }

    /** spins rollers backwards */
    public void rollersReverse() {
        moveRollerSpeed(IntakeConfig.ROLLER_MOTOR_SPEED * -1);
    }

    /**
     * sets the deploy angle
     *
     * @param angle input angle
     */
    public void moveAngle(Angle angle) {
        Angle targetAngle =
                Rotations.of(
                        MathUtil.clamp(
                                angle.in(Rotations),
                                IntakeConst.MIN_ANGLE.in(Rotations),
                                IntakeConst.MAX_ANGLE.in(Rotations)));
        deployMotor.setControl(new MotionMagicVoltage(targetAngle));
    }

    /** brings the intake down */
    public void moveDown() {
        moveAngle(IntakeConst.MIN_ANGLE);
    }

    /** brings the intake up */
    public void moveUp() {
        moveAngle(IntakeConst.MAX_ANGLE);
    }

    /** deploys the intake down and starts the rollers */
    public void deploy() {
        rollersOn();
        moveDown();
    }

    /** stows the intake up and stops rollers */
    public void stow() {
        rollersOff();
        moveUp();
    }

    /**
     * gets the angle of the intake
     *
     * @return the angle of the intake
     */
    public Angle getAngle() {
        return deployMotor.getPosition().getValue();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        builder.addDoubleProperty(
                "angle (deg)",
                () -> getAngle().in(Rotations),
                (angle) -> moveAngle(Rotations.of(angle)));
        super.initSendable(builder);
    }
}
