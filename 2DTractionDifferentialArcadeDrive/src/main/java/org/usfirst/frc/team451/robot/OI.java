/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot;


import org.opencv.imgproc.LineSegmentDetector;
import org.usfirst.frc.team451.robot.commands.CloseClaw;
import org.usfirst.frc.team451.robot.commands.OpenClaw;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick driveStickLeft;
	public static Joystick driveStickRight;
	public static XboxController mechBox;

	public static Button autoAlignOverrideButton;
	public static Button openClawButton;
	public static Button closeClawButton;
	public static DigitalInput clawSwitch;
	public static boolean clawActive;
	
//	public static boolean clawActive = false;
	
	

	public static void init() {
		driveStickLeft = new Joystick(0); 
		driveStickRight = new Joystick(1);
		mechBox = new XboxController(2);
		
		//autoAlignOverrideButton = new JoystickButton(driveStickLeft, 2);
		openClawButton = new JoystickButton(mechBox, 3);
		closeClawButton = new JoystickButton(mechBox, 5);
		mechBox.getRawAxis(1);
		openClawButton.whenPressed(new OpenClaw());
		closeClawButton.whenPressed(new CloseClaw());
	}
	
	
	
	
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
