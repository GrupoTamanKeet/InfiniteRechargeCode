package frc.robot;

import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.DriveTSpark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomo{

   private CANEncoder encoderm1,encoderm4;
   private double posicionEncoderM1, posicionEncoderM4;
   private double posicionInicialEncoderM1;
   //posicionInicialEncoderM4;
   private double tiempoInicial;
   private double PosicionPrueba;
   private SlewRateLimiter smooth;
   int paso;
 
 public Autonomo(){

   encoderm1 = new CANEncoder(DriveTSpark.motorDrivetrain1);
   encoderm4 = new CANEncoder(DriveTSpark.motorDrivetrain4);

   posicionInicialEncoderM1 = encoderm1.getPosition();
   //posicionInicialEncoderM4 = encoderm4.getPosition();

   smooth = new SlewRateLimiter(0.75);

   PosicionPrueba = posicionInicialEncoderM1 - calcularTicks(2);
   SmartDashboard.putNumber("PosicionPureba", PosicionPrueba);
   paso = 1;
   

 }

 private double calcularTicks (double inches){
   double result = 12.45*inches;
   result = result/(4*Math.PI);
   return result;
 }

 public void reiniciarTiempo(){
   tiempoInicial = Timer.getFPGATimestamp();
 }
 
 public void autonomoPelotasYControlPanel(){

   posicionEncoderM1 = encoderm1.getPosition();
   posicionEncoderM4 = encoderm4.getPosition();

   //420.488586
   //433.236633 - final
   //vuelta = 12.45
   
   //Formula de dist = (posicionEncoderM1/12.45) * Gr * 4 * Math.PI;

   //Pensar en Alex <3
   switch(paso){
      case 1:
         disparar();
         break;
      case 2:
         avanzar();
         break;
      case 3: 
         retroceder();
         break;
      case 4:
         disparar();
         break;
      default:
         
         break;
   }

   SmartDashboard.putNumber("encoderM1", posicionEncoderM1);
   SmartDashboard.putNumber("encoderM4", posicionEncoderM4);
   System.out.println(paso);
 }

//Dispara en el autónomo y funciona
 private void disparar(){
   
   double now = Timer.getFPGATimestamp();

   if(now-tiempoInicial < 2){
      Robot.torreta.secuenciaDisparar();
   }else if(now-tiempoInicial < 6 && now-tiempoInicial > 2){
      Robot.torreta.activarAcercar();
      Robot.torreta.secuenciaDisparar();
   }else if(now-tiempoInicial< 8 && now-tiempoInicial > 6){
      Robot.dTrain.destravarse();
      Robot.torreta.pararTodo();
   }else if(now-tiempoInicial < 9 && now-tiempoInicial > 8){
      Robot.torreta.secuenciaDisparar();
   }else if(now-tiempoInicial< 12 && now-tiempoInicial > 9){
      Robot.torreta.activarAcercar();
      Robot.torreta.secuenciaDisparar();
   }else{
      Robot.torreta.pararTodo();
      paso++;
   }
 }
 private void avanzar(){
   if(posicionEncoderM1 > PosicionPrueba){
      DriveTSpark.driveTrain.arcadeDrive(smooth.calculate(+0.4), 0);
      System.out.print("Paso: " + paso);
   }
   else{
      System.out.print("Termino el paso " + paso);
      DriveTSpark.driveTrain.arcadeDrive(0.0, 0);
      paso++;
   }
 }

 private void retroceder() {
   if( PosicionPrueba < posicionEncoderM1){
      DriveTSpark.driveTrain.arcadeDrive(smooth.calculate(0.5), 0);
      Robot.intake.activarIntake();
      System.out.print("Paso: " + paso);
   }
   else{
      System.out.print("Termino el paso " + paso);
      Robot.intake.desactivarIntake();
      DriveTSpark.driveTrain.arcadeDrive(0.0, 0);
      tiempoInicial = Timer.getFPGATimestamp();
      paso++;
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
         DriveTSpark.driveTrain.arcadeDrive(-0.3, 0);
         SmartDashboard.putNumber("encoderM1", posicionEncoderM1);
         SmartDashboard.putNumber("encoderM4", posicionEncoderM4);
      }
   }else{
      while (posicionEncoderM1 > vueltas(deseado)){
         posicionEncoderM1 = encoderm1.getPosition();
         posicionEncoderM4 = encoderm4.getPosition();
         DriveTSpark.driveTrain.arcadeDrive(0.3, 0);
         SmartDashboard.putNumber("encoderM1", posicionEncoderM1);
         SmartDashboard.putNumber("encoderM4", posicionEncoderM4);
      }
   }
   DriveTSpark.driveTrain.arcadeDrive(0.0, 0);
   }
}