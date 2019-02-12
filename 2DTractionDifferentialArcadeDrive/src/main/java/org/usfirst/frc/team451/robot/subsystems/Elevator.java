/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
package org.usfirst.frc.team451.robot.subsystems;

import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.commands.Drive;
import org.usfirst.frc.team451.robot.commands.ElevatorMove;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.command.Subsystem;



public class Elevator extends Subsystem {

	//heights for the center of the hatch panel spots on the rocket
	static double firstHatchHeight = 12+7;//19 inches
	static double distancebetweenH = 2*12+4;//28 inches
	public static double[] HatchHeights = {firstHatchHeight,firstHatchHeight+distancebetweenH,firstHatchHeight+2*distancebetweenH};

	//heights for the center of the cargo spots
	static double firstPortHeight = 2*12+3.5;//27.5 inches
	static double distancebetweenP = 2*12+4;//28 inches
	public static double[] PortHeights = {firstPortHeight, firstPortHeight+distancebetweenP, firstPortHeight+2*distancebetweenP};

	public static double TargetHeight = HatchHeights[0];

	public static double countsPerRevolution = 1024; //counts per motor revolution based on specifications for mag encoder
	public static double gearReduction = 1; //motor revolutions per wheel revolutions
	public static double WheelDiameter = 1.7284; //inches
	public static double inchesPerCount = (Math.PI*WheelDiameter)/(countsPerRevolution*gearReduction); //the amount of inches that are covered in one count

	// Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(5);
	
	
    
    public Elevator() {
    }
 
    
	@Override
	protected void initDefaultCommand() {
		
		setDefaultCommand(new ElevatorMove());
		
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//ignore this
//	Spark frontLeftMotor = new Spark(RobotMap.frontLeftMotor);
//	Spark frontRightMotor = new Spark(RobotMap.frontRightMotor);
//	Spark backLeftMotor = new Spark(RobotMap.backLeftMotor);
//	Spark backRightMotor = new Spark(RobotMap.backRightMotor);

//	MecanumDrive Drive = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
//	
//	public void driveCartesian(double y, double x, double z, double gyroAngle) {
//	    Vector2d input = new Vector2d(y, x);
//	    input.rotate(-gyroAngle);
//
//	    double[] wheelSpeeds = new double[4];
//	    wheelSpeeds[MotorType.kFrontLeft.value] = input.x + input.y + z;
//	    wheelSpeeds[MotorType.kFrontRight.value] = input.x - input.y + z;
//	    wheelSpeeds[MotorType.kRearLeft.value] = -input.x + input.y + z;
//	    wheelSpeeds[MotorType.kRearRight.value] = -input.x - input.y + z;
//
//	    normalize(wheelSpeeds);
//
//	    frontLeftMotor.set(wheelSpeeds[MotorType.kFrontLeft.value] * maxOutput);
//	    frontRightMotor.set(wheelSpeeds[MotorType.kFrontRight.value] * maxOutput);
//	    backLeftMotor.set(wheelSpeeds[MotorType.kRearLeft.value] * maxOutput);
//	    backRightMotor.set(wheelSpeeds[MotorType.kRearRight.value] * maxOutput);
//		
//	}
//	
//	protected static void normalize(double[] wheelSpeeds) {
//	    double maxMagnitude = Math.abs(wheelSpeeds[0]);
//	    for (int i = 1; i < wheelSpeeds.length; i++) {
//	      double temp = Math.abs(wheelSpeeds[i]);
//	      if (maxMagnitude < temp) {
//	        maxMagnitude = temp;
//	      }
//	    }
//	    if (maxMagnitude > 1.0) {
//	      for (int i = 0; i < wheelSpeeds.length; i++) {
//	        wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
//	      }
//	    }
//	  }
//	
//    public void initDefaultCommand() {
//        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new Drive());
//    }

	    

