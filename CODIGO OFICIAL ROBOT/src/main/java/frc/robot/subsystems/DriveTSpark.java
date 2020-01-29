package frc.robot.subsystems;

//imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//imports de código
import frc.robot.subsystems.controles;

public class drivetrain{

//variables públicas
public CANSparkMax motorDrivetrain1, motorDrivetrain2, motorDrivetrain3, motorDrivetrain4;
public SpeedControllerGroup motoresDerecha, motoresIzquierda;
public DifferentialDrive driveTrain;

//variables privadas
private Joystick controlChasis;


//objetos de importaciones de otros códigos
private controles localControles;

public void iniciarMotoresDrivetrain(){

    motorDrivetrain1 = new CANSparkMax(1,MotorType.kBrushless);
    motorDrivetrain2 = new CANSparkMax(2,MotorType.kBrushless);
    motorDrivetrain3 = new CANSparkMax(3,MotorType.kBrushless);
    motorDrivetrain4 = new CANSparkMax(4,MotorType.kBrushless);

    motorDrivetrain2.setInverted(true);
    motorDrivetrain4.setInverted(true);

    motoresDerecha = new SpeedControllerGroup(motorDrivetrain1, motorDrivetrain2);
    motoresIzquierda = new SpeedControllerGroup(motorDrivetrain3, motorDrivetrain4);

    driveTrain = new DifferentialDrive(motoresIzquierda, motoresDerecha);

    controlChasis = localControles.controlChasis;

}


public void moverDrivetrain(){

    driveTrain.arcadeDrive(controlChasis.getRawAxis(2)  * controlChasis.getRawAxis(1) * 1.5, controlChasis.getRawAxis(1));

}

}