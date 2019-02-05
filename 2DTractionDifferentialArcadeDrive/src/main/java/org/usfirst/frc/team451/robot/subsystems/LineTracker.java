/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;

import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.commands.AutoAlign;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * INFORM ALEX BEFORE MAKING ANY CHANGES TO THIS DOCUMENT
 */
public class LineTracker extends Subsystem {
  /* This class holds all the Line Sensors. They use the DigitalInputs because they are
  not actually normal line sensors, but more like on/off switches. centerLineSensor 
  is the sensor in the center, rightLineSensor is the sensor toward the right of the
  bot, and leftLineSensor is the sensor toward the left of the bot. */

  //VARIABLES FOR INDIVIDUAL SENSORS
  //sensors
  public static DigitalInput[] Sensors = {new DigitalInput(0),new DigitalInput(1),new DigitalInput(2)};

  //labels for sensors (identification purposes only)
  public static String[] SensorLabels = {"Left","Center","Right"};

  //boolean for whether or not the sensor has been tripped
  public static boolean[] tripped = new boolean[3];

  //sensor position on bot (position on the intitial position of the bot)(robot facing forward)(0=x, 1=y)
  public static final float[][] botPos = new float[3][2];

  //location of the sensor relative to the user (if the user was standing on the robot, given the robots current rotation relative to the field)(0=x,1=y)
  public static float[][] fieldPos = new float[3][2];

  //VARIABLES FOR THE ENTIRE TRACKER
  //location in array for Sensor currently over the line
  public static int ActiveSensorID;

  //total number of sensors that have been tripped
  public static int trippedCount = 0;

  //variable for a change in robot position since the first sensor was tripped
  public static float[] deltaPosition = new float[2];

  //variable storing the calculated points in space that lie on the line
  public static float[][] LinePoints = new float[2][2];

  //the angle of the line relative to the robot (set in )
  public static double idealRotation = 0;

  //the distance the encoder has traveled between startup and the last turn
  public static double encoderDistance = Robot.DriveTrain.frontLeftMotor.getSelectedSensorPosition();

  //METHODS
  //updates the positions of the sensor, factoring in the "rotation of the robot" (theta)
  public static void updateSensorFieldPositions(double botRotation){
    for(int i=0;i<Sensors.length;i++){
      fieldPos[i][0] = (float)((double) botPos[i][0]*Math.cos(botRotation)-(double) botPos[i][1]*Math.sin(botRotation));
      fieldPos[i][1] = (float)((double) botPos[i][1]*Math.cos(botRotation)+(double) botPos[i][0]*Math.sin(botRotation));
    }
  }

  //sets the intital position for delta (initial position of the robot center relative to a point on the line)
  public static void setInitDelta(int sensorID){
    deltaPosition[0] = -fieldPos[sensorID][0];
    deltaPosition[1] = -fieldPos[sensorID][1];
  }

  //update the delta position (untested)
  public static void updateDelta(){
    deltaPosition[0]+=((Robot.DriveTrain.frontLeftMotor.getSelectedSensorPosition()-encoderDistance)*Math.cos(Robot.gyro.getAngle()));
    deltaPosition[1]+=((Robot.DriveTrain.frontLeftMotor.getSelectedSensorPosition()-encoderDistance)*Math.sin(Robot.gyro.getAngle()));
    encoderDistance = Robot.DriveTrain.frontLeftMotor.getSelectedSensorPosition();
  }

  //trips sensors if they are active
  public static void tripActiveSensors(){
    for(int i=0;i<Sensors.length;i++){
      if(!Sensors[i].get()) {
        //only run the first time this is tripped
        if(tripped[i] == false){
          //increase the recorded number of tripped sensors
          trippedCount++;
          //set the initial delta value when the first sensor is tripped
          if(trippedCount == 1) setInitDelta(i);
          //set the required rotation of the robot when the second sensor is tripped
          if(trippedCount>2 && idealRotation != 0) setLinePositionsAndRotation();
          //record that this has launched (for debugging purposes)
          System.out.println(SensorLabels[i]+" Sensor tripped ("+trippedCount+" total sensors tripped) -- current roation: "+Math.round(Robot.gyro.getAngle()));
        }
        //record whether or not this specific sensor is tripped
        tripped[i] = true;
        //set the "active sensor" to the current sensor
        ActiveSensorID = i;
      }
    }
  }

  //resets tripped sensors (delta is reset during "setInitDelta")
  public static void resetSensorsAndDelta(){
    trippedCount = 0;
    for(int i=0;i<Sensors.length;i++){
      tripped[i] = false;
    }
  }

  //set the calculated points of the line in space (call this after the second sensor is tripped)
  public static void setLinePositionsAndRotation(){
    updateSensorFieldPositions(Robot.gyro.getAngle());

    //point 1, x-value
    LinePoints[0][0] = 0;

    //point 1, y-value
    LinePoints[0][0] = 0;

    //point 2, x-value
    LinePoints[1][0] = deltaPosition[0];

    //point 2, y-value
    LinePoints[1][1] = deltaPosition[1];

    //set the necessary rotation of the robot
    idealRotation = Math.atan2(LinePoints[1][1]-LinePoints[0][1],LinePoints[1][0]-LinePoints[0][0]);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new AutoAlign());
  }
}
