package models;

import java.util.ArrayList;
import java.util.List;

public class Charger {
    private List<Port> ports = new ArrayList<>();
    private List<Robot> robotList;

    public Charger(int howManyPorts, List<Robot> robotList) {
        for (int i = 0; i < howManyPorts; i++) {
            ports.add(new Port(String.valueOf(i)));
        }
        this.robotList = robotList;
    }

    public synchronized void removeRobotFromPorts(Robot myRobot) {
        ports.stream()
                .filter(port -> port.getChargingRobot() != null)
                .filter(port -> port
                        .getChargingRobot()
                        .getName()
                        .equals(myRobot.getName()))
                .forEach(port -> {
                    port.setChargingRobot(null);
                    port.setStatus(PortStatus.FREE);
                });
        robotList.stream().filter(robot -> robot.getStatus().equals(RobotStatus.WAITING)).forEach(Robot::notifyMe);
    }

    public synchronized boolean requestConnection(Robot robot) {
        int robotSize = robot.getSize();
        int freePorts = 0;
        for (int i = 1; i < ports.size() - 1; i++) {
            if (ports.get(i).getStatus().equals(PortStatus.FREE)) {
                freePorts++;
            } else {
                freePorts = 0;
            }
            if (freePorts == robotSize) {
                for (int j = 0; j < robotSize; j++) {
                    ports.get(i - j).setStatus(PortStatus.BUSY);
                    ports.get(i - j).setChargingRobot(robot);
                }
                return true;
            }
        }
        if (ports.get(0).getStatus().equals(PortStatus.FREE)) {
            ports.get(0).setStatus(PortStatus.BUSY);
            ports.get(0).setChargingRobot(robot);
            return true;
        }
        if (ports.get(ports.size() - 1).getStatus().equals(PortStatus.FREE)) {
            ports.get(ports.size() - 1).setStatus(PortStatus.BUSY);
            ports.get(ports.size() - 1).setChargingRobot(robot);
            return true;
        }
        return false;
    }

    public synchronized void printStatus() {
        for (Port port : ports) {
            if (port.getChargingRobot() != null) {
                System.out.printf("port: %s | %s \n", port.getName(), port.getChargingRobot().getName());
            } else {
                System.out.printf("port: %s | null \n", port.getName());
            }
        }
        System.out.println();
    }

}
