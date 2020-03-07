package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.Constantes;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Elevador{
    private static WPI_TalonSRX MotorDeslizadorLineal;
    private static WPI_TalonSRX MotorJalar;
    private static DoubleSolenoid piston1;
    private int ForwardChannel = 2;
    private int BackwardChannel = 1;

    Boolean funcional;
    Boolean pistonAbajo;
    int waaaaa;
    
    public Elevador(){
        //No es necesario declarar la compresora
        piston1 = new DoubleSolenoid(Constantes.ConexionCompresor,ForwardChannel,BackwardChannel);
        MotorDeslizadorLineal = new WPI_TalonSRX(Constantes.MotorDeslizadorLineal);
        MotorJalar = new WPI_TalonSRX(Constantes.MotorColgar);
        
        funcional = false;
        pistonAbajo = false;
        waaaaa = 0;
        MotorDeslizadorLineal.setNeutralMode(NeutralMode.Brake);
    }
    
    private void subirElevador(){
        waaaaa++;
        MotorDeslizadorLineal.set(ControlMode.PercentOutput,.5); 
    }
    
    private void bajarElevador(){
        waaaaa--;
        MotorDeslizadorLineal.set(ControlMode.PercentOutput,-0.2); 
    }
    
    private void pararElevador(){
        MotorDeslizadorLineal.stopMotor();
    }

    private void abrirPiston(){
        piston1.set(Value.kForward);
    }
    
    private void cerrarPiston (){
        piston1.set(Value.kReverse);
    }

    private void pararCasiTodo(){
        Robot.controlPanel.pararTodo();
        Robot.torreta.pararTodo();
        Robot.motorAcercar.pararMotor();
        Robot.intake.desactivarIntake();
    
    }
    
    public void funcionar(){
        
        if(Robot.control.readJoystickButtons(Constantes.LG_B7)){
            abrirPiston();
        }
        
        if(Robot.control.readJoystickButtons(Constantes.LG_B9)){
            cerrarPiston();
        }
        if(Robot.control.readJoystickButtons(Constantes.LG_B8)){
            pararCasiTodo();
            subirElevador();
        }else if(Robot.control.readJoystickButtons(Constantes.LG_B10)){
            pararCasiTodo();
            bajarElevador();
        }else{
            pararElevador();
        }
    
        if (Robot.control.readXboxButtons(Constantes.XB_B_X)){
            MotorJalar.setVoltage(12);
            pararCasiTodo();
            pararElevador();
            
            MotorJalar.set(ControlMode.PercentOutput, 1); 
        }
        else if(Robot.control.readXboxButtons(Constantes.XB_B_Y)){
            MotorJalar.set(ControlMode.PercentOutput,-1);
        }
        else{
            MotorJalar.set(ControlMode.PercentOutput,0);
        }
    }
}