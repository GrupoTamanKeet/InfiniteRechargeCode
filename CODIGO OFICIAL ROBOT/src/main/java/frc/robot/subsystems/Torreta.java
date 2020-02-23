package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogGyro;

public class Torreta  {
 
    private static WPI_TalonSRX MotorDisparar;
    private static WPI_TalonSRX MotorSusana;
    private static WPI_TalonSRX MotorAngulo;
    private static WPI_TalonSRX MotorSubir;

    private static SlewRateLimiter smooth;

    private NetworkTableInstance inst;
    private NetworkTable table;
    private NetworkTableEntry xEntry;

    private float moverse;
    private float sensibilidad = 0.1f;

    private AnalogGyro gyro;
    private double anguloDeLaTorreta;
    private double moverTorreta;
    private double sensibilidadDeTorreta = 0.01;

    public Torreta(){
        MotorDisparar = new WPI_TalonSRX(Constantes.ConexionMotorTorreta);

        MotorSusana = new WPI_TalonSRX(Constantes.ConexionMotorSusana);
        MotorAngulo = new WPI_TalonSRX(Constantes.ConexionMotorAngulo);
        MotorSubir = new WPI_TalonSRX(Constantes.ConexionMotorSubir);
        
        MotorDisparar.setInverted(false);
        MotorAngulo.setInverted(true);
        
        MotorAngulo.setNeutralMode(NeutralMode.Brake);

        smooth = new SlewRateLimiter(.8);

        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("Vision");
        xEntry = table.getEntry("torretaX");
        
        gyro = new AnalogGyro(0);
    }

    private void acomodarSusana(double Speed){
        if(Speed==0){
            desactivarSusana();
            return;
        }
        MotorSusana.set(ControlMode.PercentOutput,Speed*2);
    }

    private void acomodarSusanaAutimaticamente(){
        
        xEntry = table.getEntry("torretaX");
        
        System.out.println("Xpos " + xEntry.getDouble(0.0));
        
        moverse = (float) (xEntry.getDouble(0.0) * sensibilidad);

        acomodarSusana(moverse);
    }

    private void pidAngulo(int angulo){
        anguloDeLaTorreta = gyro.getAngle();
        
        moverTorreta = (anguloDeLaTorreta - angulo) / sensibilidadDeTorreta;

        acomodarAngulo(moverTorreta);
    }
    
    private void desactivarSusana(){
        MotorSusana.stopMotor();
        MotorSusana.setVoltage(0);
    }

    private void activarAcercar(){
        Robot.motorAcercar.moverMotor(0.6);
    }

    private void activarAcercarLento(){
        Robot.motorAcercar.moverMotor(0.4);
    }


    private void reverseAcercar(){
        Robot.motorAcercar.moverMotor(-0.5);
    }

    private void desactivarAcercar(){
        Robot.motorAcercar.pararMotor();
    }

    private void acomodarAngulo(double Speed){
        if(Speed==0){
            desactivarAngulo();
            return;
        }
        MotorAngulo.set(ControlMode.PercentOutput,Speed);
    }
    
    private void desactivarAngulo(){
        MotorAngulo.stopMotor();
        //No queremos que tenga voltaje 0 para que no cambie la posición
        //BreakMode ya está
    }

    private void subirPelota(){
        MotorSubir.set(ControlMode.PercentOutput, 0.7);
    }

    private void subirPelotaLento(){
        MotorSubir.set(ControlMode.PercentOutput, 0.4);
    }

    private void reverseSubirPelota(){
        MotorSubir.set(ControlMode.PercentOutput, -0.5);
    }

    private void desactivarSubirPelota(){
        MotorSubir.stopMotor();
        MotorSubir.setVoltage(0);
    }

    private void prepararDisparo(){
        //diferencia entre verde y subir
        //Lo hace con el SlowRate
        MotorDisparar.set(ControlMode.PercentOutput, smooth.calculate(1));

    }
    
    private void desactivarDisparo(){
        MotorDisparar.stopMotor();
        MotorDisparar.setVoltage(0);
    }

