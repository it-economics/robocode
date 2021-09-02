package com.ite.robocode.first;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;


import robocode.HitWallEvent;
import robocode.Robot;

public class UnserRobi extends Robot {

  /**
   * run:  Fire's main run function
   */
  public void run() {
    setBodyColor(Color.getHSBColor(300, 100, 100));
    setGunColor(Color.getHSBColor(300, 100, 100));
    setRadarColor(Color.getHSBColor(300, 100, 100));
    setBulletColor(Color.getHSBColor(300, 100, 100));
    drive();
    while (true) {
      turnGunRight(getHeading() - getGunHeading() + 90);
      drive();
      fire(5);
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
    fire(10);
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
    turnGunRight(getHeading() - getGunHeading());
    final double power = getEnergy() - 15;
    fireBullet(power > 1 ? power : getEnergy() - 1);
  }
}
