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
    private FadingColor fadingColor;

    /**
     * run:  Fire's main run function
     */
    @Override
    public void run() {
        fadingColor = FadingColor.create();
        setRainbowColor();
        fire(Double.MIN_VALUE);
        while (true) {
            setRainbowColor();
            turnGunRight(9);
            scan();
        }
    }

    private void setRainbowColor() {
        Color color = fadingColor.nextColor();
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
        setRainbowColor();
        if (scan.getVelocity() > 10) {
            // Don't waste energy on bullets that won't actually hit.
            return;
        }
        if (getGunHeat() > 20) {
            turnLeft(15);
            turnGunRight(15);
            ahead(15);
        } else {
            fireMultipleTimes(3, 3);
            scan();
        }
    }

    /**
     * onHitByBullet:
     */
    @Override
    public void onHitByBullet(robocode.HitByBulletEvent hit) {
        setRainbowColor();
        double degrees = Utils.normalRelativeAngleDegrees(QUARTER_TURN - (getHeading() - hit.getHeading()));
        // try to escape
        turnLeft(degrees);
        ahead(50);
        // revenge
        fire(0.1);
        turnGunRight(degrees);
        // scanning for other bots
        scan();
    }

    @Override
    public void onHitWall(HitWallEvent collision) {
        setRainbowColor();
        turnLeft(QUARTER_TURN);
        ahead(45);
        turnGunRight(QUARTER_TURN + 10);
        scan();
    }

    @Override
    public void onHitRobot(HitRobotEvent collision) {
        back(46);
        fireMultipleTimes(3, 3);
        scan();
    }

    @Override
    public void onStatus(StatusEvent e) {
        setRainbowColor();
    }

    @Override
    public void onBulletHit(BulletHitEvent hit) {
        setRainbowColor();
        setBodyColor(Color.YELLOW);
        fireMultipleTimes(3, 5);
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

    private static final class FadingColor {
        private int red = 255;
        private int green = 0;
        private int blue = 0;

        private FadingColor() {}

        private void nextRGB() {
            if (red == 255 && green < 255 && blue == 0) {
                green++;
            }
            if (green == 255 && red > 0 && blue == 0) {
                red--;
            }
            if (green == 255 && blue < 255 && red == 0) {
                blue++;
            }
            if (blue == 255 && green > 0 && red == 0) {
                green--;
            }
            if (blue == 255 && red < 255 && green == 0) {
                red++;
            }
            if (red == 255 && blue > 0 && green == 0) {
                blue--;
            }
        }

        public Color nextColor() {
            nextRGB();
            return new Color(red, green, blue);
        }

        public static FadingColor create() {
            return new FadingColor();
        }
    }
}
