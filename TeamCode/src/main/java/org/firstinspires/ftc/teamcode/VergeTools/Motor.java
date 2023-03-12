package org.firstinspires.ftc.teamcode.VergeTools;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Teleop.DriverMode;
import org.firstinspires.ftc.teamcode.Teleop.DriverMode.*;

public class Motor {
    Util util = new Util();
    protected DcMotor motor;
    String name;
    double limitSpeedMin = -1;
    double limitSpeedmax = 1;
    boolean limitSpeedEnable;
    double mapLimitSpeed = 1;
    boolean mapLimitSpeedEnable;
    double holdingPower = 0.2;
    boolean holdEnable = false;
    public volatile boolean running = false;
    public Motor(String motorName, HardwareMap hardwareMap){

        name = motorName;
        motor = hardwareMap.get(DcMotor.class, name);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    void setDCMotorSpeed(double speed){
        motor.setPower(speed);
    }
    void RunDCMotorToPosition(int targetPosition, double speed){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        System.out.println("Running motor to pos " + targetPosition + " at a speed of " + speed);
        while(motor.isBusy()){}
    }
    public void move(double speed){
        speed = util.clamp(speed, 1);
        speed = limitSpeedEnable ? util.clamp(speed, limitSpeedMin, limitSpeedmax) : speed;
        speed = mapLimitSpeedEnable ? speed * mapLimitSpeed : speed;
        while (running){}
        setDCMotorSpeed(speed);
    }
    public void moveTo(int pos, double speed){
        speed = util.clamp(speed, 1);
        speed = Math.abs(speed);
        speed = limitSpeedEnable ? util.clamp(speed, limitSpeedMin, limitSpeedmax) : speed;
        speed = mapLimitSpeedEnable ? util.clamp(speed, mapLimitSpeed) : speed;
        while (running){}
        running = true;
        MotorThread motorThread = new MotorThread(pos, speed);
        motorThread.start();
        motorThread.setName("MotorThread");
    }
    public void stop(){
        if(holdEnable){
            move(holdingPower);
        }else{
            move(0);
        }
    }
    public void finish(){
        while (running){}
    }
    public void hold(double amount){
        holdEnable = true;
        holdingPower = amount;
        move(amount);
    }
    public void hold(boolean enable){
        holdEnable = enable;
        if(enable){
            move(holdingPower);
        }else{
            move(0);
        }
    }
    public void limit(double limit){
        limitSpeedMin = -Math.abs(util.clamp(limit, 1));
        limitSpeedmax = Math.abs(util.clamp(limit, 1));
        limitSpeedEnable = true;
    }
    public void limit(double min, double max){
        limitSpeedMin = util.clamp(min, 1);
        limitSpeedmax = util.clamp(max, 1);
        limitSpeedEnable = true;
    }
    public void limit(boolean limit){
        limitSpeedEnable = limit;
    }
    public void mapLimit(double speed){
        mapLimitSpeedEnable = true;
        speed = Math.abs(util.clamp(speed, 1));
        mapLimitSpeed = speed;
    }
    public void mapLimit(double min, double max){

    }
    public void mapLimit(boolean enable){
        mapLimitSpeedEnable = enable;
    }
    private class MotorThread extends Thread {
        int targetPosition;
        double speed;
        MotorThread(int pos, double s){
            targetPosition = pos;
            speed = s;
        }
        public void run() {
            try {
                RunDCMotorToPosition(targetPosition, speed);
                if(holdEnable){
                    setDCMotorSpeed(holdingPower);
                }
                running = false;

            } catch (Exception e) {
                // Throwing an exception
                System.out.println(e);
            }
        }
    }
}