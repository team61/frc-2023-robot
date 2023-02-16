package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class SwerveDriveSubsystem extends SubsystemBase {
    private final SwerveMotorsSubsystem[] swerveMotors;

    public SwerveDriveSubsystem(int totalSwerveDriveUnits, int[] motorIDs, int[] encoderIDs) {
        swerveMotors = new SwerveMotorsSubsystem[totalSwerveDriveUnits];

        for (int i = 0; i < totalSwerveDriveUnits; i++) {
            swerveMotors[i] = new SwerveMotorsSubsystem(motorIDs[i * 2], motorIDs[i * 2 + 1], encoderIDs[i]);
        }
    }

    public void rotateIndividualWheel(int index, double volts) {
        swerveMotors[index].setRotationVoltageNoRestraint(volts);
    }

    public void setRotationVoltage(double volts) {
        for (SwerveMotorsSubsystem swerveUnit : swerveMotors) {
            swerveUnit.setRotationVoltage(volts);
        }
    }

    public void driveVolts(double volts) {
        for (SwerveMotorsSubsystem swerveUnit : swerveMotors) {
            swerveUnit.setWheelVoltage(volts);
        }
    }

    public void driveSpeed(double speed) {
        for (SwerveMotorsSubsystem swerveUnit : swerveMotors) {
            swerveUnit.setWheelSpeed(speed);
        }
    }

    public void enableBreaks() {
        for (SwerveMotorsSubsystem swerveUnit : swerveMotors) {
            swerveUnit.enableBreaks();
        }
    }

    public void disableBreaks() {
        for (SwerveMotorsSubsystem swerveUnit : swerveMotors) {
            swerveUnit.disableBreaks();
        }
    }

    public void zeroOutRotation() {
        for (SwerveMotorsSubsystem swerveUnit : swerveMotors) {
            swerveUnit.zeroOutRotation();
        }
    }

    public boolean alignMotors(String direction, int amount) {
        boolean[] results = new boolean[swerveMotors.length];
        double targetPosition = 0;
        if (direction == BACKWARDS) {
            targetPosition += ENCODER_UNITS_PER_ROTATION / 2;
        } else if (direction == SIDEWAYS) {
            targetPosition += ENCODER_UNITS_PER_ROTATION / 4;
        } else if (direction == MIDDLE) {
            for (int i = 1; i <= amount; i++) {
                SwerveMotorsSubsystem swerveUnit = swerveMotors[i - 1];
                targetPosition += swerveUnit.getRotationPosition();
            }
            targetPosition /= amount;
        }
        for (int i = 1; i <= amount; i++) {
            SwerveMotorsSubsystem swerveUnit = swerveMotors[i - 1];
            results[i - 1] = swerveUnit.rotateTowards(targetPosition);
        }

        for (boolean result : results) {
            if (!result) return false;
        }

        return true;
    }

    public boolean alignMotors(String direction) {
        return alignMotors(direction, swerveMotors.length);
    }

    @Override
    public void periodic() {}
}
