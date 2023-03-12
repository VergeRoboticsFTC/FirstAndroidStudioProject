package org.firstinspires.ftc.teamcode.VergeTools;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Chassis {
    Util util = new Util();
    Motor[] motors = new Motor[4];
    int[] motorPorts = new int[4];
    int forward = 1;
    int motorCount;
    double limitSpeedX = 1;
    boolean limitSpeedEnableX = false;
    double limitSpeedY = 1;
    boolean limitSpeedEnableY = false;
    double limitSpeedRotate = 1;
    boolean limitSpeedRotateEnable = false;
    double mapLimitSpeedX = 1;
    boolean mapLimitSpeedEnableX = false;
    double mapLimitSpeedY = 1;
    boolean mapLimitSpeedEnableY = false;
    double mapLimitSpeedRotate = 1;
    boolean mapLimitSpeedRotateEnable = false;
    public Chassis(int frontRight, int backRight, int frontLeft, int backLeft, HardwareMap hardwareMap){
        motorPorts[0] = frontRight;
        motorPorts[1] = backRight;
        motorPorts[2] = backLeft;
        motorPorts[3] = frontLeft;
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor("motor" + motorPorts[i], hardwareMap);
        }
        motorCount = motors.length;
    }
    boolean isRunning(){
        boolean output = false;
        for (int i = 0; i < motorCount; i++) {
            output = motors[i].running ? true : output;
        }
        return output;
    }
    public void finish(){
        while (isRunning()){}
    }
    public XAxis x = new XAxis();
    public class XAxis{
        public void move(double speed){
            speed = limitSpeedEnableX ? util.clamp(speed, limitSpeedX) : speed;
            for (int i = 0; i < motorCount; i++) {
                motors[i].move((i > 1 ? -forward : forward) * speed * mapLimitSpeedX);
            }
        }
        public void moveTo(int pos, double speed){
            speed = limitSpeedEnableX ? util.clamp(speed, limitSpeedX) : speed;
            for (int i = 0; i < motorCount; i++) {
                motors[i].moveTo((i > 1 ? -forward : forward) * pos, speed  * mapLimitSpeedX);
            }
        }
        public void limit(double speed){
            limitSpeedEnableX = true;
            limitSpeedX = speed;
        }
        public void limit(boolean enable){
            limitSpeedEnableX = enable;
        }
        public void mapLimit(double speed){
            mapLimitSpeedX = Math.abs(util.clamp(speed, 1));
            mapLimitSpeedEnableX = true;
        }
        public void mapLimit(boolean enable){
            mapLimitSpeedEnableX = enable;
        }
    }
    public YAxis y = new YAxis();
    public class YAxis{
        public void move(double speed){
            for (int i = 0; i < motorCount; i++) {
                motors[i].move((i % 2 == 0 ? -forward : forward) * speed * mapLimitSpeedY);
            }
        }
        public void moveTo(int pos, double speed){
            speed = limitSpeedEnableY ? util.clamp(speed, limitSpeedY) : speed;
            for (int i = 0; i < motorCount; i++) {
                motors[i].moveTo((i % 2 == 0 ? -forward : forward) * pos, speed * mapLimitSpeedY);
            }
        }
        public void limit(double speed){
            limitSpeedEnableY = true;
            limitSpeedY = speed;
        }
        public void limit(boolean enable){
            limitSpeedEnableY = enable;
        }
        public void mapLimit(double speed){
            mapLimitSpeedY = Math.abs(util.clamp(speed, 1));
            mapLimitSpeedEnableY = true;
        }
        public void mapLimit(boolean enable){
            mapLimitSpeedEnableY = enable;
        }
    }
    public Rotate rotate = new Rotate();
    public class Rotate{
        public void move(double speed){
            for (int i = 0; i < motorCount; i++) {
                motors[i].move(forward * speed);
            }
        }
        public void moveTo(int pos, double speed){
            speed = limitSpeedRotateEnable ? util.clamp(speed, limitSpeedRotate) : speed;
            for (int i = 0; i < motorCount; i++) {
                motors[i].moveTo(forward * pos, speed * mapLimitSpeedRotate);
            }
        }
        public void limit(double speed){
            limitSpeedRotateEnable = true;
            limitSpeedRotate = speed;
        }
        public void limit(boolean enable){
            limitSpeedRotateEnable = enable;
        }
        public void mapLimit(double speed){
            mapLimitSpeedRotate = Math.abs(util.clamp(speed, 1));
            mapLimitSpeedRotateEnable = true;
        }
        public void mapLimit(boolean enable){
            mapLimitSpeedRotateEnable = enable;
        }
    }
}