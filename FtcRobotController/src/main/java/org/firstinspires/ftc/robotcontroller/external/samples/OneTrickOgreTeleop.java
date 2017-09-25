package org.firstinspires.ftc.robotcontroller.external.samples;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="OneTrickOgre: Teleop", group="OneTrick")
public class OneTrickOgreTeleop extends OpMode{
    HardwareOneTrickOgre robot      = new HardwareOneTrickOgre();

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello Driver");
    }
    @Override
    public void init_loop() {}
    @Override
    public void start() {}
    @Override
    public void loop() {
        double OgreSpeed;
        OgreSpeed = -gamepad1.left_stick_y;
        robot.OgreMotor.setPower(OgreSpeed);
        telemetry.addData("left",  "%.2f", OgreSpeed);

    }
    @Override
    public void stop() {
    }
}
