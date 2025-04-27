import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Whitebox unit testing of Deck class
public class DeckWBTest {

    @Test
    // Test the method getSuitString for each suit.
    public void testCardGetSuitString() {
        Card card1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card card2 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        Card card3 = new Card(Card.Cards.Jack, Card.Suits.Clubs);
        Card card4 = new Card(Card.Cards.Ace, Card.Suits.Spades);
        assertEquals("Hearts", card1.getSuitString());
        assertEquals("Diamonds", card2.getSuitString());
        assertEquals("Clubs", card3.getSuitString());
        assertEquals("Spades", card4.getSuitString());
    }

    @Test
    // Test the method getSuit for each suit.
    public void testCardGetSuit() {
        Card card1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card card2 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        Card card3 = new Card(Card.Cards.Jack, Card.Suits.Clubs);
        Card card4 = new Card(Card.Cards.Ace, Card.Suits.Spades);
        assertEquals(Card.Suits.Hearts, card1.getSuit());
        assertEquals(Card.Suits.Diamonds, card2.getSuit());
        assertEquals(Card.Suits.Clubs, card3.getSuit());
        assertEquals(Card.Suits.Spades, card4.getSuit());
    }

    @Test
    // Test the method getCardNumber for min, middle and max cards.
    // Possible failure because of the ambiguity of the method specification.
    public void testCardGetCardNumber() {
        Card card1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card card2 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card card3 = new Card(Card.Cards.Ace, Card.Suits.Hearts);
        assertEquals(0, card1.getCardNumber());
        assertEquals(2, card2.getCardNumber());
        assertEquals(12, card3.getCardNumber());
    }

    @Test
    // Test the method getFileName.
    public void testCardGetFileName() {
        Card card = new Card(Card.Cards.Four, Card.Suits.Hearts);
        assertEquals("Cards/Four of Hearts.gif", card.getFileName());
    }

    @Test
    // Test the method getUID.
    public void testCardGetUID() {
        Card card1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card card2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card card3 = new Card(Card.Cards.Ace, Card.Suits.Spades);
        assertEquals("00", card1.getUID());
        assertEquals("21", card2.getUID());
        assertEquals("123", card3.getUID());
    }

    @Test
    // Test the method toString.
    public void testCardToString() {
        Card card = new Card(Card.Cards.Four, Card.Suits.Hearts);
        assertEquals("Four of Hearts", card.toString());
    }

    @Test
    // Test the method shuffle.
    public void testDeckShuffle() {
        int[][] arr = new int[13][4];
        Deck deck = new Deck();
        deck.shuffle();
        boolean shuffled = true;
        for (int i = 0; i < 52; i++) {
            Card c = deck.dealCard();
            int j = c.getCardNumber();
            int k = c.getSuit().ordinal();
            if (arr[j][k] == 1) {
                shuffled = false;
                break;
            }
            arr[j][k] += 1;
        }
        assertTrue(shuffled);
    }

    @Test
    // Test the method dealCard.
    public void testDeckDealCard() {
        Deck deck = new Deck();
        boolean isDeck = true;
        for (int i = 0; i < 52; i++) {
            Card c = deck.dealCard();
            int j = c.getCardNumber();
            int k = c.getSuit().ordinal();
            if (!(j == i % 13 && k == i / 13)) {
                isDeck = false;
                break;
            }
        }
        assertTrue(isDeck);
        assertThrows(IllegalStateException.class, deck::dealCard);
    }
}
