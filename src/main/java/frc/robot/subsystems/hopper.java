/*package frc.robot.subsystems;

//imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//imports codigos
import frc.robot.subsystems.controles;

public class hopper{
    public WPI_TalonSRX motorHopper;
    public controles localControles = new controles();
    

    public void iniciarMotoresHopper(){

        motorHopper = new WPI_TalonSRX(6);
        
    }

    public void moverHopper(){

        motorHopper.set(localControles.controlMecanismos.getZ());

    }


}*/