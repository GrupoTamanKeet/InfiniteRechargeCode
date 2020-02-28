package frc.robot.hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class MotorAcercar {
    private static WPI_TalonSRX MotorAcercar;

    public MotorAcercar() {
        MotorAcercar = new WPI_TalonSRX(Constantes.ConexionMotorAcercar);

    }

    public void moverMotor(double Speed) {
        MotorAcercar.set(ControlMode.PercentOutput, Speed);
    }

    public void pararMotor() {
        MotorAcercar.stopMotor();
        MotorAcercar.setVoltage(0);
    }
}