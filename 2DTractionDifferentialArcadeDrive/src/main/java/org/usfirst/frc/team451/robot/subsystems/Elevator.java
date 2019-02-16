/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
package org.usfirst.frc.team451.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import org.usfirst.frc.team451.robot.OI;
import org.usfirst.frc.team451.robot.commands.ElevatorMove;

//import edu.wpi.first.wpilibj.GenericHID.Hand;
//import org.usfirst.frc.team451.robot.commands.ElevatorMove;

//import org.usfirst.frc.team451.robot.OI;
//import org.usfirst.frc.team451.robot.commands.Drive;
import org.usfirst.frc.team451.robot.Robot;
//import org.usfirst.frc.team451.robot.commands.ElevatorMove;

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

	public static double TargetHeight = HatchHeights[0];

	public static double countsPerRevolution = 1024; //counts per motor revolution based on specifications for mag encoder
	public static double gearReduction = 1; //motor revolutions per wheel revolutions
	public static double WheelDiameter = 1.7284; //inches
	public static double inchesPerCount = (Math.PI*WheelDiameter)/(countsPerRevolution*gearReduction); //the amount of inches that are covered in one count

	//talon
	public static WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(5);

	//is the elevator at the right height? Only set to false when the user has set input
	public static boolean atProperHeight = false;

    public Elevator() {
    }
 
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	/** Runs the user override commands from the x-box controller. PrintMethodData prints 
	 * information to the console.
	 */
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

	/** Moves elevator based on TargetHeight (inches). PrintMethodData prints 
	 * information to the console.
	 */
	public static void MoveTowards(double TargetHeight, boolean PrintMethodData){
		if(elevatorMotor.getSelectedSensorPosition() > (TargetHeight+elevatorHeightMargin-minHeight)/inchesPerCount){
			elevatorMotor.set(1);
			if(PrintMethodData) System.out.println("Elevator DOWN (preset)");
		}

		if(elevatorMotor.getSelectedSensorPosition() < (TargetHeight-elevatorHeightMargin-minHeight)/inchesPerCount){
			elevatorMotor.set(-1);
			if(PrintMethodData) System.out.println("Elevator UP (preset)");
		}
	}


    
	@Override
	protected void initDefaultCommand() {
		
		setDefaultCommand(new ElevatorMove());
		
	}
}
 

