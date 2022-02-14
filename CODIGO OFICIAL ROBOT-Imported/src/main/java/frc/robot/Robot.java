/*----------------------------------------------------------------------------*/
/* Tamán Keet 3933 PrepaTec CSF                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.hardware.Controles;

import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";

  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static Controles control;
  public static DriveTalon dTrain;
  public static Intake intake;
  public static Turret turret;
  public static Cameraselect cameras;
  public static Hopper hopper;
  public static PnuematicArm Pne;

  @Override// :)
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    dTrain = new DriveTalon();
  Pne = new PnuematicArm();
    control = new Controles();
    intake = new Intake();
    turret = new Turret();
   cameras = new Cameraselect();
   hopper = new Hopper();

   cameras.CameraSetup();
   Pne.PSet();
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
  }

  
  //  ______   ______     __         ______     ______     ______  
  // /\__  _\ /\  ___\   /\ \       /\  ___\   /\  __ \   /\  == \ 
  // \/_/\ \/ \ \  __\   \ \ \____  \ \  __\   \ \ \/\ \  \ \  _-/ 
  //    \ \_\  \ \_____\  \ \_____\  \ \_____\  \ \_____\  \ \_\   
  //     \/_/   \/_____/   \/_____/   \/_____/   \/_____/   \/_/   
  
  @Override
  public void teleopPeriodic() {
    //Aqui el codigo donde vamos a poner toda la estructura del robot
    intake.IntakeTest();
    turret.turret();
    dTrain.DriveTrainTank();
    hopper.HopperTest();
    Pne.Control();

  

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
