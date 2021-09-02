package com.ite.robocode.first;

import java.awt.AWTException;
import java.awt.GraphicsDevice;


import robocode.HitWallEvent;
import robocode.Robot;

public class UnserRobi extends Robot {

  /**
   * run:  Fire's main run function
   */
  public void run() {
    drive();
    turnGunRight(90);
    while (true) {
      drive();
      fire(1);
    }
  }

  private void drive() {
    ahead(30);
  }

  @Override
  public void onHitWall(HitWallEvent event) {
    turnRight(90);
  }

  public void onScannedRobot(robocode.ScannedRobotEvent e) {
    fire(1);
  }

  /**
   * onHitByBullet:
   */
  public void onHitByBullet(robocode.HitByBulletEvent e) {
    scan();
  }

  /**
   * onHitRobot:
   */
  public void onHitRobot(robocode.HitRobotEvent e) {
    scan();
  }
}
