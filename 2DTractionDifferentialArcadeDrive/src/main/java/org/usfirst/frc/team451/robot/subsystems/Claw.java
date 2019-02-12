/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;

import org.usfirst.frc.team451.robot.OI;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
//creates buttons, joystick and solinoide
public static Solenoid clawSolenoid = new Solenoid(1);


public static void claw() {
    System.out.println("claw sub");
    clawSolenoid.set(OI.clawActive);
}

  @Override
  protected void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }
}
