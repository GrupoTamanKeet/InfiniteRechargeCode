package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;

//Basic movemetns for the autonomous robot

class Autonomous {

    private double EncoderIzquierdoPos;
    private double EncoderDerechoPos;
    private double EncoderPromDrive;

    private Encoder EncoderMotoresDerecha;
    private Encoder EncoderMotoresIzquierda;

    

    public Autonomous(Encoder EncoderMotoresIzquierda, Encoder EncoderMotoresDerecha ){

      reiniciarEncodersDrivetrain();
      //Falta iniciar el Gyro
    }

    

    public void moverAPos(float pos){ // funcion para movernos a cierta pocicion en el autonomo (hacia adelante y hacia atras)
        reiniciarEncodersDrivetrain();
        posicionDrivetrain();
        float margenDeError = 0.03f; // margen de error que tiene en metros
        double vel;
        double error;
        error = (pos -  EncoderPromDrive); // el error se calcula para saber cuanto nos tenemos que acercar
    
        while (error > margenDeError || error < -margenDeError){
          posicionDrivetrain();
          error = (pos - EncoderPromDrive);
          vel = error * .01; // normalizacion para frenar mientras este lo mas cerca
          if (vel < 0.4 && vel > -0.4){
            if (vel < 0) vel = -0.4; // pone un minimo a la velocidad, para asegurarnos de que llegue
            else vel = 0.4;
          }
    
          DriveTrain.MovimientoBaseCompleto.arcadeDrive(vel, 0); //lo convertimos de mate a movimiento
        }
      }

      
       //reiniciar posicion de encoders del drivetrain a 0
  private void reiniciarEncodersDrivetrain(){
    EncoderMotoresIzquierda.reset();
    EncoderMotoresDerecha.reset();
  }

   //leer posicion encoders de drivetrain
   private void posicionDrivetrain(){
    EncoderDerechoPos = EncoderMotoresDerecha.getDistance();
    EncoderIzquierdoPos = -EncoderMotoresIzquierda.getDistance();
    EncoderPromDrive = (EncoderDerechoPos + EncoderIzquierdoPos) / 2; // promedio de los encoders de ambas ruedas para poder sacar mejoeres medidas
    SmartDashboard.putNumber("PosicionDrivetrainDerecha", EncoderDerechoPos);
    SmartDashboard.putNumber("PosicionDrivetrainIzquierda", EncoderIzquierdoPos);
    SmartDashboard.putNumber("PosicionDrivetrainPromedio", EncoderPromDrive);
  }


}