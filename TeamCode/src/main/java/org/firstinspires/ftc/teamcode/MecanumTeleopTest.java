package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FWDTeleop: Mecanum Test", group="FWD")
public class MecanumTeleopTest extends OpMode{
    MecanumHardwareFWD robot  = new MecanumHardwareFWD();

    public void mecanumTranslation() {
        double radians = Math.atan(gamepad1.left_stick_x/gamepad1.left_stick_y);
        telemetry.addData("The radian is", radians);
        telemetry.update();
        double FLBRspeed = Math.sin(radians+Math.PI/4);
        double FRBLspeed = Math.sin(radians+Math.PI*3/4);
        telemetry.addData("The Front Left Back Right speed is (FLBR):", FLBRspeed);
        telemetry.addData("The Front Right Back Left speed is (FRBL):", FRBLspeed);
        telemetry.update();
        /*robot.Left1.setPower(FLBRspeed);
        robot.Right1.setPower(FRBLspeed);
        robot.Left2.setPower(FRBLspeed);
        robot.Right2.setPower(FLBRspeed);*/
    }

    public void mecanumRotation(double speed) {
        if (gamepad1.right_stick_x < 0){
            robot.Left1.setPower(-speed);
            robot.Right1.setPower(speed);
            robot.Left2.setPower(-speed);
            robot.Right2.setPower(speed);
            telemetry.addData("You are rotating:", "LEFT");
            telemetry.update();
        } else if (gamepad1.right_stick_x > 0){
            robot.Left1.setPower(speed);
            robot.Right1.setPower(-speed);
            robot.Left2.setPower(speed);
            robot.Right2.setPower(-speed);
            telemetry.addData("You are rotating:", "RIGHT");
            telemetry.update();
        }
    }
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
        telemetry.addData("Nothing has happened.","About to run ROTATION.");
        telemetry.update();
        mecanumRotation(0.5);
        telemetry.addData("Already done rotation.","About to run TRANSLATION.");
        telemetry.update();
        mecanumTranslation();
        //platform pusher code
        /*if (gamepad1.b) {
            robot.platformPusher.setPower(0.2);
        } else if (gamepad1.x) {
            robot.platformPusher.setPower(-0.2);
        } else {
        robot.platformPusher.setPower(0);
    }*/
    }
    @Override
    public void stop() {
    }
}
