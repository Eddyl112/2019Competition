/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team451.robot.Robot;
/**
 * An example command.  You can replace me with your own command.
 */
//extends command class with close claw
public class CloseClaw extends Command {
  public CloseClaw() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.Claw);
  }

 //Called just before this Command runs the first time
  @Override
  protected void initialize() {
   Robot.oi.button5.whenPressed(new CloseClaw());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  //turns off solenoid
  protected void execute() {
    Robot.Claw.TurnPneumaticsOff();
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
  };
}
