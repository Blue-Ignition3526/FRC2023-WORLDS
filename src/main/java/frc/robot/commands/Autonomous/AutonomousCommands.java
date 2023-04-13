// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.Motors.BaymaxMotors.Grabber;
import frc.robot.commands.Arm.MoveArmUp;
import frc.robot.commands.DriveTrain.MoveSeconds;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;

/** Add your docs here. */
public class AutonomousCommands {
    public Command leaveCommunity(DriveTrain driveTrain) {
        return new MoveSeconds(driveTrain, 7.5, null);
    }

    public SequentialCommandGroup leaveGamePieceAndCommunity(DriveTrain driveTrain, Arm arm, Grabber grabber) {
        return new SequentialCommandGroup(
            new MoveArmUp(arm, true),
            new WaitCommand(2.5),
            new MoveArmUp(arm, false),
            // Set grabber out
            leaveCommunity(driveTrain)
        );
    }
}
