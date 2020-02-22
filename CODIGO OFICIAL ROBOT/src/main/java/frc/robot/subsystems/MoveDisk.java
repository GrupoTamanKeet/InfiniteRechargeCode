package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.ColorSensor;
import frc.robot.hardware.Constantes;

import sun.nio.ch.DirectBuffer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
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
        encendido = false;
        motorDisco.setNeutralMode(NeutralMode.Brake);
    }

    private void moverDisco(double speed){
        motorDisco.set(ControlMode.PercentOutput, speed);
    }
    private void reverseMoverDisco(double speed){
        motorDisco.set(ControlMode.PercentOutput, -speed);
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
    
    private void spin (int cambiosDeColor, boolean forward, double speed){
        if ( !(ultimoColorLeido.equalsIgnoreCase(colorSensor.leerColor())) ){
            cambioDeColor ++;
            ultimoColorLeido = colorSensor.leerColor();
        }
        if (cambioDeColor == cambiosDeColor){ //
            pararMotor();
            encendido = false;
        }else{
            if(forward){
                moverDisco(speed);
            }else{
                reverseMoverDisco(speed);
            }
            
        }
    }

    public void selectedColor (){
        char colorDeseado = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
        char currentColor = colorSensor.leerColor().charAt(0);
        int goToPosition = 0, myPosition = 0;
        String [] colores = {'R','G','B','Y'};
        for (int x= 0; x<colores.length; x++){
            if(colorDeseado.equals(colores[x])){
                goToPosition = x+1;
            }
        }
        for (int x= 0; x<colores.length; x++){
            if(currentColor.equals(colores[x])){
                myPosition = x+1;
            }
        }
        
        int places = goToPosition-myPosition;
        if (places<0){
            spin(places,false, 0.2);
        }else{
            spin(places,true, 0.2);
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
            spin(24, true, 0.4);
        }else{
            pararMotor();
        }
        colorSensor.leerColor();
    }
}