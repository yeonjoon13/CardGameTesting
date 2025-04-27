import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Blackbox unit testing of BJHand class
public class BJHandBBTest {

    @Test
    // Test method BJValue when the hand has no Ace card.
    // Failure
    public void testBJValueNoAce() {
        BJHand hand = new BJHand();
        Card c1 = new Card(Card.Cards.Four, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Six, Card.Suits.Diamonds);
        hand.addCard(c1);
        hand.addCard(c2);
        assertEquals(10, hand.BJValue());
    }

    @Test
    // Test method BJValue when the hand has Ace card and the total number is within range.
    // Failure
    public void testBJValueAceInRange() {
        BJHand hand = new BJHand();
        Card c1 = new Card(Card.Cards.Ten, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        hand.addCard(c1);
        hand.addCard(c2);
        assertEquals(21, hand.BJValue());
    }

    @Test
    // Test method BJValue when the hand has Ace card and the total number is out of range.
    // Failure
    public void testBJValueAceOutOfRange() {
        BJHand hand = new BJHand();
        Card c1 = new Card(Card.Cards.Ace, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        hand.addCard(c1);
        hand.addCard(c2);
        assertEquals(12, hand.BJValue());
    }
}
