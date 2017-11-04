package org.firstinspires.ftc.teamcode;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
public abstract class AutonomousBase extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";
    RelicRecoveryVuMark vuMark;

    OpenGLMatrix lastLocation = null;
    private ElapsedTime runtime = new ElapsedTime();
    VuforiaLocalizer vuforia;
    // READ THIS: This main code is for RedBottom!!
    public boolean RedBottom;
    public boolean BlueBottom;
    public boolean RedTop;
    public boolean BlueTop;


    HardwareFWD robot  = new HardwareFWD();
    public boolean Center;
    public boolean Left;
    public boolean Right;

    //knock off jewel routine
    public void KnockoffJewel(Boolean opMode) {
        String jewelColor;
        robot.jewelMover.setPosition(0.5);
        sleep(5000);
        //extend jewel arm
        float hsvValues[] = {0F,0F,0F};
        Color.RGBToHSV(robot.colorSensor.red() * 8, robot.colorSensor.green() * 8, robot.colorSensor.blue() * 8, hsvValues);
        telemetry.addData("Red: ", robot.colorSensor.red());
        telemetry.addData("Green: ", robot.colorSensor.green());
        telemetry.addData("Blue: ", robot.colorSensor.blue());
        telemetry.update();
        sleep(5000);
        if (robot.colorSensor.red()>2 && robot.colorSensor.blue()<2) {
            jewelColor = "Red";
            telemetry.addLine("Red");
            telemetry.update();
        }else if (robot.colorSensor.red()<2 && robot.colorSensor.blue()>2){
            jewelColor = "Blue";
            telemetry.addLine("Blue");
            telemetry.update();
        }else {
            jewelColor = "None";
            telemetry.addLine("None");
            telemetry.update();
        }
        sleep(5000);
        if (jewelColor.equals("Red")) {
            if (RedBottom) {
                leftTurn(0.25, 1);
                rightTurn(0.25, 1);
            } else if (RedTop) {
                leftTurn(0.25, 1);
                rightTurn(0.25, 1);
            } else if (BlueBottom) {
                rightTurn(0.25, 1);
                leftTurn(0.25, 1);
            } else if (BlueTop) {
                rightTurn(0.25, 1);
                leftTurn(0.25, 1);
            }

        } else if (jewelColor.equals("Blue")) {
            if (RedBottom) {
                rightTurn(0.25, 1);
                leftTurn(0.25, 1);
            } else if (RedTop) {
                rightTurn(0.25, 1);
                leftTurn(0.25, 1);
            } else if (BlueBottom) {
                leftTurn(0.25, 1);
                rightTurn(0.25, 1);
            } else if (BlueTop) {
                leftTurn(0.25, 1);
                rightTurn(0.25, 1);
            }
        }
        robot.jewelMover.setPosition(1);
    }
    double rightSpeed;
    double leftSpeed;
    //stop driving
    public void stopDriving(){
        robot.Right1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left1.setPower(0);
        robot.Left2.setPower(0);
    }

    //drive forward
    public void driveForward(double speed, double time) {
        telemetry.addData(">", "Start drive forward");
        telemetry.addData("Right speed:", "%.2f", rightSpeed);
        telemetry.addData("Left speed:", "%.2f", leftSpeed);
        runtime.reset();
        leftSpeed = speed;
        rightSpeed = -speed;
        while (opModeIsActive() && (runtime.seconds() < time)) {
            robot.Right1.setPower(rightSpeed);
            robot.Right2.setPower(rightSpeed);
            robot.Left1.setPower(leftSpeed);
            robot.Left2.setPower(leftSpeed);
        }

        robot.Right1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left1.setPower(0);
        robot.Left2.setPower(0);
    }

    //drive backward
    public void driveBackward(double speed, double time) {
        telemetry.addData(">", "Start drive backward");
        telemetry.addData("Right speed:", "%.2f", rightSpeed);
        telemetry.addData("Left speed:", "%.2f", leftSpeed);
        runtime.reset();
        leftSpeed = -speed;
        rightSpeed = speed;
        while (opModeIsActive() && (runtime.seconds() < time)) {
            robot.Right1.setPower(rightSpeed);
            robot.Right2.setPower(rightSpeed);
            robot.Left1.setPower(leftSpeed);
            robot.Left2.setPower(leftSpeed);
        }

        robot.Right1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left1.setPower(0);
        robot.Left2.setPower(0);
    }

    //turn left
    public void leftTurn(double speed, double time){
        telemetry.addData(">", "Start turn left");
        telemetry.addData("Right speed:", "%.2f", rightSpeed);
        telemetry.addData("Left speed:", "%.2f", leftSpeed);
        runtime.reset();
        leftSpeed = -speed;
        rightSpeed = -speed;
        while (opModeIsActive() && (runtime.seconds() < time)) {
            robot.Right1.setPower(rightSpeed);
            robot.Right2.setPower(rightSpeed);
            robot.Left1.setPower(leftSpeed);
            robot.Left2.setPower(leftSpeed);
        }
    }

    // turn right
    public void rightTurn(double speed, double time){
        telemetry.addData(">", "Start turn left");
        telemetry.addData("Right speed:", "%.2f", rightSpeed);
        telemetry.addData("Left speed:", "%.2f", leftSpeed);
        runtime.reset();
        leftSpeed = speed;
        rightSpeed = speed;
        while (opModeIsActive() && (runtime.seconds() < time)) {
            robot.Right1.setPower(rightSpeed);
            robot.Right2.setPower(rightSpeed);
            robot.Left1.setPower(leftSpeed);
            robot.Left2.setPower(leftSpeed);
        }
    }

    //check vuforia and return the distance needed to get to the correct cryptobox column
    public  abstract double checkVuforia();

    //hold glyph
    public void holdGlyph(){
        //these values may be switched! Check it next time!!
        robot.starboardGripper.setPosition(0);
        robot.portGripper.setPosition(1);
    }

    //drop glyph into cryptobox
    public void dropGlyph(){
        //these values may be switched! Check it next time!!
        robot.starboardGripper.setPosition(1);
        robot.portGripper.setPosition(0);
    }
     public abstract void runOpMode();



    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
