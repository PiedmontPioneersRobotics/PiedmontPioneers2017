package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorDigitalTouch;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;


public class HardwareFWDMecanumGrabber {
    public final static double j_up = 0.6;
    public final static double j_down = 0;
    public DcMotor                 Right1   = null;
    public DcMotor                 Right2   = null;
    public DcMotor                 Left1   = null;
    public DcMotor                 Left2   = null;
    public DcMotor                 lifter = null;
    public DcMotor                 platformPusher = null;
    public DcMotor                 rightMecanumGlyphSucker = null;
    public DcMotor                 leftMecanumGlyphSucker = null;
    public Servo                   starboardGripper = null;
    public Servo                   portGripper = null;
    public Servo                   jewelMover = null;
    public Servo                   columnCounterArm = null;
    public ColorSensor             colorSensor = null;
    public OpticalDistanceSensor   columnCounter = null;
    public ModernRoboticsI2cRangeSensor glyphLocatorTop = null;
    public DigitalChannel          glyphInSensor = null;

    //public DcMotor                 relicArm = null;
    public Servo                   relicGrabber = null;
    public Servo                   relicWrist = null;

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    public HardwareFWDMecanumGrabber(){}
    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;
        Right1  = hwMap.get(DcMotor.class, "Right1");
        Right1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Right1.setDirection(DcMotor.Direction.FORWARD);
        Right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Right1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Right1.setPower(0);
        Right2  = hwMap.get(DcMotor.class, "Right2");
        Right2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Right2.setDirection(DcMotor.Direction.FORWARD);
        Right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Right2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Right2.setPower(0);
        Left1  = hwMap.get(DcMotor.class, "Left1");
        Left1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Left1.setDirection(DcMotor.Direction.FORWARD);
        Left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Left1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Left1.setPower(0);
        Left2  = hwMap.get(DcMotor.class, "Left2");
        Left2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Left2.setDirection(DcMotor.Direction.FORWARD);
        Left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Left2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Left2.setPower(0);
        lifter  = hwMap.get(DcMotor.class, "lifter");
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setDirection(DcMotor.Direction.FORWARD);
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lifter.setPower(0.5);
        lifter.setTargetPosition(0);
        platformPusher  = hwMap.get(DcMotor.class, "Platform Pusher");
        platformPusher.setDirection(DcMotor.Direction.FORWARD);
        platformPusher.setPower(0);
        platformPusher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMecanumGlyphSucker = hwMap.get(DcMotor.class, "Right Mecanum Glyph Sucker");
        rightMecanumGlyphSucker.setDirection(DcMotor.Direction.FORWARD);
        rightMecanumGlyphSucker.setPower(0);
        rightMecanumGlyphSucker.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMecanumGlyphSucker = hwMap.get(DcMotor.class, "Right Mecanum Glyph Sucker");
        leftMecanumGlyphSucker.setDirection(DcMotor.Direction.FORWARD);
        leftMecanumGlyphSucker.setPower(0);
        leftMecanumGlyphSucker.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        jewelMover = hwMap.get(Servo.class, "Jewel Grabber");
        starboardGripper = hwMap.get(Servo.class, "Starboard gripper");
        portGripper = hwMap.get(Servo.class, "Port gripper");
        columnCounterArm = hwMap.get(Servo.class, "Column Counter Arm");
        columnCounterArm.setPosition(0.5);
        colorSensor = hwMap.get(ColorSensor.class, "Color Sensor");
        colorSensor.enableLed(true);    //color sensor light
        columnCounter = hwMap.get(OpticalDistanceSensor.class, "Column Counter");
        glyphLocatorTop = hwMap.get(ModernRoboticsI2cRangeSensor.class, "Glyph Locator");
        glyphInSensor = hwMap.get(DigitalChannel.class, "sensor_digital");
        glyphInSensor.setMode(DigitalChannel.Mode.INPUT);
        double rgp = 1 - 0.2;   //right gripper position
        double lgp = 0.2;       //left gripper position
        starboardGripper.setPosition(rgp);
        portGripper.setPosition(lgp);
        jewelMover.setPosition(j_up);

        //relicArm  = hwMap.get(DcMotor.class, "RelicArm");
        //relicArm.setDirection(DcMotor.Direction.FORWARD);
        //relicArm.setPower(0);
        //relicArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        relicGrabber = hwMap.get(Servo.class, "RelicGrabber");
        relicGrabber.setPosition(0);
        relicWrist = hwMap.get(Servo.class, "RelicWrist");
        relicWrist.setPosition(0);
    }

 }
