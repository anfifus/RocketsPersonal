package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int NUM_ACCELERATION_ROCKETS = 3;
    private static final int NUM_SLOW_DOWN_FIRST_ROCKET = 5;
    private static final int NUM_ACCELERATION_SECOND_ROCKET = 7;
    private static final int NUM_MAX_ACCELERATION_ROCKETS = 15;
    private static final String FIRST_ROCKET = "32WESSDS";
    private static final String SECOND_ROCKET = "LDSFJA32";

    public static void main(String[] args) {
        try {
            List<Rocket> rocketList = createAllRocketWithPropellant();
            showRockets(rocketList);
            moveRockets(rocketList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void moveRockets(List<Rocket> rocketList) {
        accelerateRocketsAndShowResults(rocketList);
        slowDownAndAccelerateAndShowResults(rocketList);
        accelerateRocketsMaxPower(rocketList);

    }

    private static void accelerateRocketsMaxPower(List<Rocket> rocketList) {
        accelerateMaxPower(rocketList);
        showActualPower(rocketList);
    }

    private static void accelerateMaxPower(List<Rocket> rocketList) {

        for (Rocket currentRocket : rocketList) {
            for (int i = 0; i < NUM_MAX_ACCELERATION_ROCKETS; i++) {
                currentRocket.increasePower();
            }
        }
    }

    private static void slowDownAndAccelerateAndShowResults(List<Rocket> rocketList) {
        slowDownAndAccelerate(rocketList);
        showActualPower(rocketList);
    }

    private static void slowDownAndAccelerate(List<Rocket> rocketList) {
        for (Rocket currentRocket : rocketList) {
            if (currentRocket.getCode().equals(FIRST_ROCKET)) {
                for (int i = 0; i < NUM_SLOW_DOWN_FIRST_ROCKET; i++) {
                    currentRocket.decreasePower();
                }
            } else {
                for (int i = 0; i < NUM_ACCELERATION_SECOND_ROCKET; i++) {
                    currentRocket.increasePower();
                }
            }
        }
    }

    private static void accelerateRocketsAndShowResults(List<Rocket> rocketList) {
        accelerateFirstRocketThreeTimes(rocketList);
        showActualPower(rocketList);
    }

    private static void showActualPower(List<Rocket> rocketList) {
        for (Rocket currentRocket : rocketList) {
            System.out.println("The rocket: " + currentRocket.getCode() + " the actual power is: " + currentRocket.getThePowerOfAllPropellant());
        }
    }

    private static void accelerateFirstRocketThreeTimes(List<Rocket> rocketList) {
        for (int i = 0; i < NUM_ACCELERATION_ROCKETS; i++) {
            for (Rocket currentRocket : rocketList) {
                currentRocket.increasePower();
            }
        }
    }

    private static List<Rocket> createAllRocketWithPropellant() throws Exception {
        List<Rocket> rocketList = createAllRocket();
        createPropellant(rocketList);
        return rocketList;
    }

    private static void createPropellant(List<Rocket> rocketList) throws Exception {
        for (Rocket currentRocket : rocketList) {
            int numPropellant = askNumberOfPropellant();
            for (int cont = 0; cont < numPropellant; cont++) {
                int maxPower = addMaxPower();
                currentRocket.addPropellant(maxPower);
            }


        }
    }

    private static int addMaxPower() {
        System.out.println("How much can go the propellant?");
        Scanner scan = new Scanner(System.in);
        int potency = scan.nextInt();
        scan.nextLine();
        return potency;
    }

    private static int askNumberOfPropellant() {
        System.out.println("How many propellant want to have the rocket");
        Scanner scan = new Scanner(System.in);
        int numPropellant = scan.nextInt();
        scan.nextLine();
        return numPropellant;
    }


    private static void showRockets(List<Rocket> rocketList) {
        for (Rocket currentRocket : rocketList) {
            System.out.println(currentRocket.getCode() + ": " + currentRocket.getNumOfPropellantWithMaxPower());
        }
    }


    private static List<Rocket> createAllRocket() throws Exception {
        List<Rocket> rocketList = new ArrayList<>();
        Rocket firstRocket = createRocket(FIRST_ROCKET);
        Rocket secondRocket = createRocket(SECOND_ROCKET);
        rocketList.add(firstRocket);
        rocketList.add(secondRocket);
        return rocketList;
    }

    private static Rocket createRocket(String code) throws Exception {
        return new Rocket(code);
    }

}
