package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.hardware.Constantes;
import frc.robot.hardware.Control;

public class Intake{
    static WPI_TalonSRX MotorIntake;
    Control ctrl;

    public Intake(Control ControlRobot) {
        MotorIntake = new WPI_TalonSRX(Constantes.ConexionPosicionIntake);
        this.ctrl = ControlRobot;
    }
    
    public void activarTorreta(){
        MotorIntake.set(ControlMode.PercentOutput,1);
    }
    
    public void desactivarTorreta(){
        MotorIntake.set(ControlMode.PercentOutput,0);
    }
}


