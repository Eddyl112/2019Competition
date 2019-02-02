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
  public CameraMove() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.CameraServo);
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
    if (OI.driveStick.getPOV() == 0) {
      CameraServo.pitchSpeed++;  
      CameraServo.cameraPitch.set(CameraServo.pitchSpeed);
    } else if(OI.driveStick.getPOV() == 180){
      CameraServo.pitchSpeed--;
      CameraServo.cameraPitch.set(CameraServo.pitchSpeed);
    } else if(OI.driveStick.getPOV() == 90 ) {
      CameraServo.yawSpeed++;
      CameraServo.cameraYaw.set(CameraServo.yawSpeed);
    } else if(OI.driveStick.getPOV() == 270) {
      CameraServo.yawSpeed--;
      CameraServo.cameraYaw.set(CameraServo.yawSpeed);
    } //Change around, 0 is full left and 1 is full right
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