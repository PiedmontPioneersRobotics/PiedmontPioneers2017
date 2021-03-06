package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.RobotLog;

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
    public static final double CENTER_COLUMN_DISTANCE = 0.90;
    public static final double RIGHT_COLUMN_DISTANCE = 1.05;
    public static final double LEFT_COLUMN_DISTANCE = 0.41;
    public int CryptoboxColumnCount = 0;
    public double driving_time = 1.0;

    public static final String TAG = "Autonomous blue top";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    RelicRecoveryVuMark vuMark;
    //check vuforia and return the distance needed to get to the correct cryptobox column
    @Override public double checkVuforia() {
        telemetry.addData("Just checking","...");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AVi+Uaj/////AAAAGfWmeyFp9kJor/1TJjz9wLwAbeI4DnCVS28yGBmbfAGBJFycflauxPe49eusMdcCy8oNTAz/0MVmgKGeUKkOcAYysjx4Vu5IqACsLpAv2E4xpJrfCkOyNYAjeY3FVCPweXd+FOczSSS2sBGHbKtxXWBDH+CWCW2xAyesC/xGyY8CepTmYrZMsOm6c9imaGwUzBhZDTZzRmgQ/mxi9rN4UvHEGp0NTKSi72+kn61f8zBy0rDhZ43UjoIwNknCKvyisezzpIBxqynePB3wtANO1g02zj7a8I1AWl0yuMEjfPM5WGdiDm+g85wm9rBqwL2WOKQnC527JVG50ZB4j0RGq3jES/DOfCNESzYCbC+TqpAF";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        waitForStart();
        relicTrackables.activate();
        sleep(1000);

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();

        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
            //telemetry.addData("VuMark", "%s visible", vuMark);
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
        } else {
            telemetry.addData("VuMark", "not visible");
            telemetry.update();
        }

        Center = false;
        Right = false;
        Left = false;
        if (vuMark == RelicRecoveryVuMark.CENTER) {
            Center = true;
            CryptoboxColumnCount = 2;
            driving_time = CENTER_COLUMN_DISTANCE;
            telemetry.addData("Center:", "True");
            RobotLog.ii(TAG,       "Going Center");
            telemetry.update();
        } else if (vuMark == RelicRecoveryVuMark.LEFT) {
            Left = true;
            CryptoboxColumnCount = 1;
            driving_time = LEFT_COLUMN_DISTANCE;
            telemetry.addData("Left:", "True");
            RobotLog.ii(TAG,       "Going Left");
            telemetry.update();
        } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
            Right = true;
            CryptoboxColumnCount = 3;
            driving_time = RIGHT_COLUMN_DISTANCE;
            telemetry.addData("Right:", "True");
            RobotLog.ii(TAG,       "Going right");
            telemetry.update();
        } else {
            CryptoboxColumnCount = 2;
            telemetry.addData(">", "Cannot see it.");
            RobotLog.ii(TAG,       "Cannot See, Going Center");
            telemetry.update();
            driving_time = CENTER_COLUMN_DISTANCE;
        }

        return driving_time;
    }

    @Override public void runOpMode() {
        robot.init(hardwareMap);
        RobotLog.ii(TAG,       "***** after robot init");
        telemetry.addData("Say", "Hello Driver");

        telemetry.update();
        telemetry.addData(">", "Press Play to start");

        telemetry.update();
        waitForStart();

        telemetry.addData(">", "Start main loop");
        RobotLog.ii(TAG,       "Main loop started");
        telemetry.update();
        holdGlyph();
        raiseLifter();
        double time_for_driving = checkVuforia();
        telemetry.addData(">", "Preparing to drive.");
        telemetry.update();
        KnockoffJewel("BlueTop");
        driveBackward(0.25, 1.1);
        //leftTurn(0.25, 1.25);
        counterclockwiseTurn(80, 0.25);
        telemetry.addData(">", "Turned left");
        RobotLog.ii(TAG,       "Turned left ");
        telemetry.update();
        //countColumns("BlueTop", columnCounts);
        driveForward(0.25, time_for_driving);
        telemetry.addData(">", "Driving forward by:", time_for_driving);
        RobotLog.ii(TAG,       "Driving forward by:" + time_for_driving);
        telemetry.update();
        //leftTurn(0.25,1.35);
        counterclockwiseTurn(80, 0.25);
        telemetry.addData(">", "Turned left");
        RobotLog.ii(TAG,       "Turned Left");
        telemetry.update();
        driveForward(0.25, 1.9);
        telemetry.addData(">", "Final drive forward");
        RobotLog.ii(TAG,       "Final drive forward");
        //robot.relicWrist.setPosition(0.5);
        telemetry.update();
        dropGlyph();
        //rampage(true, true);
        pushGlyph();
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
