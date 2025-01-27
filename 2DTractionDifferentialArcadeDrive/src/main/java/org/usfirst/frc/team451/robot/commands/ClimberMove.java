/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.subsystems.Climber;
import org.usfirst.frc.team451.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberMove extends Command {
  public ClimberMove() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.myClimber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Climber.climber0.setSelectedSensorPosition(0);
    //Climber.climber0.set(ControlMode.Position, 0);
    //Climber.climber0.setSelectedSensorPosition(0, 0, 30);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (OI.climberPistonButton.get()){
			if(Climber.ClimberPistonActive) {
        Climber.ClimberPistonActive = false;
        Climber.climber();
			} else if (!Climber.ClimberPistonActive){
        Climber.ClimberPistonActive = true;
        Climber.climber();
			}
		}
    


  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
