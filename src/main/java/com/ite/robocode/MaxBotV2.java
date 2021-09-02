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

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class MaxBotV2 extends Robot {

    /**
     * run:  Fire's main run function
     */
    public void run() {
        setBodyColor(Color.green);
        setGunColor(Color.black);
        setRadarColor(Color.yellow);
        setBulletColor(Color.red);

        while (true) {
            turnGunRight(15);
        }
    }

    /**
     * onScannedRobot:
     */
    public void onScannedRobot(robocode.ScannedRobotEvent e) {
        double targetEnergy = e.getEnergy();

        if(targetEnergy < 20) {
            fire(20);
        }

        if(getGunHeat() < 10) {
            if(e.getDistance() < 20 && e.getVelocity() < 5) {
                fire(getEnergy() * 0.2);
            } else {
                fire(getEnergy() * 0.1);
            }
        } else {
            turnRight(15);
            turnGunLeft(15);
            back(10);
        }

        scan();
    }

    /**
     * onHitByBullet:
     */
    public void onHitByBullet(HitByBulletEvent e) {
        double degrees = normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading()));
        turnGunLeft(degrees);
        turnRight(degrees);
        back(10);
        scan();
    }

    /**
     * onHitRobot:
     */
    public void onHitRobot(robocode.HitRobotEvent e) {
        back(10);
        fire(20);
        scan();
    }

    @Override
    public void onWin(WinEvent event) {
        turnRight(45);
        fire(20);
        scan();
    }

    @Override
    public void onBulletHit(BulletHitEvent event) {
        super.onBulletHit(event);
        scan();
    }

    @Override
    public void onBulletHitBullet(BulletHitBulletEvent event) {
        turnRight(15);
        turnGunLeft(15);
        back(10);
        scan();
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        turnRight(90);
        turnGunLeft(90);
        scan();
    }

    @Override
    public void onBulletMissed(BulletMissedEvent event) {
        super.onBulletMissed(event);
        scan();
    }

}
