package org.firstinspires.ftc.teamcode; //    this is telling the robot what data is being used

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; //importation of data about the robot's hardware
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp // this makes the robot controlled by a controller

public class WhatIsThis extends LinearOpMode { // addition of the hardware's software

    private DcMotor leftFront;
    private DcMotor rightFront;
    private Servo Servo;
    private Servo Servo2;

    @Override

    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        Servo2 = hardwareMap.servo.get("right_handAsServo");
        Servo = hardwareMap.servo.get("left_handAsServo");

        waitForStart();

        double tgtPower2 = 0;
        double tgtPower = 0;
        double NewIdea = 1;
        double ServoPow = Servo.getPosition();
        double ServoPow2 = Servo2.getPosition();

        while (opModeIsActive()) {

            telemetry.addData("Left Motor Power", leftFront.getPower());
            telemetry.addData("Right Motor Power qqqqqq", rightFront.getPower());
            telemetry.addData("Servo pos", ServoPow);
            telemetry.addData("Status", "Running");

            telemetry.update();

            // controler 1

            //driving

            if (gamepad1.a) {
                NewIdea = 1;
            }
            if (gamepad1.b) {
                NewIdea = .5;
            }
            tgtPower = this.gamepad2.left_stick_y;
            leftFront.setPower(tgtPower * NewIdea);
            tgtPower2 = -this.gamepad2.right_stick_y;
            rightFront.setPower(tgtPower2 * NewIdea);
            // servo

            if (gamepad1.dpad_down) {
                ServoPow = (ServoPow + .01);
                ServoPow2 = (ServoPow2 - .01);
            }
            else if (gamepad1.dpad_up) {
                ServoPow = ServoPow - .01;
                ServoPow2 = ServoPow2 + .01;
            }
            if (ServoPow < 0){
                ServoPow = 0;
            }
            else if (ServoPow > 1){
                ServoPow = 1;
            }

            if (ServoPow2 < 0){
                ServoPow2 = 0;
            }
            else if (ServoPow2 > 1){
                ServoPow2 = 1;
            }

            Servo.setPosition(ServoPow);
            Servo2.setPosition(ServoPow2);

        }
    }
}