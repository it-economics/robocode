/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.*;
import robocode.Robot;
import robocode.util.Utils;

import java.awt.*;

public class LarsBot extends Robot {
    private static final double QUARTER_TURN = 90.0D;

    /**
     * run:  Fire's main run function
     */
    @Override
    public void run() {
        setBodyColor(Color.RED);
        setBulletColor(Color.RED);
        setRadarColor(Color.YELLOW);
        setGunColor(Color.BLACK);
        fire(1);
        while (true) {
            turnGunLeft(35);
            fire(1);
            ahead(15);
            turnRight(5);
        }
    }

    /**
     * onScannedRobot:
     */
    @Override
    public void onScannedRobot(robocode.ScannedRobotEvent scan) {
            for (int index = 0; index < 3; index++) {
                fire(2);
            }
    }

    /**
     * onHitByBullet:
     */
    @Override
    public void onHitByBullet(robocode.HitByBulletEvent hit) {
        double degrees = Utils.normalRelativeAngleDegrees(QUARTER_TURN - (getHeading() - hit.getHeading()));
        turnGunRight(degrees);
        turnLeft(degrees);
        fire(10);
        ahead(20);
        scan();
    }

    @Override
    public void onHitWall(HitWallEvent collision) {
        turnRight(180);
    }

    @Override
    public void onHitRobot(HitRobotEvent event) {
        fireMultipleTimes(1, 5);
    }

    @Override
    public void onBulletHit(BulletHitEvent hit) {
        fireMultipleTimes(1, 5);
    }

    private void fireMultipleTimes(double power, int amount) {
        for (int index = 0; index < amount; index++) {
            fire(power);
        }
    }
}
