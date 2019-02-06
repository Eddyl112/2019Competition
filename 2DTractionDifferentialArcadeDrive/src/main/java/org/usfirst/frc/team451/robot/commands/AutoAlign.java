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
      //change delta
    }

    if(LineTracker.trippedCount > 1){
      //run the algorithm for moving forward & turning
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
