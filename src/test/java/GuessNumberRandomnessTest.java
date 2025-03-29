import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class GuessNumberRandomnessTest {

    // Custom Random subclass that records generated numbers
    static class RecordingRandom extends Random {
        private final int[] counts = new int[101]; // 1-100
        private final Random seededRandom;

        public RecordingRandom(long seed) {
            this.seededRandom = new Random(seed);
        }

        @Override
        public int nextInt(int bound) {
            int value = seededRandom.nextInt(bound);
            if (bound == 100) { // Only record when generating 1-100
                counts[value + 1]++; // Convert 0-99 to 1-100
            }
            return value;
        }

        public int[] getCounts() {
            return counts;
        }
    }

    @Test
    public void testRandomNumberDistribution() {
        final int numberOfRuns = 30000;
        final int minNumber = 1;
        final int maxNumber = 100;

        // Create our recording random instance
        RecordingRandom recordingRandom = new RecordingRandom(0);

        // Directly generate numbers without user interaction
        for (int i = 0; i < numberOfRuns; i++) {
            // This calls nextInt(100) internally and adds 1
            recordingRandom.nextInt(100);
        }

        int[] frequencies = recordingRandom.getCounts();
        double expectedFrequency = (double) numberOfRuns / (maxNumber - minNumber + 1);

        // Find min and max frequencies
        int minFrequency = Integer.MAX_VALUE;
        int maxFrequency = Integer.MIN_VALUE;

        for (int i = minNumber; i <= maxNumber; i++) {
            if (frequencies[i] < minFrequency) {
                minFrequency = frequencies[i];
            }
            if (frequencies[i] > maxFrequency) {
                maxFrequency = frequencies[i];
            }
        }

        // Make final copies for use in lambda
        final int finalMinFrequency = minFrequency;
        final int finalMaxFrequency = maxFrequency;

        // Verify the ratio
        final double ratio = (double) finalMaxFrequency / finalMinFrequency;
        assertTrue(ratio <= 1.5,
                () -> String.format("Ratio %f exceeds 1.5 threshold (max=%d, min=%d)",
                        ratio, finalMaxFrequency, finalMinFrequency));

        // Verify all numbers were generated
        for (int i = minNumber; i <= maxNumber; i++) {
            final int number = i;
            assertTrue(frequencies[number] > 0,
                    () -> "Number " + number + " was never generated");
        }

        System.out.println("Expected frequency: " + expectedFrequency);
        System.out.println("Min frequency: " + minFrequency);
        System.out.println("Max frequency: " + maxFrequency);
        System.out.println("Ratio: " + ratio);
    }
}