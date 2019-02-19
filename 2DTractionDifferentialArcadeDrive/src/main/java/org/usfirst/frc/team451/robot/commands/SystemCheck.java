/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.commands;

import org.usfirst.frc.team451.robot.subsystems.CameraServo;
import org.usfirst.frc.team451.robot.subsystems.Claw;
import org.usfirst.frc.team451.robot.subsystems.Climber;
//import org.usfirst.frc.team451.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class SystemCheck extends Command {
  
  int delay = 5;

  public SystemCheck() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //DriveTrain.drive(100, 100);
    Timer.delay(delay);
    //DriveTrain.drive(-100, -100);
    Timer.delay(delay);
    //DriveTrain.drive(-100, 100);
    Timer.delay(delay);
    //DriveTrain.drive(100, -100);
    Timer.delay(delay);
    Claw.clawSolenoid.set(true);
    Timer.delay(delay);
    Claw.clawSolenoid.set(false);
    Timer.delay(delay);
    Climber.climb(1);
    Timer.delay(0.5);
    Climber.climb(-1);
    Timer.delay(0.5);

    
    
    
    
    
    
    
    
    CameraServo.cameraPitch.set(0);
    Timer.delay(1);
    CameraServo.cameraPitch.set(1);
    Timer.delay(1);
    //CameraServo.cameraYaw.set(0);
    Timer.delay(1);
    //CameraServo.cameraYaw.set(1);
    Timer.delay(1);
    CameraServo.cameraPitch.set(CameraServo.pitchSpeed);
    //CameraServo.cameraYaw.set(CameraServo.yawSpeed);
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
