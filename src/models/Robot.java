package models;

import java.util.Random;

public class Robot implements Runnable {
    final String name;
    int size;
    int chargeTime;
    int workTime;
    RobotStatus status;
    Charger charger;

    public Robot(String name, int maxSize, int chargeTime, int workTime, Charger charger) {
        Random rand = new Random();
        this.name = name;
        this.size = rand.nextInt(maxSize) + 1;
        this.chargeTime = chargeTime * this.size + rand.nextInt(chargeTime);
        this.workTime = workTime * this.size + rand.nextInt(chargeTime);
        this.status = RobotStatus.WORKING;
        this.charger = charger;
    }

    public String getName() {
        return name;
    }

    public int getChargeTime() {
        return chargeTime;
    }

    public int getWorkTime() {
        return workTime;
    }

    public RobotStatus getStatus() {
        synchronized (name) {
            return status;
        }
    }

    public int getSize() {
        return size;
    }

    public void setStatus(RobotStatus status) {
        synchronized (name) {
            this.status = status;
        }
    }

    @Override
    public synchronized void run() {
        try {
            System.out.format("%s | %s | %s | %d | %d\n", getName(), getSize(), getStatus(), getChargeTime(), getWorkTime());
            //noinspection InfiniteLoopStatement
            while (true) {
//                System.out.printf("Robot %s is now working\n",getName());
                setStatus(RobotStatus.WORKING);
                //noinspection BusyWait
                Thread.sleep(getWorkTime() * 100L);
                setStatus(RobotStatus.WAITING);

                while (getStatus().equals(RobotStatus.WAITING)) {

//                    System.out.printf("Robot %s is now waiting\n",getName());
                    if (charger.requestConnection(this)) {
                        setStatus(RobotStatus.CHARGING);
                        break;
                    }
                    wait();
                }
//                System.out.printf("Robot %s is now charging\n",getName());
                //noinspection BusyWait
                Thread.sleep(getChargeTime() * 100L);
                charger.removeRobotFromPorts(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void notifyMe() {
        notify();
    }
}

