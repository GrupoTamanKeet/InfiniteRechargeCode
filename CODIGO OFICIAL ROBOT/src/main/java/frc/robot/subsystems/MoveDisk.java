package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.ColorSensor;
import frc.robot.hardware.Constantes;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class MoveDisk{

    public static DoubleSolenoid pistondisco;
    public static WPI_TalonSRX motordisco;
    private ColorSensor colorSensor;
    
    int ForwardChannel = 0;
    int BackWardChannel = 7;

    public MoveDisk (){
        pistondisco = new DoubleSolenoid(Constantes.ConexionCompresor,ForwardChannel, BackWardChannel);
        motordisco = new WPI_TalonSRX(Constantes.ConexionMotorDisco);
        colorSensor = new ColorSensor();
    }
    public void moverDisco(){
        motordisco.set(ControlMode.PercentOutput, .5);
    }

    public void abrirPiston() {
        pistondisco.set(Value.kForward);
    }

    public void cerrarPiston(){
        pistondisco.set(Value.kReverse);

    }
    public void funcionar(){
        if (Robot.control.readJoystickButtons(Constantes.LG_B6)){
            abrirPiston();
        }
        if (Robot.control.readJoystickButtons(Constantes.LG_B5)){
            cerrarPiston();
        }
        if (Robot.control.readJoystickButtons(Constantes.LG_B3)){
            moverDisco();
        }else{
            pararMotor();
        }
        colorSensor.leerColor();
    }
    
    public void pararMotor(){
        motordisco.stopMotor();
        motordisco.setVoltage(0);
    }

    public void spin (int laps){
        //probar cuanto tiempo es necesario

    }


}