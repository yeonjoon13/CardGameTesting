import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

// Blackbox unit testing of PokerHand class
public class PokerHandBBTest {

    @Test
    // Test method PokerValue when the hand is straight flush.
    public void testPokerValueStraightFlush() {
        PokerHand pokerHand = new PokerHand();
        int [] pv = new int[7];
        Card c1 = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Three, Card.Suits.Diamonds);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pv[0] = 10;
        pv[1] = c5.getCardNumber();
        pv[2] = c4.getCardNumber();
        assertArrayEquals(pv, pokerHand.PokerValue());
    }

    @Test
    // Test method PokerValue when the hand is four of a kind.
    public void testPokerValueFourOfAKind() {
        PokerHand pokerHand = new PokerHand();
        int [] pv = new int[7];
        Card c1 = new Card(Card.Cards.Ace, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Ace, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Ace, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pv[0] = 9;
        pv[1] = c1.getCardNumber();
        pv[2] = c1.getCardNumber();
        pv[3] = c1.getCardNumber();
        assertArrayEquals(pv, pokerHand.PokerValue());
    }

    @Test
    // Test method PokerValue when the hand is full house.
    public void testPokerValueFullHouse() {
        PokerHand pokerHand = new PokerHand();
        int [] pv = new int[7];
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Two, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Five, Card.Suits.Hearts);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pv[0] = 8;
        pv[1] = c4.getCardNumber();
        pv[2] = c4.getCardNumber();
        pv[3] = c1.getCardNumber();
        pv[4] = c4.getCardNumber();
        assertArrayEquals(pv, pokerHand.PokerValue());
    }

    @Test
    // Test method PokerValue when the hand is flush.
    public void testPokerValueFlush() {
        PokerHand pokerHand = new PokerHand();
        int [] pv = new int[7];
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Hearts);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Hearts);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pv[0] = 7;
        pv[1] = c4.getCardNumber();
        pv[2] = c3.getCardNumber();
        assertArrayEquals(pv, pokerHand.PokerValue());
    }

    @Test
    // Test method PokerValue when the hand is straight.
    public void testPokerValueStraight() {
        PokerHand pokerHand = new PokerHand();
        int [] pv = new int[7];
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Three, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pv[0] = 6;
        pv[1] = c3.getCardNumber();
        pv[2] = c5.getCardNumber();
        assertArrayEquals(pv, pokerHand.PokerValue());
    }

    @Test
    // Test method PokerValue when the hand is three of a kind.
    public void testPokerValueThreeOfAKind() {
        PokerHand pokerHand = new PokerHand();
        int [] pv = new int[7];
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pv[0] = 5;
        pv[1] = c3.getCardNumber();
        pv[2] = c4.getCardNumber();
        assertArrayEquals(pv, pokerHand.PokerValue());
    }

    @Test
    // Test method PokerValue when the hand is two pair.
    public void testPokerValueTwoPair() {
        PokerHand pokerHand = new PokerHand();
        int [] pv = new int[7];
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pv[0] = 4;
        pv[1] = c3.getCardNumber();
        pv[2] = c4.getCardNumber();
        assertArrayEquals(pv, pokerHand.PokerValue());
    }

    @Test
    // Test method PokerValue when the hand is pair.
    public void testPokerValuePair() {
        PokerHand pokerHand = new PokerHand();
        int [] pv = new int[7];
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.King, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pv[0] = 3;
        pv[1] = c5.getCardNumber();
        pv[2] = c3.getCardNumber();
        assertArrayEquals(pv, pokerHand.PokerValue());
    }

    @Test
    // Test method PokerValue when the hand is higher card.
    public void testPokerValueHigherCard() {
        PokerHand pokerHand = new PokerHand();
        int [] pv = new int[7];
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pv[0] = 2;
        assertArrayEquals(pv, pokerHand.PokerValue());
    }

    @Test
    // Test method ShowPokerValue when the hand is straight flush.
    public void testShowPokerValueStraightFlush() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Three, Card.Suits.Diamonds);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals("Straight Flush", pokerHand.showPokerValue());
    }

    @Test
    // Test method ShowPokerValue when the hand is four of a kind.
    public void testShowPokerValueFourOfAKind() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Ace, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Ace, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Ace, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals("Four of a Kind", pokerHand.showPokerValue());
    }

    @Test
    // Test method ShowPokerValue when the hand is full house.
    public void testShowPokerValueFullHouse() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Eight, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Jack, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Hearts);
        Card c5 = new Card(Card.Cards.Jack, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals("Full House", pokerHand.showPokerValue());
    }

    @Test
    // Test method ShowPokerValue when the hand is flush.
    public void testShowPokerValueFlush() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Hearts);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Hearts);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals("Flush", pokerHand.showPokerValue());
    }

    @Test
    // Test method ShowPokerValue when the hand is straight.
    public void testShowPokerValueStraight() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Three, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals("Straight", pokerHand.showPokerValue());
    }

    @Test
    // Test method ShowPokerValue when the hand is three of a kind.
    public void testShowPokerValueThreeOfAKind() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals("Three of a Kind", pokerHand.showPokerValue());
    }

    @Test
    // Test method ShowPokerValue when the hand is two pair.
    public void testShowPokerValueTwoPair() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals("Two Pair", pokerHand.showPokerValue());
    }

    @Test
    // Test method ShowPokerValue when the hand is pair.
    public void testShowPokerValuePair() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.King, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals("Pair", pokerHand.showPokerValue());
    }

    @Test
    // Test method ShowPokerValue when the hand is higher card.
    public void testShowPokerValueHigherCard() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals("Higher Card", pokerHand.showPokerValue());
    }

    @Test
    // Test method dealerExchange when the hand is straight flush.
    public void testDealerExchangeStraightFlush() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Six, Card.Suits.Diamonds);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Three, Card.Suits.Diamonds);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card[] newHand = {c1, c2, c3, c4, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is four of a kind with the single card lower than Jack.
    public void testDealerExchangeFourOfAKindLow() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Ten, Card.Suits.Diamonds);
        Card c2 = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Ace, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Ace, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Ace, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card[] newHand = {c1, c2, c3, c4, c6};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is four of a kind with the single card higher than Ten.
    public void testDealerExchangeFourOfAKindHigh() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Jack, Card.Suits.Diamonds);
        Card c2 = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Ace, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Ace, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Ace, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card[] newHand = {c1, c2, c3, c4, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is full house.
    public void testDealerExchangeFullHouse() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Three, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Three, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Three, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Five, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Diamonds);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card[] newHand = {c1, c2, c3, c4, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is flush.
    public void testDealerExchangeFlush() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Spades);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card[] newHand = {c1, c2, c3, c4, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is straight.
    public void testDealerExchangeStraight() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Three, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card[] newHand = {c1, c2, c3, c4, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is three of a kind.
    public void testDealerExchangeThreeOfAKind() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c2 = new Card(Card.Cards.Two, Card.Suits.Clubs);
        Card c3 = new Card(Card.Cards.Two, Card.Suits.Diamonds);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Two, Card.Suits.Spades);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Three, Card.Suits.Hearts);
        Card[] newHand = {c6, c2, c3, c7, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is two pair.
    public void testDealerExchangeTwoPair() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card[] newHand = {c1, c2, c6, c4, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is pair.
    public void testDealerExchangePair() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Three, Card.Suits.Hearts);
        Card c8 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card[] newHand = {c1, c6, c7, c8, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is higher card with three hearts.
    public void testDealerExchangeHigherCardThreeHearts() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Nine, Card.Suits.Hearts);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Hearts);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Diamonds);
        Card c5 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Three, Card.Suits.Hearts);
        Card[] newHand = {c1, c2, c3, c6, c7};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is higher card with three diamonds.
    public void testDealerExchangeHigherCardDiamonds() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Diamonds);
        Card c2 = new Card(Card.Cards.Nine, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Diamonds);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Clubs);
        Card c5 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Three, Card.Suits.Hearts);
        Card[] newHand = {c1, c2, c3, c6, c7};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is higher card with three clubs.
    public void testDealerExchangeHigherCardThreeClubs() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        Card c2 = new Card(Card.Cards.Nine, Card.Suits.Clubs);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Diamonds);
        Card c5 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Three, Card.Suits.Hearts);
        Card[] newHand = {c1, c2, c3, c6, c7};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is higher card which is close to straight.
    public void testDealerExchangeHigherCardLikeStraight() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Nine, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Diamonds);
        Card c5 = new Card(Card.Cards.Ten, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card[] newHand = {c1, c2, c6, c4, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method dealerExchange when the hand is higher card which only keeps two highest cards.
    public void testDealerExchangeHigherCardTwoHigh() {
        PokerHand pokerHand = new PokerHand();
        Deck deck = new Deck();
        JLabel[] arr = new JLabel[2];
        Card c1 = new Card(Card.Cards.Seven, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Nine, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Diamonds);
        Card c5 = new Card(Card.Cards.Queen, Card.Suits.Hearts);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        pokerHand.dealerExchange(deck, arr);
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Three, Card.Suits.Hearts);
        Card c8 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card[] newHand = {c6, c7, c8, c4, c5};
        for (int i = 0; i < 5; i++) {
            assertEquals(newHand[i].getUID(), pokerHand.getCard(i).getUID());
        }
    }

    @Test
    // Test method bet when the hand is pair and the amount is negative.
    public void testBetPairNegative() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertThrows(Exception.class, () -> pokerHand.bet(-10));
    }

    @Test
    // Test method bet when the hand is pair and the amount is zero.
    public void testBetPairZero() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertThrows(Exception.class, () -> pokerHand.bet(0));
    }

    @Test
    // Test method bet when the hand is pair and the amount is more than or equal to 500.
    public void testBetPair500() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(-1, pokerHand.bet(500));
    }

    @Test
    // Test method bet when the hand is two pair and the amount is more than or equal to 500.
    public void testBetTwoPair500() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(500, pokerHand.bet(500));
    }

    @Test
    // Test method bet when the hand is straight and the amount is more than or equal to 500.
    public void testBetStraight500() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Five, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Seven, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(500, pokerHand.bet(500));
    }

    @Test
    // Test method bet when the hand is flush and the amount is more than or equal to 500.
    public void testBetFlush500() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Spades);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(600, pokerHand.bet(500));
    }

    @Test
    // Test method bet when the hand is higher card and the amount is more than or equal to 100 and less than 500.
    public void testBetHigherCard100() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Ten, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(-1, pokerHand.bet(100));
    }

    @Test
    // Test method bet when the hand is pair and the amount is more than or equal to 100 and less than 500.
    public void testBetPair100() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(250, pokerHand.bet(150));
    }

    @Test
    // Test method bet when the hand is straight and the amount is more than or equal to 100 and less than 500.
    public void testBetStraight100() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Five, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Seven, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(250, pokerHand.bet(150));
    }

    @Test
    // Test method bet when the hand is flush and the amount is more than or equal to 100 and less than 500.
    public void testBetFlush100() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Spades);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(200, pokerHand.bet(100));
    }

    @Test
    // Test method bet when the hand is higher card and the amount is more than or equal to 10 and less than 100.
    public void testBetHigherCard10() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Ten, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(10, pokerHand.bet(10));
    }

    @Test
    // Test method bet when the hand is pair and the amount is more than or equal to 10 and less than 100.
    public void testBetPair10() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(20, pokerHand.bet(10));
    }

    @Test
    // Test method bet when the hand is straight and the amount is more than or equal to 10 and less than 100.
    public void testBetStraight10() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Five, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Seven, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(20, pokerHand.bet(10));
    }

    @Test
    // Test method bet when the hand is flush and the amount is more than or equal to 10 and less than 100.
    public void testBetFlush10() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Spades);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(50, pokerHand.bet(10));
    }

    @Test
    // Test method bet when the hand is higher card and the amount is less than 10.
    public void testBetHigherCardLessThan10() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Ten, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(5, pokerHand.bet(5));
    }

    @Test
    // Test method bet when the hand is pair and the amount is less than 10.
    public void testBetPairLessThan10() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Jack, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Eight, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(105, pokerHand.bet(5));
    }

    @Test
    // Test method bet when the hand is straight and the amount is less than 10.
    public void testBetStraightLessThan10() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Five, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Seven, Card.Suits.Clubs);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(105, pokerHand.bet(5));
    }

    @Test
    // Test method bet when the hand is flush and the amount is less than 10.
    public void testBetFlushLessThan10() {
        PokerHand pokerHand = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Spades);
        pokerHand.addCard(c1);
        pokerHand.addCard(c2);
        pokerHand.addCard(c3);
        pokerHand.addCard(c4);
        pokerHand.addCard(c5);
        assertEquals(705, pokerHand.bet(5));
    }

    @Test
    // Test method higherHand when the sizes of two hands are different.
    public void testHigherHandDiffSize() {
        PokerHand pokerHand1 = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Six, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Spades);
        pokerHand1.addCard(c1);
        pokerHand1.addCard(c2);
        pokerHand1.addCard(c3);
        pokerHand1.addCard(c4);
        pokerHand1.addCard(c5);

        PokerHand pokerHand2 = new PokerHand();
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c8 = new Card(Card.Cards.Six, Card.Suits.Hearts);
        Card c9 = new Card(Card.Cards.Queen, Card.Suits.Hearts);
        pokerHand2.addCard(c6);
        pokerHand2.addCard(c7);
        pokerHand2.addCard(c8);
        pokerHand2.addCard(c9);

        assertThrows(Exception.class, () -> pokerHand1.higherHand(pokerHand2));
    }

    @Test
    // Test method higherHand when the hand itself has larger card numbers.
    public void testHigherHandHigh() {
        PokerHand pokerHand1 = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Eight, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Spades);
        pokerHand1.addCard(c1);
        pokerHand1.addCard(c2);
        pokerHand1.addCard(c3);
        pokerHand1.addCard(c4);
        pokerHand1.addCard(c5);

        PokerHand pokerHand2 = new PokerHand();
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c8 = new Card(Card.Cards.Six, Card.Suits.Hearts);
        Card c9 = new Card(Card.Cards.Queen, Card.Suits.Hearts);
        Card c10 = new Card(Card.Cards.Five, Card.Suits.Hearts);
        pokerHand2.addCard(c6);
        pokerHand2.addCard(c7);
        pokerHand2.addCard(c8);
        pokerHand2.addCard(c9);
        pokerHand2.addCard(c10);

        assertEquals(-1, pokerHand1.higherHand(pokerHand2));
    }

    @Test
    // Test method higherHand when the hand itself has smaller card numbers.
    public void testHigherHandLow() {
        PokerHand pokerHand1 = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Eight, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Spades);
        pokerHand1.addCard(c1);
        pokerHand1.addCard(c2);
        pokerHand1.addCard(c3);
        pokerHand1.addCard(c4);
        pokerHand1.addCard(c5);

        PokerHand pokerHand2 = new PokerHand();
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c8 = new Card(Card.Cards.Nine, Card.Suits.Hearts);
        Card c9 = new Card(Card.Cards.Queen, Card.Suits.Hearts);
        Card c10 = new Card(Card.Cards.Five, Card.Suits.Hearts);
        pokerHand2.addCard(c6);
        pokerHand2.addCard(c7);
        pokerHand2.addCard(c8);
        pokerHand2.addCard(c9);
        pokerHand2.addCard(c10);

        assertEquals(1, pokerHand1.higherHand(pokerHand2));
    }

    @Test
    // Test method higherHand when the hands are equal.
    public void testHigherHandEqual() {
        PokerHand pokerHand1 = new PokerHand();
        Card c1 = new Card(Card.Cards.Two, Card.Suits.Spades);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Spades);
        Card c3 = new Card(Card.Cards.Eight, Card.Suits.Spades);
        Card c4 = new Card(Card.Cards.Queen, Card.Suits.Spades);
        Card c5 = new Card(Card.Cards.Five, Card.Suits.Spades);
        pokerHand1.addCard(c1);
        pokerHand1.addCard(c2);
        pokerHand1.addCard(c3);
        pokerHand1.addCard(c4);
        pokerHand1.addCard(c5);

        PokerHand pokerHand2 = new PokerHand();
        Card c6 = new Card(Card.Cards.Two, Card.Suits.Hearts);
        Card c7 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c8 = new Card(Card.Cards.Eight, Card.Suits.Hearts);
        Card c9 = new Card(Card.Cards.Queen, Card.Suits.Hearts);
        Card c10 = new Card(Card.Cards.Five, Card.Suits.Hearts);
        pokerHand2.addCard(c6);
        pokerHand2.addCard(c7);
        pokerHand2.addCard(c8);
        pokerHand2.addCard(c9);
        pokerHand2.addCard(c10);

        assertEquals(0, pokerHand1.higherHand(pokerHand2));
    }
}