    public void pararTodo(){
        desactivarAcercar();
        desactivarAngulo();
        desactivarDisparo();
        desactivarSubirPelota();
        desactivarSusana();
    }

    private void secuenciaDisparar(){

        prepararDisparo(); 
        
        // if (miliseconds%3==0){
        //     activarAcercar();
        //     desactivarSubirPelota();
        // }else{
        //     desactivarAcercar();
        //     subirPelota();
        // }
    }

    public void funcionar(){
        //Shoot
        if(Robot.control.readJoystickButtons(Constantes.LG_B2)){
            secuenciaDisparar();
            if(Robot.control.readJoystickButtons(Constantes.LG_B1)){
                activarAcercar();
                subirPelota();
            }else if(!Robot.motorAcercar.leerSwitchElevador()){
                activarAcercarLento();
                subirPelotaLento();
            }else{
                desactivarAcercar();
                desactivarSubirPelota();
            }
        }else if (Robot.control.readJoystickButtons(Constantes.LG_B5)){
            reverseSubirPelota();
        }else if (Robot.control.readJoystickButtons(Constantes.LG_B6)){
            reverseAcercar();
        }else if (Constantes.meterBolaAlFinal && !Robot.motorAcercar.leerSwitchElevador()) {
            activarAcercarLento();
            subirPelotaLento();
        }else if (Robot.motorAcercar.leerSwitchElevador() && Constantes.meterBolaAlFinal ){
            Constantes.meterBolaAlFinal = false;
        }else{
            pararTodo();
        }   

        //contador de pelotas (por si)
        if (Robot.motorAcercar.leerSwitchElevador() && !Constantes.hayBolaEnDisparo){
            Constantes.hayBolaEnDisparo = true;
            System.out.println("entro bola");
        }else if (!Robot.motorAcercar.leerSwitchElevador() && Constantes.hayBolaEnDisparo){
            Constantes.hayBolaEnDisparo = false;
            Constantes.bolasDentro --;
            System.out.println("salio bola");
        }

        //Shake it
        if (Robot.control.readJoystickButtons(Constantes.LG_B12)){
            pararTodo();
            Robot.dTrain.destravarse();
        }

        acomodarSusana(Robot.control.readJoystickAxis(Constantes.LG_ZJ));
        acomodarAngulo(Robot.control.readJoystickAxis(Constantes.LG_YJ));

    }

    public boolean pasar1Atras(){
        return (false); // en un futuro servira para poder succionar una pelota 
    }

    public void funcionarParaGulle(){
        //Shoot
        if(Robot.control.readJoystickButtons(Constantes.LG_B2)){
            secuenciaDisparar();
            if(Robot.control.readJoystickButtons(Constantes.LG_B1)){
                activarAcercar();
                subirPelota();
            }
        }else if (Robot.control.readJoystickDPad() == 180){
            reverseSubirPelota();
            reverseAcercar();
        }//else if (Robot.control.readXboxButtons(Constantes.XB_B_RB) && Robot.motorAcercar.leerSwitchElevador()) {
        //    Robot.motorAcercar.moverMotor(0.3);
        // }
        else{
            pararTodo();
        }   

        //contador de pelotas (por si)
        if (Robot.motorAcercar.leerSwitchElevador() && !Constantes.hayBolaEnDisparo){
            Constantes.hayBolaEnDisparo = true;
            System.out.println("entro bola");
        }else if (!Robot.motorAcercar.leerSwitchElevador() && Constantes.hayBolaEnDisparo){
            Constantes.hayBolaEnDisparo = false;
            Constantes.bolasDentro --;
            System.out.println("salio bola");
        }

        //Shake it
        if (Robot.control.readJoystickButtons(Constantes.LG_B12)){
            pararTodo();
            Robot.dTrain.destravarse();
        }

        acomodarSusana(Robot.control.readJoystickAxis(Constantes.LG_ZJ));
        acomodarAngulo(Robot.control.readJoystickAxis(Constantes.LG_YJ));

    }

}
  