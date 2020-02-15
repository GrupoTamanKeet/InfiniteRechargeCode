package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;

public class DriveTrain { 
    //mandar a Sofi
    //DriveMotors
    WPI_TalonSRX MotorDerecha1;
    WPI_TalonSRX MotorDerecha2;
    WPI_TalonSRX MotorIzquierda1;
    WPI_TalonSRX MotorIzquierda2;

    private static SpeedControllerGroup MotoresDriveDerecha;
    private static SpeedControllerGroup MotoresDriveIzquierda;
    public static DifferentialDrive MovimientoBaseCompleto;

    private static double movimientoAdelanteX;
    private static double movimientoAdelanteY; // private double movimientoAdelanteZ;

    //encoders de ambas líneas de motores
  private Encoder EncoderMotoresDerecha;
  private Encoder EncoderMotoresIzquierda;
  private double EncoderDerechoPos;
  private double EncoderIzquierdoPos; 
  private double EncoderPromDrive;

    public DriveTrain() {
      //En el Ponny, las conecciones son: 2,3,4,1
      MotorDerecha1 = new WPI_TalonSRX(Constantes.PosicionMotorDriveDerecha1);
      MotorDerecha2 = new WPI_TalonSRX(Constantes.PosicionMotorDriveDerecha2);
      MotorIzquierda1 = new WPI_TalonSRX(Constantes.PosicionMotorDriveIzquierda1);
      MotorIzquierda2 = new WPI_TalonSRX(Constantes.PosicionMotorDriveIzquierda2);

      MotoresDriveIzquierda = new SpeedControllerGroup(MotorIzquierda1, MotorIzquierda2);
      MotoresDriveDerecha = new SpeedControllerGroup(MotorDerecha1, MotorDerecha2);

      MovimientoBaseCompleto = new DifferentialDrive(MotoresDriveIzquierda, MotoresDriveDerecha);

      //pid
      EncoderMotoresDerecha = new Encoder(0,1);
      EncoderMotoresIzquierda = new Encoder(2,3);

      EncoderMotoresDerecha.setDistancePerPulse(4./10000.); //sensibilidad
      EncoderMotoresIzquierda.setDistancePerPulse(4./10000.);
    }

    public static void moverseConXbox() { // funcion principal del movimiento del chasis

        // inputs del control para movimiento
        movimientoAdelanteY = Robot.control.readXboxAxis(Constantes.XB_RT)
                - Robot.control.readXboxAxis(Constantes.XB_LT); // toma el valor para ir hacia adelante o hacia atras

        movimientoAdelanteX = Robot.control.readXboxAxis(Constantes.XB_LJ_X) * movimientoAdelanteY
                * Constantes.controlSensivilidadDrive // toma una funcion para saber cuanto giro deberia de tener el robot y que sirva mejor
                + Robot.control.readXboxAxis(Constantes.XB_RJ_X); // Toma input raw y lo suma a lo que va a girar para que sea solo una funcion 
                // nota que esta separada en 2 lineas, pero en verdad es solamente 1
    
        if (Robot.control.readXboxButtons(Constantes.controlDrift)){ // controla el drift del drive, para que pueda dar vueltas mas cerradas
          movimientoAdelanteX = movimientoAdelanteX * Constantes.controlSensivilidadDrift;
        }
    
        if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
          if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
          else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
        }

        MovimientoBaseCompleto.arcadeDrive(movimientoAdelanteY,movimientoAdelanteX);
    }

    public void tankdriveConXbox(){
        MovimientoBaseCompleto.tankDrive(-Robot.control.readXboxAxis(1), -Robot.control.readXboxAxis(5));
    }

    public void moverseConPiloto(){ // funcion principal del movimiento del chasis 

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

        MovimientoBaseCompleto.arcadeDrive(movimientoAdelanteY,movimientoAdelanteX);
    }

    public void moverseAPos(float pos){
      reiniciarEncodersDrivetrain();
      posicionDrivetrain();
      float margenDeError = 0.03f; // margen de error que tiene en metros
      double vel;
      double error;
      error = (pos -  EncoderPromDrive); // el error se calcula para saber cuanto nos tenemos que acercar

      while (error > margenDeError || error < -margenDeError){
        posicionDrivetrain();
        error = (pos - EncoderPromDrive);
        vel = error * .0001; // normalizacion para frenar mientras este lo mas cerca
        if (vel < 0.4 && vel > -0.4){
          if (vel < 0) vel = -0.4; // pone un minimo a la velocidad, para asegurarnos de que llegue
          else vel = 0.4;
        }

        MovimientoBaseCompleto.arcadeDrive(vel, 0); //lo convertimos de mate a movimiento
      }
    }
    private void posicionDrivetrain(){
      EncoderDerechoPos = EncoderMotoresDerecha.getDistance();
      EncoderIzquierdoPos = -EncoderMotoresIzquierda.getDistance();
      EncoderPromDrive = (EncoderDerechoPos + EncoderIzquierdoPos) / 2; // promedio de los encoders de ambas ruedas para poder sacar mejoeres medidas
      SmartDashboard.putNumber("PosicionDrivetrainDerecha", EncoderDerechoPos);
      SmartDashboard.putNumber("PosicionDrivetrainIzquierda", EncoderIzquierdoPos);
      SmartDashboard.putNumber("PosicionDrivetrainPromedio", EncoderPromDrive);
    }

    private void reiniciarEncodersDrivetrain(){
      EncoderMotoresIzquierda.reset();
      EncoderMotoresDerecha.reset();
    }
}