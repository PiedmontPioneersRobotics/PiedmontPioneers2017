package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name="Blue Top Autonomous", group ="Concept")
public class AutonomousBlueTop extends AutonomousBase {
    // READ THIS: This main code is for RedBottom!!
    public boolean RedTop = false;
    public boolean BlueTop = true;
    public boolean RedBottom = false;
    public boolean BlueBottom = false;

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

            //   VuforiaChecks;
//   KnockOffJule;
//  Go backwords;
//   Turn 90o to port;
//   Go forward
//  Turn 90o to port;
//  Go Forward;
// Drop glyph;
            if(BlueTop) {
                holdGlyph();
                KnockoffJewel(BlueTop);
                driveBackward(0.25, 1.2);
                leftTurn(0.25, 1.2);
                driveForward(0.25, 0.45);
                leftTurn(0.25, 1.2);
                driveForward(0.25, 1.05);
                dropGlyph();
                stopDriving();
                BlueTop = false;

            }
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