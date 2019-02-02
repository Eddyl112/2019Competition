package org.usfirst.frc.team451.robot.subsystems;
import org.usfirst.frc.team451.robot.commands.Drive;
import org.usfirst.frc.team451.robot.Robot;
import org.usfirst.frc.team451.robot.OI;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.Command;



public class Elevator extends Subsystem {


    public static WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(1);
    
    static SpeedControllerGroup Left = new SpeedControllerGroup(elevatorMotor);

    @Override
    protected void initDefaultCommand() {

    }
}
 



