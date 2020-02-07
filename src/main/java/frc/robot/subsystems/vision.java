package frc.robot.subsystems;
//imports de codigo
//imports
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class vision{

    public NetworkTable valoresVision;
    public NetworkTableEntry arrayLists;

    public void iniciarVision(){
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        valoresVision = inst.getTable("nombre de networktable");
        arrayLists = valoresVision.getEntry("nombre del array del networktable");
    }

}