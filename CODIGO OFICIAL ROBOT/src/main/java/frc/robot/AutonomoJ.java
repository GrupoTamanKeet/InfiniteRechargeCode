package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.EncoderType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.subsystems.DriveTSpark;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.Timer;

public class AutonomoJ {

    CANEncoder encoderM1;
    CANEncoder encoderM2;
    CANEncoder encoderM3;
    CANEncoder encoderM4;

    CANPIDController PID_encoderM1;
    CANPIDController PID_encoderM2;
    CANPIDController PID_encoderM3;
    CANPIDController PID_encoderM4;

    double xSpeed;
    double zRotation;

    SlewRateLimiter smooth;

    public static ADXRS450_Gyro giroscopio;

    double kLlanta = 0; // Conversión de circunferencia de la llanta

    public double kP = .01;
    public double kI = 0.02;
    public double kD = 0.5;
    public double kIz = 1;

    public double setpoint = 0;
    public double errorSum = 0;
    public double lastTimestamp = 0;
    public double lastError = 0;

    double error;

    // Un setpoint es nuestra meta
    // Proportional: Aka. error, distance from setpoint. Determine the speed
    // Voltaje should be v= kP-error. kP can't kp>1
    // Integral: overcome friction
    // Once the error is negative, the robot will stop
    // Voltage (P,I): kP*error + kI*#integral(e(dt))
    // We are only intereted on the lastest segments of the line,
    // Derivative

    // Motor Output and encoder readings shall have same sign
    // you can quickly change this in the encoder constructor
    public AutonomoJ() {
        /**
         * Qué tipo de enconder tienen los SparkMax 3-phase hall-effect sensors El
         * segundo parámetro es counts_per_rev Hall-Sensor Encoder Resolution: 42 counts
         * per rev. Fuente: http://www.revrobotics.com/rev-21-1650/
         */

        encoderM1 = DriveTSpark.motorDrivetrain1.getEncoder();
        encoderM2= Robot.dTrain.motorDrivetrain2.getEncoder();
        encoderM3= DriveTSpark.motorDrivetrain3.getEncoder();
        //Cuál debería usar?
        encoderM4= Robot.dTrain.motorDrivetrain4.getEncoder(EncoderType.kHallSensor, 42);

        //PID Controler
        PID_encoderM1= Robot.dTrain.motorDrivetrain1.getPIDController();
        PID_encoderM2= Robot.dTrain.motorDrivetrain2.getPIDController();
        PID_encoderM3= Robot.dTrain.motorDrivetrain3.getPIDController();
        PID_encoderM4= Robot.dTrain.motorDrivetrain4.getPIDController();

        PID_encoderM1.setFeedbackDevice(encoderM1);
        smooth = new SlewRateLimiter(.5);

        giroscopio = new ADXRS450_Gyro();
        
    }
    
    //Cm will be constantly overwritten

    public void moveStraightDistance (int cm, boolean forward){
        int setpoint = cm;

        xSpeed = calculateSpeed();
        zRotation = 0; //para que vaya derecho
        
        //Le podemos dar un slew Rate 
        Robot.dTrain.driveTrain.arcadeDrive(smooth.calculate(xSpeed), zRotation);
        lastError = error;
    }

    public void danceMoves() {
        Robot.dTrain.driveTrain.arcadeDrive(xSpeed, zRotation);
    }
    
    private double calculateSpeed() {
        //Know the distance where we are
        //Take into accout other variables
        double robotPosition = encoderM1.getPosition(); //Tambien necesitamos multiplicar por una k para que nos de la circunferencia
        error = setpoint - robotPosition;
        double dt = Timer.getFPGATimestamp() - lastTimestamp;
        
        if(Math.abs(error) < kIz){
            errorSum += error*dt;
        }

        double errorRate = (error - lastError)/dt;
        
        return kP*error + kI*errorSum + kD*errorRate;
    }
    private double calculateRotation(double desiredAngle) {
        
        
        double actualAngle = giroscopio.getAngle()%360; //para que esté en una escala de 360

        //No estoy seguro de cuáles sean las lecturas del gyro
        //Maybe ponerle un valo absoluto? Math.abs(valor);
        double turn = desiredAngle-actualAngle;
        
        //pasamos la vuelta a una escala 360-1
        turn = turn/360;
        return turn;
        //Probablemente haya que multpilicar por una constante
    }


}