package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareTWD {
    public DcMotor      Right   = null;
    public DcMotor      Left   = null;


    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    public HardwareTWD(){}
    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;
        Right  = hwMap.get(DcMotor.class, "Right1");
        Right.setDirection(DcMotor.Direction.FORWARD);
        Right.setPower(0);
        Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Left  = hwMap.get(DcMotor.class, "Left1");
        Left.setDirection(DcMotor.Direction.FORWARD);
        Left.setPower(0);
        Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

 }
