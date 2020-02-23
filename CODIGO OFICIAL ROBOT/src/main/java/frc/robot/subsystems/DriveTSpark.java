package frc.robot.subsystems;

//imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;
import frc.robot.hardware.Gyro;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTSpark {

    //variables públicas
    public static CANSparkMax motorDrivetrain1, motorDrivetrain2, motorDrivetrain3, motorDrivetrain4;
    public SpeedControllerGroup motoresDerecha, motoresIzquierda;
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

      motorDrivetrain1.restoreFactoryDefaults();
      motorDrivetrain2.restoreFactoryDefaults();
      motorDrivetrain3.restoreFactoryDefaults();
      motorDrivetrain4.restoreFactoryDefaults();

      motorDrivetrain1.setMotorType(CANSparkMaxLowLevel.kBrushless);

      motorDrivetrain1.setInverted(true);
      motorDrivetrain3.setInverted(true);

      motoresDerecha = new SpeedControllerGroup(motorDrivetrain1, motorDrivetrain2);
      motoresIzquierda = new SpeedControllerGroup(motorDrivetrain3, motorDrivetrain4);

      driveTrain = new DifferentialDrive(motoresIzquierda, motoresDerecha);
      LeftJoystick = new SlewRateLimiter(1.75);

      rotate = new Gyro(this);
      //RightJoystick= new SlewRateLimiter(2.25);

    }

    //Se va a ocupar esta función para el driveTrain
    public void moverseConXbox() { // funcion principal del movimiento del chasis
        // inputs del control para movimiento
        movimientoAdelanteY = -Robot.control.readXboxAxis(Constantes.XB_RT)
                + Robot.control.readXboxAxis(Constantes.XB_LT); // toma el valor para ir hacia adelante o hacia atras

        movimientoAdelanteX = Robot.control.readXboxAxis(Constantes.XB_LJ_X) * movimientoAdelanteY
                * Constantes.controlSensivilidadDrive // toma una funcion para saber cuanto giro deberia de tener el robot y que sirva mejor
                - Robot.control.readXboxAxis(Constantes.XB_RJ_X); // Toma input raw y lo suma a lo que va a girar para que sea solo una funcion 
                // nota que esta separada en 3 lineas, pero en verdad es solamente 1
    
        if (Robot.control.readXboxButtons(Constantes.controlDrift)){ // controla el drift del drive, para que pueda dar vueltas mas cerradas
          movimientoAdelanteX = movimientoAdelanteX * Constantes.controlSensivilidadDrift;
        }
    
        if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
          if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
          else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
        }

        // System.out.println(Robot.control.readXboxDPad()); // BORRAR

        if (Robot.control.readXboxDPad() >= 0){
          rotate.regresarAngulo(Robot.control.readXboxDPad());
        }else rotate.leer();
        
        driveTrain.arcadeDrive(LeftJoystick.calculate(movimientoAdelanteY), movimientoAdelanteX);
    }

    public void moverseConXboxOriginal() { // funcion principal del movimiento del chasis

      // inputs del control para movimiento
      movimientoAdelanteY = -Robot.control.readXboxAxis(Constantes.XB_RT)
              + Robot.control.readXboxAxis(Constantes.XB_LT); // toma el valor para ir hacia adelante o hacia atras

      movimientoAdelanteX = Robot.control.readXboxAxis(Constantes.XB_LJ_X) * movimientoAdelanteY
              * Constantes.controlSensivilidadDrive // toma una funcion para saber cuanto giro deberia de tener el robot y que sirva mejor
              - Robot.control.readXboxAxis(Constantes.XB_RJ_X); // Toma input raw y lo suma a lo que va a girar para que sea solo una funcion 
              // nota que esta separada en 3 lineas, pero en verdad es solamente 1
  
      if (Robot.control.readXboxButtons(Constantes.controlDrift)){ // controla el drift del drive, para que pueda dar vueltas mas cerradas
        movimientoAdelanteX = movimientoAdelanteX * Constantes.controlSensivilidadDrift;
      }
  
      if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
        if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
        else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
      }

      if (Robot.control.readXboxDPad() >= 0){
        rotate.regresarAngulo(Robot.control.readXboxDPad());
      }else rotate.leer();
      
      driveTrain.arcadeDrive(LeftJoystick.calculate(movimientoAdelanteY), movimientoAdelanteX);

  }
    public void movimientoDrivetrainFinal(){
      double eje;
      if(Robot.control.readXboxButtons(Constantes.XB_B_Y)){
        eje = 2;
      }
      else{
        eje = .75;
      }
     
      driveTrain.arcadeDrive(Robot.control.readXboxAxis(Constantes.XB_LJ_Y), Robot.control.readXboxAxis(Constantes.XB_LJ_Y) * Robot.control.readXboxAxis(Constantes.XB_RJ_X) * eje);

    }

    public void moverseParaChio(){
      // coso de giro a pos

      if (Robot.control.readXboxDPad() >= 0){
        rotate.regresarAngulo(Robot.control.readXboxDPad());
      }else rotate.leer();

      // tankdrive

      driveTrain.tankDrive(-Robot.control.readXboxAxis(1), -Robot.control.readXboxAxis(5));
    }

    public void moverseParaRafa() { // funcion principal del movimiento del chasis
      // inputs del control para movimiento
      movimientoAdelanteY = -Robot.control.readXboxAxis(Constantes.XB_LJ_Y); // toma el valor para ir hacia adelante o hacia atras

      if (Robot.control.readXboxAxis(Constantes.XB_RJ_X) != 0){
        movimientoAdelanteX = Robot.control.readXboxAxis(Constantes.XB_RJ_X) * movimientoAdelanteY
              * Constantes.controlSensivilidadDrive;
      }else movimientoAdelanteX = Robot.control.readXboxAxis(Constantes.XB_RJ_X);
  
      if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
        if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
        else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
      }

      // System.out.println(Robot.control.readXboxDPad()); // BORRAR

      if (Robot.control.readXboxDPad() >= 0){
        rotate.regresarAngulo(Robot.control.readXboxDPad());
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