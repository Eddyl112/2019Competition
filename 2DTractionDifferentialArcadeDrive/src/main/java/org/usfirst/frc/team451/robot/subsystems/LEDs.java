/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class LEDs extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  static SerialPort teensySerial;

  enum State {
    disabled, waiting, crash;
  }

  static State state = State.disabled;

  public LEDs() {
    try {
      teensySerial = new SerialPort(9600, Port.kUSB1);
      state = State.waiting;
    } catch (Exception e) {
    }
  }

  

  public static void crash() {
    try {
      teensySerial.writeString("crash\n");
      teensySerial.flush();
      state = State.crash;

    } catch (Exception e) {}
  }

  

  public static void init() {
    try {
      teensySerial.writeString("init\n");
      teensySerial.flush();
      state = State.disabled;
    } catch (Exception e) {
    }
  }

  public static void disabled() {
    try {
      teensySerial.writeString("di\n");
      teensySerial.flush();
      state = State.disabled;
    } catch (Exception e) {
    }
  }


  public static void enabled() {
    try {
      teensySerial.writeString("en\n");
      teensySerial.flush();
      state = State.crash;
    } catch (Exception e) {
    }
  }

  public static void red() {
    try {
      teensySerial.writeString("red\n");
      teensySerial.flush();
      state = State.disabled;
    } catch (Exception e) {
    }
  }

  public static void blu() {
    try {
      teensySerial.writeString("blu\n");
      teensySerial.flush();
      state = State.disabled;
    } catch (Exception e) {
    }
  }

  void sendColor() {
    if (DriverStation.getInstance().getAlliance() == Alliance.Red) {
      red();
    } else {
      blu();
    }
  }

  class LedThread implements Runnable {
    boolean running = true;

    @Override
    public void run() {
      while (running) {
        if (teensySerial.getBytesReceived() > 0) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } // waiting for entire string to be read
          String string = teensySerial.readString();
          string = string.trim();
          System.out.println(string);
          if (string.equals("whatsmystate")) {
            if (state == State.crash) {
              sendColor();
              enabled();
            } else if (state == State.waiting) {
              init();
            } else if (state == State.disabled) {
              disabled();
            }
          }
        }
      }
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }
}
