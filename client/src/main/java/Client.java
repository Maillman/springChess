
import java.util.Scanner;

public class Client {
    public void run() {
        System.out.println("Welcome to Chess!");
        Scanner scanner = new Scanner(System.in);
        String result = "";
        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();
            try {
                result = evaluate(line);
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(msg);
            }
        }
    }
    
    private void printPrompt() {
        System.out.println("Placeholder");
    }

    private String evaluate(String line) {
        return "Placeholder";
    }
}