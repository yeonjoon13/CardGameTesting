import org.junit.jupiter.api.Test;
import redditgroup.cardgame.BJHand;
import redditgroup.cardgame.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Whitebox unit testing of BJHand class
public class BJHandWBTest {

    @Test
    // Test method BJValue when the hand has no Ace card.
    // Failure because the method getCardNumber does not return the correct number.
    public void testBJValueNoAce() {
        BJHand hand = new BJHand();
        Card c1 = new Card(Card.Cards.Ten, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Jack, Card.Suits.Diamonds);
        hand.addCard(c1);
        hand.addCard(c2);
        assertEquals(20, hand.BJValue());
    }

    @Test
    // Test method BJValue when the hand has Ace card and the total number is within range.
    // Failure because the method getCardNumber does not return the correct number.
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
    // Failure because the method getCardNumber does not return the correct number.
    public void testBJValueAceOutOfRange() {
        BJHand hand = new BJHand();
        Card c1 = new Card(Card.Cards.Ace, Card.Suits.Hearts);
        Card c2 = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        hand.addCard(c1);
        hand.addCard(c2);
        assertEquals(12, hand.BJValue());
    }
}
