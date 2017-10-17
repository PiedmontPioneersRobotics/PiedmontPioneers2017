package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FWDTeleop: Encoded", group="FWD")
public class FWDTeleopEncoder extends OpMode{
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
        int lifterPosition;
        double rightSpeed;
        double leftSpeed;
        rightSpeed = gamepad1.right_stick_y;
        leftSpeed = -gamepad1.left_stick_y;
        rightSpeed = rightSpeed * rightSpeed * rightSpeed;
        leftSpeed = leftSpeed * leftSpeed * leftSpeed;
        lifterPosition = robot.lifter.getCurrentPosition();
        robot.Right1.setPower(rightSpeed );
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






        if (lifterPosition <= 2880) {
            robot.lifter.setPower(0);
        }else if(lifterPosition <= 0) {
            robot.lifter.setPower(0);
        }else if(gamepad1.b) {
            robot.lifter.setPower(255);
            robot.lifter.setTargetPosition(lifterPosition + 2880);
        }else if(gamepad1.a) {
            robot.lifter.setPower(-255);
            robot.lifter.setTargetPosition(lifterPosition - 2880);
        }



        if ((gamepad1.left_bumper)&&(gamepad1.left_bumper)) {
            robot.starboardGripper.setPosition(1);
            robot.portGripper.setPosition(0);
        } else {
            double rgp = 1 - (0.11 * (gamepad1.right_trigger) + 0.2);
            double lgp = 0.11 * gamepad1.left_trigger + 0.2;
            robot.starboardGripper.setPosition(rgp);
            robot.portGripper.setPosition(lgp);
            telemetry.addData("starboard gripper", "%.2f", rgp);
            telemetry.addData("port gripper", "%.2f", lgp);
            telemetry.addData("lifter", lifterPosition);
        }
    }
    @Override
    public void stop() {
    }
}