/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team451.robot.subsystems;

import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.commands.LineSensor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Sensor extends Subsystem {
  /* These are all of the Line Sensors. They use the DigitalInputs because they are
  not actually normal line sensors, but more like on/off switches. centerLineSensor 
  is the sensor in the center, rightLineSensor is the sensor toward the right of the
  bot, and leftLineSensor is the sensor toward the left of the bot. */

  public static String[] SensorLabels = {"Left","Center","Right"};
  
  public static DigitalInput[] Sensors = {new DigitalInput(0),new DigitalInput(1),new DigitalInput(2)};

  //Sensor currently on the line
  public static DigitalInput ActiveSensor;

  public static boolean[] tripped = new boolean[2];
  public static int trippedCount = 0;

  //Sensor position on bot based on the intitial position of the bot (facing forward)(0=x, 1=y)
  public static final float[][] botPos = new float[2][1];

  //Essentially the location of the sensor relative to the user, if the user was standing on the robot(given the robots current rotation relative to the field)(0=x,1=y)
  public static float[][] fieldPos = new float[2][1];

  //Updates the positions of the sensor, factoring in the "rotation of the robot" (theta)
  public void updateSensorPositions(double theta){
    for(int i=0;i<Sensors.length;i++){
      fieldPos[i][0] = (float)((double) botPos[i][0]*Math.cos(theta)-(double) botPos[i][1]*Math.sin(theta));
      fieldPos[i][1] = (float)((double) botPos[i][1]*Math.cos(theta)+(double) botPos[i][0]*Math.sin(theta));
    }
  }

  //Trip a sensor when it's over a line, and set the intitial position for the delta variable
  public void handleSensorActivations(){
    trippedCount = 0;
    //Recognize trips
    for(int i=0;i<Sensors.length;i++){
      if(Sensors[i].get()) {
        tripped[i] = true;
        trippedCount++;
      }
      //Set intitial delta position when the first sensor is tripped.
      else if(!tripped[(i+1)%Sensors.length] || !tripped[(i+1)%Sensors.length]) {
        Robot.DriveTrain.deltaPosition[0] = -fieldPos[i][0];
        Robot.DriveTrain.deltaPosition[1] = -fieldPos[i][1];
        ActiveSensor = Sensors[i];
      }
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new LineSensor());
  }
}
