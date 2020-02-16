package frc.robot.hardware;

import edu.wpi.first.wpilibj.Compressor;

public class Compresor{
    public static Compressor Compresora;
    public Compresor(){
        Compresora = new Compressor(Constantes.ConexionCompresor);
    }
}