/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.Robot;

import java.awt.*;

public class TeamEchoBot extends Robot {

    /**
     * run:  Fire's main run function
     */
    public void run() {
        setBodyColor(Color.getHSBColor(0.280555556f, .97f, .36f));
        setGunColor(Color.getHSBColor(0.280555556f, .97f, .36f));
        setRadarColor(Color.getHSBColor(0.280555556f, .97f, .36f)); // Yeah exactly
        setBulletColor(Color.getHSBColor(0.280555556f, .97f, .36f));
        while (true) {
            ahead(100);
            turnRight(10);
            ahead(100);
            turnLeft(10);
        }
    }

    /**
     * onScannedRobot:
     */
    public void onScannedRobot(robocode.ScannedRobotEvent e) {
        fire(1);
        scan();
    }

    /**
     * onHitByBullet:
     */
    public void onHitByBullet(robocode.HitByBulletEvent e) {
        turnRight(45);
        scan();
    }

    /**
     * onHitRobot:
     */
    public void onHitRobot(robocode.HitRobotEvent e) {
        scan();
    }
}
