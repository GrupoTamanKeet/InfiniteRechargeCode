/*----------------------------------------------------------------------------*/
/* Tamán Keet 3933 PrepaTec CSF                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.hardware.Constantes;
import frc.robot.hardware.Gyro;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedController;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.controller.PIDController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //sensor de color
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 SensorColor = new ColorSensorV3(i2cPort);

  //variables de tankdrive
  

  //giroscopio

  public Gyro giroscopio;
  //Clase
           

  //encoders de ambas líneas de motores
  private Encoder EncoderMotoresDerecha;
  private Encoder EncoderMotoresIzquierda;

  //variables de posicion de los encoders
  

  //PID
  //Los pase a una clase 

  /*
  * variables extras----------------------------------------------------------
  */

  // valores de joysticks






  //-----------------------------------------------------------------------------------------------------------------------

  /**
   * -----------------------------------------------------------------INIT
   */

  @Override
  public void robotInit() {
    giroscopio = new Gyro();
    //declaración de encoders
    EncoderMotoresDerecha = new Encoder(0,1);
    EncoderMotoresIzquierda = new Encoder(2,3);

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    // calibracion de encoders para medir en metros
    EncoderMotoresDerecha.setDistancePerPulse(4./10000.);
    EncoderMotoresIzquierda.setDistancePerPulse(4./10000.);
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

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */

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
    

    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
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
    // Aqui el codigo donde vamos a poner toda la estructura del robot

    DriveTrain.moverseConXbox(); // mueve el robot, control durante teleop  
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
