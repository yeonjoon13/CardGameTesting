import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Dimension;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import javax.swing.Icon;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

public class VisualHLGUITest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
    private VisualHL visualHL;

    @Override
    protected void onSetUp() {
        visualHL = GuiActionRunner.execute(() -> new VisualHL("HighLow"));
        window = new FrameFixture(robot(), visualHL);
        window.show();
        window.resizeTo(new Dimension(400, 300));

        visualHL.higher.setName("higherButton");
        visualHL.lower.setName("lowerButton");
        visualHL.newgame.setName("newGameButton");
        visualHL.tf.setName("guessField");
        visualHL.numcorrect.setName("correctField");
        visualHL.cardpic.setName("cardPicture");
    }

    @Test @GUITest
    public void testCorrectHigherPrediction() throws Exception {
        window.button("newGameButton").click();

        Field cardsField = VisualHL.class.getDeclaredField("card");
        cardsField.setAccessible(true);
        Card[] cards = (Card[]) cardsField.get(visualHL);

        Field deckField = VisualHL.class.getDeclaredField("deck");
        deckField.setAccessible(true);
        Deck deck = (Deck) deckField.get(visualHL);

        int firstCardNumber = cards[0].getCardNumber();

        Card higherCard = null;
        while (higherCard == null) {
            Card next = deck.dealCard();
            if (next != null && next.getCardNumber() > firstCardNumber) {
                higherCard = next;
            }
        }

        cards[1] = higherCard;

        window.button("higherButton").click();

        String messageText = window.textBox("guessField").text();
        assertThat(messageText).contains("correct");

        String counterText = window.textBox("correctField").text();
        assertThat(Integer.parseInt(counterText)).isGreaterThan(0);

        window.button("higherButton").requireEnabled();
        window.button("lowerButton").requireEnabled();
    }

    @Test @GUITest
    public void testIncorrectHigherPrediction() throws Exception {
        window.button("newGameButton").click();

        Field cardsField = VisualHL.class.getDeclaredField("card");
        cardsField.setAccessible(true);
        Card[] cards = (Card[]) cardsField.get(visualHL);

        Field deckField = VisualHL.class.getDeclaredField("deck");
        deckField.setAccessible(true);
        Deck deck = (Deck) deckField.get(visualHL);

        int firstCardNumber = cards[0].getCardNumber();

        Card lowerCard = null;
        while (lowerCard == null) {
            Card next = deck.dealCard();
            if (next != null && next.getCardNumber() < firstCardNumber) {
                lowerCard = next;
            }
        }

        cards[1] = lowerCard;

        window.button("higherButton").click();

        String messageText = window.textBox("guessField").text();
        assertThat(messageText).contains("incorrect");

        window.button("higherButton").requireDisabled();
        window.button("lowerButton").requireDisabled();
    }

    @Test @GUITest
    public void testCorrectLowerPrediction() throws Exception {
        window.button("newGameButton").click();

        Field cardsField = VisualHL.class.getDeclaredField("card");
        cardsField.setAccessible(true);
        Card[] cards = (Card[]) cardsField.get(visualHL);

        Field deckField = VisualHL.class.getDeclaredField("deck");
        deckField.setAccessible(true);
        Deck deck = (Deck) deckField.get(visualHL);

        int firstCardNumber = cards[0].getCardNumber();

        Card lowerCard = null;
        while (lowerCard == null) {
            Card next = deck.dealCard();
            if (next != null && next.getCardNumber() < firstCardNumber) {
                lowerCard = next;
            }
        }

        cards[1] = lowerCard;
        window.button("lowerButton").click();
        String messageText = window.textBox("guessField").text();
        assertThat(messageText).contains("correct");
        String counterText = window.textBox("correctField").text();
        assertThat(Integer.parseInt(counterText)).isGreaterThan(0);
        window.button("higherButton").requireEnabled();
        window.button("lowerButton").requireEnabled();
    }

    @Test @GUITest
    public void testEqualCardsValue() throws Exception {
        window.button("newGameButton").click();

        Field cardsField = VisualHL.class.getDeclaredField("card");
        cardsField.setAccessible(true);
        Card[] cards = (Card[]) cardsField.get(visualHL);

        cards[1] = cards[0];

        window.button("higherButton").click();

        String messageText = window.textBox("guessField").text();
        assertThat(messageText).contains("same");

        window.button("higherButton").requireDisabled();
        window.button("lowerButton").requireDisabled();
    }

    @Test @GUITest
    public void testIncorrectLowerPrediction() throws Exception {
        window.button("newGameButton").click();

        Field cardsField = VisualHL.class.getDeclaredField("card");
        cardsField.setAccessible(true);
        Card[] cards = (Card[]) cardsField.get(visualHL);

        Field deckField = VisualHL.class.getDeclaredField("deck");
        deckField.setAccessible(true);
        Deck deck = (Deck) deckField.get(visualHL);

        Card firstCard = deck.dealCard();
        cards[0] = firstCard;
        int firstValue = firstCard.getCardNumber();

        Card.Cards[] ranks = Card.Cards.values();
        int currentIndex = firstValue - 1;
        int nextIndex = (currentIndex + 1) % ranks.length;
        Card.Cards higherRank = ranks[nextIndex];
        Card.Suits suit = firstCard.getSuit();
        Card higherCard = new Card(higherRank, suit);
        cards[1] = higherCard;

        window.button("lowerButton").click();

        String message = window.textBox("guessField").text();
        assertThat(message)
                .as("User should see an ‘incorrect’ message")
                .containsIgnoringCase("lose");

        window.button("higherButton").requireDisabled();
        window.button("lowerButton").requireDisabled();
    }


    @Test @GUITest
    public void testExactlyEqualCardsValueForLower() throws Exception {
        window.button("newGameButton").click();

        Field cardsField = VisualHL.class.getDeclaredField("card");
        cardsField.setAccessible(true);
        Card[] cards = (Card[]) cardsField.get(visualHL);

        cards[1] = cards[0];

        assertThat(cards[0].getCardNumber()).isEqualTo(cards[1].getCardNumber());

        window.button("lowerButton").click();

        String messageText = window.textBox("guessField").text();
        assertThat(messageText).contains("The card value is the same");
        assertThat(messageText).contains("Sorry, you lose!");

        Field corrField = VisualHL.class.getDeclaredField("corr");
        corrField.setAccessible(true);
        boolean corrValue = (boolean) corrField.get(visualHL);
        assertThat(corrValue).isFalse();

        window.button("higherButton").requireDisabled();
        window.button("lowerButton").requireDisabled();
    }

    @Test @GUITest
    public void testExactlyEqualCardsValueForHigher() throws Exception {
        window.button("newGameButton").click();

        Field cardsField = VisualHL.class.getDeclaredField("card");
        cardsField.setAccessible(true);
        Card[] cards = (Card[]) cardsField.get(visualHL);

        cards[1] = cards[0];

        assertThat(cards[0].getCardNumber()).isEqualTo(cards[1].getCardNumber());

        window.button("higherButton").click();

        String messageText = window.textBox("guessField").text();
        assertThat(messageText).contains("The card value is the same");
        assertThat(messageText).contains("Sorry, you lose!");

        Field corrField = VisualHL.class.getDeclaredField("corr");
        corrField.setAccessible(true);
        boolean corrValue = (boolean) corrField.get(visualHL);
        assertThat(corrValue).isFalse();

        window.button("higherButton").requireDisabled();
        window.button("lowerButton").requireDisabled();
    }


    @Override
    protected void onTearDown() {
        window.cleanUp();
    }
}