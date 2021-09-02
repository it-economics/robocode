package com.ite.robocode;

import robocode.HitWallEvent;
import robocode.Robot;

public class SchaayBot extends Robot {

    /**
     * run:  Fire's main run function
     */
    public void run() {
        while (true) {
            ahead(15);
            turnRight(10);
            if(getGunHeat() < 10 && getEnergy() >= 70) {
                fire(0.001);
            }
            scan();
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
            if(e.getDistance() < 15 && e.getVelocity() < 4) {
                fire(5);
            } else {
                fire(1);
            }
        } else {
            turnRight(15);
            ahead(10);
        }
        scan();
    }



    /**
     * onHitByBullet:
     */
    public void onHitByBullet(robocode.HitByBulletEvent e) {
        //fire(0.2);
        turnRight(30);
        ahead(50);
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

    }

    public void onHitWall(robocode.HitWallEvent e) {
        turnRight(90);
        ahead(10);
        scan();
    }

}
