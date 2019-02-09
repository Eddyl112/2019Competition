/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
package org.usfirst.frc.team451.robot.subsystems;

import org.usfirst.frc.team451.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;


//shshs
public class DriveTrain extends Subsystem {
    public static Encoder encoderLeft = new Encoder(8,9);
    public static Encoder encoderRight = new Encoder(6,7);
    public static double countsPerRevolution = 1024;//counts per motor revolution
    public static double gearReduction = 19.8;//motor revolutions per wheel revolutions
    public static double WheelDiameter = 8;//inches
    public static double inchesPerCount = (Math.PI*WheelDiameter)/(countsPerRevolution*gearReduction);

    public static double[] wheelSpeed = {0,0};

	// Put methods for controlling this subsystem
    // // here. Call these from Commands.
	public double pGain = 1.0;
	public double iGain = 1.0;
	public double dGain = 1.0;
	public double fGain = 1.0;
	
    public static WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(1);
    //public static WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(3);
    static SpeedControllerGroup Left = new SpeedControllerGroup(frontLeftMotor);
    
    public static WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(2);
    //public static WPI_TalonSRX backRightMotor = new WPI_TalonSRX(4);
    static SpeedControllerGroup Right = new SpeedControllerGroup(frontRightMotor);
    
    //public static DifferentialDrive diffDrive = new DifferentialDrive(Left, Right);
    
    public DriveTrain() {
        SmartDashboard.putNumber("Encoder Left", encoderLeft.getDistance());
        SmartDashboard.putNumber("Encoder Right", encoderRight.getDistance());
        
        encoderLeft.setDistancePerPulse(inchesPerCount);
        encoderRight.setDistancePerPulse(inchesPerCount);
        //WPI_TalonSRX[] motorArray = new WPI_TalonSRX[] {frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor};
        // WPI_TalonSRX[] motorArray = new WPI_TalonSRX[] {frontLeftMotor, frontRightMotor};
    	// for(WPI_TalonSRX motor : motorArray) {
    	// 	motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
    	// 	motor.selectProfileSlot(0, 0);
    	// 	motor.config_kP(0, pGain, 0);
    	// 	motor.config_kI(0, iGain, 0);
    	// 	motor.config_kD(0, dGain, 0);
        //     motor.config_kF(0, fGain, 0);
            
        // }
    }
    
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
		
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

	    

