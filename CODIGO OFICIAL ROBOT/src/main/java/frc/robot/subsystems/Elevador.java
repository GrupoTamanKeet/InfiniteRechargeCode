package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.Constantes;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;

public class Elevador{
    public static WPI_TalonSRX MotorDeslizadorLineal;
    public static WPI_TalonSRX MotorJalar;
    static Solenoid piston1;
    static Solenoid piston2;
    int forwardChannel = 00; //PONER VALORES/
    int reverseChannel = 00; //PONER VALORES/
    int moduleNumber = 00;

    public Elevador(){
        piston1 = new Solenoid(forwardChannel);
        piston2 = new Solenoid(forwardChannel);
        MotorDeslizadorLineal = new WPI_TalonSRX(Constantes.MotorDeslizadorLineal);
        MotorJalar = new WPI_TalonSRX(Constantes.MotorJalar);
    }
    
    private void subirElevador(){
        MotorDeslizadorLineal.set(ControlMode.PercentOutput,1); 
    }
    
    private void bajarElevador(){
        MotorDeslizadorLineal.set(ControlMode.PercentOutput,-0.2); 
    }
    
    private void pararElevador(){
        MotorDeslizadorLineal.stopMotor();
        MotorDeslizadorLineal.setVoltage(0);
    }

    private void subirRobot(){
        if (Robot.control.readJoystickButtons(8)){
            MotorJalar.set(ControlMode.PercentOutput,1); 
        }
        else{
            MotorJalar.set(ControlMode.PercentOutput,0);
        }
    }


    private void abrirPiston(){
        piston1.set(true);
    }
    
    private void cerrarPiston (){
        piston2.set(true);

    }
    
    public void funcionar(){
        if (Robot.control.readJoystickButtons(Constantes.LG_B8)){
            abrirPiston();
        }
        if ((Robot.control.readJoystickButtons(Constantes.LG_B9) == true) && (Robot.control.readJoystickButtons(Constantes.LG_B_Reverse))){
            cerrarPiston();
        }
        subirElevador();
        subirRobot();

    }

}