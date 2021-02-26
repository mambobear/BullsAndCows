package bullscows;

import java.util.*;

public class Main {

    private static String secretCode;

    public static void main(String[] args) {

        System.out.println(Main.class.getName());

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

        // stage 6.
        // Description
        /*
        * Some players need a challenge, so let's make the secret code in the game harder to guess.
        *  Add support for more than 10 symbols by adding letters. Now, the secret code can contain
        * numbers 0-9 and lowercase Latin characters a-z. The new maximum length for the code is 36.
        * Note that the length of the secret word may not match the size of possible characters in
        * the secret code, so you should make two separate inputs for the secret code length and for
        * the size of possible characters.  Also, since a secret code is not a number anymore, the
        * symbol 0 should be allowed as the first character in a secret code.
        *
        */

        // Objectives
        /*
        * In this step, your program should:
        *   1. Ask for the length of the secret code.
        *   2. Ask for the range of possible characters in the secret code.
        *   3. Generate a secret code using numbers and characters. This time, you should also print the secret
        *      code using * characters and print which characters were used to generate the secret code.
        */

        // Examples
        /*
        *
        * Input the length of the secret code:
        * > 4
        * Input the number of possible symbols in the code:
        * > 16
        * The secret is prepared: **** (0-9, a-f).
        * Okay, let's start a game!
        * Turn 1:
        * > 1a34
        * Grade: 1 bull and 1 cow
        * Turn 2:
        * > b354
        * Grade: 2 bulls and 1 cow
        * Turn 3:
        * > 93b4
        * Grade: 4 bulls
        * Congratulations! You guessed the secret code.
        *
        */

        // stage 7.
        // Description
        /*
        *
        * There are a lot of error possibilities. What if someone enters an answer of the wrong length?
        *  Or the number of possible symbols is less than the length of the code? What if the answer
        * contains invalid symbols? The game may crash before the secret number was guessed!
        *
        * Let's handle errors like this. At this point, you can implement this without the try catch construction.
        * Use the following rule of thumb: if you can avoid the exception-based logic, avoid it! If you use exceptions
        * in normal situations, how would you deal with unusual (exceptional) situations? Now it may not seem that
        * important, but when you need to find errors in more complex programs, this makes a difference.
        *
        * */
        // Objectives
        /*
        * In this stage, your program should:
        *   1. Handle incorrect input.
        *   2. Print an error message that contains the word error. After that, don't ask for the numbers again,
        *      just finish the program.
        * */
        // Examples
        /*
        * Example 1
        * Input the length of the secret code:
        * > 6
        * Input the number of possible symbols in the code:
        * > 5
        * Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.
        *
        * Example 2
        * Input the length of the secret code:
        * > abc 0 -7
        * Error: "abc 0 -7" isn't a valid number.
        *
        * Example 3
        * Input the length of the secret code:
        * > 6
        * Input the number of possible symbols in the code:
        * > 37
        * Error: maximum number of possible symbols in the code is 36 (0-9, a-z).
        *
        * Example 4
        * Input the length of the secret code:
        * > 4
        * Input the number of possible symbols in the code:
        * > 12
        * The secret is prepared: **** (0-9, a-b).
        * Okay, let's start a game!
        * Turn 1:
        * > a234
        * Grade: 1 bull and 1 cow
        * Turn 2:
        * > 73b4
        * Grade: 2 bulls and 1 cow
        * Turn 3:
        * > 9374
        * Grage: 4 bulls
        * Congratulations! You guessed the secret code.
        *
        * */
    }

    private static void playBullsAndCows() {

        Scanner scanner = new Scanner(System.in);

        int bulls;
        String guess;
        int[] grade;
        int turn = 1;

        secretCode = getSecretCode();
        if ("".equals(secretCode)) {
            return;
        }
        printGameInvitation();

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

    private static void printGameInvitation() {
        System.out.println("Okay, let's start a game!");
    }

    private static String getSecretCode() {
        Scanner scanner = new Scanner(System.in);
        int numSymbols;
        int length;
        String code;
        while (true) {
            System.out.println("Input the length of the secret code:");
            String str = scanner.nextLine();
            try {
                length = Integer.parseInt(str);
            } catch (NumberFormatException  e) {
                System.out.printf("Error: \"%s\" isn't a valid number.\n", str);
                return "";
            }
            if (length > 36) {
                System.out.println("Error: can't generate a secret code of " +
                        "length greater than 36 because there aren't enough unique" +
                        " symbols.");
                return "";
            }
            if (length <= 0) {
                System.out.println("Error: length cannot be less than 1");
                return "";
            }

            System.out.println("Input the number of possible symbols in the code:");
            str = scanner.nextLine();
            try {
                numSymbols = Integer.parseInt(str);
            } catch (NumberFormatException  e) {
                System.out.printf("Error: \"%s\" isn't a valid number.\n", str);
                return "";
            }
            if (numSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return "";
            }
            if (numSymbols < 10) {
                System.out.println("Error: number of symbols cannot be less than 10");
                return "";
            }

            if (length > numSymbols) {
                System.out.printf("Error: it's not possible to generate a code with a length of %d with %d " +
                        "unique symbols.\n", length, numSymbols);
                return "";
            }

            code = generateCode(length, numSymbols);
            break;
        }
        StringBuilder strb = new StringBuilder("The secret is prepared: ");
        strb.append("*".repeat(Math.max(0, length)));
        strb.append(" (0-9");
        if (numSymbols == 10) {
            strb.append(')');
        } else {
            strb.append(", a");
            if(numSymbols - 10 == 1) {
                strb.append(')');
            } else {
                strb.append('-');
                strb.append((char) (numSymbols - 11 + 'a'));
                strb.append(").");
            }
        }
        System.out.println(strb.toString());
        return code;
    }

    private static void testSecreteCode() {
        Scanner scanner = new Scanner(System.in);
        int numDigits = scanner.nextInt();

        if (numDigits > 10) {
            System.out.println("Error: can't generate a secret number with a " +
                    "length of 11 because there aren't enough unique digits.");
        } else {
            // TODO:
            System.out.println(generateCode(numDigits, 0));
        }
    }

    private static String generateCode(int length, int numSymbols) {


        ArrayList<Character> symbols = new ArrayList<>();
        String str = "0123456789abcdefghijklmnopqrstuvwyyz".substring(0, numSymbols);
        for (char c : str.toCharArray()) {
            symbols.add(c);
        }

        StringBuilder strb = new StringBuilder();
        Random random = new Random(System.nanoTime());

        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(symbols.size());
            strb.append(symbols.get(idx));
            symbols.remove(idx);
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
            System.out.print("Grade : None\n");
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
