/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot;

import org.usfirst.frc.team451.robot.commands.ExtendMove;
import org.usfirst.frc.team451.robot.commands.GrabMove;
import org.usfirst.frc.team451.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
	public static Button climberPistonButton;
	public static Button climberStickthingButton;
	public static Button extendButton;
	public static DigitalInput clawSwitch;
	public static Button topHatch;
	public static Button override;
	public static Button elevatorReset;
	
	

	public static void init() {
		driveStickLeft = new Joystick(0); 
		driveStickRight = new Joystick(1);
		mechBox = new XboxController(2);
		elevatorReset = new JoystickButton(mechBox, 7);
		climberPistonButton = new JoystickButton(driveStickRight, 2);
		climberStickthingButton = new JoystickButton(driveStickRight, 4);
		openClawButton = new JoystickButton(mechBox, 5);
		extendButton = new JoystickButton(mechBox, 6);

		openClawButton.whenPressed(new GrabMove());
		extendButton.whenPressed(new ExtendMove());
		

		
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
