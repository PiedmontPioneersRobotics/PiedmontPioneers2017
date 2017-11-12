package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FWDTeleop: Teleop", group="FWD")
public class FWDTeleop extends OpMode{
    HardwareFWD robot  = new HardwareFWD();
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
        int lifterPosition;
        lifterPosition = robot.lifter.getCurrentPosition();
        telemetry.addData("lifter",  "%d", lifterPosition);
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
        robot.Right1.setPower(rightSpeed);
        robot.Right2.setPower(rightSpeed);
        robot.Left1.setPower(leftSpeed);
        robot.Left2.setPower(leftSpeed);
        telemetry.addData("left",  "%.2f", leftSpeed);
        telemetry.addData("left",  "%.2f", leftSpeed);
        telemetry.addData("right",  "%.2f", rightSpeed);
        telemetry.addData("right",  "%.2f", rightSpeed);

        /* if (gamepad1.left_bumper) {
            robot.starboardGripper.setPosition(0);
            robot.portGripper.setPosition(1);

        }else if (gamepad1.right_bumper) {
            robot.starboardGripper.setPosition(1);
            robot.portGripper.setPosition(0);
        }else {
            robot.starboardGripper.setPosition(1);
            robot.portGripper.setPosition(0);
        } */


        if (lifterPosition > 8640) {
            robot.lifter.setTargetPosition(lifterPosition - 10);
            robot.lifter.setPower(-0.5);
            telemetry.addLine("Caution: Lifter is too high");
            telemetry.update();
        } else if (lifterPosition < 0){
            robot.lifter.setTargetPosition(lifterPosition + 10);
            robot.lifter.setPower(0.5);
            telemetry.addLine("Caution: Lifter is too low");
            telemetry.update();
        } else if (a_pressed && !a_previously_pressed) {
            robot.lifter.setTargetPosition(lifterPosition + 2600);
            robot.lifter.setPower(0.5);
        } else if (b_pressed && !b_previously_pressed) {
            robot.lifter.setTargetPosition(lifterPosition - 2600);
            robot.lifter.setPower(-0.5);
        }
        a_previously_pressed = a_pressed;

        if ((gamepad1.left_bumper)&&(gamepad1.left_bumper)) {
            robot.starboardGripper.setPosition(1);
            robot.portGripper.setPosition(0);
        } else {
            double rgp = 1 - (0.11 * (gamepad1.right_trigger) + 0.4);
            double lgp = 0.11 * gamepad1.left_trigger + 0.3;
            robot.starboardGripper.setPosition(rgp);
            robot.portGripper.setPosition(lgp);
            telemetry.addData("starboard gripper", "%.2f", rgp);
            telemetry.addData("port gripper", "%.2f", lgp);

        }
    }
    @Override
    public void stop() {
    }
}
