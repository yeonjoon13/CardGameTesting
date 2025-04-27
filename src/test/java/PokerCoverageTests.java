import org.junit.Test;
import java.lang.reflect.Method;
import static org.junit.Assert.*;


public class PokerCoverageTests {

    public static String callPrivateToString(Object obj) throws Exception {
        Method toStringMethod = obj.getClass().getDeclaredMethod("toString");
        toStringMethod.setAccessible(true); // bypass private access
        return (String) toStringMethod.invoke(obj);
    }

    Poker poker;

    @Test
    public void dealBasic() {
        poker = new Poker("Testing Poker");
        poker.getDealButton().doClick();
        assertEquals(5, poker.getPlayerHand().getSize());
        assertEquals(5, poker.getDealerHand().getSize());
    }

    @Test
    public void immediateFold() {
        poker = new Poker("Testing Poker");
        poker.getDealButton().doClick();
        poker.getFoldButton().doClick();
        assertEquals("You have folded. You lose $" + 0 + "!", poker.getResultsTextField().getText());
    }

    @Test
    public void betNegative() {
        poker = new Poker("Testing Poker");
        poker.getDealButton().doClick();
        poker.getBetTextField().setText("-1");
        poker.getBetButton().doClick();
        assertEquals("No cheating!", poker.getResultsTextField().getText());
    }

    @Test
    public void betNonsense() {
        poker = new Poker("Testing Poker");
        poker.getDealButton().doClick();
        poker.getBetTextField().setText("^_^");
        poker.getBetButton().doClick();
        assertEquals("Please enter a number", poker.getResultsTextField().getText());
    }

    @Test
    public void exchangeNone() throws Exception {
        poker = new Poker("Testing Poker");
        poker.getDealButton().doClick();
        poker.getBetTextField().setText("500");
        poker.getBetButton().doClick();
        poker.getCallButton().doClick();
        String original[] = new String[5];
        for (int i = 0; i < 5; i++) {
            original[i] = callPrivateToString(poker.getPlayerHand().getCard(i));
            poker.getKeep()[i].doClick();
        }
        Boolean found[] = new Boolean[5];
        poker.getExchangeButton().doClick();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (original[i].equals(callPrivateToString(poker.getPlayerHand().getCard(j)))) {
                    found[i] = true;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            assertTrue(found[i]);
        }
    }

