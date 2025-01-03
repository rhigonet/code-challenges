import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        var in = new Scanner(System.in);

        Queue<String> deckPlayerOne = new ArrayDeque<>();
        Queue<String> deckPlayerTwo = new ArrayDeque<>();

        int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            deckPlayerOne.add(in.next());
        }
        int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            deckPlayerTwo.add(in.next());
        }

        System.err.println("Player 1 deck: " + deckPlayerOne);
        System.err.println("Player 2 deck: " + deckPlayerTwo);

        System.err.println("");
        System.err.println("-----------------------");
        System.err.println("--------GAME ON--------");
        System.err.println("-----------------------");
        System.err.println("");

        play(deckPlayerOne, deckPlayerTwo);

        in.close();
    }
    
    private static void play(Queue<String> deckOne, Queue<String> deckTwo) {
        int roundCount = 0;

        while (!deckOne.isEmpty() && !deckTwo.isEmpty()) {

            var cardOne = deckOne.poll();
            var cardTwo = deckTwo.poll();

            if ( cardValue(cardOne) == cardValue(cardTwo) ) {

                System.err.println("");
                System.err.println("--------WAR HAS STARTED--------");
                war(deckOne, deckTwo, new ArrayDeque<>(Arrays.asList(cardOne)), new ArrayDeque<>(Arrays.asList(cardTwo)));
                System.err.println("--------WAR HAS ENDED--------");
                System.err.println("");

            } else if (cardValue(cardOne) >= cardValue(cardTwo)) {
                deckOne.add(cardOne);
                deckOne.add(cardTwo);
            } else {
                deckTwo.add(cardOne);
                deckTwo.add(cardTwo);
            }

            System.err.println("Player 1 deck: " + deckOne);
            System.err.println("Player 2 deck: " + deckTwo);

            roundCount++;
        }

        if (!deckOne.isEmpty() && deckTwo.isEmpty())
            System.out.println("1 " + roundCount);
        if (deckOne.isEmpty() && !deckTwo.isEmpty())
            System.out.println("2 " + roundCount);
        if (deckOne.isEmpty() && deckTwo.isEmpty())
            System.out.println("PAT");

    }

    private static void war(Queue<String> deckOne, Queue<String> deckTwo, Queue<String> pileOne, Queue<String> pileTwo) {

        System.err.println("Player 1 deck: " + deckOne);
        System.err.println("Player 2 deck: " + deckTwo);

        if (deckOne.size() < 4 || deckTwo.size() < 4) {
            deckOne.clear();
            deckTwo.clear();
        } else {
            pileOne.add(deckOne.poll());
            pileOne.add(deckOne.poll());
            pileOne.add(deckOne.poll());

            pileTwo.add(deckTwo.poll());
            pileTwo.add(deckTwo.poll());
            pileTwo.add(deckTwo.poll());

            var cardOne = deckOne.poll();
            var cardTwo = deckTwo.poll();

            pileOne.add(cardOne);
            pileTwo.add(cardTwo);

            if (cardValue(cardOne) == cardValue(cardTwo)) {
                war(deckOne, deckTwo, pileOne, pileTwo);
            } else if (cardValue(cardOne) >= cardValue(cardTwo)) {
                addPileToDeck(deckOne, pileOne);
                addPileToDeck(deckOne, pileTwo);
            } else {
                addPileToDeck(deckTwo, pileOne);
                addPileToDeck(deckTwo, pileTwo);
            }
        }

    }

    private static void addPileToDeck(Queue<String> deck, Queue<String> pile) {
        while (!pile.isEmpty()) {
            deck.add(pile.poll());
        }
    }

    private static int cardValue(String card) {
        int cardValue = 0;

        Pattern cardPattern = Pattern.compile("^([2-9]|10|J|Q|K|A)([DHCS])$");
        Matcher matcher = cardPattern.matcher(card.trim().toUpperCase());

        if (matcher.matches()) {
            String value = matcher.group(1);

            try {
                cardValue = Integer.parseInt(value);
            } catch (NumberFormatException  e) {
                if ("J".equals(value)) {
                    cardValue = 11;
                } else if ("Q".equals(value)) {
                    cardValue = 12;
                } else if ("K".equals(value)) {
                    cardValue = 13;
                } else if ("A".equals(value)) {
                    cardValue = 14;
                }
            }

        } else {
            throw new IllegalArgumentException("Invalid card: " + card);
        }

        return cardValue;
    }
}
