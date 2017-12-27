package org.firstinspires.ftc.teamcode;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import java.text.DecimalFormat;

public abstract class AutonomousBaseMecanumExperimental extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    RelicRecoveryVuMark vuMark;
    // READ THIS: This main code is for RedBottom!!
    public boolean RedBottom;
    public boolean BlueBottom;
    public boolean RedTop;
    public boolean BlueTop;
    public int columnCounts = 0;


    HardwareFWDMecanum robot  = new HardwareFWDMecanum();

    //drive forward
    public void driveBackward(double speed, int centimeters) {
        telemetry.addData(">", "Start drive forward");
        telemetry.update();
        int Right1Position = robot.Right1.getCurrentPosition();
        int Right2Position = robot.Right2.getCurrentPosition();
        int Left1Position = robot.Left1.getCurrentPosition();
        int Left2Position = robot.Left2.getCurrentPosition();
        int encoderClicks = 46*centimeters;
        robot.Right1.setTargetPosition(Right1Position + encoderClicks);
        robot.Right1.setPower(speed);
        robot.Right2.setTargetPosition(Right2Position + encoderClicks);
        robot.Right2.setPower(speed);
        robot.Left1.setTargetPosition(Left1Position - encoderClicks);
        robot.Left1.setPower(-speed);
        robot.Left2.setTargetPosition(Left2Position - encoderClicks);
        robot.Left2.setPower(-speed);
        robot.Right1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left1.setPower(0);
        robot.Left2.setPower(0);

    }

    public void driveForward(double speed, int centimeters) {
        telemetry.addData(">", "Start drive forward");
        telemetry.update();
        int Right1Position = robot.Right1.getCurrentPosition();
        int Right2Position = robot.Right2.getCurrentPosition();
        int Left1Position = robot.Left1.getCurrentPosition();
        int Left2Position = robot.Left2.getCurrentPosition();
        int encoderClicks = 184*centimeters;
        robot.Right1.setTargetPosition(Right1Position + encoderClicks);
        robot.Right1.setTargetPosition(Right1Position - encoderClicks);
        robot.Right1.setPower(-speed);
        robot.Right2.setTargetPosition(Right2Position - encoderClicks);
        robot.Right2.setPower(-speed);
        robot.Left1.setTargetPosition(Left1Position + encoderClicks);
        robot.Left1.setPower(speed);
        robot.Left2.setTargetPosition(Left2Position + encoderClicks);
        robot.Left2.setPower(speed);
        robot.Right1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left1.setPower(0);
        robot.Left2.setPower(0);
    }

    public void driveRight(double speed, int encoderClicks) {
        telemetry.addData(">", "Start drive forward");
        telemetry.update();
        int Right1Position = robot.Right1.getCurrentPosition();
        int Right2Position = robot.Right2.getCurrentPosition();
        int Left1Position = robot.Left1.getCurrentPosition();
        int Left2Position = robot.Left2.getCurrentPosition();
        robot.Right1.setTargetPosition(Right1Position + encoderClicks);
        robot.Right1.setPower(speed);
        robot.Right2.setTargetPosition(Right2Position - encoderClicks);
        robot.Right2.setPower(-speed);
        robot.Left1.setTargetPosition(Left1Position - encoderClicks);
        robot.Left1.setPower(-speed);
        robot.Left2.setTargetPosition(Left2Position + encoderClicks);
        robot.Left2.setPower(speed);
        robot.Right1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left1.setPower(0);
        robot.Left2.setPower(0);
    }

    public void driveLeft(double speed, int encoderClicks) {
        telemetry.addData(">", "Start drive forward");
        telemetry.update();
        int Right1Position = robot.Right1.getCurrentPosition();
        int Right2Position = robot.Right2.getCurrentPosition();
        int Left1Position = robot.Left1.getCurrentPosition();
        int Left2Position = robot.Left2.getCurrentPosition();
        robot.Right1.setTargetPosition(Right1Position - encoderClicks);
        robot.Right1.setPower(-speed);
        robot.Right2.setTargetPosition(Right2Position + encoderClicks);
        robot.Right2.setPower(speed);
        robot.Left1.setTargetPosition(Left1Position + encoderClicks);
        robot.Left1.setPower(speed);
        robot.Left2.setTargetPosition(Left2Position - encoderClicks);
        robot.Left2.setPower(-speed);
        robot.Right1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left1.setPower(0);
        robot.Left2.setPower(0);
    }

    //36 centimeters will give you a 90 degree turn
    public void leftTurn(double speed, int centimeters) {
        telemetry.addData(">", "Start drive forward");
        telemetry.update();
        int Right1Position = robot.Right1.getCurrentPosition();
        int Right2Position = robot.Right2.getCurrentPosition();
        int Left1Position = robot.Left1.getCurrentPosition();
        int Left2Position = robot.Left2.getCurrentPosition();
        int encoderClicks = 46*centimeters;
        robot.Right1.setTargetPosition(Right1Position - encoderClicks);
        robot.Right1.setPower(-speed);
        robot.Right2.setTargetPosition(Right2Position - encoderClicks);
        robot.Right2.setPower(-speed);
        robot.Left1.setTargetPosition(Left1Position - encoderClicks);
        robot.Left1.setPower(-speed);
        robot.Left2.setTargetPosition(Left2Position - encoderClicks);
        robot.Left2.setPower(-speed);
        robot.Right1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left1.setPower(0);
        robot.Left2.setPower(0);
    }

    public void rightTurn(double speed, int degrees) {
        telemetry.addData(">", "Start drive forward");
        telemetry.update();
        int Right1Position = robot.Right1.getCurrentPosition();
        int Right2Position = robot.Right2.getCurrentPosition();
        int Left1Position = robot.Left1.getCurrentPosition();
        int Left2Position = robot.Left2.getCurrentPosition();
        DecimalFormat newFormat = new DecimalFormat("#.0");
        int encoderClicks =  Integer.valueOf(newFormat.format(73.6*degrees));
        robot.Right1.setTargetPosition(Right1Position + encoderClicks);
        robot.Right1.setPower(speed);
        robot.Right2.setTargetPosition(Right2Position + encoderClicks);
        robot.Right2.setPower(speed);
        robot.Left1.setTargetPosition(Left1Position + encoderClicks);
        robot.Left1.setPower(speed);
        robot.Left2.setTargetPosition(Left2Position + encoderClicks);
        robot.Left2.setPower(speed);
        robot.Right1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left1.setPower(0);
        robot.Left2.setPower(0);
    }

    public void clockwise90(double speed) {
        rightTurn(speed, 90);
    }
    public void counterclockwise90(double speed) {
        leftTurn(speed, 36);
    }
