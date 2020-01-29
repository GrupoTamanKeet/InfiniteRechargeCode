 package frc.robot;
 //imports
 import edu.wpi.first.wpilibj.SpeedControllerGroup;
 import edu.wpi.first.wpilibj.controller.PIDController;
 import com.revrobotics.CANEncoder;
 import com.revrobotics.CANSparkMax;
 import edu.wpi.first.wpilibj.Timer;

 //imports de codigo
 import frc.robot.subsystems.drivetrain;

public class autonomo{
 
 private drivetrain localDrivetrain;

 private SpeedControllerGroup drivetrainDerecha;
 private SpeedControllerGroup drivetrainIzquierda;

 private PIDController PIDDrivetrainDerecha, PIDDrivetrainIzquierda;

 private double inputDerecha, outputDerecha;
 private double inputIzquierda, outputIzquierda;

 private CANEncoder encoderDerecha1, encoderDerecha2;
 private CANEncoder encoderIzquierda1, encoderIzquierda2;

 private CANSparkMax motorDerecha1, motorDerecha2;
 private CANSparkMax motorIzquierda1, motorIzquierda2;

 private Timer tiempo;


 public void initDeAutonomo(){

     localDrivetrain = new drivetrain();

     motorDerecha1 = localDrivetrain.motorDrivetrain1;
     motorDerecha2 = localDrivetrain.motorDrivetrain2;
     motorIzquierda1 = localDrivetrain.motorDrivetrain3;
     motorIzquierda2 = localDrivetrain.motorDrivetrain4;
     
     drivetrainDerecha = localDrivetrain.motoresDerecha;
     drivetrainIzquierda = localDrivetrain.motoresIzquierda;

     encoderDerecha1 = new CANEncoder(motorDerecha1);
     encoderDerecha2 = new CANEncoder(motorDerecha2);

     encoderIzquierda1 = new CANEncoder(motorIzquierda1);
     encoderIzquierda2 = new CANEncoder(motorIzquierda2);

     tiempo = new Timer();
     tiempo.start();

 }

 public void autonomoPeriodico(){

     //inputs o "posicion actual del robot"
     inputDerecha = (encoderDerecha1.getPosition() + encoderDerecha2.getPosition()) / 2;
     inputIzquierda = (encoderIzquierda1.getPosition() + encoderIzquierda2.getPosition()) / 2;

 }
 
 public void autonomo1(){

     double primerSetpoint = 10;
     
     PIDDrivetrainDerecha = new PIDController(.1, .1, .1);
     PIDDrivetrainIzquierda = new PIDController(.1, .1, .1);

     //el pid calcula los valores dependiendo de la posicion de los encoders
     //hasta un "primerSetpoint"
     outputDerecha = PIDDrivetrainDerecha.calculate(inputDerecha, primerSetpoint);
     outputIzquierda = PIDDrivetrainIzquierda.calculate(inputIzquierda, primerSetpoint);

     //fijamos los motores a los valores que nos da el PID
     drivetrainDerecha.set(outputDerecha);
     drivetrainIzquierda.set(outputIzquierda);


 }

 
}