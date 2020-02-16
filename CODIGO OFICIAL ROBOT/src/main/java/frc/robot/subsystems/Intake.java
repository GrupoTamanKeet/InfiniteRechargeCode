package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.hardware.Constantes;
import frc.robot.Robot;

public class Intake{
    static WPI_TalonSRX MotorIntake;
    static WPI_TalonSRX MotorEntregar;

    public Intake(){
          MotorIntake = new WPI_TalonSRX(Constantes.ConexionMotorIntake);
          MotorEntregar = new WPI_TalonSRX(Constantes.ConexionMotorAcercar);
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

    private void activarAcercar(){
        MotorEntregar.set(ControlMode.PercentOutput, 0.5);
    }

    private void reverseAcercar(){
        MotorEntregar.set(ControlMode.PercentOutput, -0.5);
    }

    private void desactivarAcercar(){
        MotorEntregar.stopMotor();
        MotorEntregar.setVoltage(0);
    }
    
    private void dejarEnIntake(){
        //Pelota en el limbo
        //Boton 9
    }

    private void secuenciaIntake(){
        //meter y Acercar 
        activarIntake();
        activarAcercar();

    }

    public void funcionar(){
        
        if (Robot.control.readJoystickButtons(Constantes.LG_B6)){
            secuenciaIntake();
        }else{
            if (Robot.control.readJoystickButtons(Constantes.LG_B2)) {
                activarIntake();
            }else if (Robot.control.readJoystickButtons(Constantes.LG_B2) && Robot.control.readJoystickButtons(Constantes.LG_B_Reverse)){
                reverseIntake();
            }else{
                desactivarIntake();
            }
    
            if (Robot.control.readJoystickButtons(Constantes.LG_B8)){
                activarAcercar();
            }else if (Robot.control.readJoystickButtons(Constantes.LG_B8) && Robot.control.readJoystickButtons(Constantes.LG_B_Reverse)){
                reverseAcercar();
            }else{
                desactivarAcercar();
            }
        }  
    }
}