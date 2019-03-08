/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.commands;

import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;
/**
 * An example command.  You can replace me with your own command.
 */
public class OpenClaw extends Command {
  public OpenClaw() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.myClaw);
  }

 //Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  //turns pneumatics on 
  protected void execute() {
    // System.out.println("open claw");
    // OI.clawActive = true;
    // Claw.claw();
    Claw.clawSolenoid.set(false);
    Claw.PushSolenoid.set(true);
    Claw.clawSolenoid.set(true);
    Claw.PushSolenoid.set(false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //return false;
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  };

}
