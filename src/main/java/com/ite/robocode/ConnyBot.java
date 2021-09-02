/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class ConnyBot extends Robot {

    boolean wallReached = false;
    /**
     * run:  Fire's main run function
     */
    public void run() {
        setBodyColor(Color.black);
        setGunColor(Color.black);
        setRadarColor(Color.black);
        setBulletColor(Color.black);
        setScanColor(Color.black);
        goCorner();
        while (true) {
            ahead(30);
            turnRadarLeft(45);
            scan();
        }
    }

    /**
     * onScannedRobot:
     */
    public void onScannedRobot(robocode.ScannedRobotEvent e) {
            double absoluteBearing = getHeading() + e.getBearing();
            double bearingFromGun = absoluteBearing - getGunHeading();
            distanceRelativeFire(e.getDistance(),bearingFromGun);
    }

    /**
     * onHitByBullet:
     */
    public void onHitByBullet(robocode.HitByBulletEvent e) {
        scan();
        ahead(200);
    }

    /**
     * onHitRobot:
     */
    public void onHitRobot(robocode.HitRobotEvent e) {
        scan();
    }

    public void goCorner() {
        double direction = getHeading();
        double turnValue = direction % 90;
        turnLeft(turnValue);
        wallReached = true;
    }

    public void onHitWall(HitWallEvent e) {
        turnLeft(90);
    }

    public void distanceRelativeFire(double dist,double bearing) {
        if (dist < 200) {
            turnGunRight(bearing);
            fire(3);
        } else if (dist < 300) {
            turnGunRight(bearing);
            fire(2);
        }
        else if(wallReached) {
            turnGunRight(bearing);
            fire(1);
        }
    }
}
