import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;
import javax.swing.*;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import java.awt.Dimension;
import java.util.ArrayList;


public class PokerGUITest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
    private Poker pokerFrame;

    private static final int DEFAULT_BET_AMOUNT = 20;
    private static final int SECOND_BET_AMOUNT = 10;
    private static final int RAISE_AMOUNT = 10;

    private static final String DEAL_BUTTON = "Deal";
    private static final String BET_BUTTON = "Bet!";
    private static final String CALL_BUTTON = "Call";
    private static final String FOLD_BUTTON = "Fold";

    private static final String BET_FIELD = "betField";
    private static final String RESULT_FIELD = "resultField";
    private static final String POT_LABEL = "potLabel";
    private static final String EXCHANGE_BUTTON = "exchangeButton";
    private static final String KEEP_BUTTON_PREFIX = "keepButton[";
    private static final String PLAYER_CARD_PREFIX = "playercards[";

    @Override
    protected void onSetUp() {
        pokerFrame = GuiActionRunner.execute(() -> new Poker("Poker"));
        setupDefaultDealerHand();
        setupComponentNames();
        window = new FrameFixture(robot(), pokerFrame);
        window.show();
        window.resizeTo(new Dimension(800, 600));
    }

    private void setupDefaultDealerHand() {
        pokerFrame.dealerHand = new PokerHand() {
            @Override
            public int bet(int playerBet) {
                return 0;
            }
        };
    }

    private void setupComponentNames() {
        pokerFrame.bettf.setName(BET_FIELD);
        pokerFrame.results.setName(RESULT_FIELD);
        pokerFrame.pot.setName(POT_LABEL);
        pokerFrame.exchange.setName(EXCHANGE_BUTTON);

        for (int i = 0; i < pokerFrame.playercards.length; i++) {
            pokerFrame.playercards[i].setName(PLAYER_CARD_PREFIX + i + "]");
        }

        for (int i = 0; i < pokerFrame.keep.length; i++) {
            pokerFrame.keep[i].setName(KEEP_BUTTON_PREFIX + i + "]");
        }
    }

    @Test @GUITest
    public void initialState() {
        verifyInitialButtonStates();
    }

    private void verifyInitialButtonStates() {
        window.button(JButtonMatcher.withText(DEAL_BUTTON)).requireEnabled();
        window.button(JButtonMatcher.withText(BET_BUTTON)).requireDisabled();
        window.button(JButtonMatcher.withText(CALL_BUTTON)).requireDisabled();
        window.button(JButtonMatcher.withText(FOLD_BUTTON)).requireDisabled();
        window.textBox(BET_FIELD).requireDisabled();
        window.label(POT_LABEL).requireVisible().requireText("Pot: 0");
    }

    @Test @GUITest
    public void dealButtonDisplaysCardsAndEnablesBets() {
        clickDealButton();
        verifyPostDealState();
        verifyPlayerCardsDisplayed();
    }

    private void clickDealButton() {
        window.button(JButtonMatcher.withText(DEAL_BUTTON)).click();
    }

    private void verifyPostDealState() {
        window.button(JButtonMatcher.withText(DEAL_BUTTON)).requireDisabled();
        window.button(JButtonMatcher.withText(BET_BUTTON)).requireEnabled();
        window.button(JButtonMatcher.withText(FOLD_BUTTON)).requireEnabled();
        window.textBox(BET_FIELD).requireEnabled();
    }

    private void verifyPlayerCardsDisplayed() {
        IntStream.range(0, 5).forEach(i -> {
            Icon icon = window.label(PLAYER_CARD_PREFIX + i + "]").target().getIcon();
            assertThat(icon).as("Player card %d icon", i).isNotNull();
        });
    }

    @Test @GUITest
    public void foldingResetsState() {
        clickDealButton();
        clickFoldButton();
        verifyFoldedState();
    }

    private void clickFoldButton() {
        window.button(JButtonMatcher.withText(FOLD_BUTTON)).click();
    }

    private void verifyFoldedState() {
        window.textBox(BET_FIELD).requireDisabled();
        window.button(JButtonMatcher.withText(BET_BUTTON)).requireDisabled();
        window.button(JButtonMatcher.withText(DEAL_BUTTON)).requireEnabled();
        window.label(POT_LABEL).requireText("Pot: $0");

        JTextComponentFixture resultBox = window.textBox(RESULT_FIELD);
        assertThat(resultBox.text()).contains("You have folded");
    }

    @Test @GUITest
    public void noRaiseKeepsEnabledImmediately() {
        clickDealButton();
        placeBet(DEFAULT_BET_AMOUNT);
        window.button(JButtonMatcher.withText(CALL_BUTTON)).requireDisabled();
        verifyAllKeepButtonsEnabled();
    }

    private void placeBet(int amount) {
        window.textBox(BET_FIELD).enterText(String.valueOf(amount));
        window.button(JButtonMatcher.withText(BET_BUTTON)).click();
    }

    private void verifyAllKeepButtonsEnabled() {
        IntStream.range(0, 5).forEach(i ->
                window.button(KEEP_BUTTON_PREFIX + i + "]").requireEnabled()
        );
    }

    @Test @GUITest
    public void selectingTwoCardsEnablesExchange() {
        startGameAndPlaceBet();

        selectKeepButtons(0, 1);
        window.button(EXCHANGE_BUTTON).requireEnabled();

        // Unselect one button to verify exchange gets disabled
        window.button(KEEP_BUTTON_PREFIX + "0]").click();
        window.button(EXCHANGE_BUTTON).requireDisabled();
    }

    private void startGameAndPlaceBet() {
        clickDealButton();
        placeBet(DEFAULT_BET_AMOUNT);
    }

    private void selectKeepButtons(int... indices) {
        for (int index : indices) {
            window.button(KEEP_BUTTON_PREFIX + index + "]").click();
        }
    }

    @Test @GUITest
    public void exchangeDisablesKeepButtons() {
        startGameAndPlaceBet();
        selectKeepButtons(0, 1);
        clickExchangeButton();

        verifyPostExchangeState();
    }

    private void clickExchangeButton() {
        window.button(EXCHANGE_BUTTON).click();
    }

    private void verifyPostExchangeState() {
        for (int i = 0; i < 5; i++) {
            window.button(KEEP_BUTTON_PREFIX + i + "]").requireDisabled();
        }

        window.button(EXCHANGE_BUTTON).requireDisabled();
        window.button(JButtonMatcher.withText(BET_BUTTON)).requireEnabled();
        window.textBox(BET_FIELD).requireEnabled();
    }

    @Test @GUITest
    public void completingFullGameFlow() {
        runBasicGameFlow();

        String result = window.textBox(RESULT_FIELD).text();
        assertThat(result).isNotEmpty();
    }

    private void runBasicGameFlow() {
        startGameAndPlaceBet();
        selectKeepButtons(0, 2);
        clickExchangeButton();
        placeBet(SECOND_BET_AMOUNT);
    }

    @Test @GUITest
    public void testAllFiveKeepButtons() {
        startGameAndPlaceBet();

        for (int i = 0; i < 5; i++) {
            toggleAndVerifyKeepButton(i);
        }

        window.button(EXCHANGE_BUTTON).requireEnabled();

        // Unselect enough buttons to disable exchange
        for (int i = 0; i < 4; i++) {
            window.button(KEEP_BUTTON_PREFIX + i + "]").click();
        }

        window.button(EXCHANGE_BUTTON).requireDisabled();
    }

    private void toggleAndVerifyKeepButton(int index) {
        window.button(KEEP_BUTTON_PREFIX + index + "]").click();
        assertEquals("Trade", pokerFrame.keep[index].getText());

        window.button(KEEP_BUTTON_PREFIX + index + "]").click();
        assertEquals("Keep", pokerFrame.keep[index].getText());

        window.button(KEEP_BUTTON_PREFIX + index + "]").click();
        assertEquals("Trade", pokerFrame.keep[index].getText());
    }

    @Test @GUITest
    public void testDealerRaisesAndPlayerCalls() {
        setupDealerWithCustomBehavior(
                playerBet -> playerBet + RAISE_AMOUNT,
                new int[] {1, 0, 0},
                "High Card"
        );

        setupPlayerWithCustomValues(
                new int[] {2, 0, 0},
                "Pair"
        );

        clickDealButton();
        placeBet(DEFAULT_BET_AMOUNT);

        verifyDealerRaised();
        clickCallButton();
        completeHandAfterCall();
    }

    private void setupDealerWithCustomBehavior(
            BetFunction betFunction,
            int[] pokerValue,
            String pokerValueString) {

        pokerFrame.dealerHand = new TestPokerHand(betFunction, pokerValue, pokerValueString);
    }

    private void setupPlayerWithCustomValues(int[] pokerValue, String pokerValueString) {
        pokerFrame.playerHand = new TestPokerHand(null, pokerValue, pokerValueString);
    }

    private void verifyDealerRaised() {
        JTextComponentFixture resultBox = window.textBox(RESULT_FIELD);
        assertThat(resultBox.text()).contains("dealer has raised you $" + RAISE_AMOUNT);
        window.button(JButtonMatcher.withText(CALL_BUTTON)).requireEnabled();
    }

    private void clickCallButton() {
        window.button(JButtonMatcher.withText(CALL_BUTTON)).click();
    }

    private void completeHandAfterCall() {
        selectKeepButtons(0, 1);
        clickExchangeButton();
        placeBet(SECOND_BET_AMOUNT);
    }

    @Test @GUITest
    public void testPlayerLosesWithLowerHand() {
        setupHandsForComparison(
                createTestHand(playerBet -> playerBet, new int[] {3, 0, 0}, "Three of a Kind"),
                createTestHand(null, new int[] {2, 0, 0}, "Pair")
        );

        runBasicGameFlow();

        JTextComponentFixture resultBox = window.textBox(RESULT_FIELD);
        assertThat(resultBox.text()).contains("You lose");
    }

    private TestPokerHand createTestHand(BetFunction betFunction, int[] pokerValue, String pokerValueString) {
        return new TestPokerHand(betFunction, pokerValue, pokerValueString);
    }

    private void setupHandsForComparison(TestPokerHand dealerHand, TestPokerHand playerHand) {
        pokerFrame.dealerHand = dealerHand;
        pokerFrame.playerHand = playerHand;
    }

    @Test @GUITest
    public void testSamePairValueCompareHighCards() {
        setupHandsForComparison(
                createTestHandWithHigherHand(playerBet -> playerBet, new int[] {2, 10, 5}, "Pair of 10s"),
                createTestHand(null, new int[] {2, 10, 4}, "Pair of 10s")
        );

        runBasicGameFlow();
    }

    private TestPokerHand createTestHandWithHigherHand(BetFunction betFunction, int[] pokerValue, String pokerValueString) {
        TestPokerHand hand = new TestPokerHand(betFunction, pokerValue, pokerValueString);
        hand.setHigherHandResult(1);
        return hand;
    }

    @Test @GUITest
    public void testTieGame() {
        setupHandsForComparison(
                createTestHand(playerBet -> playerBet, new int[] {4, 10, 10}, "Two Pair"),
                createTestHand(null, new int[] {4, 10, 10}, "Two Pair")
        );

        runBasicGameFlow();

        JTextComponentFixture resultBox = window.textBox(RESULT_FIELD);
        assertThat(resultBox.text()).contains("tie");
        assertThat(resultBox.text()).contains("get your money back");
    }

    @Test @GUITest
    public void testCallBeforeExchange() {
        setupDealerWithCustomBehavior(
                playerBet -> playerBet + RAISE_AMOUNT,
                null,
                null
        );

        clickDealButton();
        placeBet(DEFAULT_BET_AMOUNT);

        verifyDealerRaised();
        clickCallButton();

        verifyKeepButtonsEnabledAfterCall();
    }

    private void verifyKeepButtonsEnabledAfterCall() {
        for (int i = 0; i < 5; i++) {
            window.button(KEEP_BUTTON_PREFIX + i + "]").requireEnabled();
        }
        window.button(JButtonMatcher.withText(BET_BUTTON)).requireDisabled();
        window.button(JButtonMatcher.withText(CALL_BUTTON)).requireDisabled();
    }

    @Test @GUITest
    public void testPlayerWinsWithHigherHandRank() {
        setupHandsForComparison(
                createHandWithCards(playerBet -> playerBet, new int[] {3, 0, 0}, "Three of a Kind",
                        Card.Cards.Queen, Card.Suits.Hearts,
                        Card.Cards.Queen, Card.Suits.Diamonds,
                        Card.Cards.Queen, Card.Suits.Clubs,
                        Card.Cards.Five, Card.Suits.Hearts,
                        Card.Cards.Two, Card.Suits.Spades
                ),
                createHandWithCards(null, new int[] {5, 0, 0}, "Straight",
                        Card.Cards.Five, Card.Suits.Clubs,
                        Card.Cards.Six, Card.Suits.Hearts,
                        Card.Cards.Seven, Card.Suits.Diamonds,
                        Card.Cards.Eight, Card.Suits.Spades,
                        Card.Cards.Nine, Card.Suits.Clubs
                )
        );

        runCompleteGameFlow();

        JTextComponentFixture resultBox = window.textBox(RESULT_FIELD);
        assertThat(resultBox.text()).contains("You win");
        assertThat(resultBox.text()).contains("Straight");
    }

    private TestHandWithCards createHandWithCards(
            BetFunction betFunction,
            int[] pokerValue,
            String pokerValueString,
            Card.Cards card1, Card.Suits suit1,
            Card.Cards card2, Card.Suits suit2,
            Card.Cards card3, Card.Suits suit3,
            Card.Cards card4, Card.Suits suit4,
            Card.Cards card5, Card.Suits suit5) {

        return new TestHandWithCards(
                betFunction, pokerValue, pokerValueString,
                card1, suit1, card2, suit2, card3, suit3, card4, suit4, card5, suit5
        );
    }

    @Test @GUITest
    public void testDealerFolds() {
        setupDealerWithCustomBehavior(
                playerBet -> -1,
                new int[] {1, 0, 0},
                "High Card"
        );

        setupPlayerWithCustomValues(
                new int[] {2, 0, 0},
                "Pair"
        );

        clickDealButton();
        placeBet(30);

        JTextComponentFixture resultBox = window.textBox(RESULT_FIELD);
        assertThat(resultBox.text()).contains("The dealer folded. You win");
        assertThat(window.button(JButtonMatcher.withText(DEAL_BUTTON)).isEnabled()).isTrue();
    }

    @Test @GUITest
    public void testNegativeBetAmount() {
        clickDealButton();
        placeBet(-10);

        JTextComponentFixture resultBox = window.textBox(RESULT_FIELD);
        assertThat(resultBox.text()).contains("No cheating!");
    }

    @Test @GUITest
    public void testInvalidBetInput() {
        clickDealButton();

        window.textBox(BET_FIELD).setText("aaa");
        window.button(JButtonMatcher.withText(BET_BUTTON)).click();

        JTextComponentFixture resultBox = window.textBox(RESULT_FIELD);
        assertThat(resultBox.text()).contains("Please enter a number");
    }

    private void runCompleteGameFlow() {
        clickDealButton();
        placeBet(DEFAULT_BET_AMOUNT);

        if (window.textBox(RESULT_FIELD).text().contains("raised")) {
            clickCallButton();
        }

        selectKeepButtons(0, 1);
        clickExchangeButton();

        placeBet(SECOND_BET_AMOUNT);

        if (window.textBox(RESULT_FIELD).text().contains("raised")) {
            clickCallButton();
        }
    }

    @Override
    protected void onTearDown() {
        window.cleanUp();
    }


    interface BetFunction {
        int apply(int playerBet);
    }

    class TestPokerHand extends PokerHand {
        private final BetFunction betFunction;
        private final int[] pokerValueResult;
        private final String pokerValueString;
        private int higherHandResult = 0;

        public TestPokerHand(BetFunction betFunction, int[] pokerValueResult, String pokerValueString) {
            this.betFunction = betFunction;
            this.pokerValueResult = pokerValueResult;
            this.pokerValueString = pokerValueString;
        }

        public void setHigherHandResult(int result) {
            this.higherHandResult = result;
        }

        @Override
        public int bet(int playerBet) {
            return betFunction != null ? betFunction.apply(playerBet) : 0;
        }

        @Override
        public int[] PokerValue() {
            return pokerValueResult;
        }

        @Override
        public String showPokerValue() {
            return pokerValueString;
        }

        @Override
        public int higherHand(PokerHand other) {
            return higherHandResult;
        }

        @Override
        public ArrayList<Card> getHand() {
            ArrayList<Card> cards = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                cards.add(new Card(Card.Cards.Two, Card.Suits.Hearts));
            }
            return cards;
        }
    }

    class TestHandWithCards extends TestPokerHand {
        private final Card.Cards[] cardTypes;
        private final Card.Suits[] cardSuits;

        public TestHandWithCards(
                BetFunction betFunction,
                int[] pokerValueResult,
                String pokerValueString,
                Card.Cards card1, Card.Suits suit1,
                Card.Cards card2, Card.Suits suit2,
                Card.Cards card3, Card.Suits suit3,
                Card.Cards card4, Card.Suits suit4,
                Card.Cards card5, Card.Suits suit5) {

            super(betFunction, pokerValueResult, pokerValueString);

            this.cardTypes = new Card.Cards[] { card1, card2, card3, card4, card5 };
            this.cardSuits = new Card.Suits[] { suit1, suit2, suit3, suit4, suit5 };
        }

        @Override
        public ArrayList<Card> getHand() {
            ArrayList<Card> cards = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                cards.add(new Card(cardTypes[i], cardSuits[i]));
            }
            return cards;
        }
    }
}