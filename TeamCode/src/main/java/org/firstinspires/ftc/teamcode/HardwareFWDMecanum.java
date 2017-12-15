package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class HardwareFWDMecanum {
    public final static double j_up = 0.6;
    public final static double j_down = 0;
    public DcMotor                 Right1   = null;
    public DcMotor                 Right2   = null;
    public DcMotor                 Left1   = null;
    public DcMotor                 Left2   = null;
    public DcMotor                 lifter = null;
    public DcMotor                 platformPusher = null;
    //public DcMotor                 rightMechanumGlyphSucker = null;
    //public DcMotor                 leftMechanumGlyphSucker = null;
    public Servo                   starboardGripper = null;
    public Servo                   portGripper = null;
    public Servo                   jewelMover = null;
    public Servo                   columnCounterArm = null;
    public ColorSensor             colorSensor = null;
    public OpticalDistanceSensor   columnCounter = null;
    //public SensorMRRangeSensor     glyphLocator = null;
    //public DigitalChannel          glyphInSensor = null;

    //public DcMotor                 RelicArm = null;
    //public Servo                   RelicGrabber = null;

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    public HardwareFWDMecanum(){}
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
        //rightMechanumGlyphSucker = hwMap.get(DcMotor.class, "Right Mecanum Glyph Sucker");
        //rightMechanumGlyphSucker.setDirection(DcMotor.Direction.FORWARD);
        //rightMechanumGlyphSucker.setPower(0);
        //rightMechanumGlyphSucker.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //leftMechanumGlyphSucker = hwMap.get(DcMotor.class, "Right Mecanum Glyph Sucker");
        //leftMechanumGlyphSucker.setDirection(DcMotor.Direction.FORWARD);
        //leftMechanumGlyphSucker.setPower(0);
        //leftMechanumGlyphSucker.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        jewelMover = hwMap.get(Servo.class, "Jewel Grabber");
        starboardGripper = hwMap.get(Servo.class, "Starboard gripper");
        portGripper = hwMap.get(Servo.class, "Port gripper");
        columnCounterArm = hwMap.get(Servo.class, "Column Counter Arm");
        columnCounterArm.setPosition(0.5);
        colorSensor = hwMap.get(ColorSensor.class, "Color Sensor");
        colorSensor.enableLed(true);    //color sensor light
        columnCounter = hwMap.get(OpticalDistanceSensor.class, "Column Counter");
        //glyphLocator = hwMap.get(SensorMRRangeSensor.class, "Glyph Locator");
        //glyphInSensor = hwMap.get(DigitalChannel.class, "Glyph In Sensor");
        //glyphInSensor.setMode(DigitalChannel.Mode.INPUT);
        double rgp = 1 - 0.2;   //right gripper position
        double lgp = 0.2;       //left gripper position
        starboardGripper.setPosition(rgp);
        portGripper.setPosition(lgp);
        jewelMover.setPosition(j_up);

        //RelicArm  = hwMap.get(DcMotor.class, "RelicArm");
        //RelicArm.setDirection(DcMotor.Direction.FORWARD);
        //RelicArm.setPower(0);
        //RelicArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //RelicGrabber = hwMap.get(Servo.class, "RelicGrabber");
        //RelicGrabber.setPosition(0);
    }

 }
