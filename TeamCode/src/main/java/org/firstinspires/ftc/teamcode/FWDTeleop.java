package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FWDTeleop: Teleop", group="FWD")
public class FWDTeleop extends OpMode{
    HardwareFWD robot  = new HardwareFWD();

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
        double childSpeed;
        double OgreSpeed;
        OgreSpeed = -gamepad1.left_stick_y;
        childSpeed = -gamepad1.right_stick_y;

        robot.Right1.setPower(OgreSpeed);
        robot.Right2.setPower(OgreSpeed);
        robot.Left1.setPower(childSpeed);
        robot.Left2.setPower(childSpeed);
        telemetry.addData("right",  "%.2f", OgreSpeed);
        telemetry.addData("right",  "%.2f", OgreSpeed);
        telemetry.addData("left",  "%.2f", childSpeed);
        telemetry.addData("left",  "%.2f", childSpeed);

    }
    @Override
    public void stop() {
    }
}
