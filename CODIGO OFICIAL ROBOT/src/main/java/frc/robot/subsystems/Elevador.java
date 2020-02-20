package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.Constantes;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;

public class Elevador{
    public static WPI_TalonSRX MotorDeslizadorLineal;
    public static WPI_TalonSRX MotorJalar;
    public static Solenoid piston1;

    public Elevador(){
        //No es necesario declarar la compresora
        //compresora = new Compressor(Constantes.ConexionCompresor);
        //compresora.setClosedLoopControl(true);
        
        piston1 = new Solenoid(Constantes.ConexionCompresor,Constantes.ConexionPistonDisco);
        MotorDeslizadorLineal = new WPI_TalonSRX(Constantes.MotorDeslizadorLineal);
        MotorJalar = new WPI_TalonSRX(Constantes.MotorColgar);
        
        MotorDeslizadorLineal.setNeutralMode(NeutralMode.Brake);
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
        piston1.set(true);
    }
    
    private void cerrarPiston (){
        piston1.set(false);
    }
    
    public void funcionar(){
        //Cambar al control de Logitech
        if(Robot.control.readJoystickButtons(Constantes.LG_B7)){
            abrirPiston();
        }
        if(Robot.control.readJoystickButtons(Constantes.LG_B8)){
            subirElevador();
        }else if(Robot.control.readJoystickButtons(Constantes.LG_B10)){
            bajarElevador();
        }else{
            pararElevador();
        }
     
        if(Robot.control.readJoystickButtons(Constantes.LG_B9)){
            cerrarPiston();
        }
        
        subirRobot(); //Mapear al otro control


    }

}