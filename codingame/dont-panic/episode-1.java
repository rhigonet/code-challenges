import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators

        Map<Integer, Integer> elevators = new HashMap<>();

        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            elevators.put(elevatorFloor, elevatorPos);
        }

        int round = 0;
        int cloneCount = 0;

        // game loop
        while (true) {
            System.err.println("round = " + round);
            System.err.println("cloneCount = " + cloneCount);

            if (round % 3 == 0) {
                cloneCount++;
            }

            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            if (clonePos != -1) {
                System.err.println("nbTotalClones = " + nbTotalClones);
                System.err.println("clonePos = " + clonePos);
                System.err.println("cloneFloor = " + cloneFloor);
                System.err.println("direction = " + direction);
                System.err.println("width = " + width);
                System.err.println("exitFloor = " + exitFloor);
                System.err.println("nbRounds = " + nbRounds);
                System.err.println("nbFloors = " + nbFloors);
                System.err.println("exitPos = " + exitPos);
                System.err.println("elevators = " + elevators);

                if ((cloneFloor == exitFloor && isCloneMovingInTheWrongDirection(direction, clonePos, exitPos)) ||
                        (cloneFloor != exitFloor
                                && isCloneMovingInTheWrongDirection(direction, clonePos, elevators.get(cloneFloor)))) {
                    cloneBlock();
                } else {
                    cloneWait();
                }

            } else if (isCloneAboutToBeDestroyed(direction, clonePos, width)) {
                cloneBlock();
            } else {
                cloneWait();
            }

            round++;
        }
    }

    private static boolean isCloneMovingInTheWrongDirection(String direction, int clonePos, int objectivePos) {
        if ((clonePos < objectivePos && direction.equals("LEFT")) ||
                (clonePos > objectivePos && direction.equals("RIGHT")))
            return true;

        return false;
    }

    private static boolean isCloneAboutToBeDestroyed(String direction, int clonePos, int width) {
        if ((direction.equals("LEFT") && clonePos - 1 == 0) ||
                (direction.equals("RIGHT") && clonePos + 1 == width))
            return true;
        return false;
    }

    private static void cloneBlock() {
        System.out.println("BLOCK");
    }

    private static void cloneWait() {
        System.out.println("WAIT");
    }
}