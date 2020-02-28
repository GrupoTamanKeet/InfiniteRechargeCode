package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Torreta  {
 
    private static WPI_TalonSRX MotorDisparar;
    private static WPI_TalonSRX MotorSusana;
    private static WPI_TalonSRX MotorAngulo;
    private static WPI_VictorSPX MotorSubir;

    private static SlewRateLimiter smooth;

    private NetworkTableInstance inst;
    private NetworkTable table;
    private NetworkTableEntry xEntry;

    private float moverse;
    private float sensibilidad = 0.1f;

    //comentado porque tenemos encoder
    //private AnalogGyro gyro;
    private double anguloDeLaTorreta;
    private double moverTorreta;
    private double sensibilidadDeTorreta = 0.01;

    private Solenoid Luz;

    public Torreta(){
        MotorDisparar = new WPI_TalonSRX(Constantes.ConexionMotorTorreta);

        //Están en pulgadas
        //distancePerPulse2 = (8*Math.PI)/175;

        MotorSusana = new WPI_TalonSRX(Constantes.ConexionMotorSusana);
        MotorAngulo = new WPI_TalonSRX(Constantes.ConexionMotorAngulo);
        MotorSubir = new WPI_VictorSPX(Constantes.ConexionMotorSubir);

        MotorSubir.setInverted(true);
        MotorDisparar.setInverted(false);
        MotorAngulo.setInverted(true);
        
        MotorAngulo.setNeutralMode(NeutralMode.Brake);

        smooth = new SlewRateLimiter(.8);

        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("Vision");
        xEntry = table.getEntry("torretaX");
        
        Luz = new Solenoid(Constantes.ConexionCompresor, Constantes.ConexionLuz);   
    }

    private void acomodarSusanaAutimaticamente(){
        Luz.set(true);

        xEntry = table.getEntry("torretaX");
        
        System.out.println(xEntry.toString());
        
        moverse = (float) (xEntry.getDouble(0.0) * sensibilidad);

        acomodarSusana(moverse);
    }

    private void pidAngulo(int angulo){
        //comentado porque ahora tenemos un encoder
        //anguloDeLaTorreta = gyro.getAngle();
        moverTorreta = (anguloDeLaTorreta - angulo) / sensibilidadDeTorreta;
        acomodarAngulo(moverTorreta);
    }
    
    public void secuenciaDisparar(){
        prepararDisparo(); 
        subirPelota();
    }

    //  ____                                                  
    // /\  _`\                    __                          
    // \ \ \L\ \     __      ____/\_\    ___    ___     ____  
    //  \ \  _ <'  /'__`\   /',__\/\ \  /'___\ / __`\  /',__\ 
    //   \ \ \L\ \/\ \L\.\_/\__, `\ \ \/\ \__//\ \L\ \/\__, `\
    //    \ \____/\ \__/.\_\/\____/\ \_\ \____\ \____/\/\____/
    //     \/___/  \/__/\/_/\/___/  \/_/\/____/\/___/  \/___/ 
    private void acomodarSusana(double Speed){
        if((Speed<=0.3) && (Speed>-0.3)){
            desactivarSusana();
            return;
        }else{
            MotorSusana.set(ControlMode.PercentOutput,Speed*2);
        }        
    }

    private void desactivarSusana(){
        MotorSusana.stopMotor();
        MotorSusana.setVoltage(0);
    }

    public void activarAcercar(){
        Robot.motorAcercar.moverMotor(0.6);
    }

    private void reverseAcercar(){
        Robot.motorAcercar.moverMotor(-0.5);
    }

    private void desactivarAcercar(){
        Robot.motorAcercar.pararMotor();
    }

    private void acomodarAngulo(double Speed){
        if((Speed<=0.2) && (Speed>=-0.2)){
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

    public void subirPelota(){
        MotorSubir.set(ControlMode.PercentOutput, 0.7);
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

//       ___                                                               
//     /'___\                         __                                   
//    /\ \__/  __  __    ___     ___ /\_\    ___     ___      __     _ __  
//    \ \ ,__\/\ \/\ \ /' _ `\  /'___\/\ \  / __`\ /' _ `\  /'__`\  /\`'__\
//     \ \ \_/\ \ \_\ \/\ \/\ \/\ \__/\ \ \/\ \L\ \/\ \/\ \/\ \L\.\_\ \ \/ 
//      \ \_\  \ \____/\ \_\ \_\ \____\\ \_\ \____/\ \_\ \_\ \__/.\_\\ \_\ 
//       \/_/   \/___/  \/_/\/_/\/____/ \/_/\/___/  \/_/\/_/\/__/\/_/ \/_/ 
                                                                        
public void funcionar(){
    //Shoot
    if(Robot.control.readJoystickButtons(Constantes.LG_B2)){
        secuenciaDisparar();
        if(Robot.control.readJoystickButtons(Constantes.LG_B1)){
            activarAcercar();
        }else{
            desactivarAcercar();
            desactivarSubirPelota();
        }
    }else if (Robot.control.readJoystickButtons(Constantes.LG_B11)){
        reverseSubirPelota();
        reverseAcercar();
    }else{
        pararTodo();
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
  