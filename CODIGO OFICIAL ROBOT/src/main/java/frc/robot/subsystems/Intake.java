package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.hardware.Constantes;
import frc.robot.Robot;

public class Intake{
    static WPI_TalonSRX MotorIntake;
    

    public Intake(){
          MotorIntake = new WPI_TalonSRX(Constantes.ConexionMotorIntake);
          MotorIntake.setInverted(true);
          
    }

    private void activarIntake(){
        MotorIntake.set(ControlMode.PercentOutput, 0.4);
    }

    private void reverseIntake(){
        MotorIntake.set(ControlMode.PercentOutput, -0.4);
    }

    private void desactivarIntake(){
        MotorIntake.stopMotor();
        MotorIntake.setVoltage(0);
    }

    
    
    private void dejarEnIntake(){
        //Pelota en el limbo
        //Boton 9
    }

    // private void secuenciaIntake(){
    //     //meter y Acercar 
    //     activarIntake();
    //     activarAcercar();

    // }

    public void funcionar(){
        
        
            if (Robot.control.readXboxButtons(Constantes.XB_B_RB)) {
                activarIntake();
            }else if (Robot.control.readXboxButtons(Constantes.XB_B_Start)){
                reverseIntake();
            }else{
                desactivarIntake();
            }
    
            
        
    }
}