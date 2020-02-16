package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;


public class Torreta  {
 
    private static WPI_TalonSRX MotorDisparar;
    private static WPI_TalonSRX MotorSusana;
    private static WPI_TalonSRX MotorAngulo;
    private static WPI_TalonSRX MotorSubir;

    private static boolean readyToShoot = false;

    public Torreta(){
        MotorDisparar = new WPI_TalonSRX(Constantes.ConexionMotorTorreta);
        MotorSusana = new WPI_TalonSRX(Constantes.ConexionMotorSusana);
        MotorAngulo = new WPI_TalonSRX(Constantes.ConexionMotorAngulo);
        MotorSubir = new WPI_TalonSRX(Constantes.ConexionMotorSubir);
        MotorDisparar.setInverted(true);
    }

    private void acomodarSusana(double Speed){
        if(Speed==0){
            desactivarSusana();
        }
        MotorSusana.set(ControlMode.PercentOutput,Speed);
    }

    private void acomodarSusanaAutimaticamente(){
        System.out.println("Aun no se hace");
    }
    
    private void desactivarSusana(){
        MotorSusana.stopMotor();
        MotorSusana.setVoltage(0);
    }


    private void acomodarAngulo(double Speed){
        if(Speed==0){
            desactivarAngulo();
        }
        MotorSusana.set(ControlMode.PercentOutput,Speed);
    }

    private void acomodarAnguloAutimaticamente(){
        System.out.println("Aun no se hace");
    }
    
    private void desactivarAngulo(){
        MotorAngulo.stopMotor();
        MotorAngulo.setVoltage(0);
    }

    private void subirPelota(){
        MotorSubir.set(ControlMode.PercentOutput, 0.4);

    }
    private void desactivarSubirPelota(){
        MotorSubir.stopMotor();
        MotorSubir.setVoltage(0);
    }

    private void prepararDisparo(){
        MotorDisparar.set(ControlMode.PercentOutput, 1);
    }
    
    private void desactivarDisparo(){
        MotorDisparar.stopMotor();
        MotorDisparar.setVoltage(0);
    }

    private void secuenciaDisparar(){
        //Activar acercar y subir
        //Mover el robot
        //Boton 5
        Robot.dTrain.destravarse();
    }

    public void funcionar(){
            if (Robot.control.readJoystickButtons(Constantes.LG_B7)){
                readyToShoot=!readyToShoot;
            }

            if(readyToShoot){
                prepararDisparo();
            }else{
                desactivarDisparo();
            }

            if(Robot.control.readJoystickButtons(Constantes.LG_B6)){
                subirPelota();
            }else{
                desactivarSubirPelota();
            }

            if (Robot.control.readJoystickButtons(Constantes.LG_B12)){
                Robot.dTrain.destravarse();
            }

            acomodarSusana(Robot.control.readJoystickAxis(Constantes.LG_ZJ));
            acomodarAngulo(Robot.control.readJoystickAxis(Constantes.LG_YJ));

    }

}

