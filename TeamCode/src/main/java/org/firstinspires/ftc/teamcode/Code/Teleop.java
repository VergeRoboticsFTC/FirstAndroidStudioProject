package org.firstinspires.ftc.teamcode.Code;
import org.firstinspires.ftc.teamcode.VergeTools.*;

public class Teleop {
    static Motor motor = new Motor("motor");
    public static void Init(){
        motor.initMotor();
    }
    public static void Loop(){
        motor.move(0.5);
    }
}
