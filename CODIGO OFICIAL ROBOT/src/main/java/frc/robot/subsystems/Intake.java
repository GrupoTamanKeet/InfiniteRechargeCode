package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.hardware.Constantes;
import frc.robot.Robot;

public class Intake{
    static WPI_TalonSRX MotorIntake;
    
    public static DigitalInput switchIntake1;
    public static DigitalInput switchIntake2;

    public Intake(){
          MotorIntake = new WPI_TalonSRX(Constantes.ConexionMotorIntake);

          switchIntake1 = new DigitalInput(1);
          switchIntake2 = new DigitalInput(2);

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

    public boolean leerSwitches(){

        return ((!(switchIntake1.get()) || !(switchIntake2.get())));

    }

    public void meterBolaYContar(){
        if (leerSwitches() && !Constantes.hayBolaEnIntake){
            Constantes.bolasDentro ++;
            Constantes.hayBolaEnIntake = true;
            if (Constantes.bolasDentro == 1){
                Constantes.meterBolaAlFinal = true;
            }
        }else if (!leerSwitches()){
            Constantes.hayBolaEnIntake = false;
        }

        System.out.println (Constantes.bolasDentro);


    }

    public void funcionar(){
            if (Robot.control.readXboxButtons(Constantes.XB_B_RB)) {
                activarIntake();
            }else if (Robot.control.readXboxButtons(Constantes.XB_B_Start)){
                //intentar dejar en el intake
                reverseIntake();
            }else if (Constantes.bolasDentro < 5 && leerSwitches()) {
                activarIntake();
            }else{
                desactivarIntake();
            } 
   
    }


    public void funcionarParaChio(){
        if (Robot.control.readXboxButtons(Constantes.XB_B_RB)) {
            activarIntake();
        }else if (Robot.control.readXboxButtons(Constantes.XB_B_LB)){
            //intentar dejar en el intake
            reverseIntake();
        }else{
            desactivarIntake();
        }  
    }
}