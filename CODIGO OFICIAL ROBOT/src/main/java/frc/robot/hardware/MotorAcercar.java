package frc.robot.hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class MotorAcercar {
    private static WPI_TalonSRX MotorAcercar;
    public static DigitalInput switchElevador;
    public MotorAcercar (){
        MotorAcercar = new WPI_TalonSRX(Constantes.ConexionMotorAcercar);
        switchElevador = new DigitalInput(0);
    }
    public void moverMotor(double Speed){
        MotorAcercar.set(ControlMode.PercentOutput, Speed);
    }
    public void pararMotor(){
        MotorAcercar.stopMotor();
        MotorAcercar.setVoltage(0);
    }
    public boolean leerSwitchElevador(){
        return (!switchElevador.get());
    }
}