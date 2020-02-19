package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.Constantes;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Elevador{
    public static WPI_TalonSRX MotorDeslizadorLineal;
    public static WPI_TalonSRX MotorJalar;
    public static DoubleSolenoid piston1;
 
    //Compressor compresora;
    int abierto = 0;
    int cerrado = 0;
    int forwardChannel = 1; //PONER VALORES/
    int reverseChannel = 2; //PONER VALORES/

    public Elevador(){
        //No es necesario tener la compresora
        //compresora = new Compressor(Constantes.ConexionCompresor);
        //compresora.setClosedLoopControl(true);
        
        piston1 = new DoubleSolenoid(Constantes.ConexionCompresor,forwardChannel,reverseChannel);
        MotorDeslizadorLineal = new WPI_TalonSRX(Constantes.MotorDeslizadorLineal);
        MotorJalar = new WPI_TalonSRX(Constantes.MotorJalar);
        MotorDeslizadorLineal.setNeutralMode(NeutralMode.Brake);
        //cerrarPiston();
    }
    
    private void subirElevador(){
        MotorDeslizadorLineal.set(ControlMode.PercentOutput,.4); 
    }
    
    private void bajarElevador(){
        MotorDeslizadorLineal.set(ControlMode.PercentOutput,-0.2); 
    }
    
    private void pararElevador(){
        MotorDeslizadorLineal.stopMotor();
        MotorDeslizadorLineal.setVoltage(0);
    }

    private void subirRobot(){
        if (Robot.control.readXboxButtons(Constantes.XB_B_X)){
            MotorJalar.set(ControlMode.PercentOutput,.5); 
        }
        else{
            MotorJalar.set(ControlMode.PercentOutput,0);
        }
    }


    private void abrirPiston(){
        piston1.set(Value.kForward);
    }
    
    private void cerrarPiston (){
        piston1.set(Value.kReverse);
    }
    
    public void funcionar(){
        //Cambar al control de Logitech
        if(Robot.control.readXboxButtons(Constantes.XB_B_A)){
            abierto++;
            //subirElevador();
            abrirPiston();
        }
        else if(Robot.control.readXboxButtons(Constantes.XB_B_B)){
            cerrado++;
            //bajarElevador();
            cerrarPiston();
        }
        else{
            pararElevador();
        }
        if(Robot.control.readXboxButtons(Constantes.XB_B_Y)==true){
            abrirPiston();
        }
     
        if(Robot.control.readXboxButtons(Constantes.XB_B_Back)){
            cerrarPiston();
        }
        
        subirRobot();


    }

}