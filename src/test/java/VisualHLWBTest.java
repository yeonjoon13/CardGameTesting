import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

// Whitebox unit testing of VisualHL class
public class VisualHLWBTest {

    @Test
    // Test the method actionPerformed when the command is "New Game".
    public void testHighLowNewGame() {
        VisualHL window = new VisualHL("HighLow");
        ActionEvent evt = new ActionEvent(window, ActionEvent.ACTION_PERFORMED, "New Game");
        window.actionPerformed(evt);
        assertEquals(0, window.correct);
        assertTrue(window.higher.isEnabled());
    }

    @Test
    // Test the method actionPerformed when the command is "Higher" and the guess is correct.
    public void testHighLowHigherCorrect() {
        VisualHL window = new VisualHL("HighLow");
        ActionEvent evt = new ActionEvent(window, ActionEvent.ACTION_PERFORMED, "Higher");
        window.card[0] = new Card(Card.Cards.King, Card.Suits.Diamonds);
        window.card[1] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.actionPerformed(evt);
        assertEquals(1, window.correct);
        window.card[0] = new Card(Card.Cards.King, Card.Suits.Diamonds);
        window.card[1] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.actionPerformed(evt);
        assertEquals(2, window.correct);
    }

    @Test
    // Test the method actionPerformed when the command is "Higher" and the guess is incorrect.
    public void testHighLowHigherIncorrect() {
        VisualHL window = new VisualHL("HighLow");
        ActionEvent evt = new ActionEvent(window, ActionEvent.ACTION_PERFORMED, "Higher");
        window.card[0] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.card[1] = new Card(Card.Cards.King, Card.Suits.Diamonds);
        window.actionPerformed(evt);
        assertEquals(" Your prediction was incorrect.", window.tf.getText());
        assertFalse(window.higher.isEnabled());
        assertFalse(window.lower.isEnabled());
    }

    @Test
    // Test the method actionPerformed when the command is "Higher" and the guess is same.
    public void testHighLowHigherSame() {
        VisualHL window = new VisualHL("HighLow");
        ActionEvent evt = new ActionEvent(window, ActionEvent.ACTION_PERFORMED, "Higher");
        window.card[0] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.card[1] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.actionPerformed(evt);
        assertEquals(" The card value is the same. Sorry, you lose!", window.tf.getText());
        assertFalse(window.higher.isEnabled());
        assertFalse(window.lower.isEnabled());
    }

    @Test
    // Test the method actionPerformed when the command is "Lower" and the guess is correct.
    public void testHighLowLowerCorrect() {
        VisualHL window = new VisualHL("HighLow");
        ActionEvent evt = new ActionEvent(window, ActionEvent.ACTION_PERFORMED, "Lower");
        window.card[0] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.card[1] = new Card(Card.Cards.King, Card.Suits.Diamonds);
        window.actionPerformed(evt);
        assertEquals(1, window.correct);
        window.card[0] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.card[1] = new Card(Card.Cards.King, Card.Suits.Diamonds);
        window.actionPerformed(evt);
        assertEquals(2, window.correct);
    }

    @Test
    // Test the method actionPerformed when the command is "Lower" and the guess is incorrect.
    public void testHighLowLowerIncorrect() {
        VisualHL window = new VisualHL("HighLow");
        ActionEvent evt = new ActionEvent(window, ActionEvent.ACTION_PERFORMED, "Lower");
        window.card[0] = new Card(Card.Cards.King, Card.Suits.Diamonds);
        window.card[1] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.actionPerformed(evt);
        assertEquals(" Your prediction was incorrect.", window.tf.getText());
        assertFalse(window.higher.isEnabled());
        assertFalse(window.lower.isEnabled());
    }

    @Test
    // Test the method actionPerformed when the command is "Lower" and the guess is same.
    public void testHighLowLowerSame() {
        VisualHL window = new VisualHL("HighLow");
        ActionEvent evt = new ActionEvent(window, ActionEvent.ACTION_PERFORMED, "Lower");
        window.card[0] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.card[1] = new Card(Card.Cards.Ace, Card.Suits.Diamonds);
        window.actionPerformed(evt);
        assertEquals(" The card value is the same. Sorry, you lose!", window.tf.getText());
        assertFalse(window.higher.isEnabled());
        assertFalse(window.lower.isEnabled());
    }
}
