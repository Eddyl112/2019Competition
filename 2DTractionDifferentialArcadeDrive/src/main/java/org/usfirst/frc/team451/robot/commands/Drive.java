/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
package org.usfirst.frc.team451.robot.commands;

//import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.Robot;
//import org.usfirst.frc.team451.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
    public Double deadzone = 0.25; // Could be issue, would test

    public Drive() {

        requires(Robot.DriveTrain);
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.DriveTrain.drive(-OI.driveStickLeft.getY(), -OI.driveStickRight.getY());

        // if (OI.driveStickLeft.getY() < deadzone && OI.driveStickLeft.getY() > -deadzone) {
        //    DriveTrain.frontLeftMotor.set(ControlMode.PercentOutput, 0);
        // } else {
        //     DriveTrain.frontLeftMotor.set(ControlMode.PercentOutput, -OI.driveStickLeft.getY());
        // }

        // if (OI.driveStickRight.getY() < deadzone && OI.driveStickRight.getY() > -deadzone) {
        //    DriveTrain.frontRightMotor.set(ControlMode.PercentOutput, 0);
        // } else {
        //     DriveTrain.frontRightMotor.set(ControlMode.PercentOutput, OI.driveStickRight.getY());
        // }
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
