package com.ite.robocode;

import java.awt.*;

import robocode.Robot;
import robocode.*;


public class SyfdsRobot extends Robot {

  private boolean helperFlag = true;

  @Override
  public void run() {
    setColors(Color.darkGray, Color.cyan, Color.green);

    while (true) {
      turnGunLeft(50);
    }
  }

  @Override
  public void onHitByBullet(HitByBulletEvent event) {
    doCoolMove();
  }

  @Override
  public void onBulletHitBullet(BulletHitBulletEvent event) {
    changeHelperFlag();
    doCoolMove();
  }

  @Override
  public void onHitWall(HitWallEvent event) {
    changeHelperFlag();
    doCoolMove();
  }

  @Override
  public void onScannedRobot(ScannedRobotEvent event) {

    if (getGunHeat() == 0d) {
      fire(Rules.MAX_BULLET_POWER);
    }

    changeHelperFlag();
    doCoolMove();
  }

  private void doCoolMove() {
    if (helperFlag) {
      turnLeft(Rules.MAX_TURN_RATE);
    } else {
      turnRight(Rules.MAX_TURN_RATE);
    }

    ahead(Math.random() > 0.7d ? 100 : -50);

    if (helperFlag) {
      turnRight(Rules.MAX_TURN_RATE);
    } else {
      turnLeft(Rules.MAX_TURN_RATE);
    }
  }

  private void changeHelperFlag() {
    helperFlag = !helperFlag;
  }
}
