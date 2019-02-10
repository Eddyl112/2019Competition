/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.commands;

import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.subsystems.LineTracker;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * INFORM ALEX BEFORE MAKING ANY CHANGES TO THIS DOCUMENT
*/

public class AutoAlign extends Command {

  public static String Mode = "UN-ACTIVATED";
  public static boolean warned = LineTracker.printInfo;

  public AutoAlign() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.LineTracker);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*
    //Warn the user when they are not printing debug information, and create a new line for debug info otherwise
    if(!warned){
      System.out.println("NOTE: You are not printing debug information from LineTracker.java");
      warned = true;
    } else if(LineTracker.printInfo) {
      //Creates new line, since all debug info is in print commands (to show what all happens within the same iteration)
      System.out.println();
    }

    //track encoder values on smartdashboard
    SmartDashboard.setDefaultNumber("Robot Distance", Robot.DriveTrain.frontLeftMotor.getSelectedSensorPosition());

    //HOVER OVER ANY OF THE FOLLOWING METHODS TO SEE WHAT THEY DO
    //call these after one sensor has been tripped (must be done first, since it must be done on the loop when the second sensor is tripped)
    if(LineTracker.trippedCount == 1) LineTracker.updateDelta();

    //always call these
    LineTracker.updateSensorFieldPositions(Robot.gyro.getAngle());
    LineTracker.tripActiveSensors();

    if(OI.driveStickRight.getRawButton(1)) {
      LineTracker.AUTO = false;
      LineTracker.resetSensorsAndDelta();
    }

    if(LineTracker.trippedCount > 1){
      //e.g., the difference in angle between the robot angle and the angle of the line
      double deltaAngle = LineTracker.idealRotation-Robot.gyro.getAngle();
      while(deltaAngle<0) deltaAngle+=360;
      while(deltaAngle>360) deltaAngle-=360;
      double angleMargin = 1;

      //e.g., if the angle is between 10 and 180 degrees
      if(LineTracker.distanceToTravel <= 0){
        if(deltaAngle > angleMargin && deltaAngle < 180){
          System.out.print("Rotating "+Math.round(deltaAngle*100)/100+" degrees. ");
          LineTracker.rotateDirection = -1;
        } else if(deltaAngle < 360-angleMargin && deltaAngle > 180) {
          System.out.print("Rotating "+Math.round(deltaAngle*100)/100+" degrees. ");
          LineTracker.rotateDirection = 1;
        } else {
          System.out.print(Math.round(deltaAngle*100)/100+" degrees was an insufficient rotation. ");
          LineTracker.resetSensorsAndDelta();
        }
      } else {
        LineTracker.distanceToTravel -= (Robot.DriveTrain.frontLeftMotor.getSelectedSensorPosition()-LineTracker.encoderDistance);
        System.out.print("Distace to line: "+LineTracker.distanceToTravel);
      }
    }*/
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
