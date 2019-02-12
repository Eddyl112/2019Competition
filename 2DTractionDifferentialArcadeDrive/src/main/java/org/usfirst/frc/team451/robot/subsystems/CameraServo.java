/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;

import org.usfirst.frc.team451.robot.commands.CameraMove;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class CameraServo extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Servo cameraPitch = new Servo(0);
  public static Servo cameraYaw = new Servo(1);
  public static double pitchSpeed = 0.65;
  public static double yawSpeed = 0.5;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CameraMove());
  }
}
