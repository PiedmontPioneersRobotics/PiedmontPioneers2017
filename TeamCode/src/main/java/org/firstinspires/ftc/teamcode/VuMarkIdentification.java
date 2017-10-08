package org.firstinspires.ftc.teamcode;/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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

/**
 * This OpMode illustrates the basics of using the Vuforia engine to determine
 * the identity of Vuforia VuMarks encountered on the field. The code is structured as
 * a LinearOpMode. It shares much structure with {@link ConceptVuforiaNavigation}; we do not here
 * duplicate the core Vuforia documentation found there, but rather instead focus on the
 * differences between the use of Vuforia for navigation vs VuMark identification.
 * 
 * @see ConceptVuforiaNavigation
 * @see VuforiaLocalizer
 * @see VuforiaTrackableDefaultListener
 * see  ftc_app/doc/tutorial/FTC_FieldCoordinateSystemDefinition.pdf
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained in {@link ConceptVuforiaNavigation}.
 */

@Autonomous(name="Concept: VuMark Id", group ="Concept")
//@Disabled
public class VuMarkIdentification extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;
    private ElapsedTime runtime = new ElapsedTime();
    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;
    // READ THIS: This main code is for RedBottom!!
    public boolean RedBottom;
    public boolean BlueBottom;
    public boolean RedTop;
    public boolean BlueTop;

    VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
    VuforiaTrackable relicTemplate = relicTrackables.get(0);
    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

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
    //turn left
    public void leftTurn(double speed, double time){
        runtime.reset();
        leftSpeed = -speed;
        rightSpeed = speed;
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
        rightSpeed = -speed;
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
        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        /*
         * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
         * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
         * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
         * web site at https://developer.vuforia.com/license-manager.
         *
         * Vuforia license keys are always 380 characters long, and look as if they contain mostly
         * random data. As an example, here is a example of a fragment of a valid key:
         *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
         * Once you've obtained a license key, copy the string from the Vuforia web site
         * and paste it in to your code onthe next line, between the double quotes.
         */
        parameters.vuforiaLicenseKey = "AVi+Uaj/////AAAAGfWmeyFp9kJor/1TJjz9wLwAbeI4DnCVS28yGBmbfAGBJFycflauxPe49eusMdcCy8oNTAz/0MVmgKGeUKkOcAYysjx4Vu5IqACsLpAv2E4xpJrfCkOyNYAjeY3FVCPweXd+FOczSSS2sBGHbKtxXWBDH+CWCW2xAyesC/xGyY8CepTmYrZMsOm6c9imaGwUzBhZDTZzRmgQ/mxi9rN4UvHEGp0NTKSi72+kn61f8zBy0rDhZ43UjoIwNknCKvyisezzpIBxqynePB3wtANO1g02zj7a8I1AWl0yuMEjfPM5WGdiDm+g85wm9rBqwL2WOKQnC527JVG50ZB4j0RGq3jES/DOfCNESzYCbC+TqpAF";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();


        while (opModeIsActive()) {

            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */
            VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
            VuforiaTrackable relicTemplate = relicTrackables.get(0);
            relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);


            if(RedBottom){
                holdGlyph();
                KnockoffJewel("Red", RedBottom);
                driveForward(1, checkVuforia());
                rightTurn(1,2);
                driveForward(1,2);
                dropGlyph();
            //}else if(BlueBottom){
            //    ArmKnocksOffJule;
            //if distance
            //   Go Backwards;
            //   Phone checks vuforia;
                            //  Turn 90o  toward the cryptoBox
            //if distance
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

            }


            telemetry.update();
            /*
            AddedCode
            */

            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                //telemetry.addData("VuMark", "%s visible", vuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
               // telemetry.addData("Pose", format(pose));

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
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

            //telemetry.update();
        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
