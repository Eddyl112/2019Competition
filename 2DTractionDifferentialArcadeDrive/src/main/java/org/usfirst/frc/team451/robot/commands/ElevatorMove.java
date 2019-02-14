/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.commands;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.sun.java.swing.plaf.windows.TMSchema.Control;

import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorMove extends Command {
  //private static final String Elevator = null;

public ElevatorMove() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.myElevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }
 
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Set hatch presets
    if(OI.mechBox.getYButton()) Elevator.TargetHeight = Elevator.HatchHeights[2];
    if(OI.mechBox.getXButton() || OI.mechBox.getBButton()) Elevator.TargetHeight = Elevator.HatchHeights[1];
    if(OI.mechBox.getAButton()) Elevator.TargetHeight = Elevator.HatchHeights[0];

    //set port presets
    /*Preset elevator levels on dpad. This is the ONLY PLACE where any dpad code is; there is 
    NONE in OI because the XBOX dpad is very unique. 0 is UP, 90 is RIGHT, 180 is DOWN, and 270 
    is LEFT. You can also get intermediates at each 45 degree interval between those numbers, 
    we're not going to to leave some dead zones for the mech driver + it's not applicable here
    anyway. */
    if (OI.mechBox.getPOV() == 0) {
      Elevator.TargetHeight = Elevator.PortHeights[2];
    } else if (OI.mechBox.getPOV() == 90 || OI.mechBox.getPOV() == 270) {
      Elevator.TargetHeight = Elevator.PortHeights[1];
    } else if (OI.mechBox.getPOV() == 180) {
      Elevator.TargetHeight = Elevator.PortHeights[0];
    }

    //Go to the preset (run first so it does not override the user override)
    Elevator.MoveTowards(Elevator.TargetHeight, false);

    //Only run this method when the user is trying to override
    Elevator.RunUserOverride(OI.mechBox.getY(Hand.kRight), false);
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
