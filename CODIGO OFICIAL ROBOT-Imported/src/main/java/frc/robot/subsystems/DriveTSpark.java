package frc.robot.subsystems;

//imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;
import frc.robot.hardware.Gyro;

public class DriveTSpark {

    //variables públicas
    public static CANSparkMax motorDrivetrain1, motorDrivetrain2, motorDrivetrain3, motorDrivetrain4;
    public static DifferentialDrive driveTrain;

    private static double movimientoAdelanteX;
    private static double movimientoAdelanteY;

    SlewRateLimiter LeftJoystick;
    SlewRateLimiter RightJoystick;

    private Gyro rotate;
    //variables privadas

    public DriveTSpark(){

      motorDrivetrain1 = new CANSparkMax(1,MotorType.kBrushless);
      motorDrivetrain2 = new CANSparkMax(2,MotorType.kBrushless);
      motorDrivetrain3 = new CANSparkMax(3,MotorType.kBrushless);
      motorDrivetrain4 = new CANSparkMax(4,MotorType.kBrushless);

      //el motor 2 de la derecha sigue al 1
      motorDrivetrain2.follow(motorDrivetrain1);
      //el motor 3 de la izquierda sigue al 4
      motorDrivetrain3.follow(motorDrivetrain4);

      //No es necesario volver a poner el tipo de motor. :)!

      motorDrivetrain1.setInverted(true);
      motorDrivetrain3.setInverted(true);

      driveTrain = new DifferentialDrive(motorDrivetrain4, motorDrivetrain1);
      LeftJoystick = new SlewRateLimiter(1.75);

      rotate = new Gyro(this);
      //RightJoystick= new SlewRateLimiter(2.25);

    }

    //Se va a ocupar esta función para el driveTrain
    public void moverseConPS4() { // funcion principal del movimiento del chasis
        // inputs del control para movimiento
        movimientoAdelanteY = -Robot.control.readPS4Axis(Constantes.XB_RT)
                + Robot.control.readPS4Axis(Constantes.XB_LT); // toma el valor para ir hacia adelante o hacia atras

        movimientoAdelanteX = Robot.control.readPS4Axis(Constantes.XB_LJ_X) * movimientoAdelanteY
                * Constantes.controlSensivilidadDrive // toma una funcion para saber cuanto giro deberia de tener el robot y que sirva mejor
                - Robot.control.readPS4Axis(Constantes.XB_RJ_X); // Toma input raw y lo suma a lo que va a girar para que sea solo una funcion 
                // nota que esta separada en 3 lineas, pero en verdad es solamente 1
    
        if (Robot.control.readPS4Buttons(Constantes.controlDrift)){ // controla el drift del drive, para que pueda dar vueltas mas cerradas
          movimientoAdelanteX = movimientoAdelanteX * Constantes.controlSensivilidadDrift;
        }
    
        if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
          if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
          else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
        }

        // System.out.println(Robot.control.readXboxDPad()); // BORRAR

        if (Robot.control.readPS4DPad() >= 0){
          rotate.regresarAngulo(Robot.control.readPS4DPad());
        }else rotate.leer();
        
