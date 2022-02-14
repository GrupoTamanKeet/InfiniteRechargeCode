package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.hardware.*;
import frc.robot.Robot;
import java.util.concurrent.TimeUnit;


public class Hopper {
    TalonSRX HopperTalon = new TalonSRX(7);

public void HopperTest(){
    if (Robot.control.readPS4Buttons(Constantes.XB_B_Y)){
        HopperTalon.set(ControlMode.PercentOutput, 0.4);
    }
    else if(Robot.control.readPS4Buttons(Constantes.XB_B_A)){
        HopperTalon.set(ControlMode.PercentOutput, -0.4);
    }
    else if(Robot.control.readJoystickButtons(Constantes.LG_B1)){
        HopperTalon.set(ControlMode.PercentOutput, -0.4);
        try{
            Thread.sleep(750);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
        HopperTalon.set(ControlMode.PercentOutput, -0.4);

    }
    else{
        HopperTalon.set(ControlMode.PercentOutput,0.0);
    }

    
}

}
