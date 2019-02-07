/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.commands;

import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.subsystems.LineTracker;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * INFORM ALEX BEFORE MAKING ANY CHANGES TO THIS DOCUMENT
*/

public class AutoAlign extends Command {

  public static String Mode = "UN-ACTIVATED";

  public AutoAlign() {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.DriveTrain);
    requires(Robot.LineTracker);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    LineTracker.updateSensorFieldPositions(Robot.gyro.getAngle());
    LineTracker.tripActiveSensors();

    if(LineTracker.trippedCount == 1){
      LineTracker.updateDelta();
      SmartDashboard.setDefaultNumber("Robot Distance", Robot.DriveTrain.frontLeftMotor.getSelectedSensorPosition());
    }

    if(LineTracker.trippedCount > 1){
      double deltaAngle = (LineTracker.idealRotation-Robot.gyro.getAngle())%360;
      if(deltaAngle > Math.PI/18 && deltaAngle < Math.PI){
      //if(true) {
        System.out.println("Rotating "+Math.round(deltaAngle*100)/100+" radians");
        //rotate clockwise
      } else if(deltaAngle < 35*Math.PI/18 && deltaAngle > Math.PI) {
        System.out.println("Rotating "+Math.round(deltaAngle*100)/100+" radians");
      //   //rotate counterclockwise
      } else {
        System.out.println(""+Math.round(deltaAngle*100)/100+" radians was an insufficient rotation.");
        //don't rotate
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
