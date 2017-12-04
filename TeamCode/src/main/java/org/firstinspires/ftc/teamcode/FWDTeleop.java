package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FWDTeleop: Teleop", group="FWD")
public class FWDTeleop extends OpMode{
    HardwareFWD robot  = new HardwareFWD();
    boolean a_previously_pressed = false;
    boolean y_previously_pressed = false;
    boolean up_previously_pressed = false;
    boolean down_previously_pressed = false;
    boolean b_previously_pressed = false;
    boolean x_previously_pressed = false;

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
        int relicGrabberPosition;
        //relicGrabberPosition = robot.RelicArm.getCurrentPosition();
        //int platformPusherPosition;
        //platformPusherPosition = robot.platformPusher.getCurrentPosition();
        telemetry.addData("lifter",  "%d", lifterPosition);
        //telemetry.addData("Relic Arm",  "%d", relicGrabberPosition);
        boolean a_pressed;
        boolean y_pressed;
        boolean up_pressed;
        boolean down_pressed;
        boolean b_pressed;
        boolean x_pressed;
        y_pressed = gamepad1.y;
        a_pressed = gamepad1.a;
        up_pressed = gamepad1.dpad_up;
        down_pressed = gamepad1.dpad_down;
        b_pressed = gamepad1.b;
        x_pressed = gamepad1.x;
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

        //lifter code
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
        } else if (y_pressed && !y_previously_pressed) {
            robot.lifter.setTargetPosition(lifterPosition + 2800);
            robot.lifter.setPower(0.5);
        } else if (a_pressed && !a_previously_pressed) {
            robot.lifter.setTargetPosition(lifterPosition - 2800);
            robot.lifter.setPower(-0.5);
        }
        a_previously_pressed = a_pressed;
        y_previously_pressed = y_pressed;

        /*
        //mechanum wheel glyph grabber code
        if (!(glyphInSensor.getState() == false) && (glyphLocator.getDistance(DistanceUnit.CM) < 8 inches)) {
            robot.rightMechanumGlyphSucker.setPower(1);
            robot.leftMechanumGlyphSucker.setPower(1);
        }
        if (b_pressed && !b_previously_pressed) {
            while (runtime.seconds() < 1) {
                robot.rightMechanumGlyphSucker.setPower(-1);
                robot.leftMechanumGlyphSucker.setPower(-1);
            }
        } else if (x_pressed && !x_previously_pressed) {
            while (runtime.seconds() < 1) {
                robot.rightMechanumGlyphSucker.setPower(-1);
                robot.leftMechanumGlyphSucker.setPower(-1);
            }
        } else {
            robot.rightMechanumGlyphSucker.setPower(0);
            robot.leftMechanumGlyphSucker.setPower(0);
        }*/

        //glyph grabber code
        if (gamepad1.left_bumper) {
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
        /*
        //relic grabber code
        if (relicGrabberPosition > 360) {
            robot.lifter.setTargetPosition(lifterPosition - 2);
            robot.lifter.setPower(-0.5);
            telemetry.addLine("Caution: Relic Grabber out of position");
            telemetry.update();
        } else if (relicGrabberPosition < 0){
            robot.lifter.setTargetPosition(lifterPosition + 2);
            robot.lifter.setPower(0.5);
            telemetry.addLine("Caution: Relic Grabber out of position");
            telemetry.update();
        } else if (up_pressed && !up_previously_pressed) {
            robot.lifter.setTargetPosition(lifterPosition + 360);
            robot.lifter.setPower(0.5);
        } else if (down_pressed && !down_previously_pressed) {
            robot.lifter.setTargetPosition(lifterPosition - 360);
            robot.lifter.setPower(-0.5);
        }
        up_previously_pressed = up_pressed;
        down_previously_pressed = down_pressed;*/

        //platform pusher code
        if (gamepad1.b) {
            robot.platformPusher.setPower(0.2);
        } else if (gamepad1.x) {
            robot.platformPusher.setPower(-0.2);
        } else {
            robot.platformPusher.setPower(0);
        }
    }
    @Override
    public void stop() {
    }
}
