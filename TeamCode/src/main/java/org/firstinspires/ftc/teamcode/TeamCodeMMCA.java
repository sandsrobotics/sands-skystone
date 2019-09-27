package org.firstinspires.ftc.teamcode; //    this is telling the robot what data is being used

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; //importation of data about the robot's hardware
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp // this makes the robot controlled by a controller

public class TeamCodeMMCA extends LinearOpMode { // addition of the hardware's software

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;

    private DcMotor intake;

    @Override

    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "left-front");
        rightFront = hardwareMap.get(DcMotor.class, "right-front");
        leftRear = hardwareMap.get(DcMotor.class, "left-rear");
        rightRear = hardwareMap.get(DcMotor.class, "right-rear");
        intake = hardwareMap.get(DcMotor.class, "intake");


        waitForStart();

        boolean slowMode = false;

        while (opModeIsActive()) {

            telemetry.addData("Left Motor Power", leftFront.getPower());
            telemetry.addData("Right Motor Power", rightFront.getPower());
            telemetry.addData("Right Motor Power", intake.getPower());
            telemetry.addData("Status", "Running");

            telemetry.update();

            // controler 1

            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            if (gamepad1.a) {
                slowMode = false;
            }
            else if (gamepad1.b){
                slowMode = true;
            }
            if (slowMode == false) {
                leftFront.setPower(v1);
                rightFront.setPower(v2);
                leftRear.setPower(v3);
                rightRear.setPower(v4);
            }
            else if (slowMode == true) {
                leftFront.setPower((v1)/2);
                rightFront.setPower((v2)/2);
                leftRear.setPower((v3)/2);
                rightRear.setPower((v4)/2);
            }
            if (gamepad1.x) {
                intake.setPower(1);
            }
            else if (gamepad1.y) {
                intake.setPower(-1);
            }
        }
    }
}