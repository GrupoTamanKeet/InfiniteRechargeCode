package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.Constantes;
import frc.robot.hardware.Controles;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Elevador{
    private static WPI_TalonSRX MotorDeslizadorLineal;
    private static WPI_TalonSRX MotorJalar;
    private static DoubleSolenoid piston1;

    public Elevador(){
        //No es necesario declarar la compresora
        //compresora = new Compressor(Constantes.ConexionCompresor);
        //compresora.setClosedLoopControl(true);
        int ForwardChannel = 2;
        int BackwardChannel = 1;
        piston1 = new DoubleSolenoid(Constantes.ConexionCompresor,ForwardChannel,BackwardChannel);
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
            MotorJalar.set(ControlMode.PercentOutput,1); 
        }
        else if(Robot.control.readXboxButtons(Constantes.XB_B_Y)){
            MotorJalar.set(ControlMode.PercentOutput,-1);
        }
        else{


                MotorJalar.set(0);

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
        if(Robot.control.readJoystickButtons(Constantes.LG_B7)){
            abrirPiston();
        }
        if(Robot.control.readJoystickButtons(Constantes.LG_B8)){
            Robot.torreta.pararTodo();
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