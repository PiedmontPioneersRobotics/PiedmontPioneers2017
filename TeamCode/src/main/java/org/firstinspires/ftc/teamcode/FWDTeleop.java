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
        double rightSpeed;
        double leftSpeed;
        rightSpeed = gamepad1.right_stick_x;
        leftSpeed = -gamepad1.right_stick_y;

        robot.Right1.setPower(rightSpeed - leftSpeed);
        robot.Right2.setPower(rightSpeed - leftSpeed);
        robot.Left1.setPower(rightSpeed + leftSpeed);
        robot.Left2.setPower(rightSpeed + leftSpeed);
        telemetry.addData("left",  "%.2f", leftSpeed);
        telemetry.addData("left",  "%.2f", leftSpeed);
        telemetry.addData("right",  "%.2f", rightSpeed);
        telemetry.addData("right",  "%.2f", rightSpeed);

        /* if (gamepad1.left_bumper) {
            robot.starboardGripper.setPosition(0);
            robot.portGripper.setPosition(1);

        }else if (gamepad1.right_bumper) {
            robot.starboardGripper.setPosition(0.5);
            robot.portGripper.setPosition(0.5);
        }else {
            robot.starboardGripper.setPosition(1);
            robot.portGripper.setPosition(0);
        } */






        if (gamepad1.b) {
            robot.lifter.setPower(255);
        }else if(gamepad1.a) {
            robot.lifter.setPower(-255);
        }else {
            robot.lifter.setPower(0);
        }



        robot.starboardGripper.setPosition(0.11*(1-gamepad1.right_trigger)+0.2);
        robot.portGripper.setPosition(0.11*gamepad1.left_trigger+0.2);
        telemetry.addData("port gripper",  "%.2f", gamepad1.right_trigger);
        telemetry.addData("starboard gripper",  "%.2f", gamepad1.left_trigger);

    }
    @Override
    public void stop() {
    }
}
