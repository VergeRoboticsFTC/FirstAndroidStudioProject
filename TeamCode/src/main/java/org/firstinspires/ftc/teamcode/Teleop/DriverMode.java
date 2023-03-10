package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Code.Teleop;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "DriverMode")
public class DriverMode extends OpMode{
    protected DcMotor motor;
    @Override
    public void init(){
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setPower(0.2);
    }

    @Override
    public void loop(){
    }
}