        driveTrain.arcadeDrive(LeftJoystick.calculate(movimientoAdelanteY), movimientoAdelanteX);
    }

    public void girarAAnguloParaNoComplicarCosasLoHiceAqui(int angulo){
      rotate.regresarAngulo(angulo);
    }

    public void moverseConXboxOriginal() { // funcion principal del movimiento del chasis

      // inputs del control para movimiento
      movimientoAdelanteY = -Robot.control.readPS4Axis(Constantes.XB_RT)
              + Robot.control.readPS4Axis(Constantes.XB_LT); // toma el valor para ir hacia adelante o hacia atras

      movimientoAdelanteX = Robot.control.readPS4Axis(Constantes.XB_LJ_X) * movimientoAdelanteY
              * Constantes.controlSensivilidadDrive // toma una funcion para saber cuanto giro deberia de tener el robot y que sirva mejor
              - Robot.control.readPS4Axis(Constantes.XB_RJ_X); // Toma input raw y lo suma a lo que va a girar para que sea solo una funcion 
              // nota que esta separada en 3 lineas, pero en verdad es solamente 1
  
      if (Robot.control.readPS4Buttons(Constantes.controlDrift)){ // controla el drift del drive, para que pueda dar vueltas mas cerradas
        movimientoAdelanteX = movimientoAdelanteX * Constantes.controlSensivilidadDrift;
      }
  
      if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
        if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
        else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
      }

      if (Robot.control.readPS4DPad() >= 0){
        rotate.regresarAngulo(Robot.control.readPS4DPad());
      }else rotate.leer();
      
      driveTrain.arcadeDrive(LeftJoystick.calculate(movimientoAdelanteY), movimientoAdelanteX);

  }
    public void movimientoDrivetrainFinal(){
            
      if(Math.abs(Robot.control.readPS4Axis(Constantes.XB_LJ_Y)) > .1){
      
        driveTrain.arcadeDrive(Robot.control.readPS4Axis(Constantes.XB_LJ_Y), Robot.control.readPS4Axis(Constantes.XB_LJ_Y) * Robot.control.readPS4Axis(2) * .8);

      } else if(Robot.control.readPS4Axis(Constantes.XB_LJ_Y) < Math.abs(.1)){

        driveTrain.arcadeDrive(0, -Robot.control.readPS4Axis(2) * .5);

      } else{

        driveTrain.arcadeDrive(Robot.control.readPS4Axis(Constantes.XB_LJ_Y), Robot.control.readPS4Axis(Constantes.XB_LJ_Y) * Robot.control.readPS4Axis(2) * .8);

      }
      
    }

    public void moverseParaChio(){
      // coso de giro a pos

      if (Robot.control.readPS4DPad() >= 0){
        rotate.regresarAngulo(Robot.control.readPS4DPad());
      }else rotate.leer();

      // tankdrive

      driveTrain.tankDrive(-Robot.control.readPS4Axis(1), -Robot.control.readPS4Axis(5));
    }

    public void moverseParaRafa() { // funcion principal del movimiento del chasis
      // inputs del control para movimiento
      movimientoAdelanteY = -Robot.control.readPS4Axis(Constantes.XB_LJ_Y); // toma el valor para ir hacia adelante o hacia atras

      if (Robot.control.readPS4Axis(Constantes.XB_RJ_X) != 0){
        movimientoAdelanteX = Robot.control.readPS4Axis(Constantes.XB_RJ_X) * movimientoAdelanteY
              * Constantes.controlSensivilidadDrive;
      }else movimientoAdelanteX = Robot.control.readPS4Axis(Constantes.XB_RJ_X);
  
      if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
        if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
        else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
      }

      // System.out.println(Robot.control.readXboxDPad()); // BORRAR

      if (Robot.control.readPS4DPad() >= 0){
        rotate.regresarAngulo(Robot.control.readPS4DPad());
      }else rotate.leer();
      
      driveTrain.arcadeDrive(LeftJoystick.calculate(movimientoAdelanteY), movimientoAdelanteX);
  }

    public void moverseConPiloto(){ // funcion opcional del movimiento del chasis 

        // inputs del control para movimiento
        movimientoAdelanteY = Robot.control.readJoystickAxis(Constantes.LG_YJ); // toma el valor para ir hacia adelante o hacia atras 

        movimientoAdelanteX = Robot.control.readJoystickAxis(Constantes.LG_XJ)*movimientoAdelanteY*Constantes.controlSensivilidadDrive // toma una funcion para saber cuanto giro deberia de tener el robot y que sirva mejor
                + Robot.control.readJoystickAxis(Constantes.LG_ZJ); // Toma input raw y lo suma a lo que va a girar para que sea solo una funcion 
                // nota que esta separada en 2 lineas, pero en verdad es solamente 1

        if (Robot.control.readJoystickButtons(Constantes.controlDrift)){ // controla el drift del drive, para que pueda dar vueltas mas cerradas
        movimientoAdelanteX = movimientoAdelanteX * Constantes.controlSensivilidadDrift;
        }

        if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
        if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
        else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
        }

        driveTrain.arcadeDrive(movimientoAdelanteY,movimientoAdelanteX);

    }

    public void destravarse(){
      int miliseconds = (int) (System.currentTimeMillis()%10000)/100;
      if (miliseconds%2==0){
        driveTrain.arcadeDrive(0, 0.4);
      }else{
        driveTrain.arcadeDrive(0, -0.4);
      }
    }

    public void driveDelArcade(double adelante, double vuelta){
      driveTrain.arcadeDrive(adelante,vuelta);
    }
}
