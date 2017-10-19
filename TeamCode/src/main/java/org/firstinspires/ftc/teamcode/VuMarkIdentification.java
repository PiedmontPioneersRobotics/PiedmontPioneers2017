package org.firstinspires.ftc.teamcode;
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
@Autonomous(name="Red Bottom Autonomous", group ="Concept")
public class VuMarkIdentification extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";
    RelicRecoveryVuMark vuMark;

    OpenGLMatrix lastLocation = null;
    private ElapsedTime runtime = new ElapsedTime();
    VuforiaLocalizer vuforia;
    // READ THIS: This main code is for RedBottom!!
    public boolean RedBottom = true;
    public boolean BlueBottom;
    public boolean RedTop;
    public boolean BlueTop;


    HardwareFWD robot  = new HardwareFWD();
    public boolean Center;
    public boolean Left;
    public boolean Right;

    public void KnockoffJewel(String jewelColor, Boolean opMode) {
        //extend jewel arm
        if (jewelColor == "Red") {

        } else if (jewelColor == "Blue") {

        }
    }
    double rightSpeed;
    double leftSpeed;
    //drive forward
    public void driveForward(double speed, double time) {
        telemetry.addData(">", "Start drive forward");

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
    public void driveBackward(double speed, double time) {
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
    public double checkVuforia() {
        Center = false;
        Right = false;
        Left = false;
        double distance = 0.0;
        if(vuMark == RelicRecoveryVuMark.CENTER){
            Center = true;
            distance = 1;
            telemetry.addData("Center ", "True!!");
        } else if(vuMark == RelicRecoveryVuMark.LEFT){
            Left = true;
            distance = 2;
            telemetry.addData("Left ", "True!!");
        } else if(vuMark == RelicRecoveryVuMark.RIGHT){
            Right = true;
            distance = 3;
            telemetry.addData("Right ", "True!!");
        }else{
            distance = 0.0;
        }
        return distance;
    }
    //hold glyph
    public void holdGlyph(){
        //these values may be switched! Check it next time!!
        robot.starboardGripper.setPosition(1);
        robot.portGripper.setPosition(0);
    }
    //drop glyph into cryptobox
    public void dropGlyph(){
        //these values may be switched! Check it next time!!
        robot.starboardGripper.setPosition(0);
        robot.portGripper.setPosition(1);
    }
    @Override public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello Driver");
        telemetry.update();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AVi+Uaj/////AAAAGfWmeyFp9kJor/1TJjz9wLwAbeI4DnCVS28yGBmbfAGBJFycflauxPe49eusMdcCy8oNTAz/0MVmgKGeUKkOcAYysjx4Vu5IqACsLpAv2E4xpJrfCkOyNYAjeY3FVCPweXd+FOczSSS2sBGHbKtxXWBDH+CWCW2xAyesC/xGyY8CepTmYrZMsOm6c9imaGwUzBhZDTZzRmgQ/mxi9rN4UvHEGp0NTKSi72+kn61f8zBy0rDhZ43UjoIwNknCKvyisezzpIBxqynePB3wtANO1g02zj7a8I1AWl0yuMEjfPM5WGdiDm+g85wm9rBqwL2WOKQnC527JVG50ZB4j0RGq3jES/DOfCNESzYCbC+TqpAF";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        vuMark = RelicRecoveryVuMark.from(relicTemplate);
        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        telemetry.addData(">", "Start main loop");
        telemetry.update();

        while (opModeIsActive()) {
            RedBottom = true;
            if(RedBottom) {
                holdGlyph();
                KnockoffJewel("Red", RedBottom);
                driveForward(1, checkVuforia());
                rightTurn(1, 2);
                driveForward(1, 1000);
                dropGlyph();
            } else if(BlueBottom) {
                KnockoffJewel("Blue", BlueBottom);
                driveForward(-1,checkVuforia());
                rightTurn(1,2);
                driveForward(1,checkVuforia());
                dropGlyph();
            }



            //}else if(BlueBottom){
            //    ArmKnocksOffJule;

            //   Go Backwards;
            //   Phone checks vuforia;
            //  Turn 90o  toward the cryptoBox

            //   Go Backwards;
            //Drop glyph;
            //}
            // Start Starboard facing juels
            //if(Redtop){
            //   VuforiaChecks;
            //   KnockOffJule;
            //   Go Forward;
            //   Turn 90o to left;
            //   Go forward
            //  Turn 90o to Right;
            //  Go Forward;
            // Drop glyph;
            //}else
            // Start Starboard facing juels
            //if(bluetop){
            //   VuforiaChecks;
            //   KnockOffJule;
            //  Go backwords;
            //   Turn 90o to port;
            //   Go forward
            //  Turn 90o to port;
            //  Go Forward;
            // Drop glyph;
            //}




            telemetry.update();
            /*
            AddedCode
            */

            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                //telemetry.addData("VuMark", "%s visible", vuMark);
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
                // telemetry.addData("Pose", format(pose));
                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;
                }
            }
            else {
                // telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();
        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
