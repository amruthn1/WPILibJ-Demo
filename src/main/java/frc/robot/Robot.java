// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final Timer m_timer = new Timer();
  private Joystick j;
  //USB port which Joystick is plugged into
  int port;
  //A joystick axis
  int axis;
  //Create new Human Interface Device Hand
  GenericHID.Hand hand;
  //Create 4 SpeedControllers for each of the motors
  SpeedController topRight = new PWMVictorSPX(1);
  SpeedController topLeft = new PWMVictorSPX(2);
  SpeedController bottomRight = new PWMVictorSPX(3);
  SpeedController bottomLeft = new PWMVictorSPX(4);
  //Create 2 SpeedControllerGroups
  SpeedControllerGroup right = new SpeedControllerGroup(topRight, bottomRight);
  SpeedControllerGroup left = new SpeedControllerGroup(topLeft, bottomLeft);
  //Create 1 DifferentialDrive
  DifferentialDrive drive = new DifferentialDrive(left, right);
  //Construct 1 MecanumDrive
  MecanumDrive mDrive =  new MecanumDrive(topLeft, bottomLeft, topRight, bottomRight);
  //X speed
  int xSpeed;
  //Y speed
  int ySpeed;
  //Z rotation
  int zRotation;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //USB port number
    port = 1;
    //Axis number
    axis = 5;
    //Initialize new Joystick
    j = new Joystick(port);
    //X speed
    xSpeed = 6;
    //Y speed
    ySpeed = 7;
    //zRotation
    zRotation = 9;
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      drive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      drive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    drive.arcadeDrive(j.getY(), j.getX());
    //MecanumDrive.driveCartesian() 
    mDrive.driveCartesian(ySpeed, xSpeed, zRotation);

    /*GETTER METHODS FOR JOYSTICK*/

    //Get axis type of joystick axis
    j.getAxisType(axis);
    //Return number of axis
    j.getAxisCount();
    //Get state of top button on joystick. Returns boolean.
    j.getTop();
    //Get whether button was pressed since last check. Returns boolean.
    j.getTopPressed();
    //Get whether button was released since last check. Returns boolean.
    j.getTopReleased();
    //Find twist value of current joystick. Returns double.
    j.getTwist();
    //Gets twist axis channel. Returns int.
    j.getTwistChannel();
    //Get X value of joystick. Returns double.
    j.getX(hand);
    //Get channel of X axis. Returns int.
    j.getXChannel();
    //Get Y value of joystick. Returns double. 
    j.getY(hand);
    //Get channel of Y axis. Returns int.
    j.getYChannel();
    //Get Z value of joystick. Returns double. 
    j.getZ();
    //Get channel of Z axis. Returns int.
    j.getZChannel();
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
