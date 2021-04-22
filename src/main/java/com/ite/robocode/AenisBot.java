package com.ite.robocode;

import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;


public class AenisBot extends Robot{
    int dist = 20; // distance to move when we're hit
    public void run() {
        // Set colors
        setBodyColor(Color.green);
        setGunColor(Color.black);
        setRadarColor(Color.black);


        setBulletColor(Color.red);

        // Spin the gun around slowly... forever
        while (true) {
            turnGunRight(10);
            if(getOthers() > 2) {
                turnRight(40);
                ahead(30);
            }
        }
    }

    /**
     * onScannedRobot:  Fire!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // If the other robot is close by, and we have plenty of life,
        // fire hard
        if (e.getDistance() < 50 && getEnergy() > 51) {
            fire(50);
        } // otherwise, fire 1.
        else if (e.getDistance() < 100 && getEnergy() > 31) {
            fire(30);
        } // otherwise, fire 1.
        else {
            if(getOthers() > 2) {
                fire(40);
            }
            else
            {
                fire(10);
                turnRight(e.getBearing());
                ahead(e.getDistance());
            }
        }
        // Call scan again, before we turn the gun
        scan();
    }

    /**
     * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
     */
    public void onHitByBullet(HitByBulletEvent e) {

        ahead(dist);
        dist *= -1;
        scan();
    }

    /**
     * onHitRobot:  Aim at it.  Fire Hard!
     */
    public void onHitRobot(HitRobotEvent e) {
        double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

        turnGunRight(turnGunAmt);
        fire(3);
    }
}
