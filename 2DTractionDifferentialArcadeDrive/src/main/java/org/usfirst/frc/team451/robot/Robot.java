/*******************************************************************************
 * Copyright (c) 2018 Edward Lui. All Rights Reserved.
 *******************************************************************************/
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot;

import com.ctre.phoenix.sensors.PigeonIMU;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team451.robot.commands.SystemCheck;
//import org.usfirst.frc.team451.robot.subsystems.CameraServo;
import org.usfirst.frc.team451.robot.subsystems.Claw;
import org.usfirst.frc.team451.robot.subsystems.Climber;
import org.usfirst.frc.team451.robot.subsystems.DriveTrain;
import org.usfirst.frc.team451.robot.subsystems.Elevator;
import org.usfirst.frc.team451.robot.subsystems.LEDs;
import org.usfirst.frc.team451.robot.subsystems.LineTracker;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static DriveTrain myDriveTrain = new DriveTrain();
	public static Claw myClaw = new Claw();
	public static LineTracker myLineTracker = new LineTracker();
	//public static CameraServo myCameraServo = new CameraServo();
	public static Elevator myElevator = new Elevator();
	public static OI myOI;
	//public static ADXRS450_Gyro myGyro;
	public static PigeonIMU myGyro;
	public static Climber myClimber = new Climber();
	public static LEDs myLeds = new LEDs();
	//public static SystemCheck mySystemCheck;
	
	Thread m_visionThread;

	//SmartDashboard Editable variables
	Preferences prefs;
	public static double UserAssistCorrectionSpeed;
	public static double ElevatorUserOverrideDeadzone;

	//Autonomous
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//Initialize gyro and OI
		//myGyro = new ADXRS450_Gyro();
		myGyro = new PigeonIMU(1);
		myOI = new OI();
		OI.init();

		//SmartDashboard editable variables
		prefs = Preferences.getInstance();
		UserAssistCorrectionSpeed = prefs.getDouble("UserAssistCorrectionSpeed (%)", 3);
		ElevatorUserOverrideDeadzone = prefs.getDouble("Deadzone for X-Box Elevator User Override (%)", 3);
		
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
	
		// new Thread(() -> {
		// 	UsbCamera USBcamera = CameraServer.getInstance().startAutomaticCapture();
		// 	USBcamera.setResolution(426, 240);
		// 	//AxisCamera axisCamera = CameraServer.getInstance().addAxisCamera("axis-camera.local");
		// 	// Set the resolution
		// 	//axisCamera.setResolution(640, 480);
		// 	CvSink cvSink = CameraServer.getInstance().getVideo();
		// 	CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 426,
		// 	240);
		// 	Mat source = new Mat();
		// 	Mat output = new Mat();
		// 	while(true) {
		// 	cvSink.grabFrame(source);
		// 	Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
		// 	outputStream.putFrame(output);
		// 	}
		// 	}).start();
		}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		LEDs.disabled();

	}

	@Override
	public void disabledPeriodic() {
		LEDs.disabled();
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		short[] data = new short[3]; 
		myGyro.getBiasedAccelerometer(data);
		if(data[0] > 5 || data[1] > 5 || data[2] > 5) {
			LEDs.crash();
		} else {
			LEDs.enabled();
		}
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	//mySystemCheck = new SystemCheck();
		
	}
}
