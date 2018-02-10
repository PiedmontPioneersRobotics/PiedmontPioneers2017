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
    public int columnCounts = 0;
    public int offset = 5;


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
        sleep(2000);
        //extend jewel arm
        float hsvValues[] = {0F, 0F, 0F};
        Color.RGBToHSV(robot.colorSensor.red(), robot.colorSensor.green(), robot.colorSensor.blue(), hsvValues);
        telemetry.addData("Red: ", robot.colorSensor.red());
        telemetry.addData("Green: ", robot.colorSensor.green());
        telemetry.addData("Blue: ", robot.colorSensor.blue());
        telemetry.update();
        if (robot.colorSensor.red() > robot.colorSensor.blue()) {
            jewelColor = "Red";
            telemetry.addLine("Red");
            telemetry.update();
        } else if (robot.colorSensor.blue() > robot.colorSensor.red()) {
            jewelColor = "Blue";
            telemetry.addLine("Blue");
            telemetry.update();
        } else {
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
                robot.jewelMover.setPosition(j_up);
            }
        } else {
            telemetry.addLine("The jewel color was not recognized as red or blue.");
            telemetry.addLine("Not going to do anything");
            telemetry.update();
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

    //special drive forward
    public void specialDriveForward(double speedRight, double speedLeft, double time) {
        telemetry.addData(">", "Start drive forward");
        telemetry.addData("Right speed:", "%.2f", rightSpeed);
        telemetry.addData("Left speed:", "%.2f", leftSpeed);
        telemetry.update();
        runtime.reset();
        leftSpeed = speedLeft;
        rightSpeed = -speedRight;
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

    //special drive backward
    public void specialDriveBackward(double speedRight, double speedLeft, double time) {
        telemetry.addData(">", "Start drive backward");
        telemetry.addData("Right speed:", "%.2f", rightSpeed);
        telemetry.addData("Left speed:", "%.2f", leftSpeed);
        telemetry.update();
        runtime.reset();
        leftSpeed = -speedLeft;
        rightSpeed = speedRight;
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
    //mecanum drive left
    public void driveLeft(double speed, double time) {
        telemetry.addData(">", "Start drive forward");
        telemetry.addData("Right speed:", "%.2f", rightSpeed);
        telemetry.addData("Left speed:", "%.2f", leftSpeed);
        telemetry.update();
        runtime.reset();
        leftSpeed = speed;
        rightSpeed = -speed;
        while (opModeIsActive() && (runtime.seconds() < time)) {
            robot.Right1.setPower(rightSpeed);
            robot.Right2.setPower(leftSpeed);
            robot.Left1.setPower(leftSpeed);
            robot.Left2.setPower(rightSpeed);
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
        robot.lifter.setTargetPosition(robot.lifter.getCurrentPosition()+560);
        robot.lifter.setPower(0.5);
    }

    //lower lifter
    public void lowerLifter(){
        //these values may be switched! Check it next time!!
        robot.lifter.setTargetPosition(robot.lifter.getCurrentPosition()-560);
        robot.lifter.setPower(-0.5);
    }
    //push the glyph in
    public void pushGlyph () {
        sleep(750);
        driveBackward(0.25, 0.5);
        sleep(750);
        lowerLifter();
        sleep(2000);
        driveForward(0.25, 0.7);
        sleep(750);
        driveBackward(0.25, 0.4);
    }

    public void clockwiseTurn (int degrees, double speed) {
        robot.gyro.resetZAxisIntegrator();
        while (robot.gyro.getIntegratedZValue() > (-degrees+offset)) {
            robot.Right1.setPower(speed);
            robot.Left1.setPower(speed);
            robot.Right2.setPower(speed);
            robot.Left2.setPower(speed);
        }
        robot.Right1.setPower(0);
        robot.Left1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left2.setPower(0);
    }

    public void counterclockwiseTurn (int degrees, double speed) {
        robot.gyro.resetZAxisIntegrator();
        while (robot.gyro.getIntegratedZValue() < (degrees-offset)) {
            robot.Right1.setPower(-speed);
            robot.Left1.setPower(-speed);
            robot.Right2.setPower(-speed);
            robot.Left2.setPower(-speed);
        }
        robot.Right1.setPower(0);
        robot.Left1.setPower(0);
        robot.Right2.setPower(0);
        robot.Left2.setPower(0);
    }
    //rampage code (going for second glyph)
    public void rampage (boolean Blue, boolean top) { //start rampage
        telemetry.addData(">", "Rampage code, I'm going for the second glyph!");
        if(top == false) {
            driveBackward(1, 0.7);
            lowerLifter();
            counterclockwiseTurn(165, 1);
            driveForward(1, 1.1);
            holdGlyph();
            sleep(300);
            raiseLifter();
            sleep(300);
            driveBackward(1, 0.8);
            clockwiseTurn(165, 1);
            driveForward(1, 1);
            dropGlyph();
            lowerLifter();
            driveBackward(1, 0.5);
            counterclockwiseTurn(180, 1);
            driveBackward(1, 0.9);
            driveForward(1, 0.2);
            telemetry.update();
        } else if(top == true && Blue == false) {
            counterclockwiseTurn(90, 1);
            driveForward(1, 0.9);
            clockwiseTurn(90, 1);
            driveForward(1, 2.25);
            holdGlyph();
            sleep(300);
            raiseLifter();
            sleep(300);
            driveBackward(1, 1.6);
            counterclockwiseTurn(180, 1);
            driveForward(1, 0.65);
            dropGlyph();
            lowerLifter();
            driveBackward(1, 0.1);
            telemetry.update();
        } else if(top == true && Blue == true) {
            clockwiseTurn(150, 1);
            driveForward(1, 2.25);
            holdGlyph();
            sleep(300);
            raiseLifter();
            sleep(300);
            driveBackward(1, 1.6);
            counterclockwiseTurn(180, 1);
            driveForward(1, 0.65);
            dropGlyph();
            counterclockwiseTurn(180, 1);
            driveBackward(1, 1);
            telemetry.update();
        }

        //start rampage
       // telemetry.addData(">", "Rampage code, I'm going for the second glyph!");

    }

    //code for counting columns 
    //severe AI: be warned

    public void countColumns (String opMode, int columns) {
        // robot.columnCounterArm.setPosition(0);
        if (opMode == "RedBottom") {
            //while (columnCounts <= columns) {
            //   telemetry.addData("Columns passed:", columnCounts);
            //   telemetry.update();
            //  specialDriveForward(1, 0.3, 0.01);
            // if ((robot.columnCounter.getLightDetected() < 0.03) && (robot.columnCounter.getLightDetected() > 0.01)) {
            //     columnCounts += 1;
            //     robot.columnCounterArm.setPosition(0.5);
            //  }
            //}
        } else if (opMode == "BlueBottom") {
            //while (columnCounts <= columns) {
            // telemetry.addData("Columns passed:", columnCounts);
            // telemetry.update();
            // specialDriveBackward(1, 0.3, 0.01);;
            //if ((robot.columnCounter.getLightDetected() < 0.03) && (robot.columnCounter.getLightDetected() > 0.01)) {
            //    columnCounts += 1;
            //    sleep(1000);
            //    robot.columnCounterArm.setPosition(0.5);
            //}
            //}
        } else if (opMode == "RedTop") {
            //while (columnCounts <= columns) {
            //    specialDriveForward(1, 0.3, 0.01);
            //    if ((robot.columnCounter.getLightDetected() < 0.04) && (robot.columnCounter.getLightDetected() > 0.035)) {
            //        columnCounts += 1;
            //        sleep(1000);
            //        robot.columnCounterArm.setPosition(0.5);
            //   }
            //}
        } else if (opMode == "BlueTop") {
            //while (columnCounts <= columns) {
            //   driveBackward(0.5, 0.01);
            //   if ((robot.columnCounter.getLightDetected() < 0.04) && (robot.columnCounter.getLightDetected() > 0.035)) {
            //      columnCounts += 1;
            //       sleep(1000);
            //       robot.columnCounterArm.setPosition(0.5);
            //   }
            //}
        } else {
            telemetry.addLine("We don't seem to have a mode?");
            sleep(1000);
            telemetry.update();
        }
        // robot.columnCounterArm.setPosition(0.5);
        //columnCounts = 0;
    }

    public abstract void runOpMode();



    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
