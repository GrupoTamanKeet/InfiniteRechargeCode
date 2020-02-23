/*----------------------------------------------------------------------------*/
/* Tamán Keet 3933 PrepaTec CSF                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.hardware.Controles;
import frc.robot.hardware.MotorAcercar;


import frc.robot.subsystems.Elevador;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MoveDisk;
import frc.robot.subsystems.Torreta;
import frc.robot.subsystems.DriveTSpark;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";

  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static Controles control;
  public static DriveTSpark dTrain;
  public static Intake intake;
  public static Torreta torreta;
  public static Elevador elevador;
  public static MotorAcercar motorAcercar;
  public static MoveDisk controlPanel;

  

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    dTrain = new DriveTSpark();
    intake = new Intake();
    control = new Controles();
    elevador = new Elevador();
    torreta = new Torreta();
    motorAcercar = new MotorAcercar();
    controlPanel = new MoveDisk();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }


   /*
    //  ______           __                                                                 
    // /\  _  \         /\ \__                                                              
    // \ \ \L\ \  __  __\ \ ,_\   ___     ___     ___     ___ ___     ___   __  __    ____  
    //  \ \  __ \/\ \/\ \\ \ \/  / __`\ /' _ `\  / __`\ /' __` __`\  / __`\/\ \/\ \  /',__\ 
    //   \ \ \/\ \ \ \_\ \\ \ \_/\ \L\ \/\ \/\ \/\ \L\ \/\ \/\ \/\ \/\ \L\ \ \ \_\ \/\__, `\
    //    \ \_\ \_\ \____/ \ \__\ \____/\ \_\ \_\ \____/\ \_\ \_\ \_\ \____/\ \____/\/\____/
    //     \/_/\/_/\/___/   \/__/\/___/  \/_/\/_/\/___/  \/_/\/_/\/_/\/___/  \/___/  \/___/                                                                                    
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
  
    // cosas de if selected has esto con nombres de cosas, si esta dentro de un loop

    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

  }

  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        //si esta diseñado con loop externo aqui
        break;
      case kDefaultAuto:
      default:
        break; // Técnicamente es inneceseario
    }
  }

  /**
  //  ______   ______     __         ______     ______     ______  
  // /\__  _\ /\  ___\   /\ \       /\  ___\   /\  __ \   /\  == \ 
  // \/_/\ \/ \ \  __\   \ \ \____  \ \  __\   \ \ \/\ \  \ \  _-/ 
  //    \ \_\  \ \_____\  \ \_____\  \ \_____\  \ \_____\  \ \_\   
  //     \/_/   \/_____/   \/_____/   \/_____/   \/_____/   \/_/   
   */
  @Override
  public void teleopPeriodic() {
    //Aqui el codigo donde vamos a poner toda la estructura del robot
    dTrain.movimientoDrivetrainFinal();
    intake.funcionar();
    elevador.funcionar();
    torreta.funcionar();
    controlPanel.funcionar();
    
    intake.meterBolaYContar();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
