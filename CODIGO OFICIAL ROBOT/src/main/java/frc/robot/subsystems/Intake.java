package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.hardware.Constantes;
import frc.robot.Robot;

public class Intake{
    private static WPI_TalonSRX MotorIntake;

    public Intake(){
        MotorIntake = new WPI_TalonSRX(Constantes.ConexionMotorIntake);
        MotorIntake.setInverted(true);
    }

    public void activarIntake(){
        MotorIntake.set(ControlMode.PercentOutput, 0.4);
    }

    private void reverseIntake(){
        MotorIntake.set(ControlMode.PercentOutput, -0.4);
    }

    public void desactivarIntake(){
        MotorIntake.stopMotor();
        MotorIntake.setVoltage(0);
    }

    public void pararTodo(){
        desactivarIntake();
    }

    public void funcionar(){
        if (Robot.control.readXboxButtons(6)) { // hice cambios aqui jorge, todo bien 
            activarIntake();
        }else if (Robot.control.readXboxButtons(5)){ // podemos regresar a constantes. cosas
            //intentar dejar en el intake
            reverseIntake();
        }else{
            desactivarIntake();
        }
    }
}