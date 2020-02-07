/*package frc.robot.subsystems;

//imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//imports codigos
import frc.robot.subsystems.controles;

public class intake{
    public WPI_TalonSRX motorIntake;
    public controles localControles = new controles();
    

    public void iniciarMotoresIntake(){

        motorIntake = new WPI_TalonSRX(5);
        
    }

    public void moverIntake(){

        motorIntake.set(localControles.controlMecanismos.getY());

    }


}*/