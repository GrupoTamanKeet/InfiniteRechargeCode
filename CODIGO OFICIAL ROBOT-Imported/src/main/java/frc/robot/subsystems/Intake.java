package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.hardware.*;
import frc.robot.Robot;

public class Intake {
    TalonSRX testTalon = new TalonSRX(0);
public void IntakeTest(){
    if(Robot.control.readPS4Buttons(Constantes.XB_B_A)){
                   testTalon.set(ControlMode.PercentOutput, 1); 
    }
    else if(Robot.control.readPS4Buttons(Constantes.XB_B_Y)){
        testTalon.set(ControlMode.PercentOutput, 1);

    }
    else{
        testTalon.set(ControlMode.PercentOutput, 0.0);
    }
    



}


    
}
