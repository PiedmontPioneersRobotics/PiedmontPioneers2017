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


    HardwareFWD robot  = new HardwareFWD();
    public final static double j_up = 0.6;
    public final static double j_down = 0;
    public boolean Center;
    public boolean Left;
    public boolean Right;

    //knock off jewel routine
    public void KnockoffJewel(String opMode) {
        double turnTime1 = 0.75;
        double turnTime2 = 0.6;
        long motorSleep = 200;
        double powerForTurn = 0.15;
        String jewelColor = "None";
        robot.jewelMover.setPosition(j_down);
        sleep(1500);
        //extend jewel arm
        float hsvValues[] = {0F,0F,0F};
        Color.RGBToHSV(robot.colorSensor.red() * 8, robot.colorSensor.green() * 8, robot.colorSensor.blue() * 8, hsvValues);
        telemetry.addData("Red: ", robot.colorSensor.red());
        telemetry.addData("Green: ", robot.colorSensor.green());
        telemetry.addData("Blue: ", robot.colorSensor.blue());
        telemetry.update();
        if (robot.colorSensor.red()>=1) {
            jewelColor = "Red";
            telemetry.addLine("Red");
            telemetry.update();
        }else if (robot.colorSensor.blue()>=1){
            jewelColor = "Blue";
            telemetry.addLine("Blue");
            telemetry.update();
        }else {
            jewelColor = "None";
            telemetry.addLine("None");
            telemetry.update();
        }
        if (jewelColor == "Red") {
            telemetry.addLine("The jewel is red.");
            telemetry.addLine("About to check our mode/color.");
            telemetry.update();
            if (opMode == "RedBottom") {
                telemetry.addLine("The jewel is red.");
                telemetry.addLine("Our mode is RedBottom");
                telemetry.addLine("Should twise away from jewel.");
                telemetry.update();
                sleep(2000);
                leftTurn(powerForTurn, turnTime1);
                stopDriving();
                robot.jewelMover.setPosition(j_up);
                sleep(motorSleep);
                rightTurn(powerForTurn, turnTime2);
                stopDriving();
                sleep(motorSleep);
            } else if (opMode == "RedTop") {
                telemetry.addLine("The jewel is red.");
                telemetry.addLine("Our mode is RedTop");
                telemetry.addLine("Should twise away from jewel.");
                telemetry.update();
                sleep(2000);
                leftTurn(powerForTurn, turnTime1);
                stopDriving();
                robot.jewelMover.setPosition(j_up);
                sleep(motorSleep);
                rightTurn(powerForTurn, turnTime2);
                stopDriving();
                sleep(motorSleep);
            } else if (opMode == "BlueBottom") {
                telemetry.addLine("The jewel is red.");
                telemetry.addLine("Our mode is BlueBottom");
                telemetry.addLine("Should twise towards jewel.");
                telemetry.update();
                sleep(2000);
                rightTurn(powerForTurn, turnTime1);
                stopDriving();
                robot.jewelMover.setPosition(j_up);
                sleep(motorSleep);
                leftTurn(powerForTurn, turnTime2);
                stopDriving();
                sleep(motorSleep);
            } else if (opMode == "BlueTop") {
                telemetry.addLine("The jewel is red.");
                telemetry.addLine("Our mode is BlueTop");
                telemetry.addLine("Should twise towards jewel.");
                telemetry.update();
                sleep(2000);
                rightTurn(powerForTurn, turnTime1);
                stopDriving();
                robot.jewelMover.setPosition(j_up);
                sleep(motorSleep);
                leftTurn(powerForTurn, turnTime2);
                stopDriving();
                sleep(motorSleep);
            } else {
                telemetry.addLine("We don't seem to have a mode?");
                telemetry.update();
                sleep(2000);
                robot.jewelMover.setPosition(j_up);
            }

        } else if (jewelColor == "Blue") {
            telemetry.addLine("The jewel is blue.");
            telemetry.addLine("About to check our mode/color.");
            telemetry.update();
            if (opMode == "RedBottom") {
                telemetry.addLine("The jewel is blue.");
                telemetry.addLine("Our mode is RedBottom");
                telemetry.addLine("Should twise towards jewel.");
                telemetry.update();
                sleep(2000);
                rightTurn(powerForTurn, turnTime1);
                stopDriving();
                robot.jewelMover.setPosition(j_up);
                sleep(motorSleep);
                leftTurn(powerForTurn, turnTime2);
                stopDriving();
                sleep(motorSleep);
            } else if (opMode == "RedTop") {
                telemetry.addLine("The jewel is blue.");
                telemetry.addLine("Our mode is RedTop");
                telemetry.addLine("Should twise towards jewel.");
                telemetry.update();
                sleep(2000);
                rightTurn(powerForTurn, turnTime1);
                stopDriving();
                robot.jewelMover.setPosition(j_up);
                sleep(motorSleep);
                leftTurn(powerForTurn, turnTime2);
                stopDriving();
                sleep(motorSleep);
            } else if (opMode == "BlueBottom") {
                telemetry.addLine("The jewel is blue.");
                telemetry.addLine("Our mode is BlueBottom");
                telemetry.addLine("Should twise away from jewel.");
                telemetry.update();
                sleep(2000);
                leftTurn(powerForTurn, turnTime1);
                stopDriving();
                robot.jewelMover.setPosition(j_up);
                sleep(motorSleep);
                rightTurn(powerForTurn, turnTime2);
                stopDriving();
                sleep(motorSleep);
            } else if (opMode == "BlueTop") {
                telemetry.addLine("The jewel is blue.");
                telemetry.addLine("Our mode is BlueTop");
                telemetry.addLine("Should twise away from jewel.");
                telemetry.update();
                sleep(2000);
                leftTurn(powerForTurn, turnTime1);
                stopDriving();
                robot.jewelMover.setPosition(j_up);
                sleep(motorSleep);
                rightTurn(powerForTurn, turnTime2);
                stopDriving();
                sleep(motorSleep);
            } else {
                telemetry.addLine("We don't seem to have a mode?");
                telemetry.update();
                sleep(2000);
                robot.jewelMover.setPosition(j_up);
            }
        } else {
            telemetry.addLine("The jewel color was not recognized as red or blue.");
            telemetry.addLine("Not going to do anything");
            telemetry.update();
            sleep(2000);
            robot.jewelMover.setPosition(j_up);
        }

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
        telemetry.update();
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
        telemetry.update();
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
        telemetry.update();
        runtime.reset();
        leftSpeed = -speed;
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

    // turn right
    public void rightTurn(double speed, double time){
        telemetry.addData(">", "Start turn left");
        telemetry.addData("Right speed:", "%.2f", rightSpeed);
        telemetry.addData("Left speed:", "%.2f", leftSpeed);
        telemetry.update();
        runtime.reset();
        leftSpeed = speed;
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

    //check vuforia and return the distance needed to get to the correct cryptobox column
    public double checkVuforia(){
        telemetry.addData("Not overriding", "!!");
        telemetry.update();
        return 0;
    };


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

     public abstract void runOpMode();



    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
