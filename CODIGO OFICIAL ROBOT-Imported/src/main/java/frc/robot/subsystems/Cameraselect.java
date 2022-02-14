package frc.robot.subsystems;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Robot;
import frc.robot.hardware.Constantes;


public class Cameraselect {
    UsbCamera camera1;
    UsbCamera camera2;
    NetworkTableEntry cameraSelection;

    public void CameraSetup(){
          camera1 = CameraServer.startAutomaticCapture(0);
    camera2 = CameraServer.startAutomaticCapture(1);
    cameraSelection = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");
    }

    public void CameraChange(){
        if (Robot.control.readJoystickButtons(Constantes.LG_B5)){
cameraSelection.setString(camera2.getName());
        }
        else if(Robot.control.readJoystickButtons(Constantes.LG_B3)){
            cameraSelection.setString(camera1.getName());
        }
    }
  
}
