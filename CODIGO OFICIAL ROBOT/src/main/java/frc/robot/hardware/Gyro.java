package frc.robot.hardware;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.hardware.Constantes;
import frc.robot.subsystems.DriveTrain;

public class Gyro{

  // id del gyro y cosas 
  public static ADXRS450_Gyro giroscopio;

  public Gyro(){ // falta importar arcade drive
    giroscopio = new ADXRS450_Gyro();
  }
  //reiniciar giroscopio
  public void reiniciar() {
    giroscopio.reset();
  }

  public void leer(){ // toma la lectura del Giroscopio 

      Constantes.anguloTotalRobot = giroscopio.getAngle(); // toma la cantidad de grados que ha cambiado el gyro (nota puede pasar de 360)
  
      if (Constantes.anguloTotalRobot - 360*Constantes.vueltasDelRobot > 360){ // cambia la cantidad de vueltas del gyro, para asegurarse que podamos sacar solo 360
        Constantes.vueltasDelRobot ++;
      }else if(Constantes.anguloTotalRobot - 360*Constantes.vueltasDelRobot <= 0){
        Constantes.vueltasDelRobot --;
      }
      Constantes.anguloRobot = Constantes.anguloTotalRobot - 360*Constantes.vueltasDelRobot; // normaliza la cantidad de grados del gyro, para que solo valla de 0 a 360
      Math.round(Constantes.anguloRobot);
  
      // imprime todo
      SmartDashboard.putNumber("angulo total", Constantes.anguloTotalRobot); 
      SmartDashboard.putNumber("angulo", Constantes.anguloRobot); 
      SmartDashboard.putNumber("vueltas", Constantes.vueltasDelRobot);  
  }

  private void regresarAngulo(int deseado){ // funcion para poder girar el robot de forma automatica a una posicion en grados
      leer();
      int offset = 180-deseado; // convertimos los grados a 180 para asegurarnos que tome el camino mas rapido (que si esta en 359 y queremos que entre a 0, no se de toda la vuelta)
      deseado = 180;
      int margenDeError = 5; // desde cuando inicia a frenar
      double vel;
      double error;
      Constantes.anguloRobot = Constantes.anguloRobot + offset; // sacamos la pocicion actual del robot, con el offset para normalizar su angulo
      error = (deseado -  Constantes.anguloRobot); // el error se calcula para saber cuanto nos tenemos que acercar
  
      while (error > margenDeError || error < -margenDeError){
        leer();
        Constantes.anguloRobot = Constantes.anguloRobot + offset;
        if (Constantes.anguloRobot > 360) Constantes.anguloRobot = Constantes.anguloRobot - 360;
        error = (deseado - Constantes.anguloRobot);
        vel = error * .01;
        if (vel < 0.45 && vel > -0.45) { // tomamos un minimo de poder, para asegurarnos que siempre tenga la fuerza de completar la vuelta
          if (vel < 0) vel = -0.45;
          else vel = 0.45;
        }
        if (vel > Constantes.controlMaximaVelocidadDeGiro || vel < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
          if (vel < 0) vel = -Constantes.controlMaximaVelocidadDeGiro;
          else vel = Constantes.controlMaximaVelocidadDeGiro;
        }
        DriveTrain.MovimientoBaseCompleto.arcadeDrive(0, vel); //lo convertimos de mate a movimiento DIJO aLEX QUE CERO asi es dije eso
      }
  }
}

