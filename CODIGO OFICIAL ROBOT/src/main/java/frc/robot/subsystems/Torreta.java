package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;


public class Torreta  {
 
    private static WPI_TalonSRX MotorDisparar;
    private static WPI_TalonSRX MotorSusana;
    private static WPI_TalonSRX MotorAngulo;
    private static WPI_TalonSRX MotorSubir;

    private static SlewRateLimiter smooth;

    public Torreta(){
        MotorDisparar = new WPI_TalonSRX(Constantes.ConexionMotorTorreta);

        MotorSusana = new WPI_TalonSRX(Constantes.ConexionMotorSusana);
        MotorAngulo = new WPI_TalonSRX(Constantes.ConexionMotorAngulo);
        MotorSubir = new WPI_TalonSRX(Constantes.ConexionMotorSubir);
        MotorDisparar.setInverted(false);
        MotorAngulo.setInverted(true);

        smooth = new SlewRateLimiter(.8);
    }

    private void acomodarSusana(double Speed){
        if(Speed==0){
            desactivarSusana();
            return;
        }
        MotorSusana.set(ControlMode.PercentOutput,Speed*2);
    }

    private void acomodarSusanaAutimaticamente(){
        System.out.println("Aun no se hace");
    }
    
    private void desactivarSusana(){
        MotorSusana.stopMotor();
        MotorSusana.setVoltage(0);
    }

    private void activarAcercar(){
        Robot.motorAcercar.moverMotor(0.5);
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

    private void acomodarAnguloAutimaticamente(){
        System.out.println("Aun no se hace");
    }
    
    private void desactivarAngulo(){
        MotorAngulo.stopMotor();
        //No queremos que tenga voltaje 0 para que no cambie la posición
    }

    private void subirPelota(){
        MotorSubir.set(ControlMode.PercentOutput, 0.4);
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

    private void pararTodo(){
        desactivarAcercar();
        desactivarAngulo();
        desactivarDisparo();
        desactivarSubirPelota();
        desactivarSusana();
    }

    private void secuenciaDisparar(){
        //Activar acercar y subir
        //Boton 5
        prepararDisparo();
        subirPelota();
        int miliseconds = (int) (System.currentTimeMillis()%10000)/100;
        if (miliseconds%2==0){
            activarAcercar();
        }else{
            desactivarAcercar();
        }
        //Mover el robot
        //Robot.dTrain.destravarse();
    }

    public void funcionar(){

            if(Robot.control.readJoystickAxis(Constantes.LG_Slider)==-1){
                prepararDisparo();
            }else{
                desactivarDisparo();
            }

            if(Robot.control.readJoystickButtons(Constantes.LG_B2)){
                subirPelota();
            }else{
                desactivarSubirPelota();
            }

            if (Robot.control.readJoystickButtons(Constantes.LG_B12)){
                pararTodo();
                Robot.dTrain.destravarse();
            }

            if (Robot.control.readJoystickButtons(Constantes.LG_B1)){
                activarAcercar();
            }else if (Robot.control.readJoystickButtons(Constantes.LG_B1) && Robot.control.readJoystickButtons(Constantes.LG_B_Reverse)){
                reverseAcercar();
            }else{
                desactivarAcercar();
            }

            acomodarSusana(Robot.control.readJoystickAxis(Constantes.LG_ZJ));
            acomodarAngulo(Robot.control.readJoystickAxis(Constantes.LG_YJ));

    }

}

