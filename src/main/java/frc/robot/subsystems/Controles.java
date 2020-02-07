package frc.robot.subsystems;
//imports
import edu.wpi.first.wpilibj.Joystick;

//imports de otro código

public class Controles{

//variables
public Joystick controlMecanismos;
public Joystick controlChasis;    

//funciones
    public Controles(){
        
        controlMecanismos = new Joystick(1);
        controlChasis = new Joystick(0);
        
    }
    public double getYaxis(){

        return controlChasis.getRawAxis(1);

    }

    public double getZaxis(){

        return controlChasis.getRawAxis(2);

    }

    }