/*
    //raise lifter
    public void raiseLifter(){
        //these values may be switched! Check it next time!!
        robot.lifter.setTargetPosition(2600);
        robot.lifter.setPower(0.5);
    }

    //lower lifter
    public void lowerLifter(){
        //these values may be switched! Check it next time!!
        robot.lifter.setTargetPosition(0);
        robot.lifter.setPower(-0.5);
    }

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
    }*/

    //push the glyph in
    public void pushGlyph () {
        sleep(750);
        driveBackward(1, 61);
        sleep(750);
        //lowerLifter();
        sleep(2000);
        driveForward(1, 66);
        sleep(750);
        driveBackward(1, 61);
    }

    public void rampage (String column) {
        //start rampage
        telemetry.addData(">", "Rampage code, I'm going for the second glyph!");
        leftTurn(1, 36);
        driveForward(1, 182);
        //holdGlyph();
        //raiseLifter();
        sleep(1000);
        driveBackward(1, 122);
        leftTurn(1, 36);
        if(column == "center") {
            driveLeft(1, 720);
        } else if (column == "left") {
            driveRight(1, 720);
        } else if (column == "right") {
            driveLeft(1, 720);
        }
        driveForward(1, 61);
        //dropGlyph();
        pushGlyph();
        telemetry.update();
        telemetry.addData(">", "Rampage code, I'm going for the third glyph!");
        leftTurn(1, 36);
        driveForward(1, 182);
        //holdGlyph();
        //raiseLifter();
        sleep(1000);
        driveBackward(1, 122);
        leftTurn(1, 36);
        if(column == "center") {
            driveRight(1, 1440);
        } else if (column == "left") {
            driveRight(1, 720);
        } else if (column == "right") {
            driveLeft(1, 720);
        }
        driveForward(1, 61);
        //dropGlyph();
        pushGlyph();
        telemetry.update();


    }

     public abstract void runOpMode();



    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
