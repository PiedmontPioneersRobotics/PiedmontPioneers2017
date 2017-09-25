package org.firstinspires.ftc.robotcontroller.external.samples;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
public class HardwareOneTrickOgre {
    public DcMotor  OgreMotor   = null;
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    public HardwareOneTrickOgre(){}
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        OgreMotor  = hwMap.get(DcMotor.class, "OgreDrive");
        OgreMotor.setDirection(DcMotor.Direction.FORWARD);
        OgreMotor.setPower(0);
        OgreMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
 }
