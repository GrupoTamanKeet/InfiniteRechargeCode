package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.hardware.*;
import frc.robot.Robot;

public class Intake {
    TalonSRX testTalon = new TalonSRX(8);
public void IntakeTest(){

    if (Robot.control.readPS4Buttons(Constantes.XB_B_X)){
        testTalon.set(ControlMode.PercentOutput, 0.3);
    }
    else if(Robot.control.readPS4Buttons(Constantes.XB_B_B)){
        testTalon.set(ControlMode.PercentOutput, -0.25);
    }
    else{
        testTalon.set(ControlMode.PercentOutput,0.0);
    }

}
    
}
