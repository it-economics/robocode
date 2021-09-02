package com.ite.robocode;

import robocode.HitWallEvent;
import robocode.Robot;

public class SchaayBot extends Robot {

    /**
     * run:  Fire's main run function
     */
    public void run() {
        while (true) {
            ahead(5);
            turnRight(5);
            fire(0.01);
        }
    }

    /**
     * onScannedRobot:
     */
    public void onScannedRobot(robocode.ScannedRobotEvent e) {
        fire(5);
        scan();
    }

    /**
     * onHitByBullet:
     */
    public void onHitByBullet(robocode.HitByBulletEvent e) {
        fire(0.2);
        turnLeft(30);
        ahead(10);
        scan();
    }

    /**
     * onHitRobot:
     */
    public void onHitRobot(robocode.HitRobotEvent e) {
        ahead(5);
        scan();
    }

    public void onHitWall(robocode.HitWallEvent e) {
        turnLeft(90);
        ahead(10);
        scan();
    }

}
