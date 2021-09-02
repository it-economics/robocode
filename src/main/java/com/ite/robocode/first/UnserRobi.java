package com.ite.robocode.first;

import java.awt.AWTException;
import java.awt.GraphicsDevice;


import robocode.Robot;

public class UnserRobi extends Robot {

  /**
   * run:  Fire's main run function
   */
  public void run() {
    while (true) {
      fire(1);
      ahead(5);
      turnRight(5);
    }
  }

  /**
   * onScannedRobot:
   */
  public void onScannedRobot(robocode.ScannedRobotEvent e) {
    scan();
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
