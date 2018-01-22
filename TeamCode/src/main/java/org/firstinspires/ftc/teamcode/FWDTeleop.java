package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static java.lang.Math.atan;
import static java.lang.Math.round;
import static java.lang.Thread.sleep;

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
        int relicArmPosition;
        //relicArmPosition = robot.RelicArm.getCurrentPosition();
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
        up_pressed = gamepad2.y;
        down_pressed = gamepad2.a;
        b_pressed = gamepad1.b;
        x_pressed = gamepad1.x;
        double rightSpeed;
        double leftSpeed;
        if(gamepad1.right_bumper) {
            rightSpeed = gamepad1.right_stick_y;
            rightSpeed = rightSpeed * rightSpeed * rightSpeed * 0.2;
            robot.Right1.setPower(rightSpeed);
            robot.Right2.setPower(rightSpeed);
            telemetry.addData("right",  "%.2f", rightSpeed);
            telemetry.addData("right",  "%.2f", rightSpeed);
            leftSpeed = gamepad1.left_stick_y;
            leftSpeed = leftSpeed * leftSpeed * leftSpeed * 0.2;
            robot.Left1.setPower(-leftSpeed);
            robot.Left2.setPower(-leftSpeed);
            telemetry.addData("left", "%.2f", leftSpeed);
            telemetry.addData("left", "%.2f", leftSpeed);

        } else {

            if (gamepad1.right_stick_y > 0.9) {
                robot.Right1.setPower(1);
                robot.Right2.setPower(1);
            } else if (gamepad1.right_stick_y < -0.9) {
                robot.Right1.setPower(-1);
                robot.Right2.setPower(-1);
            } else {
                rightSpeed = gamepad1.right_stick_y;
                rightSpeed = rightSpeed * rightSpeed * rightSpeed * 0.5;
                robot.Right1.setPower(rightSpeed);
                robot.Right2.setPower(rightSpeed);
                telemetry.addData("right", "%.2f", rightSpeed);
                telemetry.addData("right", "%.2f", rightSpeed);

            }

            if (gamepad1.left_stick_y > 0.9) {
                robot.Left1.setPower(-1);
                robot.Left2.setPower(-1);
            } else if (gamepad1.left_stick_y < -0.9) {
                robot.Left1.setPower(1);
                robot.Left2.setPower(1);
            } else {
                leftSpeed = gamepad1.left_stick_y;
                leftSpeed = leftSpeed * leftSpeed * leftSpeed * 0.5;
                robot.Left1.setPower(-leftSpeed);
                robot.Left2.setPower(-leftSpeed);
                telemetry.addData("left", "%.2f", leftSpeed);
                telemetry.addData("left", "%.2f", leftSpeed);
            }
        }

        if(gamepad2.a){
            robot.relicGrabber.setPosition(0.75);
            robot.relicArm.setPower(-0.5);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.relicArm.setPower(0);
            robot.relicGrabber.setPosition(0.5);
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else{
            robot.relicArm.setPower(gamepad2.left_stick_y*0.5);
            robot.relicGrabber.setPosition(0.25+gamepad2.right_trigger*0.5);
            robot.relicWrist.setPosition(gamepad2.left_trigger);
        }
        //mecanum wheels code

        //lifter code
        if(lifterPosition > 8640) {
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

       //HEXTANT code
    /*
        public void mecanumTranslation() {
            double radians = Math.atan(gamepad1.left_stick_x/gamepad1.left_stick_y);
            telemetry.addData("The radian is", radians);
            telemetry.update();
            double FLBRspeed = Math.sin(radians+pi/4);
            double FRBLspeed = Math.sin(radians+pi*3/4);
            telemetry.addData("The Front Left Back Right speed is (FLBR):", FLBRspeed);
            telemetry.addData("The Front Right Back Left speed is (FRBL):", FRBLspeed);
            telemetry.update();
            robot.leftForwardMecanumWheel.setPower(FLBRspeed);
            robot.rightForwardMecanumWheel.setPower(FRBLspeed);
            robot.leftBackwardMecanumWheel.setPower(FRBLspeed);
            robot.rightBackwardMecanumWheel.setPower(FLBRspeed);
        }
        //mecanum wheels code for LeftTop Quadrant
        public void mecanumGoLeft(double speed, double time) {
            robot.leftForwardMecanumWheel.setPower(-speed);
            robot.rightForwardMecanumWheel.setPower(speed);
            robot.leftBackwardMecanumWheel.setPower(speed);
            robot.rightBackwardMecanumWheel.setPower(-speed);
            sleep(time);
            robot.leftForwardMecanumWheel.setPower(0);
            robot.rightForwardMecanumWheel.setPower(0);
            robot.leftBackwardMecanumWheel.setPower(0);
            robot.rightBackwardMecanumWheel.setPower(0);
        }
        public void mecanumGoLeftLeftForward(double speed, double time) {
            robot.leftForwardMecanumWheel.setPower(-speed/2);
            robot.rightForwardMecanumWheel.setPower(speed);
            robot.leftBackwardMecanumWheel.setPower(speed);
            robot.rightBackwardMecanumWheel.setPower(-speed/2);
            sleep(time);
            robot.leftForwardMecanumWheel.setPower(0);
            robot.rightForwardMecanumWheel.setPower(0);
            robot.leftBackwardMecanumWheel.setPower(0);
            robot.rightBackwardMecanumWheel.setPower(0);
        }
        public void mecanumGoLeftForward(double speed, double time) {
            robot.leftForwardMecanumWheel.setPower(0);
            robot.rightForwardMecanumWheel.setPower(speed);
            robot.leftBackwardMecanumWheel.setPower(speed);
            robot.rightBackwardMecanumWheel.setPower(0);
            sleep(time);
            robot.leftForwardMecanumWheel.setPower(0);
            robot.rightForwardMecanumWheel.setPower(0);
            robot.leftBackwardMecanumWheel.setPower(0);
            robot.rightBackwardMecanumWheel.setPower(0);
        }
        public void mecanumGoLeftForwardForward(double speed, double time) {
            robot.leftForwardMecanumWheel.setPower(speed/2);
            robot.rightForwardMecanumWheel.setPower(speed);
            robot.leftBackwardMecanumWheel.setPower(speed);
            robot.rightBackwardMecanumWheel.setPower(speed/2);
            sleep(time);
            robot.leftForwardMecanumWheel.setPower(0);
            robot.rightForwardMecanumWheel.setPower(0);
            robot.leftBackwardMecanumWheel.setPower(0);
            robot.rightBackwardMecanumWheel.setPower(0);
        }
        public void mecanumGoForward(double speed, double time) {
            robot.leftForwardMecanumWheel.setPower(speed);
            robot.rightForwardMecanumWheel.setPower(speed);
            robot.leftBackwardMecanumWheel.setPower(speed);
            robot.rightBackwardMecanumWheel.setPower(speed);
            sleep(time);
            robot.leftForwardMecanumWheel.setPower(0);
            robot.rightForwardMecanumWheel.setPower(0);
            robot.leftBackwardMecanumWheel.setPower(0);
            robot.rightBackwardMecanumWheel.setPower(0);
        }
        */

        /*
        //mecanum wheel glyph grabber code
        if (b_pressed && !b_previously_pressed) {
            while (runtime.seconds() < 1) {
                robot.rightmecanumGlyphSucker.setPower(-1);
                robot.leftmecanumGlyphSucker.setPower(-1);
            }
        } else if (!(glyphInSensor.getState() == false) && (glyphLocator.getDistance(DistanceUnit.CM) < 8)) {
            robot.rightmecanumGlyphSucker.setPower(1);
            robot.leftmecanumGlyphSucker.setPower(1);
        } else if (x_pressed && !x_previously_pressed) {
            while (runtime.seconds() < 1) {
                robot.rightmecanumGlyphSucker.setPower(-1);
                robot.leftmecanumGlyphSucker.setPower(-1);
            }
        } else {
            robot.rightmecanumGlyphSucker.setPower(0);
            robot.leftmecanumGlyphSucker.setPower(0);
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
        //platform pusher code
        if (gamepad1.b) {
            robot.platformPusher.setPower(1);
        } else if (gamepad1.x) {
            robot.platformPusher.setPower(-1);
        } else {
            robot.platformPusher.setPower(0);
        }
*/

    }
    @Override
    public void stop() {
    }
}
