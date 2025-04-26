import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.RepeatedTest;
import redditgroup.cardgame.Card;
import redditgroup.cardgame.Deck;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Randomness testing of Deck class
public class DeckRandomTest {
    Deck deck = new Deck();
    static int[][][] freq = new int[4][13][52];

    @RepeatedTest(30000)
    // Test the method shuffle, verifying that each card is placed at each position with similar frequency.
    // We consider the frequencies to be roughly the same if the frequency of a pair (card, position)
    // is within +50%/-50% of the frequency of any other pair.
    public void testDeckShuffleRandom() {
        deck.shuffle();
        for (int k = 0; k < 52; k++) {
            Card c = deck.dealCard();
            int i = c.getSuit().ordinal();
            int j = c.getCardNumber();
            freq[i][j][k] += 1;
        }
    }

    @AfterAll
    public static void tearDown() {
        int min = 0;
        int max = 30000;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                for (int k = 0; k < 52; k++) {
                    int frequency = freq[i][j][k];
                    int num1 = frequency / 2;
                    int num2 =  (int) Math.round(frequency * 1.5);
                    if (num1 > min) {
                        min = num1;
                    }
                    if (num2 < max) {
                        max = num2;
                    }
                }
            }
        }
        System.out.println(min + " " + max);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                for (int k = 0; k < 52; k++) {
                    int frequency = freq[i][j][k];
                    assertTrue(frequency >= min && frequency <= max);
                }
            }
        }
    }
}
