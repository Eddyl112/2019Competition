/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
package org.usfirst.frc.team451.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.subsystems.DriveTrain;
import org.usfirst.frc.team451.robot.subsystems.LineTracker;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Command {
    public Double deadzone = 0.25; // Could be issue, would test

    public Drive() {

        requires(Robot.myDriveTrain);
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        
         //Register user imput from left drive stick   
        // if (OI.driveStickLeft.getY() < deadzone && OI.driveStickLeft.getY() > -deadzone) {
        //     DriveTrain.wheelSpeed[0] = 0;
        // } else {
            DriveTrain.wheelSpeed[0] = OI.driveStickLeft.getY();
        //}

        //register input from right drive stick
        // if (OI.driveStickRight.getY() < deadzone && OI.driveStickRight.getY() > -deadzone) {
        //     DriveTrain.wheelSpeed[1] = 0;
        // } else {
            DriveTrain.wheelSpeed[1] = OI.driveStickRight.getY();
         //}

        //register user input from joystick trigger to enable user assist
        if(OI.driveStickRight.getRawButton(1)) {
            DriveTrain.userAssistEnabled = true;
            //set the left side motion equal to the right side when driving straight forward
            //DriveTrain.wheelSpeed[0] = - DriveTrain.wheelSpeed[1];
            DriveTrain.wheelSpeed[0] = DriveTrain.wheelSpeed[1];
        } else DriveTrain.userAssistEnabled = false;

        //SmartDashboard.putBoolean("User Assist",DriveTrain.userAssistEnabled);
        
        //Modify user input to keep the bot on the line during user assist
        if(DriveTrain.userAssistEnabled){
            if(LineTracker.Sensors[0].get()) {
                //If the left line sensor is tripped, then increase left wheel speed
                DriveTrain.wheelSpeed[0] += Robot.UserAssistCorrectionSpeed/100;
                System.out.println("Correcting right: "+DriveTrain.wheelSpeed[0]);
            }
            if(LineTracker.Sensors[2].get()){
                //If the right line sensor is tripped, then increase the right wheel speed
                DriveTrain.wheelSpeed[1] -= Robot.UserAssistCorrectionSpeed/100;
                System.out.println("Correcting left: "+DriveTrain.wheelSpeed[1]);

            }
        }

        if (OI.driveStickLeft.getRawButton(1)) {
            DriveTrain.drive(1.0, 1.0);
        }

        // if (OI.driveStickLeft.getRawButton(1)) {
        // DriveTrain.drive(DriveTrain.wheelSpeed[0], -DriveTrain.wheelSpeed[1]);
        // } else {
        //     DriveTrain.drive(0,0);
        // }
        
        
        /*
        //Normally, run left motor based on left motor input (equal to modified right side durin user assist)
        DriveTrain.frontLeftMotor.set(ControlMode.PercentOutput, DriveTrain.wheelSpeed[0]);

        //Always run the right motor based on right motor input
        DriveTrain.frontRightMotor.set(ControlMode.PercentOutput, DriveTrain.wheelSpeed[1]);
/*
        if(Robot.LineTracker.AUTO && Robot.LineTracker.rotateDirection != 0){
            //Rotate in place to get the robot lined up with the line
            DriveTrain.wheelSpeed[0] = Robot.LineTracker.rotateDirection;
            DriveTrain.wheelSpeed[1] = Robot.LineTracker.rotateDirection;

        //} else if(Robot.LineTracker.distanceToTravel > 0) {
            //Drive Forward
            //DriveTrain.wheelSpeed [1] = -0.5;
            //DriveTrain.wheelSpeed[0] = 0.5;

        }*/


        
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
