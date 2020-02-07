package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

//imports librerías

//imports código
import frc.robot.subsystems.Controles;
import frc.robot.subsystems.drivetrain;
//import frc.robot.subsystems.intake;
//import frc.robot.subsystems.hopper;
import frc.robot.subsystems.autonomo;


public class Robot extends TimedRobot {
 
  //variables de objetos de otros códigos
  public static Controles localControles= new Controles();
  private drivetrain localDrivetrain;
  //private intake localIntake;
  //private hopper localHopper;
  private autonomo localAutonomo1;

  @Override
  public void robotInit() {
    
    //creación de objetos de códigos
    localDrivetrain = new drivetrain();
    //localIntake = new intake();
    //localHopper = new hopper();

    //iniciar mecanismos
    localDrivetrain.iniciarMotoresDrivetrain();
    //localIntake.iniciarMotoresIntake();
    //localHopper.iniciarMotoresHopper();
    
    
    
  }

  @Override
  public void robotPeriodic() {

  }


  @Override
  public void autonomousInit() {
   
    //localAutonomo1.initDeAutonomo();

  }

  @Override
  public void autonomousPeriodic() {
   
    

  }

  @Override
  public void teleopPeriodic() {

    //funciones de subsistemas
    //localIntake.moverIntake();
    localDrivetrain.moverDrivetrain();
    //localHopper.moverHopper();
    
    
    
  }

  @Override
  public void testPeriodic() {

  }
}
