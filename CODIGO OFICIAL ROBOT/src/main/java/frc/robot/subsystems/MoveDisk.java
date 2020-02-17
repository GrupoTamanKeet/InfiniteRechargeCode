package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.ColorSensor;
import frc.robot.hardware.Constantes;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class MoveDisk{

    public static DoubleSolenoid pistondisco;
    public static WPI_TalonSRX motordisco;
    private I2C.Port i2cPort = I2C.Port.kOnboard;
    private ColorSensor colorSensor;
    
    int ForwardChannel = 3;


    public MoveDisk (){
        pistondisco = new DoubleSolenoid(Constantes.ConexionPistonDisco,ForwardChannel);
        motordisco = new WPI_TalonSRX(Constantes.ConexionMotorDisco);
        colorSensor = new ColorSensor();
    }
    public void MoverDisco(){
        motordisco.set(ControlMode.PercentOutput, .5);
    }

    public void AbrirPiston() {
        pistondisco.set(Value.kForward);
    }

    public void CerrarPiston(){
        pistondisco.set(Value.kReverse);

    }
    public void funcionar(){
        if (Robot.control.readJoystickButtons(Constantes.LG_B3)== true){
            MoverDisco();
        
        }
        colorSensor.leerColor();
    }

    public void spin (int laps){

    }

    public void PonerColor(){
        
    }

}