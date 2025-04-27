import org.junit.Test;
import java.lang.reflect.Method;
import static org.junit.Assert.*;

public class THCoverageTests {
    public static String callPrivateToString(Object obj) throws Exception {
        Method toStringMethod = obj.getClass().getDeclaredMethod("toString");
        toStringMethod.setAccessible(true); // bypass private access
        return (String) toStringMethod.invoke(obj);
    }

    THpublic th;

    @Test
    public void dealBasic() throws InterruptedException {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(5000);
        assertEquals(2, th.getPlayerHand().getSize());
        assertEquals(2, th.getDealerHand().getSize());
        assertEquals(0, th.getCommunityHand().getSize());
    }

    @Test
    public void betNegative() throws InterruptedException {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(1000);
        th.getBetAmountField().setText("-300");
        th.getBetButton().doClick();
        assertFalse(th.getConsole().getText().contains("-"));
    }

    @Test
    public void betNonsense() throws InterruptedException {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(1000);
        th.getBetAmountField().setText("^_^");
        th.getBetButton().doClick();
        assertFalse(th.getConsole().getText().contains("Please enter a valid number"));
    }

    @Test
    public void betSmall() throws InterruptedException {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(1000);
        th.getBetAmountField().setText("5");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        while (th.getConsole().getText().contains("has raised")) {
            th.getCallButton().doClick();
            //Thread.sleep(1000);
        }
        th.getBetAmountField().setText("5");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        while (th.getConsole().getText().contains("has raised")) {
            th.getCallButton().doClick();
            //Thread.sleep(1000);
        }
        System.out.println(th.getConsole().getText());
        assertTrue(th.getConsole().getText().contains("$"));
    }

    @Test
    public void betBig() throws InterruptedException {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(1000);
        th.getBetAmountField().setText("5000");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        System.out.println(th.getConsole().getText());
        assertTrue(th.getConsole().getText().contains("folded"));
    }

    @Test
    public void resetEarly() throws  InterruptedException {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(1000);
        th.getBetAmountField().setText("5000");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        System.out.println(th.getConsole().getText());
        th.getDealButton().doClick();
        Thread.sleep(2000);
        assertEquals(2, th.getPlayerHand().getSize());
        assertEquals(2, th.getDealerHand().getSize());
        assertEquals(0, th.getCommunityHand().getSize());
    }

    @Test
    public void resetLate() throws InterruptedException {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(1000);
        th.getBetAmountField().setText("5");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        while (th.getConsole().getText().contains("has raised")) {
            th.getCallButton().doClick();
            //Thread.sleep(1000);
        }
        th.getBetAmountField().setText("5");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        while (th.getConsole().getText().contains("has raised")) {
            th.getCallButton().doClick();
            //Thread.sleep(1000);
        }
        th.getDealButton().doClick();
        Thread.sleep(2000);
        assertEquals(2, th.getPlayerHand().getSize());
        assertEquals(2, th.getDealerHand().getSize());
        assertEquals(0, th.getCommunityHand().getSize());
    }

    @Test
    public void foldEarly() throws InterruptedException {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(2000);
        th.getFoldButton().doClick();
        Thread.sleep(1000);
        System.out.println(th.getConsole().getText());
        assertTrue(th.getConsole().getText().contains("Folded"));
    }

    @Test
    public void lose() throws Exception {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(2000);
        while (((!callPrivateToString(th.getDealerHand().getCard(0)).contains("Ace")
                        && !callPrivateToString(th.getDealerHand().getCard(1)).contains("Ace"))))
        {
            System.out.println(callPrivateToString(th.getPlayerHand().getCard(0)));
            System.out.println(callPrivateToString(th.getPlayerHand().getCard(1)));
            System.out.println("Dealer");
            System.out.println(callPrivateToString(th.getDealerHand().getCard(0)));
            System.out.println(callPrivateToString(th.getDealerHand().getCard(1)));

            th.getFoldButton().doClick();
            //Thread.sleep(1000);
            th.getDealButton().doClick();
            Thread.sleep(2000);
        }
        th.getBetAmountField().setText("5");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        while (th.getConsole().getText().contains("has raised")) {
            th.getCallButton().doClick();
            //Thread.sleep(1000);
        }
        th.getBetAmountField().setText("5");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        while (th.getConsole().getText().contains("has raised")) {
            th.getCallButton().doClick();
            //Thread.sleep(1000);
        }
        System.out.println(th.getConsole().getText());
        assertTrue(th.getConsole().getText().contains("lose"));

    }

    @Test
    public void win() throws Exception {
        th = new THpublic("Testing Poker");
        th.getDealButton().doClick();
        Thread.sleep(2000);
        while (((!callPrivateToString(th.getPlayerHand().getCard(0)).contains("Ace")
                && !callPrivateToString(th.getPlayerHand().getCard(1)).contains("Ace"))))
        {

            th.getFoldButton().doClick();
            //Thread.sleep(1000);
            th.getDealButton().doClick();
            Thread.sleep(2000);
        }
        th.getBetAmountField().setText("5");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        while (th.getConsole().getText().contains("has raised")) {
            th.getCallButton().doClick();
            //Thread.sleep(1000);
        }
        th.getBetAmountField().setText("5");
        th.getBetButton().doClick();
        Thread.sleep(1000);
        while (th.getConsole().getText().contains("has raised")) {
            th.getCallButton().doClick();
            //Thread.sleep(1000);
        }
        System.out.println(th.getConsole().getText());
        assertTrue(th.getConsole().getText().contains("win"));

    }
}
