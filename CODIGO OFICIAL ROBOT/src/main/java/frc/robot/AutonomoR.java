 package frc.robot;
 import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.revrobotics.CANEncoder;

//imports
 import edu.wpi.first.wpilibj.Timer;
import frc.robot.hardware.Constantes;
import frc.robot.subsystems.DriveTSpark;
 import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomoR{

    private DriveTSpark drivetrain;
    private CANEncoder encoderm1,encoderm4;
    private double posicionEncoderM1, posicionEncoderM4, posicionInicialEncoderM1, posicionInicialEncoderM4;
    private double kP;
    private double tiempoInicial;

    int paso;
 
 public AutonomoR(){

    encoderm1 = new CANEncoder(Robot.dTrain.motorDrivetrain1);
    encoderm4 = new CANEncoder(Robot.dTrain.motorDrivetrain4);

    posicionInicialEncoderM1 = encoderm1.getPosition();
    posicionInicialEncoderM4 = encoderm4.getPosition();

    PosicionPrueba = posicionInicialEncoderM1 + 12.45;
    SmartDashboard.putNumber("PosicionPureba", PosicionPrueba);
    kP = 4.06;
   paso = 1;
   tiempoInicial = Timer.getFPGATimestamp();
 }

 double PosicionPrueba;
 public void AutonomoRafa(){

   posicionEncoderM1 = encoderm1.getPosition();
   posicionEncoderM4 = encoderm4.getPosition();

   //420.488586
   //433.236633 - final
   //vuelta = 12.45
   
   //Pensar en Alex <3
   switch(paso){
      case 2:
         if(posicionEncoderM1 > PosicionPrueba){
            Robot.dTrain.driveTrain.arcadeDrive(0.3, 0);
            System.out.print("Paso: " + paso);
         }
         else{
            System.out.print("Termino el paso " + paso);
            Robot.dTrain.driveTrain.arcadeDrive(0.0, 0);
            paso++;
            posicionEncoderM1 = encoderm1.getPosition(); 
            PosicionPrueba = posicionEncoderM1
         }
         break;
      case 3:
            if(posicionEncoderM1 < -PosicionPrueba){
               Robot.dTrain.driveTrain.arcadeDrive(-0.3, 0);
               System.out.print("Paso: " + paso);
            }
            else{
               System.out.print("Termino el paso " + paso);
               Robot.dTrain.driveTrain.arcadeDrive(0.0, 0);
               paso++;
            }
         break;
      case 1: 
         if (Torreta.)
         Robot.torreta.secuenciaDisparar();
         break;
   }
   


   SmartDashboard.putNumber("encoderM1", posicionEncoderM1);
   SmartDashboard.putNumber("encoderM4", posicionEncoderM4);

 }

 private void autonomo1(){

    double tiempo1 = 1;

 }

 private boolean funcionDeLasPelotas(){
   if (Robot.motorAcercar.leerSwitchElevador() && Constantes.hayBolaEnDisparo){
      Constantes.hayBolaEnDisparo = true;
      System.out.println("entro bola");
   }else if (!Robot.motorAcercar.leerSwitchElevador() && !Constantes.hayBolaEnDisparo){
      Constantes.hayBolaEnDisparo = false;
      Constantes.bolasDentro --;
      System.out.println("salio bola");
   }

   if (Constantes.hayBolaEnDisparo){
      return true;
   }
 }

 public double vueltas (int lecturaEncoder){
    return lecturaEncoder/12.56;
 }
 public double pulgadas (int vueltas){
    return vueltas*6*Math.PI;
 }

 public void mueveteAPos(int deseado){
   posicionEncoderM1 = encoderm1.getPosition();
   posicionEncoderM4 = encoderm4.getPosition();

   if (deseado > 0){
      while (posicionEncoderM1 < vueltas(deseado)){
         posicionEncoderM1 = encoderm1.getPosition();
         posicionEncoderM4 = encoderm4.getPosition();
         Robot.dTrain.driveTrain.arcadeDrive(-0.3, 0);
         SmartDashboard.putNumber("encoderM1", posicionEncoderM1);
         SmartDashboard.putNumber("encoderM4", posicionEncoderM4);
      }
   }else{
      while (posicionEncoderM1 > vueltas(deseado)){
         posicionEncoderM1 = encoderm1.getPosition();
         posicionEncoderM4 = encoderm4.getPosition();
         Robot.dTrain.driveTrain.arcadeDrive(0.3, 0);
         SmartDashboard.putNumber("encoderM1", posicionEncoderM1);
         SmartDashboard.putNumber("encoderM4", posicionEncoderM4);
      }
   }
   

   Robot.dTrain.driveTrain.arcadeDrive(0.0, 0);
 }
}