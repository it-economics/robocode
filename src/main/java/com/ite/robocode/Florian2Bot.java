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

// IMPORTANT!!!
// DO NOT CHANGE THIS CLASS ==> CREATE YOUR OWN ROBOT, you can copy this class for starters
public class Florian2Bot extends Robot {

    boolean bulletHit = false;

    /**
     * run:  Fire's main run function
     */
    @Override
    public void run() {
        // Set colors
        setBodyColor(Color.MAGENTA);
        setGunColor(Color.CYAN);
        setRadarColor(Color.GREEN);

        setBulletColor(Color.MAGENTA);

        while (true) {
            turnGunRight(10D);
            if (!bulletHit) {
                ahead(30);
            }
        }
    }

    /**
     * onScannedRobot: if our robot sees another robot
     */
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        fire(3);
        scan();
    }

    @Override
    public void onBulletHit(BulletHitEvent event) {
        bulletHit = true;
        fire(3);
    }


    /**
     * onHitByBullet:  Ouch...our robot was hit by a bullet
     */
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        turnRight(45);
        ahead(100);
        scan();
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        turnRight(180);
        ahead(250);
        scan();
    }

    /**
     * onHitRobot:  Yes, we hit a robot
     */
    @Override
    public void onHitRobot(HitRobotEvent e) {
        // fire again
        fire(3);
    }
}
