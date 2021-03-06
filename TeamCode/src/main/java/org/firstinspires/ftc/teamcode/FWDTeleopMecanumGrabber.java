package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="FWDTeleop: Mecanum Glyph Grabber", group="FWD")
public class FWDTeleopMecanumGrabber extends OpMode{
    HardwareFWDMecanumGrabber robot  = new HardwareFWDMecanumGrabber();


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

        //gripper code
        double rgp = 1 - (0.11 * (gamepad1.right_trigger) + 0.4);
        double lgp = 0.11 * gamepad1.left_trigger + 0.3;
        robot.starboardGripper.setPosition(rgp);
        robot.portGripper.setPosition(lgp);
        telemetry.addData("starboard gripper", "%.2f", rgp);
        telemetry.addData("port gripper", "%.2f", lgp);


        //sucker code
        if (gamepad1.b) {
            robot.rightMecanumGlyphSucker.setPower(-1);
            robot.leftMecanumGlyphSucker.setPower(1);
        } else if ((robot.glyphLocatorTop.getDistance(DistanceUnit.CM) < 2) && (robot.glyphInSensor.getState() == true)) {
            robot.rightMecanumGlyphSucker.setPower(0);
            robot.leftMecanumGlyphSucker.setPower(0);
        } else {
            robot.rightMecanumGlyphSucker.setPower(1);
            robot.leftMecanumGlyphSucker.setPower(-1);
        }

        double rightSpeed;
        double leftSpeed;


/*
        //driving code
        if (gamepad1.left_stick_button) {

            //mecanum drive
            double radians = Math.atan(gamepad1.left_stick_x/gamepad1.left_stick_y);
            telemetry.addData("The radian is", radians);
            telemetry.update();
            double FLBRspeed = Math.sin(radians+3.14156/4);
            double FRBLspeed = Math.sin(radians+3.14156*3/4);
            telemetry.addData("The Front Left Back Right speed is (FLBR):", FLBRspeed);
            telemetry.addData("The Front Right Back Left speed is (FRBL):", FRBLspeed);
            telemetry.update();
            robot.Left1.setPower(FLBRspeed);    //leftForwardMecanumWheel
            robot.Right1.setPower(FRBLspeed);   //rightForwardMecanumWheel
            robot.Left2.setPower(FRBLspeed);    //leftBackwardMecanumWheel
            robot.Right2.setPower(FLBRspeed);   //rightBackwardMecanumWheel

        } else {
            //normal drive

            //deadzone right
            if(gamepad1.right_stick_y < 0.05 && gamepad1.right_stick_y>-0.05){
                robot.Right1.setPower(0);
                robot.Left1.setPower(0);
                robot.Right2.setPower(0);
                robot.Left2.setPower(0);
            }

            //deadzone left
            if(gamepad1.left_stick_y < 0.05 && gamepad1.left_stick_y>-0.05){
                robot.Right1.setPower(0);
                robot.Left1.setPower(0);
                robot.Right2.setPower(0);
                robot.Left2.setPower(0);
            }

            //fine tune right
            if(gamepad1.right_stick_y>0.9) {
                robot.Right1.setPower(1);
                robot.Right2.setPower(1);
            } else if(gamepad1.right_stick_y<-0.9) {
                robot.Right1.setPower(-1);
                robot.Right2.setPower(-1);
            } else {
                rightSpeed = gamepad1.right_stick_y;
                rightSpeed = rightSpeed * rightSpeed * rightSpeed * 0.5;
                robot.Right1.setPower(rightSpeed);
                robot.Right2.setPower(rightSpeed);
                telemetry.addData("right",  "%.2f", rightSpeed);
                telemetry.addData("right",  "%.2f", rightSpeed);
            }

            //fine tune left
            if(gamepad1.left_stick_y>0.9) {
                robot.Left1.setPower(-1);
                robot.Left2.setPower(-1);
            } else if(gamepad1.left_stick_y<-0.9) {
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
        */

    }
    @Override
    public void stop() {
    }
}