    @Test
    public void keepMinimum() throws Exception {
        poker = new Poker("Testing Poker");
        poker.getDealButton().doClick();
        poker.getBetTextField().setText("500");
        poker.getBetButton().doClick();
        poker.getCallButton().doClick();
        String original[] = new String[5];
        for (int i = 0; i < 5; i++) {
            original[i] = callPrivateToString(poker.getPlayerHand().getCard(i));
            if (i < 3) {
                poker.getKeep()[i].doClick();
            }
        }
        Boolean found[] = new Boolean[5];
        poker.getExchangeButton().doClick();
        for (int i = 0; i < 5; i++) {
            found[i] = false;
            for (int j = 0; j < 5; j++) {
                if (original[i].equals(callPrivateToString(poker.getPlayerHand().getCard(j)))) {
                    found[i] = true;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            if (i < 3) {
                assertTrue(found[i]);
            }else {
                assertFalse(found[i]);
            }
        }
    }

    @Test
    public void keepElse() throws Exception {
        poker = new Poker("Testing Poker");
        poker.getDealButton().doClick();
        poker.getBetTextField().setText("500");
        poker.getBetButton().doClick();
        poker.getCallButton().doClick();
        String original[] = new String[5];
        for (int i = 0; i < 5; i++) {
            original[i] = callPrivateToString(poker.getPlayerHand().getCard(i));
            if (i >= 3) {
                poker.getKeep()[i].doClick();
            }
        }
        Boolean found[] = new Boolean[5];
        poker.getExchangeButton().doClick();
        for (int i = 0; i < 5; i++) {
            found[i] = false;
            for (int j = 0; j < 5; j++) {
                if (original[i].equals(callPrivateToString(poker.getPlayerHand().getCard(j)))) {
                    found[i] = true;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            if (i >= 3) {
                assertTrue(found[i]);
            }else {
                assertFalse(found[i]);
            }
        }
    }

    @Test
    public void toggleAll() {
        poker = new Poker("Testing Poker");
        poker.getDealButton().doClick();
        poker.getBetTextField().setText("500");
        poker.getBetButton().doClick();
        poker.getCallButton().doClick();
        for (int i = 0; i < 10; i++) {
            poker.getKeep()[i / 2].doClick();
        }
    }

    @Test
    public void playerWin() {
        poker = new Poker("Testing Poker");
        while (poker.getPlayerHand().showPokerValue().equals("Flush") || !poker.getDealerHand().showPokerValue().equals("Flush")) {
            poker = new Poker("Testing Poker");
            poker.getDealButton().doClick();
            poker.getBetTextField().setText("500");
            poker.getBetButton().doClick();
        }
        poker.getCallButton().doClick();
        for (int i = 0; i < 5; i++) {
            poker.getKeep()[i].doClick();
        }
        poker.getExchangeButton().doClick();
        poker.getBetTextField().setText("500");
        poker.getBetButton().doClick();
        poker.getCallButton().doClick();
        assertTrue(poker.getResultsTextField().getText().contains("You win"));
    }

    @Test
    public void dealerWin() {
        poker = new Poker("Testing Poker");
        while (!poker.getPlayerHand().showPokerValue().equals("Flush") || poker.getDealerHand().showPokerValue().equals("Flush")) {
            poker = new Poker("Testing Poker");
            poker.getDealButton().doClick();
            poker.getBetTextField().setText("500");
            poker.getBetButton().doClick();
        }
        poker.getCallButton().doClick();
        for (int i = 0; i < 5; i++) {
            poker.getKeep()[i].doClick();
        }
        poker.getExchangeButton().doClick();
        poker.getBetTextField().setText("500");
        poker.getBetButton().doClick();
        poker.getCallButton().doClick();
        assertTrue(poker.getResultsTextField().getText().contains("You lose"));
    }

    @Test
    public void playerWinDeep() {
        poker = new Poker("Testing Poker");
        while (!poker.getResultsTextField().getText().contains("You win")) {
            poker = new Poker("Testing Poker");
            poker.getDealButton().doClick();
            poker.getBetTextField().setText("500");
            poker.getBetButton().doClick();
            poker.getCallButton().doClick();
            for (int i = 0; i < 5; i++) {
                poker.getKeep()[i].doClick();
            }
            poker.getExchangeButton().doClick();
            poker.getBetTextField().setText("500");
            poker.getBetButton().doClick();
            poker.getCallButton().doClick();
            if (poker.getPlayerHand().PokerValue()[0] != 2 ||
                    poker.getDealerHand().PokerValue()[0] != 2) {
                continue;
            }
        }
    }

    @Test
    public void dealerWinDeep() {
        poker = new Poker("Testing Poker");
        while (!poker.getResultsTextField().getText().contains("You lose")) {
            poker = new Poker("Testing Poker");
            poker.getDealButton().doClick();
            poker.getBetTextField().setText("500");
            poker.getBetButton().doClick();
            poker.getBetTextField().setText("1000");
            poker.getBetButton().doClick();
            poker.getCallButton().doClick();
            for (int i = 0; i < 5; i++) {
                poker.getKeep()[i].doClick();
            }
            poker.getExchangeButton().doClick();
            poker.getBetTextField().setText("500");
            poker.getBetButton().doClick();
            poker.getCallButton().doClick();
            if (poker.getPlayerHand().PokerValue()[0] != 2 ||
                    poker.getDealerHand().PokerValue()[0] != 2) {
                continue;
            }
        }
    }

    @Test
    public void tie() {
        poker = new Poker("Testing Poker");
        while (!poker.getResultsTextField().getText().contains("tie")) {
            poker = new Poker("Testing Poker");
            poker.getDealButton().doClick();
            poker.getBetTextField().setText("500");
            poker.getBetButton().doClick();
            poker.getBetTextField().setText("1000");
            poker.getBetButton().doClick();
            poker.getCallButton().doClick();
            for (int i = 0; i < 5; i++) {
                poker.getKeep()[i].doClick();
            }
            poker.getExchangeButton().doClick();
            poker.getBetTextField().setText("500");
            poker.getBetButton().doClick();
            poker.getCallButton().doClick();
        }
    }


}
