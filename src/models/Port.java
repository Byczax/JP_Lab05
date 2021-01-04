package models;

public class Port {
    String name;
    PortStatus status;
    Robot chargingRobot;

    public Port(String name) {
        this.name = name;
        this.status = PortStatus.FREE;
        this.chargingRobot = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PortStatus getStatus() {
        return status;
    }

    public void setStatus(PortStatus status) {
        this.status = status;
    }

    public Robot getChargingRobot() {
        return chargingRobot;
    }

    public void setChargingRobot(Robot chargingRobot) {
        this.chargingRobot = chargingRobot;
    }

}
