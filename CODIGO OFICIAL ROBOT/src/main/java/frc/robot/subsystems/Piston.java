package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.hardware.Constantes;
import edu.wpi.first.wpilibj.Solenoid;

public class Piston{
    static Solenoid piston1;
    static Solenoid piston2;
    int forwardChannel = 00; //PONER VALORES/
    int reverseChannel = 00; //PONER VALORES/
    int moduleNumber = 00;
    public Piston(){
        piston1 = new Solenoid(forwardChannel);
        piston2 = new Solenoid(forwardChannel)

    }
    public static void abrirPiston(){
        piston1.set(true);
    }
    public static void cerrarPiston (){
        piston2.set(true);

    }
    public static void funcionar(){
        if (Robot.control.readXboxButtons(Constantes.XB_B_Y) == true){
            abrirPiston();
        }
        if ((Robot.control.readXboxButtons(Constantes.XB_B_Start) == true) && (Robot.control.readXboxButtons(Constantes.XB_B_Y)){
            cerrarPiston();
        }
    }

}