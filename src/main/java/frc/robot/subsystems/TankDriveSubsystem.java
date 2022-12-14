package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDriveSubsystem extends SubsystemBase {
    private final SwerveMotorsSubsystem[] driveLeft;
    private final SwerveMotorsSubsystem[] driveRight;

    public TankDriveSubsystem(int unitsPerSide, int[] leftPorts, int[] rightPorts) {
        driveLeft = new SwerveMotorsSubsystem[unitsPerSide];
        driveRight = new SwerveMotorsSubsystem[unitsPerSide];

        for (int i = 0; i < driveLeft.length; i++) {
            driveLeft[i] = new SwerveMotorsSubsystem(leftPorts[i * 2], leftPorts[i * 2 + 1]);
        }

        for (int i = 0; i < driveRight.length; i++) {
            driveRight[i] = new SwerveMotorsSubsystem(rightPorts[i * 2], rightPorts[i * 2 + 1]);
        }
    }

    public void driveVolts(double lVolts, double rVolts) {
        for (SwerveMotorsSubsystem swerveUnit : driveLeft) {
            swerveUnit.setWheelVoltage(lVolts);
        }

        for (SwerveMotorsSubsystem swerveUnit : driveRight) {
            swerveUnit.setWheelVoltage(rVolts);
        }
    }

    public void driveSpeed(double lSpeed, double rSpeed) {
        for (SwerveMotorsSubsystem swerveUnit : driveLeft) {
            swerveUnit.setWheelSpeed(lSpeed);
        }

        for (SwerveMotorsSubsystem swerveUnit : driveRight) {
            swerveUnit.setWheelSpeed(rSpeed);
        }
    }

    public void stop() {
        for (SwerveMotorsSubsystem swerveUnit : driveLeft) {
            swerveUnit.stopWheel();
        }

        for (SwerveMotorsSubsystem swerveUnit : driveRight) {
            swerveUnit.stopWheel();
        }
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}
}
