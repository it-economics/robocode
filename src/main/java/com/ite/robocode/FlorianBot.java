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

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class FlorianBot extends Robot {
    private int dist = 50; // distance to move when we're hit

    private int goAhead = 20;


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
            ahead(200);
            for (int i = 0; i <= 360; i += 60) {
                ahead(50);
                turnGunRight(i);
                fire(1);
            }
            turnRight(90);
            ahead(200);

        }
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        turnRight(90);
        ahead(200);
        turnRight(45);
    }

    /**
     * onScannedRobot:  Fire!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // If the other robot is close by, and we have plenty of life,
        turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

        // fire hard!
        if (e.getDistance() < 50 && getEnergy() > 50) {
            fire(8);
        } // otherwise, fire 1.
        else if (e.getDistance() < 100 && getEnergy() > 50) {
            fire(5);
        } else {
            fire(3);
        }
        // Call scan again, before we turn the gun
        scan();
    }

    /**
     * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
        goAhead = dist;

        dist *= -1;
        scan();
    }

    /**
     * onHitRobot:  Aim at it.  Fire Hard!
     */
    public void onHitRobot(HitRobotEvent e) {
        double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());
        //turnGunRight(turnGunAmt);
        fire(5);
    }


}
