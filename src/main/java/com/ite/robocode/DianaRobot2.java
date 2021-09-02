/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.BulletMissedEvent;
import robocode.HitWallEvent;
import robocode.Robot;

import java.awt.*;

public class DianaRobot2 extends Robot {

    /**
     * run:  Fire's main run function
     */
    public void run() {
        setBodyColor(Color.CYAN);
        while (true)
        {   ahead(100);
             for (int nr = 5; nr < 360; nr++) {
                turnRight(nr);
                scan();
            }
            ahead(100);
        }
    }

    /**
     * onScannedRobot:
     */
    public void onScannedRobot(robocode.ScannedRobotEvent e) {
        fire(3);
        fire(3);
        fire(3);
        fire(3);
        scan();
    }

    /**
     * onHitByBullet:
     */
    public void onHitByBullet(robocode.HitByBulletEvent e) {
        turnLeft(45);
        ahead(1000);
    }

    /**
     * onHitRobot:
     */
    public void onHitRobot(robocode.HitRobotEvent e) {
        ahead(100);
    }

    public void onHitWall(HitWallEvent event) {
        turnRight(180);
        ahead(100);
    }


}