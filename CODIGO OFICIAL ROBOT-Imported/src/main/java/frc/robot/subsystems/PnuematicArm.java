package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;



public class PnuematicArm {
    Compressor c = new Compressor(0, PneumaticsModuleType.CTREPCM);

    DoubleSolenoid exampleDouble = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
    
    
    
boolean enabled = c.enabled();
boolean pressureSwitch = c.getPressureSwitchValue();
double current = c.getCurrent();
   public void PSet(){
      c.enableDigital();
      
      exampleDouble.set(Value.kOff);
    exampleDouble.set(Value.kForward);
 
   }


    public void Control(){
        

        if (Robot.control.readPS4Buttons(Constantes.XB_B_LB)) {
            exampleDouble.toggle();
         }



    }


}
