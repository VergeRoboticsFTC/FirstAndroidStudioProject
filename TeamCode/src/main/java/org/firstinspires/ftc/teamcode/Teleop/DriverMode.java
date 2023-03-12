package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Code.Teleop;
import org.firstinspires.ftc.teamcode.VergeTools.Motor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "DriverMode")
public class DriverMode extends OpMode{
    protected DcMotor motor;
    Motor motor1;
    Teleop teleop;
    @Override
    public void init(){
        Teleop teleop = new Teleop(hardwareMap);
        teleop.Init();
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop(){
        teleop.Loop();
        motor.setPower(1);
    }
}