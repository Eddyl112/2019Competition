/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team451.robot.OI;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
//creates buttons, joystick and solinoide

  public void TurnPneumaticsOn() {
  System.out.print("claw on");
  OI.Solenoid1.set(true);
  }

  public void TurnPneumaticsOff(){
    System.out.print("claw off");
    OI.Solenoid1.set(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
