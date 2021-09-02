/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.BulletHitEvent;
import robocode.Robot;

import java.awt.*;

public class TeamEchoBot extends Robot {
    int ticksSinceFight = 0; // this lock onto the target when we're in fight (per tick counts down when not hitting target)

    /**
     * run:  Fire's main run function
     */
    public void run() {
        setBodyColor(Color.getHSBColor(0.280555556f, .97f, .36f));
        setGunColor(Color.getHSBColor(0.280555556f, .97f, .36f));
        setRadarColor(Color.getHSBColor(0.280555556f, .97f, .36f));

        while (true) {
            if (ticksSinceFight > 0) {
                ticksSinceFight--;
            } else {
                turnGunRight(8);
            }
        }
    }

    public void onScannedRobot(robocode.ScannedRobotEvent e) {
        double absoluteBearing = (getHeading() + e.getBearing());
        if (absoluteBearing < 0) absoluteBearing = 360 - absoluteBearing;
        double leadCorrection = (e.getHeading() - absoluteBearing) * 0.02 * e.getVelocity();
        System.out.println("heading: " + e.getHeading());
        System.out.println("absoluteBearing: " + absoluteBearing);
        System.out.println("velocity: " + e.getVelocity());
        System.out.println("Correction: " + leadCorrection);
//        turnGunRight(leadCorrection);
        if (e.getDistance() > 300) {
            fire(strengthRampup);
            return;
        }
        int headingBonus = e.getHeading() == getGunHeading() ? 10 : 0;
        int rangeBonus = e.getDistance() < 30 ? 5 : 0;
        fire(2 + rangeBonus + headingBonus + strengthRampup);
        scan();
    }

    int strengthRampup = 1;

    @Override
    public void onBulletHit(BulletHitEvent e) {
        ticksSinceFight = 10;
        strengthRampup *= 1.1;

        if (e.getEnergy() > 10) {
            double bulletHeading = e.getBullet().getHeading();
            double diff = getHeading() - bulletHeading;
            if (diff < 0 && diff > -180) {
                turnRight(Math.abs(diff) + 10);
            } else {
                turnLeft(Math.abs(diff) + 10);
            }

            ahead(20);
        }

        scan();
    }

    public void onHitByBullet(robocode.HitByBulletEvent e) {
        strengthRampup = 1;
        turnRight(e.getBearing() + 90);
        ahead(200);
        scan();
    }

    public void onHitRobot(robocode.HitRobotEvent e) {
        scan();
    }

    public void onBulletMissed(robocode.BulletMissedEvent e) {
        strengthRampup = 1;
    }

    public void onHitWall(robocode.HitWallEvent e) {
        turnRight(e.getBearing() + 150);
        ahead(100);
        scan();
    }
}
