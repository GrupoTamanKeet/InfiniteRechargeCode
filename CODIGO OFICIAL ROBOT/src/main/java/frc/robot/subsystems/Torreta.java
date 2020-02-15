package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;


public class Torreta  {
 
    public static WPI_TalonSRX MotorTorreta;
    public static boolean LanzarPelota;


    public Torreta(){
      
        MotorTorreta = new WPI_TalonSRX(Constantes.ConexionPosicionSubir);
        
        MotorTorreta.setInverted(true);
       

    }
    public void LanzarPelota(){
        LanzarPelota = Robot.control.readXboxButtons(Constantes.XB_B_A);
            if (LanzarPelota==true){
                MotorTorreta.set(ControlMode.PercentOutput,.6);
            }
            else {
             MotorTorreta.stopMotor();    
            }


    }

}

