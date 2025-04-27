import org.junit.Test;
import java.lang.reflect.Method;
import static org.junit.Assert.*;

public class BJCoverageTests {

    public static String callPrivateToString(Object obj) throws Exception {
        Method toStringMethod = obj.getClass().getDeclaredMethod("toString");
        toStringMethod.setAccessible(true); // bypass private access
        return (String) toStringMethod.invoke(obj);
    }



    VisualBJ bj;

    /*
    @BeforeEach
    public void setUp() throws Exception {
        bj = new VisualBJ("Testing Blackjack");
    }

     */

    @Test
    public void betWithoutInput() {
        bj = new VisualBJ("Testing Blackjack");
        bj.getBetButton().doClick();
        assertEquals("Please enter a number", bj.getResultsTextField().getText());
        assertEquals(0, bj.getPlayerHand().getSize());
        assertEquals(0, bj.getDealerHand().getSize());
    }

    @Test
    public void betNormalInput() {
        bj = new VisualBJ("Testing Blackjack");
        bj.getBetTextField().setText("100");
        bj.getBetButton().doClick();
        assertEquals(2, bj.getPlayerHand().getSize());
        assertEquals(2, bj.getDealerHand().getSize());
    }

    @Test
    public void userBlackJackOnDraw() throws Exception {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getPlayerHand().BJValue() != 21) {
            //System.out.println(bj.getPlayerHand().BJValue());
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
        }
        bj.getBetButton().doClick();
        assertEquals(21, bj.getPlayerHand().BJValue());
        assertEquals(2, bj.getPlayerHand().getSize());
        String first = callPrivateToString(bj.getPlayerHand().getCard(0));
        String second = callPrivateToString(bj.getPlayerHand().getCard(1));
        assertTrue(first.contains("Ace") || second.contains("Ace"));
        assertFalse(second.contains("Ace") && first.contains("Ace"));
        assertEquals("BLACKJACK! You win! You win $" + 100 * 3 + "!", bj.getResultsTextField().getText());
    }

    @Test
    public void dealerBlackJackOnDraw() throws Exception {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getDealerHand().BJValue() != 21) {
            //System.out.println(bj.getPlayerHand().BJValue());
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
        }
        assertEquals(21, bj.getDealerHand().BJValue());
        assertEquals(2, bj.getDealerHand().getSize());
        String first = callPrivateToString(bj.getDealerHand().getCard(0));
        String second = callPrivateToString(bj.getDealerHand().getCard(1));
        assertTrue(first.contains("Ace") || second.contains("Ace"));
        assertFalse(second.contains("Ace") && first.contains("Ace"));
        assertEquals("BLACKJACK! Dealer wins! You lose $" + 100 + "!", bj.getResultsTextField().getText());
    }

    @Test
    public void pushOnDraw() {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getPlayerHand().BJValue() < 17 || (bj.getDealerHand().BJValue() != bj.getPlayerHand().BJValue())) {
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
        }
        bj.getStayButton().doClick();
        assertEquals("You and the dealer have the same hand. You push.", bj.getResultsTextField().getText());
    }

    @Test
    public void loseOnDraw() {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getPlayerHand().BJValue() < 17 || (bj.getDealerHand().BJValue() <= bj.getPlayerHand().BJValue())) {
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
        }
        bj.getStayButton().doClick();
        assertEquals("The dealer has a better hand than you. You lose $" + 100 + "!", bj.getResultsTextField().getText());
    }

    @Test
    public void winOnDraw() {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getDealerHand().BJValue() < 17 || (bj.getDealerHand().BJValue() >= bj.getPlayerHand().BJValue())) {
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
        }
        bj.getStayButton().doClick();
        assertEquals("You have a better hand than the dealer. You win $" + 100 + "!", bj.getResultsTextField().getText());
    }

    @Test
    public void stayDealerBusts() {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getDealerHand().BJValue() < 21) {
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
            bj.getStayButton().doClick();
        }

        assertEquals("The dealer busted. You win $" + 100 + "!", bj.getResultsTextField().getText());
    }

    @Test
    public void stayDealerDrawsLose() {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getDealerHand().getSize() < 3 || bj.getDealerHand().BJValue() < bj.getPlayerHand().BJValue() || bj.getDealerHand().BJValue() > 21) {
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
            bj.getStayButton().doClick();
        }
        assertEquals("The dealer has a better hand than you. You lose $" + 100 + "!", bj.getResultsTextField().getText());
    }

    @Test
    public void stayDealerDrawsWin() {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getDealerHand().getSize() < 3 || bj.getDealerHand().BJValue() > bj.getPlayerHand().BJValue() || bj.getDealerHand().BJValue() > 21) {
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
            bj.getStayButton().doClick();
        }
        assertEquals("You have a better hand than the dealer. You win $" + 100 + "!", bj.getResultsTextField().getText());
    }

    @Test
    public void hitToBust() {
        bj = new VisualBJ("Testing Blackjack");
        bj.getBetTextField().setText("100");
        bj.getBetButton().doClick();
        while (bj.getPlayerHand().BJValue() < 21) {
            bj.getHitButton().doClick();
        }
        assertEquals("You busted! Dealer wins! You lose $" + 100 + "!", bj.getResultsTextField().getText());
    }

    @Test
    public void hitToWin() {
        bj = new VisualBJ("Testing Blackjack");

        do {
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
            while (bj.getPlayerHand().BJValue() < bj.getDealerHand().BJValue() || bj.getPlayerHand().BJValue() < 17) {
                bj.getHitButton().doClick();
            }
        } while (bj.getPlayerHand().BJValue() > 21 || bj.getDealerHand().BJValue() > bj.getPlayerHand().BJValue());

        bj.getStayButton().doClick();

        assertEquals("You have a better hand than the dealer. You win $" + 100 + "!", bj.getResultsTextField().getText());
    }

    @Test
    public void downBust() {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getPlayerHand().BJValue() < 21) {
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
            bj.getDoubleDownButton().doClick();
        }
        assertEquals("You busted! Dealer wins!" + "    You lose $" + 100 * 2 + "!", bj.getResultsTextField().getText());

    }

    @Test
    public void dealSimple() {
        bj = new VisualBJ("Testing Blackjack");
        while (bj.getPlayerHand().BJValue() < 17 || (bj.getDealerHand().BJValue() != bj.getPlayerHand().BJValue())) {
            bj = new VisualBJ("Testing Blackjack");
            bj.getBetTextField().setText("100");
            bj.getBetButton().doClick();
        }
        bj.getStayButton().doClick();

        bj.getDealButton().doClick();

        assertEquals("", bj.getResultsTextField().getText());
        assertEquals(2, bj.getDealerHand().getSize());
        assertEquals(2, bj.getPlayerHand().getSize());
    }
}
