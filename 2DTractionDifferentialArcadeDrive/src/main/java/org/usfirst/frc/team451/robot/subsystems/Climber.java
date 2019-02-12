/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team451.robot.commands.ClimberMove;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static WPI_TalonSRX climber0 = new WPI_TalonSRX(6);
  public static WPI_TalonSRX climber1 = new WPI_TalonSRX(7);
  

  static SpeedControllerGroup climberGroup = new SpeedControllerGroup(climber0, climber1);
  //TEST THIS MAKE SURE MOTORS SPIN IN THE SAME DIRECTION BECAUSE OF LINE 32

  
  public Climber() {
    climber0.setInverted(true);
    climber0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
    climber1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
    //set soft limit and postitions for 0. climber starts at 0 so should be easy
  }

  public void stop() {
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ClimberMove());
  }

  public static void climb(double speed) {
    climberGroup.set(speed);
  }


}
  
