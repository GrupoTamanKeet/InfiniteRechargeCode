package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.hardware.Constantes;
import frc.robot.hardware.Controles;

public class Intake{
    static WPI_TalonSRX MotorIntake;
    static WPI_TalonSRX MotorAcercar;
    Controles ctrl;

    public Intake() {
        MotorIntake = new WPI_TalonSRX(Constantes.ConexionPosicionIntake);
        MotorAcercar = new WPI_TalonSRX(Constantes.ConexionPosicionAcercar);
    }
    
    public void activarIntake(){
        
    }

    public void desactivarIntake(){
        
    }

    public void activarAcercar(){
        
    }

    public void desactivarAcercar(){
        
    }
}


