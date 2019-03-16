/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team451.robot.commands.ClimberMove;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Creates climber motors and sets feedbacksensor
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static WPI_TalonSRX climber0 = new WPI_TalonSRX(1);
  public static WPI_TalonSRX climber1 = new WPI_TalonSRX(2);
  //public static double targetVelocity = 5.0;

  public static Solenoid leftClimberSolenoid = new Solenoid(3);
  public static Solenoid rightClimberSolenoid = new Solenoid(4);

	public static boolean ClimberPistonActive = false;
  
  //TEST THIS MAKE SURE MOTORS SPIN IN THE SAME DIRECTION BECAUSE OF LINE 32


  
  public static void climber() {
    leftClimberSolenoid.set(SmartDashboard.getBoolean("Left Climber Solenoid", false));
    rightClimberSolenoid.set(SmartDashboard.getBoolean("Right Climber Solenoid", false));
    
    if (ClimberPistonActive) {
      Climber.leftClimberSolenoid.set(true);
      Climber.rightClimberSolenoid.set(true);
    } else if(!ClimberPistonActive) {
      Climber.leftClimberSolenoid.set(false);
      Climber.rightClimberSolenoid.set(false);
    }
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
    climber0.set(ControlMode.Velocity, speed);
    climber1.set(ControlMode.Velocity, -speed);
    
  }


}
  
 