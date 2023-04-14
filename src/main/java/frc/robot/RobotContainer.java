package frc.robot;

import frc.robot.Enum.DriveDirection;
import frc.robot.commands.Arm.MoveArmAngle;
import frc.robot.commands.Arm.MoveArmDown;
import frc.robot.commands.Arm.MoveArmUp;
import frc.robot.commands.Autonomous.LeaveCommunity;
import frc.robot.commands.Autonomous.ThrowGamePieceAndCommunity;
import frc.robot.commands.DriveTrain.Drive;
import frc.robot.commands.DriveTrain.MoveDistance;
import frc.robot.commands.DriveTrain.ResetDriveTrainEncoders;
import frc.robot.commands.Grabber.SetGrabberHold;
import frc.robot.commands.Grabber.SetGrabberIn;
import frc.robot.commands.Grabber.SetGrabberOut;

// Subsystems /////////////////////////////////////////////////////////////////////////////////////////

import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.Grabber;

// Other Libraries ////////////////////////////////////////////////////////////////////////////////////

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

///////////////////////////////////////////////////////////////////////////////////////////////////////

public class RobotContainer {

// Subsystems /////////////////////////////////////////////////////////////////////////////////////////

  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Arm m_arm = new Arm();
  private final Grabber m_grabber = new Grabber();
  //private final ColorSensor m_colorSensor = new ColorSensor();
  //private final LED m_led = new LED();

// Controller & Triggers //////////////////////////////////////////////////////////////////////////////

  private final CommandXboxController m_driverController = new CommandXboxController(Constants.driverControllerPort);
  private final CommandXboxController m_operatorController = new CommandXboxController(Constants.operatorControllerPort);

  private final Trigger m_OC_rightBumper = m_operatorController.rightBumper();

  private final Trigger m_OC_aButton = m_operatorController.a();
  private final Trigger m_OC_bButton = m_operatorController.b();

  private final Trigger m_OC_povUp = m_operatorController.povUp();
  private final Trigger m_OC_povDown = m_operatorController.povDown();


// Robot Container /////////////////////////////////////////////////////////////////////////////////////

  public RobotContainer() {
    //m_led.setRGB(51 / 255, 0, 1);

    m_driveTrain.setDefaultCommand(new Drive(m_driveTrain, () -> m_driverController.getLeftY(), () -> m_driverController.getRightY()));

    configureBindings();
  }

// Configure Bindings //////////////////////////////////////////////////////////////////////////////////

  private void configureBindings() {
    m_operatorController.povUp().onTrue(new MoveArmUp(m_arm, true));
    m_operatorController.povUp().onFalse(new MoveArmUp(m_arm, false));

    m_operatorController.povDown().onTrue(new MoveArmDown(m_arm, true));
    m_operatorController.povDown().onFalse(new MoveArmDown(m_arm, false));

    m_operatorController.leftTrigger(0.85).toggleOnTrue(new SetGrabberIn(m_grabber));

    m_operatorController.rightTrigger(0.85).onTrue(new SetGrabberOut(m_grabber, true));
    m_operatorController.rightTrigger(0.85).onFalse(new SetGrabberOut(m_grabber, false));

    m_operatorController.a().toggleOnTrue(new SetGrabberHold(m_grabber));
  }

  public Command getAutonomousCommand() {
    return new ThrowGamePieceAndCommunity(m_driveTrain, m_arm, m_grabber);
  }
}
