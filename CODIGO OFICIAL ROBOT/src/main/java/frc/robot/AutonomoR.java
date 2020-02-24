 package frc.robot;
 import com.revrobotics.CANEncoder;

//imports
 import edu.wpi.first.wpilibj.Timer;
import frc.robot.hardware.Constantes;
import frc.robot.subsystems.DriveTSpark;
 import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomoR{

    private DriveTSpark drivetrain;
    private CANEncoder encoderm1,encoderm4;
    private double posicionEncoderM1, posicionEncoderM4;
    private double kP;
    private double tiempoActual;
 
 public AutonomoR(){

    encoderm1 = new CANEncoder(Robot.dTrain.motorDrivetrain1);
    encoderm4 = new CANEncoder(Robot.dTrain.motorDrivetrain4);

    kP = 4.06;

    tiempoActual = Timer.getFPGATimestamp();

 }

 public void AutonomoRafa(){

    posicionEncoderM1 = encoderm1.getPosition();
    posicionEncoderM4 = encoderm4.getPosition();

    while(Robot.control.readJoystickButtons(Constantes.LG_B9) == true){
        
        System.out.println("reiniciando encoders");
        encoderm1.setPosition(0);
        encoderm4.setPosition(0);

    }

    SmartDashboard.putNumber("encoderM1", posicionEncoderM1);
    SmartDashboard.putNumber("encoderM4", posicionEncoderM4);

 }

 private void autonomo1(){

    double tiempo1 = 1;

    while(tiempo1 - tiempoActual < 0){



    }


 }
}