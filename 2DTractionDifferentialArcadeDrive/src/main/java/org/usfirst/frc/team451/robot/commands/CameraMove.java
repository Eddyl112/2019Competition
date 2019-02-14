/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team451.robot.commands;

import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.subsystems.CameraServo;

import edu.wpi.first.wpilibj.command.Command;

public class CameraMove extends Command {

  public double speed = 0.04;

  public CameraMove() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myCameraServo);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    CameraServo.cameraPitch.set(CameraServo.pitchSpeed);
    CameraServo.cameraYaw.set(CameraServo.yawSpeed);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (OI.driveStickLeft.getPOV() == 0) {
      CameraServo.pitchSpeed = CameraServo.pitchSpeed + speed;
      if (CameraServo.pitchSpeed > 1) {
        CameraServo.pitchSpeed = 1;
      } else {
        CameraServo.cameraPitch.set(CameraServo.pitchSpeed);
      }
    } else if (OI.driveStickLeft.getPOV() == 180) {
      CameraServo.pitchSpeed = CameraServo.pitchSpeed - speed;
      if (CameraServo.pitchSpeed < 0) {
        CameraServo.pitchSpeed = 0;
      } else {
        CameraServo.cameraPitch.set(CameraServo.pitchSpeed);
      }
    }

    if (OI.driveStickLeft.getPOV() == 90) {
      CameraServo.yawSpeed = CameraServo.yawSpeed + speed;
      if (CameraServo.yawSpeed > 1) {
        CameraServo.yawSpeed = 1;
      } else {
        CameraServo.cameraYaw.set(CameraServo.yawSpeed);
      }
    } else if (OI.driveStickLeft.getPOV() == 270) {
      CameraServo.yawSpeed = CameraServo.yawSpeed - speed;
      if (CameraServo.yawSpeed < 0) {
        CameraServo.yawSpeed = 0;
      } else {
        CameraServo.cameraYaw.set(CameraServo.yawSpeed);
      }
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}