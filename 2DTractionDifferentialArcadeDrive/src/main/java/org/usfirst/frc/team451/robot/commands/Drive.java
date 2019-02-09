/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
package org.usfirst.frc.team451.robot.commands;

import org.usfirst.frc.team451.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
    public Double deadzone = 0.25; //Could be issue, would test

    public Drive() {

        requires(Robot.DriveTrain);
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        if(!Robot.LineTracker.AUTO){
            if (OI.driveStickLeft.getY() < deadzone && OI.driveStickLeft.getY() > -deadzone) {
                DriveTrain.wheelSpeed[0] = 0;
            } else {
                DriveTrain.wheelSpeed[0] = -OI.driveStickLeft.getY();
            }

            if (OI.driveStickRight.getY() < deadzone && OI.driveStickRight.getY() > -deadzone) {
                DriveTrain.wheelSpeed[1] = 0;
            } else {
                DriveTrain.wheelSpeed[1] = OI.driveStickRight.getY();
            }
            if (Robot.LineTracker.Sensors[0].get()) {
                //If the left line sensor is tripped, then increase left wheel speed and decrese right wheel speed by a set proportion
                DriveTrain.wheelSpeed[0] *= 1.08;
                DriveTrain.wheelSpeed[1] *= 0.92; 
            } else if  (Robot.LineTracker.Sensors[1].get()){
                //If the right line sensor is tripped, then decrease the left wheel speed and increase the right wheel speed by a set proportion
                DriveTrain.wheelSpeed[0] *= 0.92;
                DriveTrain.wheelSpeed[1] *= 1.08; 

            }

        } else if(Robot.LineTracker.distanceToTravel <= 0) {
            //Rotate in place to get the robot lined up with the line
            DriveTrain.wheelSpeed[0] = Robot.LineTracker.rotateDirection;
            DriveTrain.wheelSpeed[1] = Robot.LineTracker.rotateDirection;

        } else if(Robot.LineTracker.distanceToTravel > 0) {
            //Drive Forward
            DriveTrain.wheelSpeed [1] = 1;
            DriveTrain.wheelSpeed[0] = -1;

        }


        DriveTrain.frontLeftMotor.set(ControlMode.PercentOutput, DriveTrain.wheelSpeed[0]);
        DriveTrain.frontRightMotor.set(ControlMode.PercentOutput, DriveTrain.wheelSpeed[1]);
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
