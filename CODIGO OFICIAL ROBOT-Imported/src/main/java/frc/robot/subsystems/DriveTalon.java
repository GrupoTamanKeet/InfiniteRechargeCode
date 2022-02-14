package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.math.filter.SlewRateLimiter;
import frc.robot.hardware.*;
import frc.robot.Robot;



public class DriveTalon {
    TalonSRX Tdr1 = new TalonSRX(9);
    TalonSRX Tdr2 = new TalonSRX(11);
    TalonSRX Tdl1 = new TalonSRX(12);
    TalonSRX Tdl2 = new TalonSRX(13);

    SlewRateLimiter LeftJoystick;
    SlewRateLimiter RightJoystick;

    public void DriveTrainTank(){

        LeftJoystick = new SlewRateLimiter(1.75);
        RightJoystick = new SlewRateLimiter(1.75);

        Tdr1.set(ControlMode.PercentOutput, (Robot.control.readPS4Axis(Constantes.XB_RJ_Y)));
        Tdr2.set(ControlMode.PercentOutput, (Robot.control.readPS4Axis(Constantes.XB_RJ_Y)));
        Tdl1.set(ControlMode.PercentOutput, -(Robot.control.readPS4Axis(Constantes.XB_LJ_Y)));
        Tdl2.set(ControlMode.PercentOutput, -(Robot.control.readPS4Axis(Constantes.XB_LJ_Y)));
    }
}
