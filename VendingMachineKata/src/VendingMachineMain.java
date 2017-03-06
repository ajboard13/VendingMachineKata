import java.util.Objects;
import java.util.Scanner;

public class VendingMachineMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        String answer;

        VendingMachine vendingMachine = new VendingMachine();
        do {
            do {
                vendingMachine.printWelcomeMessage();
                choice = input.nextInt();
                vendingMachine.determineItemSelected(choice);
            } while (vendingMachine.checkForValidItemSelection());
            do {
                vendingMachine.displayCoinOptions();
                int coinToInsert = input.nextInt();
                vendingMachine.insertACoin(coinToInsert);
                vendingMachine.displayMoneyInserted();
            } while (vendingMachine.getAmountOfMoneyNeeded() > vendingMachine.getValueOfMoneyInserted());

            if (vendingMachine.determineChangeNeeded() != Math.round(vendingMachine.getValueOfMoneyInserted() * 100.0d) / 100.0d) {
                System.out.println("\n" + vendingMachine.giveChange(vendingMachine.determineChangeNeeded()));
                System.out.println(vendingMachine.dispenseAnItem());
            } else {
                System.out.println(vendingMachine.giveChange(vendingMachine.getValueOfMoneyInserted()));
                vendingMachine.setValuesToZero();
            }
            vendingMachine.displayContinueQuestion();
            answer = input.next();
        }
        while (!Objects.equals(answer.toLowerCase(), "q"));
    }
}
