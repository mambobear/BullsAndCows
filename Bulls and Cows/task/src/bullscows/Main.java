package bullscows;

import java.util.*;

public class Main {

    public static String secretCode;

    public static void main(String[] args) {

        // stage 1
        // printGameLog();

        // Stage 2
        // testGrade();

        // stage 3
        // testSecreteCode();

        // stage 4
        // Description
        /*
        * n this stage, you should combine all the previous parts into a simple playable
        * version of the "Bulls and Cows" game. First, prompt the player to input the
        * length of the secret code. The length will determine the difficulty level for
        * the current game session. The program should generate a secret code of the
        * given length. Remember that it should consist of unique numbers.
        *
        * Then, the game starts and the program prompts the player to guess the code.
        * When the player inputs a number, the program should grade it in bulls and cows.
        * The game goes on until the code is guessed, that is, until the number of bulls
        * is equal to the number of digits in the code. When the game ends, the program
        * should finish its execution.
        *
        */

        // Objectives
        /*
        *   1. Ask for the length of the secret code and then generate the code.
        *   2. Wait for the user input.
        *   3. Grade the guessing attempt in bulls and cows.
        *   4. If the secret code has been guessed, the program stops; otherwise, return
        *      to the second step.
        */

        // Examples
        /*
        *
        * Please, enter the secret code's length:
        * > 4
        * Okay, let's start a game!
        * Turn 1:
        * > 1234
        * Grade: 1 bull and 1 cow
        * Turn 2:
        * > 7354
        * Grade: 2 bulls and 1 cow
        * Turn 3:
        * > 9374
        * Grade: 4 bulls
        * Congratulations! You guessed the secret code.
        */
        playBullsAndCows();

        // stage 5. Improve the code generator
    }

    private static void playBullsAndCows() {

        Scanner scanner = new Scanner(System.in);

        int bulls = 0;
        String guess;
        int[] grade;
        int turn = 1;

        secretCode = getSecretCode();
        System.out.println("Okay, let's start a game!");

        while (true) {
            System.out.printf("Turn %d:\n", turn++);
            guess = scanner.next();
            grade = grade(guess);
            bulls = grade[0];
            printGrade(grade);
            if (bulls == secretCode.length()) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }

    }

    private static String getSecretCode() {
        Scanner scanner = new Scanner(System.in);
        int numDigits;
        String code;
        while (true) {
            System.out.println("Please, enter the secret code's length:");
            numDigits = scanner.nextInt();
            if (numDigits > 10) {
                System.out.println("Error: can't generate a secret number with a " +
                        "length of 11 because there aren't enough unique digits.");
            } else {
                code = generateCode(numDigits);
                break;
            }
        }
        return code;
    }

    private static void testSecreteCode() {
        Scanner scanner = new Scanner(System.in);
        int numDigits = scanner.nextInt();

        if (numDigits > 10) {
            System.out.println("Error: can't generate a secret number with a " +
                    "length of 11 because there aren't enough unique digits.");
        } else {
            System.out.println(generateCode(numDigits));
        }
    }

    private static String generateCode(int numDigits) {
        ArrayList<Integer> digits =  new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        StringBuilder strb = new StringBuilder();
        Random random = new Random(System.nanoTime());

        int idx = random.nextInt(digits.size() - 1) + 1;
        strb.append(digits.get(idx));
        digits.remove(idx);

        for (int i = 1; i < numDigits; i++) {
            idx = random.nextInt(digits.size());
            strb.append(digits.get(idx));
            digits.remove(idx);
        }
        return strb.toString();
    }

    private static void testGrade() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        printGrade(grade(str));
    }

    private static void printGrade(int[] grade) {
        int bulls = grade[0];
        int cows = grade[1];
        String bullsStr = bulls == 1 ? "bull" : "bulls";
        String cowsStr = cows == 1 ? "cow" : "cows";
        if (bulls > 0 && cows > 0) {
            System.out.printf("Grade : %d %s and %d %s\n", bulls, bullsStr, cows, cowsStr);
        } else if (bulls > 0) {
            System.out.printf("Grade : %d %s\n", bulls, bullsStr);
        } else if (cows > 0) {
            System.out.printf("Grade : %d %s\n", cows, cowsStr);
        } else {
            System.out.printf("Grade : None\n");
        }
    }

    private static int[] grade(String str) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < str.length(); i++) {
            if (secretCode.charAt(i) == str.charAt(i)) bulls++;
        }
        for (int i = 0; i < str.length(); i++) {
            int idx = secretCode.indexOf(str.charAt(i));
            if (idx != -1 && idx != i) cows++;
        }
        return new int[] {bulls, cows};
    }

    private static void printGameLog() {
        int secretCode = 9876;
        System.out.println("The secret code is prepared: ****.");

        System.out.println("Turn 1. Answer:\n" +
                "1234\n" +
                "Grade: None.");
        System.out.println("Turn 2. Answer:\n" +
                "9876\n" +
                "Grade: 4 bulls.\n" +
                "Congrats! The secret code is 9876.");
    }
}
