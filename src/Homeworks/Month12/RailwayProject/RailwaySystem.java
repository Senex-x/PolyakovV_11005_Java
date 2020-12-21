package Homeworks.Month12.RailwayProject;

import com.google.gson.Gson;

import static Methods.Methods.*;

import java.util.*;

class Test {
    public static void main(String[] args) {
        RailwaySystem system = new RailwaySystem();
        system.start();
    }
}

/**
 * TODO
 * - GENERIC TASKS
 * - Authorization (with password saving using hash code maybe)
 * - Dialog asking how long to continue simulation in loop
 * - Sign out
 * - Quit
 * -
 * - LOGGING
 * - Tickets selling logging
 * - Passenger's boardings logging
 * - Train's movements logging
 * - Save logs somewhere
 * - Possibility to delete logs
 * -
 * - PASSENGER ACCOUNT
 * - Account saving in database
 * - Buying ticket to train (unavailable before previous is not cancelled or finished)
 * - Find proper train according to demands
 * - Check timetable for appropriate trains
 * - Buy ticket to certain date
 * - Cancelling ticket
 * - Check history
 * -
 * - ADMINISTRATOR ACCOUNT privileges
 * - Possibility to add new trains
 * - Possibility to delete trains
 * - Possibility to add new stations and routes
 * - Possibility to edit train's route
 * - Possibility to edit any info about train besides of ID
 */

class RailwaySystem {
    TimeHandler timeHandler = new TimeHandler();
    TrainsMovementHandler trainsHandler = new TrainsMovementHandler();
    // Doing all the work referred to data files
    DatabaseHandler databaseHandler = new DatabaseHandler();
    // Contains list of all possible ways and offers different methods to work with this list
    WaysHandler waysHandler = databaseHandler.getWaysHandler();
    // Contains all trains from database
    // Singleton pattern
    ArrayList<Train> trains = databaseHandler.getTrains();
    // Singleton pattern
    ArrayList<Station> stations = DatabaseHandler.getStations();
    ArrayList<Passenger> passengers = databaseHandler.getPassengers();

    Gson gson = new Gson();

    void start() {
        Passenger passenger = null;
        timeHandler.printDate();

        Train testTrain = new Train(
                0,
                "EP2K",
                100,
                200,
                600,
                TrainType.ECONOMY,
                "null",
                new ArrayList<>(Arrays.asList(new Way("Vahitovo", "Kamaevo", 320)))
        );


        //System.out.println("testTrain: " + testTrain);

        /*
        Senex
        qwerty123
         */

        Passenger passengerTest = passengers.get(0);
        Train train = trains.get(0);
        //train = testTrain;

        displayArray(passengers, 1);

        Station departureTest = withName("Sosnovka");
        Station destinationTest = withName("Karambai");

        System.out.println("... Welcome to Electronic Railway System (ERS) ...\n" +
                "Please sign up (1) or log in (2)");
        int inp;
        while(true) {
            try {
                inp = Integer.parseInt(getLine());
                break;
            } catch (Exception e) {
                System.out.println("Incorrect input, please try again.");
            }
        }

        while (true) {
            if (inp == 1) {
                System.out.println("To sign up please enter following information.\n" +
                        "Your name: ");
                String name = getLine();
                System.out.println("Your password: ");
                String password = getLine();

                Passenger newPassenger = new Passenger(
                        name,
                        password
                );

                databaseHandler.addToTablePassengers(newPassenger);
                databaseHandler.updatePassengers(passengers);
                passenger = passengers.get(passengers.size() - 1);
                break;

            } else if (inp == 2) {
                System.out.println("To log in please enter following information.\n" +
                        "Your name: ");
                String name = getLine();
                System.out.println("Your password: ");
                String password = getLine();

                for (Passenger p : passengers) {
                    if (p.getName().equals(name) && p.getPassword().equals(password)) {
                        System.out.println("Logged in successfully.");
                        passenger = p;
                    }
                }
                if(passenger == null) {
                    System.out.println("User not found, please try again.");
                    continue;
                }
                break;
            } else {
                System.out.println("Incorrect input, try again.");
            }
        }

        System.out.println("If you want to buy a ticket enter (1).");
        inp = Integer.parseInt(getLine());
        if(inp == 1) {
            System.out.println("Please enter following information. ");
            displayArray(stations, 1);
            System.out.println("Please, enter desired departure station ID: ");
            int stationId = Integer.parseInt(getLine());
            Station departure = withId(stationId);
            System.out.println("Please, enter desired destination station ID: ");
            stationId = Integer.parseInt(getLine());
            Station destination = withId(stationId);

            System.out.println("Trains suitable for trip: ");
            for(Train t : trains) {
                if(t.route().isStationIncluded(departure) && t.route().isStationIncluded(destination)) {
                    System.out.println(train);
                }
            }
        }
    }

    private Station withName(String name) {
        return Station.getStationByName(stations, name);
    }

    private Station withId(int id) {
        return Station.getStationById(stations, id);
    }

    private void simulateAllTrains(int hours) {
        for (Train train : trains) {
        }
        for (int i = 0; i < hours; i++) {
            timeHandler.nextHour();
            for (Train train : trains) {
                train.move();
            }
        }
    }

    private void simulateTrain(Train train, int hours) {
        line(paint(Colors.RED, "SIMULATION START // "));
        System.out.println(train);
        //train.start(timeHandler);
        for (int i = 0; i < hours; i++) {
            line("-");
            timeHandler.nextHour();
            train.move();
        }
        line(paint(Colors.RED, "SIMULATION END // "));
    }

    class Handler {

    }

    private static class TrainsMovementHandler {
        ArrayList<Train> trains;
    }

    DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }
}