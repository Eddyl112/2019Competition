/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
package org.usfirst.frc.team451.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.commands.ElevatorMove;

//import edu.wpi.first.wpilibj.GenericHID.Hand;
//import org.usfirst.frc.team451.robot.commands.ElevatorMove;

//import org.usfirst.frc.team451.robot.OI;
//import org.usfirst.frc.team451.robot.commands.Drive;
import org.usfirst.frc.team451.robot.Robot;
//import org.usfirst.frc.team451.robot.commands.ElevatorMove;

import edu.wpi.first.wpilibj.DigitalInput;

//import com.ctre.phoenix.motorcontrol.can.*;

//import edu.wpi.first.wpilibj.GenericHID.Hand;
//import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	public static double elevatorHeightMargin = 0.5;// in inches

	//lowest position of the elevator
	public static double minHeight = 19;//inches

	//heights for the center of the hatch panel spots on the rocket
	static double firstHatchHeight = 12 + 7;// 19 inches
	static double distancebetweenH = 2 * 12 + 4;// 28 inches
	public static double[] HatchHeights = { firstHatchHeight, firstHatchHeight + distancebetweenH,
			firstHatchHeight + 2 * distancebetweenH };

	// heights for the center of the cargo spots
	static double firstPortHeight = 2*12+3.5;//27.5 inches
	static double distancebetweenP = 2*12+4;//28 inches
	public static double[] PortHeights = {firstPortHeight, firstPortHeight+distancebetweenP, firstPortHeight+2*distancebetweenP};

	public static double countsPerRevolution = 1024; //counts per motor revolution based on specifications for mag encoder
	public static double gearReduction = 1; //motor revolutions per wheel revolutions
	public static double WheelDiameter = 1.7284; //inches
	public static double inchesPerCount = (Math.PI*WheelDiameter)/(countsPerRevolution*gearReduction); //the amount of inches that are covered in one count

	public static double TargetHeightInInches = HatchHeights[0];
	public static double TargetHeightInTicks = (TargetHeightInInches-minHeight)/(inchesPerCount);

	public static double[] ypr = new double[3];
	public static DigitalInput elevatorZero = new DigitalInput(3);

	//talon
	public static WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(3);

	//is the elevator at the right height? Only set to false when the user has set input
	public static boolean atProperHeight = false;

	
    public Elevator() {
		elevatorMotor.setInverted(true);
		elevatorMotor.setSelectedSensorPosition(0, 0, 30);
		elevatorMotor.config_kP(0, 1.1);
		elevatorMotor.config_kD(0, 1);
		elevatorMotor.config_kF(0, 0.2);
		elevatorMotor.config_kI(0, 0);
		elevatorMotor.config_IntegralZone(0, 0);
    }
 
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	//NO METHODS USED -- CHECK ElevatorMove.java FOR COMMAND CODE
	
	/** Runs the user override commands from the x-box controller. PrintMethodData prints 
	 * information to the console.
	 */
	/*
	public static void RunUserOverride(double UserInput, boolean PrintMethodData){
		if (UserInput > Robot.ElevatorUserOverrideDeadzone/100) {
			elevatorMotor.set(-1);
			TargetHeight=inchesPerCount*elevatorMotor.getSelectedSensorPosition();
			if(PrintMethodData) System.out.println("Elevator UP (user)");
		   } else if (UserInput < -Robot.ElevatorUserOverrideDeadzone/100) {
			 elevatorMotor.set(1);
			 TargetHeight = inchesPerCount*elevatorMotor.getSelectedSensorPosition();
    			if(PrintMethodData) System.out.println("Elevator DOWN (user)");
		   }
	}

	// public static void speedControl() {
		
	// 	elevatorMotor.set(ControlMode.MotionMagic, 4096*2);
	// 	//this runs the motor at 102.4 ticks per 100ms in velocity control mode
	// }

	/** Moves elevator based on TargetHeight (inches). PrintMethodData prints 
	 * information to the console.
	 */
	// public static void MoveTowards(double TargetHeight, boolean PrintMethodData){
	// 	if(elevatorMotor.getSelectedSensorPosition() > (TargetHeight+elevatorHeightMargin-minHeight)/inchesPerCount){
	// 		elevatorMotor.set(1);
	// 		if(PrintMethodData) System.out.println("Elevator DOWN (preset)");
	// 	}

	// 	if(elevatorMotor.getSelectedSensorPosition() < (TargetHeight-elevatorHeightMargin-minHeight)/inchesPerCount){
	// 		elevatorMotor.set(-1);
	// 		if(PrintMethodData) System.out.println("Elevator UP (preset)");
	// 	}
	// }


    
	@Override
	protected void initDefaultCommand() {
		
		setDefaultCommand(new ElevatorMove());
		
	}

	public static void moveToPosition(double position) {
		elevatorMotor.set(ControlMode.MotionMagic, position);
	}
}
 

