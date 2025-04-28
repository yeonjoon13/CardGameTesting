Instructions:

Within the project, there is a /slides folder that contains the presentation slides and a /htmlReport that contains a report from a previous coverage report.

To run the tests locally, download the project and open in IntelliJ.  A java JDK that supports jUnit4 and jUnit 5 such as JDK 17 is required.
Within the project, right-click the src/tests/java folder, hover over the "More Run/Debug option" in the menu and click "Run 'Tests in 'CardGameTestting''" with coverage.

The GUI tests, BJCoverageTests, PokerCoverageTests, and THCoverageTests utilize the randomness of the SUT and may produce minorly different results on different executions.
