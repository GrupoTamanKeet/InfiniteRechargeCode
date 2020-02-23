package frc.robot.hardware; 

import edu.wpi.first.wpilibj.command.PIDSubsystem;

//comandos PID para los autonomos del robot, drivetrain izquierdo

class PIDllantasIzquierda extends PIDSubsystem {
    private PIDSubsystem PIDllantasIzquierda;
    private double valorPIDllantasIzquierdas;

    public PIDllantasIzquierda(double p, double i, double d) {
      super(p, i, d);
      p = 1;
      i = 1;
      d = 1;
    }

    @Override
    protected void initDefaultCommand() {
      // TODO Auto-generated method stub
      
    }
  
    @Override
    protected void usePIDOutput(double output) {
      // TODO Auto-generated method stub
      valorPIDllantasIzquierdas = output;
      
    }
  
    @Override
    protected double returnPIDInput() {
      // TODO Auto-generated method stub
      //valor al que se desea llegar
      return 0;
    }
}