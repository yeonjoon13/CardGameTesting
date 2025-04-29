Instructions:

Within the project, there is a /Slides folder that contains the presentation slides and a /htmlReport that contains a report from a previous coverage report.

To run the tests locally, download the project and open in IntelliJ.  All the dependencies are in "build.gradle". A java JDK that supports jUnit4 and jUnit 5 such as JDK 17 is required.
Within the project, right-click the src/tests/java folder, hover over the "More Run/Debug option" in the menu and click "Run 'Tests in 'CardGameTestting''" with coverage.

The GUI tests, BJCoverageTests, PokerCoverageTests, and THCoverageTests utilize the randomness of the SUT and may produce minorly different results on different executions.
In addition, sometimes one or two GUI tests may fail due to chance that sometimes the High-Low game's first card is an Ace. Since there is no card higher than an Ace, it will go through
all the cards in the deck and find no card higher which results in a failure which states that there are no more cards in the deck. 
