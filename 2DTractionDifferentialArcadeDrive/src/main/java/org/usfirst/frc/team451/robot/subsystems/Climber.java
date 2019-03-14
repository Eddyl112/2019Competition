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

  public static WPI_TalonSRX climber = new WPI_TalonSRX(1);
  public static double targetVelocity = 5.0;

  public static Solenoid leftClimberSolenoid = new Solenoid(3);
  public static Solenoid rightClimberSolenoid = new Solenoid(4);

  
  //TEST THIS MAKE SURE MOTORS SPIN IN THE SAME DIRECTION BECAUSE OF LINE 32


  
  public Climber() {
    leftClimberSolenoid.set(SmartDashboard.getBoolean("Left Climber Solenoid", false));
    rightClimberSolenoid.set(SmartDashboard.getBoolean("Right Climber Solenoid", false));
    // climber0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDLoopIdx, Constants.timeoutMS);
    // climber1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PIDLoopIdx, Constants.timeoutMS);
    // climber0.setSensorPhase(true);
    // climber1.setSensorPhase(true);
    // climber0.configNominalOutputForward(1, Constants.timeoutMS);
    // climber0.configNominalOutputReverse(-1, Constants.timeoutMS);
    // climber1.configNominalOutputForward(1, Constants.timeoutMS);
    // climber1.configNominalOutputReverse(-1, Constants.timeoutMS);

    // climber0.config_kP(Constants.kPIDLoopIdx, Constants.gainsVelocity.P, Constants.timeoutMS);
    // climber0.config_kI(Constants.kPIDLoopIdx, Constants.gainsVelocity.I, Constants.timeoutMS);
    // climber0.config_kD(Constants.kPIDLoopIdx, Constants.gainsVelocity.D, Constants.timeoutMS);
    // climber0.config_kF(Constants.kPIDLoopIdx, Constants.gainsVelocity.F, Constants.timeoutMS);

    
    // climber1.config_kP(Constants.PIDLoopIdx, Constants.gainsVelocity.P, Constants.timeoutMS);
    // climber1.config_kI(Constants.PIDLoopIdx, Constants.gainsVelocity.I, Constants.timeoutMS);
    // climber1.config_kD(Constants.PIDLoopIdx, Constants.gainsVelocity.D, Constants.timeoutMS);
    // climber1.config_kF(Constants.PIDLoopIdx, Constants.gainsVelocity.F, Constants.timeoutMS);


    
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
    climber.set(ControlMode.Velocity,speed);
    
  }


}
  
 