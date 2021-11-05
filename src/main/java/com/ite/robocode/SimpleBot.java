/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.AdvancedRobot;
import robocode.BulletHitBulletEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

// IMPORTANT!!!
// DO NOT CHANGE THIS CLASS ==> CREATE YOUR OWN ROBOT, you can copy this class for starters
public class SimpleBot extends AdvancedRobot {

    /**
     * run:  Fire's main run function
     */
    @Override
    public void run() {
        // Set colors
        setBodyColor(Color.BLUE);
        setGunColor(Color.YELLOW);
        setRadarColor(Color.GREEN);
        setAdjustRadarForRobotTurn(true);

        // This will be the default behaviour of your robot
        while (true) {
            ahead(100);
        }
    }

    /**
     * onScannedRobot: if our robot sees another robot
     */
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        turnGunRight(getHeading() - getRadarHeading() + e.getBearing());
        turnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
        fireBullet(3);
        fire(3);
        turnLeft(45);
        ahead(100);
        scan();
     }

    /**
     * onHitByBullet:  Ouch...our robot was hit by a bullet
     */
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        turnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
        fireBullet(3);
        fire(3);
        // let's back off
        back(50);
        scan();
    }

    @Override
    public void onBulletHitBullet(BulletHitBulletEvent event) {
        super.onBulletHitBullet(event);
        fireBullet(3);
        fire(3);
        scan();
    }

    /**
     * onHitRobot:  Yes, we hit a robot
     */
    @Override
    public void onHitRobot(HitRobotEvent e) {
        // fire again
        turnRight(45);
        ahead(50);
    }

    @Override
    public void onHitWall(HitWallEvent e) {
        super.onHitWall(e);
        turnLeft(90 - e.getBearing());
    }
}
