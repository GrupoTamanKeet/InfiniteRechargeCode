package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.ColorSensor;
import frc.robot.hardware.Constantes;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class MoveDisk{

    public static DoubleSolenoid pistonDisco;
    public static WPI_TalonSRX motorDisco;
    private ColorSensor colorSensor;
    
    private int cambioDeColor;
    private int ForwardChannel = 0;
    private int BackWardChannel = 7;
    private boolean encendido;
    private String ultimoColorLeido;

    public MoveDisk (){
        pistonDisco = new DoubleSolenoid(Constantes.ConexionCompresor,ForwardChannel, BackWardChannel);
        motorDisco = new WPI_TalonSRX(Constantes.ConexionMotorDisco);
        colorSensor = new ColorSensor();
        encendido= false;
        motorDisco.setNeutralMode(NeutralMode.Brake);
    }

    private void moverDisco(){
        motorDisco.set(ControlMode.PercentOutput, .4);
    }

    private void abrirPiston() {
        pistonDisco.set(Value.kForward);
    }

    private void cerrarPiston(){
        pistonDisco.set(Value.kReverse);

    }

    private void pararMotor(){
        motorDisco.stopMotor();
        motorDisco.setVoltage(0);
    }
    
    private void spin (){
        //One spin is equal to every second time you see the same color. 
        
        if ( !(ultimoColorLeido.equalsIgnoreCase(colorSensor.leerColor())) ){
            cambioDeColor ++;
            ultimoColorLeido = colorSensor.leerColor();
        }
        if (cambioDeColor == 24){ //
            pararMotor();
            encendido = false;
        }else {
            moverDisco();
        }

    }
    public void funcionar(){
        if (Robot.control.readJoystickButtons(Constantes.LG_B6)){
            abrirPiston();
        }
        if (Robot.control.readJoystickButtons(Constantes.LG_B5)){
            cerrarPiston();
        }
        if (Robot.control.readJoystickButtons(Constantes.LG_B3)){
            cambioDeColor = 0;   
            encendido = true;
            ultimoColorLeido = colorSensor.leerColor();
        }
        if (encendido){
            spin();
        }else{
            pararMotor();
        }
        colorSensor.leerColor();
    }
}