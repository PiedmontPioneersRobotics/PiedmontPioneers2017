package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareFWD {
    public DcMotor      Right1   = null;
    public DcMotor      Right2   = null;
    public DcMotor      Left1   = null;
    public DcMotor      Left2   = null;
    public DcMotor      lifter = null;
    public Servo        starboardGripper = null;
    public Servo        portGripper = null;
    public Servo        jewelMover = null;//child
    public ColorSensor  colorSensor = null;
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    public HardwareFWD(){}
    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;
        Right1  = hwMap.get(DcMotor.class, "Right1");
        Right1.setDirection(DcMotor.Direction.FORWARD);
        Right1.setPower(0);
        Right1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Right2  = hwMap.get(DcMotor.class, "Right2");
        Right2.setDirection(DcMotor.Direction.FORWARD);
        Right2.setPower(0);
        Right2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Left1  = hwMap.get(DcMotor.class, "Left1");
        Left1.setDirection(DcMotor.Direction.FORWARD);
        Left1.setPower(0);
        Left1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Left2  = hwMap.get(DcMotor.class, "Left2");
        Left2.setDirection(DcMotor.Direction.FORWARD);
        Left2.setPower(0);
        Left2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifter  = hwMap.get(DcMotor.class, "lifter");
        lifter.setDirection(DcMotor.Direction.FORWARD);
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        jewelMover = hwMap.get(Servo.class, "Jewel Grabber");
        starboardGripper = hwMap.get(Servo.class, "Starboard gripper");
        portGripper = hwMap.get(Servo.class, "Port gripper");
        colorSensor = hwMap.get(ColorSensor.class, "Color Sensor");
        colorSensor.enableLed(true);    //color sensor light
        double rgp = 1 - 0.2;   //right gripper position
        double lgp = 0.2;       //left gripper position
        starboardGripper.setPosition(rgp);
        portGripper.setPosition(lgp);
        jewelMover.setPosition(1);

    }

 }
