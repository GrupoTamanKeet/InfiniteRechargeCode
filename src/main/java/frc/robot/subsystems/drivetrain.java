package frc.robot.subsystems;

//imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

//imports de código

public class drivetrain{

//variables públicas
public CANSparkMax motorDrivetrain1, motorDrivetrain2, motorDrivetrain3, motorDrivetrain4;
public SpeedControllerGroup motoresDerecha, motoresIzquierda;
public DifferentialDrive driveTrain;

//variables privadas
private Joystick controlChasis;

//objetos de importaciones de otros códigos


public void iniciarMotoresDrivetrain(){

    controlChasis = new Joystick(1);

    motorDrivetrain1 = new CANSparkMax(1,MotorType.kBrushless);
    motorDrivetrain2 = new CANSparkMax(2,MotorType.kBrushless);
    motorDrivetrain3 = new CANSparkMax(3,MotorType.kBrushless);
    motorDrivetrain4 = new CANSparkMax(4,MotorType.kBrushless);

    motorDrivetrain4.setInverted(true);
    motorDrivetrain2.setInverted(true);

    motoresDerecha = new SpeedControllerGroup(motorDrivetrain1, motorDrivetrain2);
    motoresIzquierda = new SpeedControllerGroup(motorDrivetrain3, motorDrivetrain4);

    driveTrain = new DifferentialDrive(motoresIzquierda, motoresDerecha);

}


public void moverDrivetrain(){

    driveTrain.arcadeDrive(controlChasis.getRawAxis(1) , controlChasis.getRawAxis(2) * -1);

}

}
