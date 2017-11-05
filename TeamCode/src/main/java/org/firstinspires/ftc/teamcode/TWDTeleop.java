package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TWDTeleop: Teleop", group="TWD")
public class TWDTeleop extends OpMode{
    HardwareTWD robot  = new HardwareTWD();
    boolean a_previously_pressed = false;
    boolean b_previously_pressed = false;

    @Override
    public void init() {
        telemetry.addData("Say", "B4 init dog");


        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello Driver");
        telemetry.update();
    }
    @Override
    public void init_loop() {}
    @Override
    public void start() {}
    @Override
    public void loop() {

        boolean a_pressed;
        boolean b_pressed;
        a_pressed = gamepad1.a;
        b_pressed = gamepad1.b;
        double rightSpeed;
        double leftSpeed;
        rightSpeed = gamepad1.right_stick_y;
        leftSpeed = -gamepad1.left_stick_y;
        rightSpeed = rightSpeed * rightSpeed * rightSpeed;
        leftSpeed = leftSpeed * leftSpeed * leftSpeed;
        robot.Right1.setPower(rightSpeed );

        robot.Left1.setPower(leftSpeed);

        telemetry.addData("left",  "%.2f", leftSpeed);
        telemetry.addData("left",  "%.2f", leftSpeed);
        telemetry.addData("right",  "%.2f", rightSpeed);
        telemetry.addData("right",  "%.2f", rightSpeed);






    }
    @Override
    public void stop() {
    }
}
