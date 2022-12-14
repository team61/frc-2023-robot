package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class AlignMotorsCommand extends CommandBase {
    private final DriveTrainSubsystem drivetrain;
    private final String direction;
    private boolean finished = false;

    public AlignMotorsCommand(DriveTrainSubsystem dt, String dir) {
        drivetrain = dt;
        direction = dir;

        addRequirements(dt);
    }

    @Override
    public void initialize() {
        drivetrain.disableBreaks();
    }

    @Override
    public void execute() {
        finished = drivetrain.alignMotors(direction);

        if (finished) {
            drivetrain.enableBreaks();
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return finished;
    }
}
