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

    /**
     * run:  Fire's main run function
     */
    public void run() {
        setBodyColor(Color.getHSBColor(0.280555556f, .97f, .36f));
        setGunColor(Color.getHSBColor(0.280555556f, .97f, .36f));
        setRadarColor(Color.getHSBColor(0.280555556f, .97f, .36f));

        while (true) {
            turnGunRight(8);
        }
    }

    public void onScannedRobot(robocode.ScannedRobotEvent e) {
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
    public void onBulletHit(BulletHitEvent event) {
        strengthRampup *= 1.1;
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

    }

    public void onHitWall(robocode.HitWallEvent e) {
        turnRight(e.getBearing() + 180);
        ahead(100);
        scan();
    }
}
