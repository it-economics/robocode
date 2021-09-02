/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors All rights reserved. This
 * program and the accompanying materials are made available under the terms of the Eclipse Public
 * License v1.0 which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.Robot;

public class ExampleRobot extends Robot {

  /**
   * run:  Fire's main run function
   */
  public void run() {
    fire(1);
    while (true) {
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
