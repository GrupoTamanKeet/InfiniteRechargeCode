package frc.robot.subsystems;

//imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;

public class DriveTSpark {

    //variables públicas
    public static CANSparkMax motorDrivetrain1, motorDrivetrain2, motorDrivetrain3, motorDrivetrain4;
    public SpeedControllerGroup motoresDerecha, motoresIzquierda;
    public static DifferentialDrive driveTrain;

    private static double movimientoAdelanteX;
    private static double movimientoAdelanteY;

    //variables privadas

    public DriveTSpark(){

        motorDrivetrain1 = new CANSparkMax(1,MotorType.kBrushless);
        motorDrivetrain2 = new CANSparkMax(2,MotorType.kBrushless);
        motorDrivetrain3 = new CANSparkMax(3,MotorType.kBrushless);
        motorDrivetrain4 = new CANSparkMax(4,MotorType.kBrushless);

        motorDrivetrain2.setInverted(true);
        motorDrivetrain4.setInverted(true);

        motoresDerecha = new SpeedControllerGroup(motorDrivetrain1, motorDrivetrain2);
        motoresIzquierda = new SpeedControllerGroup(motorDrivetrain3, motorDrivetrain4);

        driveTrain = new DifferentialDrive(motoresIzquierda, motoresDerecha);

    }

    public static void moverseConXbox() { // funcion principal del movimiento del chasis

        // inputs del control para movimiento
        movimientoAdelanteY = -Robot.control.readXboxAxis(Constantes.XB_RT)
                - Robot.control.readXboxAxis(Constantes.XB_LT); // toma el valor para ir hacia adelante o hacia atras

        movimientoAdelanteX = Robot.control.readXboxAxis(Constantes.XB_LJ_X) * movimientoAdelanteY
                * Constantes.controlSensivilidadDrive // toma una funcion para saber cuanto giro deberia de tener el robot y que sirva mejor
                - Robot.control.readXboxAxis(Constantes.XB_RJ_X); // Toma input raw y lo suma a lo que va a girar para que sea solo una funcion 
                // nota que esta separada en 2 lineas, pero en verdad es solamente 1
    
        if (Robot.control.readXboxButtons(Constantes.controlDrift)){ // controla el drift del drive, para que pueda dar vueltas mas cerradas
          movimientoAdelanteX = movimientoAdelanteX * Constantes.controlSensivilidadDrift;
        }
    
        if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
          if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
          else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
        }

        driveTrain.arcadeDrive(movimientoAdelanteY,movimientoAdelanteX);
    }

    public void moverseConPiloto(){ // funcion principal del movimiento del chasis 

        // inputs del control para movimiento
        movimientoAdelanteY = Robot.control.readJoystickAxis(Constantes.LG_YJ); // toma el valor para ir hacia adelante o hacia atras 

        movimientoAdelanteX = Robot.control.readJoystickAxis(Constantes.LG_XJ)*movimientoAdelanteY*Constantes.controlSensivilidadDrive // toma una funcion para saber cuanto giro deberia de tener el robot y que sirva mejor
                + Robot.control.readJoystickAxis(Constantes.LG_ZJ); // Toma input raw y lo suma a lo que va a girar para que sea solo una funcion 
                // nota que esta separada en 2 lineas, pero en verdad es solamente 1

        if (Robot.control.readJoystickButtons(Constantes.controlDrift)){ // controla el drift del drive, para que pueda dar vueltas mas cerradas
        movimientoAdelanteX = movimientoAdelanteX * Constantes.controlSensivilidadDrift;
        }

        if (movimientoAdelanteX > Constantes.controlMaximaVelocidadDeGiro || movimientoAdelanteX < -Constantes.controlMaximaVelocidadDeGiro) { // si va muy rapido, se desconfigura el gyroscopio, asi que no queremos eso 
        if (movimientoAdelanteX < 0) movimientoAdelanteX = -Constantes.controlMaximaVelocidadDeGiro;
        else movimientoAdelanteX = Constantes.controlMaximaVelocidadDeGiro;
        }

        driveTrain.arcadeDrive(movimientoAdelanteY,movimientoAdelanteX);
    }
}