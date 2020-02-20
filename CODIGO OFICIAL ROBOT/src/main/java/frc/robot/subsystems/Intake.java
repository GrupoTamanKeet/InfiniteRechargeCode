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
        int miliseconds = (int) (System.currentTimeMillis()%10000)/100;
        if (miliseconds%3==0){
            MotorIntake.set(ControlMode.PercentOutput, -0.4);
        }else{
            MotorIntake.set(ControlMode.PercentOutput, 0);
        }
    }
    
    /* Ya no es necesario
    private void activarAcercar() {
        int miliseconds = (int) (System.currentTimeMillis()%10000)/100;
        if (miliseconds%2==0){
            Robot.motorAcercar.moverMotor(.4);
          }else{
            Robot.motorAcercar.moverMotor(0);
          }
    }
    */

    /* Ya no es necesario
    private void secuenciaIntake(){
        //meter y Acercar 
        activarIntake();
        activarAcercar();

    }
    */

    public void funcionar(){
            if (Robot.control.readXboxButtons(Constantes.XB_B_RB)) {
                activarIntake();
            }else if (Robot.control.readXboxButtons(Constantes.XB_B_Start)){
                //intentar dejar en el intake
                reverseIntake();
            }else{
                desactivarIntake();
            }    
    }
}