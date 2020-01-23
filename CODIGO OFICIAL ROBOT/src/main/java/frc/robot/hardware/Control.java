package frc.robot.hardware;

import edu.wpi.first.wpilibj.Joystick;

public class Control {

    private static Joystick ControlXbox;
    private static Joystick ControlPiloto;
    private static Joystick Botonera;

    // _____ _ _ _ _ _
    // |_ _| (_) (_) | (_) (_)
    // | | _ __ _ ___ _ __ _| |_ ______ _ ___ _ ___ _ __
    // | | | '_ \| |/ __| |/ _` | | |_ / _` |/ __| |/ _ \| '_ \
    // _| |_| | | | | (__| | (_| | | |/ / (_| | (__| | (_) | | | |
    // |_____|_| |_|_|\___|_|\__,_|_|_/___\__,_|\___|_|\___/|_| |_|

    public Control() {
        ControlXbox = new Joystick(Constantes.puertoXbox);
        ControlPiloto = new Joystick(Constantes.puertoJoystick);
        Botonera = new Joystick(Constantes.puertoBottonera);
    }

    // -----------------------------------------------------
    // _ _ _ _
    // | | | | (_) | |
    // | | ___ _ _ ___| |_ _ ___| | __
    // _ | |/ _ \| | | / __| __| |/ __| |/ /
    // | |__| | (_) | |_| \__ \ |_| | (__| <
    // \____/ \___/ \__, |___/\__|_|\___|_|\_\
    // __/ |
    // |___/

    public static boolean readJoystickButtons(int id) {
        return (ControlPiloto.getRawButton(id));
    }

    public static double readJoystickAxis(int axis) {
        return (ControlPiloto.getRawAxis(axis));
    }
    
    // -----------------------------------------------------
    // __ __ ____
    // \ \ / / | _ \
    // \ V / ______ | |_) | _____ __
    // > < |______| | _ < / _ \ \/ /
    // / . \ | |_) | (_) > <
    // /_/ \_\ |____/ \___/_/\_\
    public static boolean readXboxButtons(int id) {
        return (ControlXbox.getRawButton(id));
    }

    public static double readXboxAxis(int axis){
        return(ControlXbox.getRawAxis(axis));
    }
}