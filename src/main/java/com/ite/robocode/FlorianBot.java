/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.Robot;
import robocode.*;
import robocode.util.Utils;

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class FlorianBot extends Robot {
    private int dist = 50; // distance to move when we're hit

    private int goAhead, goBack = 100;
    private int turnRight, turnLeft = 0;
    private int turnGun = 10;


    /**
     * run:  Fire's main run function
     */
    public void run() {
        // Set colors
        setBodyColor(Color.MAGENTA);
        setGunColor(Color.CYAN);
        setRadarColor(Color.GREEN);

        setBulletColor(Color.MAGENTA);


        while (true) {
            if (goAhead > 0) {
                ahead(goAhead);
            }

            if (goBack > 0) {
                back(goBack);
            }

            if (turnRight > 0) {
                turnRight(turnRight);
                turnRight = 0;
            }

            if (turnLeft > 0) {
                turnLeft(turnLeft);
                turnLeft = 0;
            }

            if (turnGun > 0) {
                turnGunRight(turnGun);
            }
        }
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        if (Utils.getRandom().nextInt() % 2 == 0) {
            turnRight = 90;
        } else {
            turnLeft = 90;
        }
    }

    /**
     * onScannedRobot:  Fire!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // If the other robot is close by, and we have plenty of life,
        // fire hard!
        goAhead = 0;

        if (e.getDistance() < 50 && getEnergy() > 50) {
            fire(5);
        } // otherwise, fire 1.
        else {
            fire(1);
        }
        // Call scan again, before we turn the gun
        scan();
    }

    /**
     * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
     */
    public void onHitByBullet(HitByBulletEvent e) {
        turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

        if (Utils.getRandom().nextInt() % 3 == 0) {
            goAhead = dist;
        } else {
            goBack = dist;
        }

        dist *= -1;
        scan();
    }

    /**
     * onHitRobot:  Aim at it.  Fire Hard!
     */
    public void onHitRobot(HitRobotEvent e) {
        double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

        goAhead = 0;
        turnGunRight(turnGunAmt);
        fire(5);
    }


}
