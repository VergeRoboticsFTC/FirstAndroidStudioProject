package org.firstinspires.ftc.teamcode.Code;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.VergeTools.Motor;

public class Teleop {
    //Code to link back to the main DriverMode file.
    HardwareMap hardwareMap;
    public Teleop(HardwareMap hardwareMapInput){
        hardwareMap = hardwareMapInput;
    }

    //Declare your motors from here.
    Motor motor;

    public void Init(){
        motor = new Motor("motor1", hardwareMap);
    }
    public void Loop() {
        motor.move(1);
    }
}
