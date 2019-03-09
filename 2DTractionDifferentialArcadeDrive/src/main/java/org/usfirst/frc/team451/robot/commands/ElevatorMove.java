/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

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
    Elevator.elevatorMotor.setSelectedSensorPosition(0);
  }
 
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //SET ELEVATOR TARGET POSITION
    //Hatch Presets
    
    if (Elevator.ypr[1] > 20 || Elevator.ypr[1] < -20) {
      Elevator.moveToPosition(0);
    } else if (Elevator.ypr[2] > 20 || Elevator.ypr[2] < -20) {
      Elevator.moveToPosition(0);
    } else {
      //Port Presets
      /*Preset elevator levels on dpad. This is the ONLY PLACE where any dpad code is; there is 
      NONE in OI because the XBOX dpad is very unique. 0 is UP, 90 is RIGHT, 180 is DOWN, and 270 
      is LEFT. You can also get intermediates at each 45 degree interval between those numbers, 
      we're not going to to leave some dead zones for the mech driver + it's not applicable here
      anyway. */
      if (OI.mechBox.getPOV() == 0) {
        Elevator.TargetHeightInInches = Elevator.PortHeights[2];
        Elevator.TargetHeightInTicks = (Elevator.TargetHeightInInches-Elevator.minHeight)/(Elevator.inchesPerCount);
      } else if (OI.mechBox.getPOV() == 90 || OI.mechBox.getPOV() == 270) {
        Elevator.TargetHeightInInches = Elevator.PortHeights[1];
        Elevator.TargetHeightInTicks = (Elevator.TargetHeightInInches-Elevator.minHeight)/(Elevator.inchesPerCount);
      } else if (OI.mechBox.getPOV() == 180) {
        Elevator.TargetHeightInInches = Elevator.PortHeights[0];
        Elevator.TargetHeightInTicks = (Elevator.TargetHeightInInches-Elevator.minHeight)/(Elevator.inchesPerCount);
      }

      if(OI.mechBox.getYButton()) {
        Elevator.TargetHeightInInches = Elevator.HatchHeights[2];
        Elevator.TargetHeightInTicks = (Elevator.TargetHeightInInches-Elevator.minHeight)/(Elevator.inchesPerCount);
      }
      if(OI.mechBox.getXButton() || OI.mechBox.getBButton()) {
        Elevator.TargetHeightInInches = Elevator.HatchHeights[1];
        Elevator.TargetHeightInTicks = (Elevator.TargetHeightInInches-Elevator.minHeight)/(Elevator.inchesPerCount);
      }
      if(OI.mechBox.getAButton()) {
        Elevator.TargetHeightInInches = Elevator.HatchHeights[0];
        Elevator.TargetHeightInTicks = (Elevator.TargetHeightInInches-Elevator.minHeight)/(Elevator.inchesPerCount);
      }
  
      //RESETS ENCODER POSITION TO ZERO
      // if (OI.mechBox.getRawButtonPressed(7)) {
      //   Elevator.elevatorMotor.setSelectedSensorPosition(0);
      // }
  
      //RUN ELEVATOR MOTION
      if (Elevator.elevatorZero.get() == true) {
        System.out.println("---------------------------------LIMIT SWITCH ACTIVAITED-------------------------------------");
        Elevator.elevatorMotor.setSelectedSensorPosition(0);
        if(OI.mechBox.getY(Hand.kRight) > 0.01){
          //Control motors directly when user is overriding
          Elevator.elevatorMotor.set(ControlMode.PercentOutput, OI.mechBox.getY(Hand.kRight));
          //Set the target height in ticks equal to current height so it doesn't try to move back when you stop overriding
          Elevator.TargetHeightInTicks = Elevator.elevatorMotor.getSelectedSensorPosition();
        } else {
          //uses motion magic to get to the proper height when user is not overriding
          Elevator.moveToPosition(Elevator.TargetHeightInTicks);
        }
      } else {
        if(OI.mechBox.getY(Hand.kRight) > 0.01 || OI.mechBox.getY(Hand.kRight) < -0.01){
          //Control motors directly when user is overriding
          Elevator.elevatorMotor.set(ControlMode.PercentOutput, OI.mechBox.getY(Hand.kRight));
          //Set the target height in ticks equal to current height so it doesn't try to move back when you stop overriding
          Elevator.TargetHeightInTicks = Elevator.elevatorMotor.getSelectedSensorPosition();
        } else {
          //uses motion magic to get to the proper height when user is not overriding
          Elevator.moveToPosition(Elevator.TargetHeightInTicks);
        }
          
      }
      
    }

    

/*
    //Go to the preset (run first so it does not override the user override)
    //Elevator.MoveTowards(Elevator.TargetHeight, false);
    Elevator.moveToPosition(Elevator.TargetHeightInTicks);

    //Only run this method when the user is trying to override
    Elevator.RunUserOverride(OI.mechBox.getY(Hand.kRight), false);

    //Elevator.speedControl();
    */
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
