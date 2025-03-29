import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GuessNumberIOTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inContent = null;
    private final java.io.InputStream originalIn = System.in;

    // Define a constant seed value for reproducible tests
    private static final long SEED_VALUE = 12345L;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    // This test verifies that the game correctly identifies when a player guesses the number on first try
    @Test
    public void testCorrectGuessFirstTry() {
        // Create a seeded Random object
        Random seededRandom = new Random(SEED_VALUE);
        // Generate the number that will be produced with this seed
        int targetNumber = 1 + seededRandom.nextInt(100);

        // Reset the random with the same seed for the actual test
        seededRandom = new Random(SEED_VALUE);

        // Set up input for the game with the correct guess
        String input = targetNumber + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the game
        int result = GuessNumber.guessingNumberGame(seededRandom);

        // Verify results
        assertEquals(targetNumber, result);
        assertTrue(outContent.toString().contains("Congratulations! You guessed the number."));
    }

    // This test verifies handling of multiple guesses ending in success
    @Test
    public void testCorrectGuessAfterMultipleTries() {
        // Create a seeded Random object
        Random seededRandom = new Random(SEED_VALUE);
        // Generate the number that will be produced with this seed
        int targetNumber = 1 + seededRandom.nextInt(100);

        // Reset the random with the same seed for the actual test
        seededRandom = new Random(SEED_VALUE);

        // Set up input for the game - several incorrect guesses, then correct
        String input = "10\n20\n30\n" + targetNumber + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the game
        int result = GuessNumber.guessingNumberGame(seededRandom);

        // Verify results
        assertEquals(targetNumber, result);
        assertTrue(outContent.toString().contains("Congratulations! You guessed the number."));
    }

    // This test verifies that the game correctly identifies when a player runs out of guesses
    @Test
    public void testRunOutOfGuesses() {
        // Create a seeded Random object
        Random seededRandom = new Random(SEED_VALUE);
        // Generate the number that will be produced with this seed
        int targetNumber = 1 + seededRandom.nextInt(100);

        // Reset the random with the same seed for the actual test
        seededRandom = new Random(SEED_VALUE);

        // Set up input for 5 incorrect guesses (all far from the target number)
        String input = "1\n2\n3\n4\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the game
        int result = GuessNumber.guessingNumberGame(seededRandom);

        // Verify results
        assertEquals(targetNumber, result);
        assertTrue(outContent.toString().contains("You have exhausted 5 trials."));
        assertTrue(outContent.toString().contains("The number was " + targetNumber));
    }

    // FAULT: The game provides incorrect feedback when the guess is greater than the target number
    // on the final guess (4th index, which is the 5th guess)
    @Test
    public void testIncorrectFeedbackOnLastGuessHigher() {
        // Create a seeded Random object
        Random seededRandom = new Random(SEED_VALUE);
        // Generate the number that will be produced with this seed
        int targetNumber = 1 + seededRandom.nextInt(100);

        // Reset the random with the same seed for the actual test
        seededRandom = new Random(SEED_VALUE);

        // Set up input where last guess is higher than target
        int higherGuess = targetNumber + 10; // Ensure it's higher
        String input = "10\n20\n30\n40\n" + higherGuess + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the game
        GuessNumber.guessingNumberGame(seededRandom);

        // On the last guess, it should say "The number is less than <higherGuess>"
        String output = outContent.toString();
        assertTrue(output.contains("The number is less than " + higherGuess));
    }

    // FAULT: The game gives inconsistent feedback for higher vs lower guesses
    @Test
    public void testInconsistentFeedbackConditions() {
        // Create a seeded Random object
        Random seededRandom = new Random(SEED_VALUE);
        // Generate the number that will be produced with this seed
        int targetNumber = 1 + seededRandom.nextInt(100);

        // Reset the random with the same seed for the actual test
        seededRandom = new Random(SEED_VALUE);

        // Set up input with alternating too high and too low guesses
        int lowerGuess = targetNumber - 1;
        int higherGuess = targetNumber + 1;
        int finalHigherGuess = targetNumber + 5;

        String input = lowerGuess + "\n" + higherGuess + "\n" +
                lowerGuess + "\n" + higherGuess + "\n" +
                finalHigherGuess + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the game
        GuessNumber.guessingNumberGame(seededRandom);

        // Examine output to verify the inconsistency
        String output = outContent.toString();

        // On the 5th guess (index 4), feedback should be consistent
        // But the code gives feedback when guess < number, not when guess > number
        assertTrue(output.contains("The number is greater than " + lowerGuess));
        assertTrue(output.contains("The number is less than " + higherGuess));
        assertFalse(output.contains("The number is less than " + finalHigherGuess));
    }

    // Test boundary values using seeded random
    @Test
    public void testBoundaryValues() {
        // Test for lower boundary (1)
        testForSpecificNumber(1);

        // Test for upper boundary (100)
        testForSpecificNumber(100);
    }

    // Helper method to test a specific number
    private void testForSpecificNumber(int targetNumber) {
        // Instead of using a seeded random, use a mock that always returns the specific value
        Random mockRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return targetNumber - 1; // Will make number = targetNumber
            }
        };

        // Reset output for this test
        outContent.reset();

        // Set up input with the correct guess
        String input = targetNumber + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the game
        int result = GuessNumber.guessingNumberGame(mockRandom);

        // Verify results
        assertEquals(targetNumber, result);
        assertTrue(outContent.toString().contains("Congratulations! You guessed the number."));
    }


    // FAULT: The game doesn't validate user input, allowing non-numeric entries to crash the game
    @Test
    public void testInvalidInputHandling() {
        // Create a seeded Random object
        Random seededRandom = new Random(SEED_VALUE);

        // Input with a non-numeric value
        String input = "abc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThrows(java.util.InputMismatchException.class, () -> {
            GuessNumber.guessingNumberGame(seededRandom);
        });
    }
}