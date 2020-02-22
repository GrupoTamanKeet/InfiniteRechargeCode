package frc.robot.hardware;

public class Constantes {
    // conecciones de controles -----------------------------------------------------------------------------------------------------------

    public static final int controlAdelante = 2; // palanquita derecha
    public static final int controlAtras = 3; // palanqquita izquierda

    public static final int controlGirar = 0;
    public static final int controlGirarRaw = 4;

    public static final int controlDrift = 9;

    public static final float controlSensivilidadDrift = 2;
    public static final float controlSensivilidadDrive = .8f;

    public static final float controlMaximaVelocidadDeGiro = .8f;

    // gyro stuff -------------------------------------------------------------------------------------------------------------------------

    public static double anguloTotalRobot;
    public static double anguloRobot; //@Alex, puedes hacerle un mod 360
    public static int vueltasDelRobot; 

    // cosas cross paltaform del elevador e intake

    public static int bolasDentro;
    public static boolean meterBolaAlFinal;
    public static boolean hayBolaEnIntake;
    

    // PID COSOS
    static double kP = 4.06; 
    static double kS = 0.0797;
    static double kD = 1.83; 
    static double kV = 0.281; 
    static double kA = 0.0387; 
    static double kIz = 0; 
    static double kFF = 0; 
    static double kMaxOutput = 1; 
    static double kMinOutput = -1;

//      _____                      _                         _____       _           _   
//     / ____|                    (_)                       |  __ \     | |         | |  
//    | |     ___  _ __   _____  ___  ___  _ __   ___  ___  | |__) |___ | |__   ___ | |_ 
//    | |    / _ \| '_ \ / _ \ \/ / |/ _ \| '_ \ / _ \/ __| |  _  // _ \| '_ \ / _ \| __|
//    | |___| (_) | | | |  __/>  <| | (_) | | | |  __/\__ \ | | \ \ (_) | |_) | (_) | |_ 
//     \_____\___/|_| |_|\___/_/\_\_|\___/|_| |_|\___||___/ |_|  \_\___/|_.__/ \___/ \__|          
    
    //DriveTrain
    public static final int PosicionMotorDriveDerecha1 = 4;
    public static final int PosicionMotorDriveDerecha2 = 1;
    public static final int PosicionMotorDriveIzquierda1 = 3;
    public static final int PosicionMotorDriveIzquierda2 = 2;
    //Intake
    public static final int ConexionMotorIntake = 13;
    public static final int ConexionMotorAcercar = 5;
    //Torreta
    public static final int ConexionMotorSubir = 10;
    public static final int ConexionMotorAngulo = 8;
    public static final int ConexionMotorSusana = 7;
    public static final int ConexionMotorTorreta = 9;

    //Colgarse
    public static final int MotorDeslizadorLineal = 12;
    public static final int MotorColgar = 11;
    public static final int ConexionCompresor = 1;

    // Control Panel
    public static final int ConexionMotorDisco= 6;  //cambiar el valor de estas dos
    public static final int ConexionPistonDisco=0;


//-----------------------------------------------------
//      _____            _             _            
//     / ____|          | |           | |           
//    | |     ___  _ __ | |_ _ __ ___ | | ___  ___  
//    | |    / _ \| '_ \| __| '__/ _ \| |/ _ \/ __| 
//    | |___| (_) | | | | |_| | | (_) | |  __/\__ \ 
//     \_____\___/|_| |_|\__|_|  \___/|_|\___||___/ 

public static final int puertoXbox = 0;
public static final int puertoJoystick = 1;

//-----------------------------------------------------
//    __   __           ____            
//    \ \ / /          |  _ \           
//     \ V /   ______  | |_) | _____  __
//      > <   |______| |  _ < / _ \ \/ /
//     / . \           | |_) | (_) >  < 
//    /_/ \_\          |____/ \___/_/\_\
                                  
// XB stands for X-Box
// B standsFor Button
// L/R Left and Right
// J joystick
// T Trigger

public static final int XB_B_A = 1;
public static final int XB_B_B = 2;
public static final int XB_B_X = 3;
public static final int XB_B_Y = 4;
// botón de torreta

public static final int XB_B_LB = 5;
public static final int XB_B_RB = 6;

public static final int XB_B_Back = 7;
public static final int XB_B_Start = 8;

public static final int XB_B_JL = 9;
public static final int XB_B_JR = 10;

public static final int XB_LJ_X = 0;
public static final int XB_LJ_Y = 1;
public static final int XB_RJ_X = 4;
public static final int XB_RJ_Y = 5;

public static final int XB_LT = 2;
public static final int XB_RT = 3;

//-----------------------------------------------------
//        _                 _   _      _    
//       | |               | | (_)    | |   
//       | | ___  _   _ ___| |_ _  ___| | __
//   _   | |/ _ \| | | / __| __| |/ __| |/ /
//  | |__| | (_) | |_| \__ \ |_| | (__|   < 
//   \____/ \___/ \__, |___/\__|_|\___|_|\_\
//                 __/ |                    
//                |___/  

// LG stands for Logitech
// X,Y,Z Joystick

public static final int LG_XJ = 0;
public static final int LG_YJ = 1;
public static final int LG_ZJ = 2;
public static final int LG_Slider = 3;


//Verifica en DriveTSpark, Elevador, Torreta, e Intake cada boton
public static final int LG_B_Reverse = 11; //DEFINIR UNO

public static final int LG_B1 = 1; //Disparar
public static final int LG_B2 = 2; // Intake
public static final int LG_B3 = 3; // Disco
public static final int LG_B4 = 4;
public static final int LG_B5 = 5; //Secuencia disparar
public static final int LG_B6 = 6; //Secuencia intake
public static final int LG_B7 = 7; //Preparar disparo
public static final int LG_B8 = 8; // Acercar
public static final int LG_B9 = 9; //Intake en el limno
public static final int LG_B10 = 10;
public static final int LG_B11 = 11; //NO se puede ocupar
public static final int LG_B12 = 12; //Destravarse

}