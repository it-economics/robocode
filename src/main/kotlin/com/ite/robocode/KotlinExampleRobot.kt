package com.ite.robocode

import robocode.HitByBulletEvent
import robocode.HitRobotEvent
import robocode.Robot
import robocode.ScannedRobotEvent

class KotlinExampleRobot : Robot() {

    /**
     * run:  Fire's main run function
     */
    override fun run() {
        fire(1.0)
        while (true) {
            ahead(5.0)
            turnRight(5.0)
        }
    }

    /**
     * onScannedRobot:
     */
    override fun onScannedRobot(e: ScannedRobotEvent) {
        scan()
    }

    /**
     * onHitByBullet:
     */
    override fun onHitByBullet(e: HitByBulletEvent) {
        scan()
    }

    /**
     * onHitRobot:
     */
    override fun onHitRobot(e: HitRobotEvent) {
        scan()
    }
}
