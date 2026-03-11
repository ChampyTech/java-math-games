import java.util.Scanner;
import java.util.Random;

public class MathGames {
    public static Random casuale = new Random();

    public static void main(String[] args) {
        Scanner keyb = new Scanner(System.in);
        int score = 0;

        System.out.println("===== WELCOME TO MATH GAMES =====");

        // 1. Chiedere quante operazioni l'utente vuole fare
        System.out.print("How many operations do you want to play? ");
        int numOperations = keyb.nextInt();

        // 2. Chiedere il livello di difficoltà (numero di cifre)
        System.out.print("Choose difficulty (1, 2, 3, ... corresponds to the number of digits): ");
        int difficulty = keyb.nextInt();

        System.out.println("\nLet's start! (Note: divisions are integer divisions)");
        System.out.println("---------------------------------------------------");

        // 3. Ciclo per il numero di operazioni richieste
        for (int i = 1; i <= numOperations; i++) {
            // Generazione numeri
            int num1 = generateNumber(difficulty);
            int num2 = generateNumber(difficulty);

            // Generazione casuale dell'operazione (0: +, 1: -, 2: *, 3: /, 4: %)
            int operationType = casuale.nextInt(5);
            char symbol = ' ';
            int correctAnswer = 0;

            // Prevenzione della divisione (o resto) per zero
            if ((operationType == 3 || operationType == 4) && num2 == 0) {
                num2 = 1; // Forza num2 a 1 per evitare eccezioni di sistema
            }

            // Selezione dell'operazione
            switch (operationType) {
                case 0:
                    symbol = '+';
                    correctAnswer = num1 + num2;
                    break;
                case 1:
                    symbol = '-';
                    if (num1 < num2) { // Se il risultato è negativo
                        int temp = num1;
                        num1 = num2;
                        num2 = temp;
                    }
                    correctAnswer = num1 - num2;
                    break;
                case 2:
                    symbol = '*';
                    correctAnswer = num1 * num2;
                    break;
                case 3:
                    symbol = '/';
                    int originalNum1 = num1;
                    num1 = originalNum1 * num2;
                    correctAnswer = originalNum1;
                    break;
                case 4:
                    symbol = '%';
                    if (num1 < num2) {
                        int temp = num1;
                        num1 = num2;
                        num2 = temp;
                    }
                    correctAnswer = num1 % num2;
                    break;
            }

            // Stampa della domanda e acquisizione della risposta
            System.out.print("Operation " + i + ": What is " + num1 + " " + symbol + " " + num2 + "? ");
            int userAnswer = keyb.nextInt();

            // 4. Controllo del punteggio
            if (userAnswer == correctAnswer) {
                System.out.println("Correct! +1 point.");
                score++;
            } else {
                System.out.println("Wrong. The correct answer was: " + correctAnswer);
            }
        }

        // Stampa del punteggio finale
        System.out.println("---------------------------------------------------");
        System.out.println("GAME OVER! Your final score is: " + score + " out of " + numOperations);

        keyb.close();
    }

    public static int generateNumber(int difficulty) {
        if (difficulty < 1) difficulty = 1;

        int min = (int) Math.pow(10, difficulty - 1);
        int max = (int) Math.pow(10, difficulty) - 1;

        if (difficulty == 1) min = 1;

        return casuale.nextInt((max - min) + 1) + min;
    }
}