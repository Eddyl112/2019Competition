/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;

import org.usfirst.frc.team451.robot.OI;
//import org.usfirst.frc.team451.robot.commands.GrabMove;
import org.usfirst.frc.team451.robot.commands.GrabMove;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Creates claw and sets solenoids
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
public static Solenoid clawSolenoid = new Solenoid(2);
public static Solenoid pushSolenoid = new Solenoid(1);
public static boolean clawActive = false;
public static boolean retracted = false;


public static void claw() {
    //clawSolenoid.set(SmartDashboard.getBoolean("Claw Status", true));
    //pushSolenoid.set(SmartDashboard.getBoolean("Claw Extend", false));
    
    if (clawActive) {
      Claw.clawSolenoid.set(false);
    } else if (!clawActive) {
      Claw.clawSolenoid.set(true);
    }

    if (retracted) {
      Claw.pushSolenoid.set(false);
    } else if (!retracted) {
      Claw.pushSolenoid.set(true);
    }
}

  @Override
  protected void initDefaultCommand() {
    // setDefaultCommand(new GrabMove());
  }
}
