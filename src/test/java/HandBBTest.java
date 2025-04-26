import org.junit.jupiter.api.Test;
import redditgroup.cardgame.Card;
import redditgroup.cardgame.Hand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Blackbox unit testing of Hand class
public class HandBBTest {

    @Test
    // Test the method addCard. Add one more card after reaching capacity.
    public void testAddCardNormal() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        assertTrue(hand.addCard(c1));
        assertTrue(hand.addCard(c2));
        assertTrue(hand.addCard(c3));
        assertFalse(hand.addCard(c4));
    }

    @Test
    // Test the method addCard. Add two same cards.
    // Failure
    public void testAddCardDuplicate() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        assertTrue(hand.addCard(c1));
        assertFalse(hand.addCard(c2));
    }

    @Test
    // Test the method getSize.
    public void testGetSize() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Four, Card.Suits.Spades);
        assertEquals(0, hand.getSize());
        hand.addCard(c1);
        assertEquals(1, hand.getSize());
        hand.addCard(c2);
        assertEquals(2, hand.getSize());
        hand.addCard(c3);
        assertEquals(3, hand.getSize());
        hand.addCard(c4);
        assertEquals(3, hand.getSize());
    }

    @Test
    // Test the method removeCard when the input is a valid index.
    public void testRemoveCardByPosNormal() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.removeCard(1);
        assertEquals(2, hand.getSize());
        assertEquals("21", hand.hand.getFirst().getUID());
        assertEquals("22", hand.hand.get(1).getUID());
    }

    @Test
    // Test the method removeCard when the input is an invalid index.
    public void testRemoveCardByPosOutOfBounds() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        assertThrows(Exception.class, () -> hand.removeCard(0));
        assertThrows(Exception.class, () -> hand.removeCard(4));
    }

    @Test
    // Test the method removeCard when the input is a card and the card exists in the hand.
    public void testRemoveCardByCardNormal() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.removeCard(c1);
        assertEquals(2, hand.getSize());
        assertEquals("21", hand.hand.getFirst().getUID());
        assertEquals("22", hand.hand.get(1).getUID());
    }

    @Test
    // Test the method removeCard when the input is a card and the card does not exist in the hand.
    // Failure
    public void testRemoveCardByCardNotExist() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        Card c4 = new Card(Card.Cards.Ace, Card.Suits.Clubs);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        assertThrows(Exception.class, () -> hand.removeCard(c4));
    }

    @Test
    // Test the method clearHand.
    public void testClearHand() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.clearHand();
        assertEquals(0, hand.getSize());
    }

    @Test
    // Test the method getCard when the input is a valid index.
    public void testGetCardNormal() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        assertEquals("21", hand.getCard(1).getUID());
    }

    @Test
    // Test the method getCard when the input is an invalid index.
    public void testGetCardOutOfBounds() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        assertThrows(Exception.class, () -> hand.getCard(3));
    }

    @Test
    // Test the method getHand.
    public void testGetHand() {
        Hand hand = new Hand(3);
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Four, Card.Suits.Diamonds);
        Card c3 = new Card(Card.Cards.Four, Card.Suits.Clubs);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        ArrayList<Card> list = hand.getHand();
        assertEquals(hand.hand.size(), list.size());
        for (int i = 0; i < hand.hand.size(); i++) {
            assertEquals(hand.hand.get(i).getUID(), list.get(i).getUID());
        }
    }

    @Test
    // Test the method getMaxSize.
    public void testGetMaxSize() {
        Hand hand = new Hand(3);
        assertEquals(3, hand.getMaxSize());
    }
}
