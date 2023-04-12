package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

public class AutonomousCommandsHandler {
    private DriveTrain driveTrain;

    AutonomousCommandsHandler(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
    }

    public SequentialCommandGroup LeaveCommunity() {
        new SequentialCommandGroup();
    }
}
