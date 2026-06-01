# Shopping Cart TDD Lab

This repository includes the code, tests, and report for the TDD shopping cart assignment.

## How to capture screenshots

Use a terminal window and run the commands below. After each command finishes, take a screenshot of the terminal output.

### 1) Compile the project

```bash
rm -rf out && mkdir -p out && javac -d out -cp /tmp/junit-extract/usr/share/java/junit-jupiter-api-5.10.1.jar:/tmp/junit-extract/usr/share/java/apiguardian-api-1.1.2.jar:/tmp/junit-extract/usr/share/java/opentest4j-1.2.0.jar src/*.java Test/*.java
```

### 2) Capture the RED phase screenshot

Run the failing test set from the temporary red-phase worktree:

```bash
git worktree add /tmp/red-phase f2fa427^
cd /tmp/red-phase
rm -rf out && mkdir -p out && javac -d out -cp /tmp/junit-extract/usr/share/java/junit-jupiter-api-5.10.1.jar:/tmp/junit-extract/usr/share/java/apiguardian-api-1.1.2.jar:/tmp/junit-extract/usr/share/java/opentest4j-1.2.0.jar src/*.java Test/*.java
java -jar /tmp/junit-extract/usr/share/java/junit-platform-console-standalone-1.9.1.jar -cp out --scan-classpath
```

The output should show one failing test for `testUpdateItemPrice_ShouldChangePrice()`.

### 3) Capture the GREEN phase screenshot

Return to the main repository and run the full suite:

```bash
cd /home/sahand/Desktop/selab_hw3/base-project-for-tdd-shoppingcart-main
rm -rf out && mkdir -p out && javac -d out -cp /tmp/junit-extract/usr/share/java/junit-jupiter-api-5.10.1.jar:/tmp/junit-extract/usr/share/java/apiguardian-api-1.1.2.jar:/tmp/junit-extract/usr/share/java/opentest4j-1.2.0.jar src/*.java Test/*.java
java -jar /tmp/junit-extract/usr/share/java/junit-platform-console-standalone-1.9.1.jar -cp out --scan-classpath
```

The output should show all tests passing except the intentionally skipped `testDiscountAtBoundary_WRONG()`.

### 4) Capture the coverage output

Run the tests with JaCoCo instrumentation and then print coverage numbers:

```bash
java -javaagent:/tmp/jacocoagent.jar=destfile=/tmp/final.exec -jar /tmp/junit-extract/usr/share/java/junit-platform-console-standalone-1.9.1.jar -cp out --select-class ShoppingCartTest --select-class ShoppingCartEdgeCaseTest
java -cp /tmp:/tmp/junit-extract/usr/share/java/org.jacoco.core-0.8.11.jar:/tmp/junit-extract/usr/share/java/org.jacoco.report-0.8.11.jar:/tmp/junit-extract/usr/share/java/asm-all-9.7.jar JacocoMetrics /tmp/final.exec out ShoppingCart
```

Expected final coverage:

- Line Coverage: `100.00%`
- Method Coverage: `100.00%`
- Branch Coverage: `100.00%`

## Notes

- `REPORT.md` contains the Persian lab report.
- Replace `<put hamgit repo link here>` and the screenshot placeholders before submission.
