package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.hardware.Constantes;
import frc.robot.hardware.Control;

public class Intake{
    static WPI_TalonSRX MotorIntake;
    static WPI_TalonSRX MotorAcercar;
    Control ctrl;

    public Intake(Control ControlRobot) {
        MotorIntake = new WPI_TalonSRX(Constantes.ConexionPosicionIntake);
        MotorAcercar = new WPI_TalonSRX(Constantes.ConexionPosicionAcercar);
        this.ctrl = ControlRobot;
    }
    
    public void activarIntake(){
        MotorIntake.set(ControlMode.PercentOutput,1);
    }

    public void desactivarIntake(){
        MotorIntake.set(ControlMode.PercentOutput,0);
    }

    public void activarAcercar(){
        MotorIntake.set(ControlMode.PercentOutput,1);
    }

    public void desactivarAcercar(){
        MotorIntake.set(ControlMode.PercentOutput,0);
    }
}


