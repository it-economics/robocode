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
import java.util.Random;

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
        fire(Double.MIN_VALUE);
        while (true) {
            setRandomColor();
            fire(Double.MIN_VALUE);
            turnGunRight(5);
            scan();
        }
    }

    private void setRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        Color color = new Color(r, g, b);
        setBodyColor(color);
        setRadarColor(color);
        setGunColor(color);
        setScanColor(color);
    }

    /**
     * onScannedRobot:
     */
    @Override
    public void onScannedRobot(robocode.ScannedRobotEvent scan) {
        setRandomColor();
        if (scan.getVelocity() > 10) {
            // Don't waste energy on bullets that won't actually hit.
            return;
        }
        if (getGunHeat() > 20) {
            turnLeft(20);
            turnGunRight(20);
            ahead(5);
        } else {
            fireMultipleTimes(70, 2);
            scan();
        }
    }

    /**
     * onHitByBullet:
     */
    @Override
    public void onHitByBullet(robocode.HitByBulletEvent hit) {
        setRandomColor();
        double degrees = Utils.normalRelativeAngleDegrees(QUARTER_TURN - (getHeading() - hit.getHeading()));
        // try to escape
        turnLeft(degrees);
        ahead(20);
        // revenge
        fire(0.1);
        turnGunRight(degrees);
        // scanning for other bots
        scan();
    }

    @Override
    public void onHitWall(HitWallEvent collision) {
        setRandomColor();
        turnLeft(QUARTER_TURN);
        ahead(10);
        turnGunRight(QUARTER_TURN + 10);
        scan();
    }

    @Override
    public void onHitRobot(HitRobotEvent event) {
        back(20);
        scan();
    }

    @Override
    public void onStatus(StatusEvent e) {
        setRandomColor();
    }

    @Override
    public void onBulletHit(BulletHitEvent hit) {
        setRandomColor();
        setBodyColor(Color.YELLOW);
        fireMultipleTimes(50, 5);
    }

    private void fireMultipleTimes(double power, int amount) {
        for (int index = 0; index < amount; index++) {
            fire(power);
        }
    }

    @Override
    public void onBulletMissed(BulletMissedEvent event) {
        scan();
    }
}
