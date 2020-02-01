package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.hardware.Constantes;
import frc.robot.hardware.Control;

public class Elevator{
     static WPI_TalonSRX MotorSubir;
     static WPI_TalonSRX MotorEntregar;

     public Elevator(){
          MotorSubir = new WPI_TalonSRX(Constantes.ConexionPosicionSubir);
          MotorEntregar = new WPI_TalonSRX(Constantes.ConexionPosicionEntregar);
     }

     public void activarSubir(){
          MotorSubir.set(ControlMode.PercentOutput,1);
      }
  
      public void desactivarSubir(){
          MotorSubir.set(ControlMode.PercentOutput,0);
      }
  
      public void activarEntregar(){
          MotorEntregar.set(ControlMode.PercentOutput,1);
      }
  
      public void desactivarEntregar(){
          MotorEntregar.set(ControlMode.PercentOutput,0);
      }
}