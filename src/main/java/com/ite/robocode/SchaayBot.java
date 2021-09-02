package com.ite.robocode;

import robocode.HitWallEvent;
import robocode.Robot;

import java.awt.*;

public class SchaayBot extends Robot {

    /**
     * run:  Fire's main run function
     */
    public void run() {
        setBodyColor(Color.black);
        setRadarColor(Color.yellow);
        setBulletColor(Color.red);
        while (true) {
            //ahead(15);
            // turnRight(10);
            turnGunRight(45);
            /*if(getGunHeat() < 10 && getEnergy() >= 80) {
                fire(0.001);
            }*/
            scan();
        }
    }

    /**
     * onScannedRobot:
     */
    public void onScannedRobot(robocode.ScannedRobotEvent e) {
        double targetEnergy = e.getEnergy();

        /*if(targetEnergy < 20) {
            fire(20);
        }

        if(getGunHeat() < 10) {
            if(e.getDistance() < 15 && e.getVelocity() < 4 && getEnergy() >= 80) {
                fire(10);
            } else if (e.getDistance() < 15 && e.getVelocity() < 4 && getEnergy() < 80) {
                fire(5);
            } else {
                fire(3);
            }
        } else {
            turnRight(15);
            ahead(10);
        }*/
        if(e.getDistance() < 15 && e.getVelocity() < 4 && getEnergy() >= 80) {
            fire(10);
        } else if (e.getDistance() < 15 && e.getVelocity() < 4 && getEnergy() < 80 && getEnergy() >= 30) {
            fire(6);
        } else {
            fire(2);
            //turnGunRight(e.getBearing() + 180);
        }
        scan();
    }



    /**
     * onHitByBullet:
     */
    public void onHitByBullet(robocode.HitByBulletEvent e) {
        //fire(0.2);
        turnRight(75);
        ahead(150);
        turnRight(30);
        ahead(70);
        scan();
    }

    /**
     * onHitRobot:
     */
    public void onHitRobot(robocode.HitRobotEvent e) {
        fire(5);
        scan();
    }

    public void onBulletMissed(robocode.BulletMissedEvent e) {
        //turnGunLeft(5);
    }

    public void onHitWall(robocode.HitWallEvent e) {
        turnRight(90);
        ahead(30);
        scan();
    }

}
