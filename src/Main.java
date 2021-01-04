import models.Charger;
import models.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static int robotAmount = 1;
    public static int robotSize = 1;
    public static int chargeTime = 1;
    public static int workTime = 1;
    public static List<Robot> robotList = new ArrayList<>();
    public static List<Thread> createdThreads = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many robots you want?");
        robotAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("How big robots you want?");
        robotSize = Integer.parseInt(scanner.nextLine());
        System.out.println("How long max charge time do you want?");
        chargeTime = Integer.parseInt(scanner.nextLine());
        System.out.println("How long max work time do you want?");
        workTime = Integer.parseInt(scanner.nextLine());

        Charger charger = new Charger(robotSize, robotList);

        for (int i = 0; i < robotAmount; i++) {
            int robotNumberName = i + 65;
            String robotName = "";
            if (robotNumberName > 90) {
                int firstLetterName = 64 + i / 26;
                robotName += Character.toString(firstLetterName);
                int secondLetterName = i % 26 + 65;
                robotName += Character.toString(secondLetterName);
            } else {
                robotName += Character.toString(robotNumberName);
            }
            robotList.add(new Robot(robotName, robotSize, chargeTime, workTime, charger));
        }
        createdThreads = robotList.stream().map(Thread::new).collect(Collectors.toList());
        createdThreads.forEach(Thread::start);

        new Thread(() -> {
            while (true) {
                charger.printStatus();

                try {
                    //noinspection BusyWait
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
