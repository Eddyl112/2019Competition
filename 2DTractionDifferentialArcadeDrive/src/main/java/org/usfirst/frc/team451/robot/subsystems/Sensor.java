/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;

import org.usfirst.frc.team451.robot.commands.LineSensor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Sensor extends Subsystem {
  /* These are all of the Line Sensors. They use the DigitalInputs because they are
  not actually normal line sensors, but more like on/off switches. centerLineSensor 
  is the sensor in the center, rightLineSensor is the sensor toward the right of the
  bot, and leftLineSensor is the sensor toward the left of the bot. */

  
  public static DigitalInput centerLineSensor = new DigitalInput(0);
  public static DigitalInput rightLineSensor = new DigitalInput(1);
  public static DigitalInput leftLineSensor = new DigitalInput(2);

  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new LineSensor());
  }
}
