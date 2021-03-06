import java.util.Objects;

class VendingMachine {
    private String itemChosen = "No item selected";
    private double valueOfMoneyInserted = 0.0d;
    private double amountOfMoneyNeeded = 0.0d;
    private CoinAcceptor coinAcceptor;
    private int numberOfChipsInStock;
    private int numberOfCandyInStock;
    private int numberOfSodaInStock;

    VendingMachine() {
        numberOfChipsInStock = 3;
        coinAcceptor = new CoinAcceptor();
        numberOfCandyInStock = 3;
        numberOfSodaInStock = 3;
    }

    void printWelcomeMessage() {
        System.out.println("\nWelcome to Aaron's vending machine" +
                "\nItems available:" +
                "\n1.) Chips\t$0.50" +
                "\n2.) Candy\t$0.65" +
                "\n3.) Soda\t$1.00\n" +
                "\nSelect an item with the corresponding number");
    }

    void displayMoneyInserted() {
        System.out.println("Total: $" + String.format("%.2f", valueOfMoneyInserted) + "\n");
    }

    void displayCoinOptions(){
        System.out.println("\nINSERT COIN\n1.)Quarter\n2.)Dime\n3.)Nickle\n4.)Return coins");
    }

    void displayContinueQuestion(){
        System.out.println("Press any key to continue or <Q> to quit");
    }

    String getItemChosen() {
        return itemChosen;
    }

    double getValueOfMoneyInserted() {
        return valueOfMoneyInserted;
    }

    double getAmountOfMoneyNeeded() {
        return amountOfMoneyNeeded;
    }

    void determineItemSelected(int choice) {
        switch (choice) {
            case 1:
                if (numberOfChipsInStock > 0) {
                    itemChosen = "Chips";
                    this.displayItemSelected();
                } else {
                    itemChosen = "Item is out of stock, sorry";
                }
                break;
            case 2:
                if (numberOfCandyInStock > 0) {
                    itemChosen = "Candy";
                    this.displayItemSelected();
                } else {
                    itemChosen = "Item is out of stock, sorry";
                }
                break;
            case 3:
                if (numberOfSodaInStock > 0) {
                    itemChosen = "Soda";
                    this.displayItemSelected();
                } else {
                    itemChosen = "Item is out of stock, sorry";
                }
                break;
            default:
                itemChosen = "No item selected";
                break;
        }
        this.setAmountOfMoneyNeeded();
        System.out.println("Price: $" + String.format("%.2f", amountOfMoneyNeeded));
        System.out.println(coinAcceptor.determineIfExactChangeIsNeeded());
    }

    private void displayItemSelected() {
        System.out.println(itemChosen + " has been selected");
    }

    private void displayOutOfStockOrItemNotSelected() {
        System.out.println(itemChosen);
    }

    private void setAmountOfMoneyNeeded() {
        if (Objects.equals(itemChosen, "Chips")) {
            amountOfMoneyNeeded = 0.5d;
        }
        if (Objects.equals(itemChosen, "Candy")) {
            amountOfMoneyNeeded = 0.65d;

        }
        if (Objects.equals(itemChosen, "Soda")) {
            amountOfMoneyNeeded = 1.0d;
        }

    }

    String dispenseAnItem() {
        String message;
        if (Objects.equals(itemChosen, "No item selected")) {
            message = "Please select an item first";
        } else if (valueOfMoneyInserted >= amountOfMoneyNeeded) {
            message = itemChosen + " has been dispensed\n THANK YOU";
        } else {
            message = "Not enough money inserted";
        }
        decrementItemInStock();
        setValuesToZero();
        return message;
    }

    void setValuesToZero() {
        amountOfMoneyNeeded = 0.0d;
        valueOfMoneyInserted = 0.0d;
    }

    private void decrementItemInStock() {
        if (Objects.equals(itemChosen, "Chips")) {
            numberOfChipsInStock -= 1;
        }
        if (Objects.equals(itemChosen, "Candy")) {
            numberOfCandyInStock -= 1;

        }
        if (Objects.equals(itemChosen, "Soda")) {
            numberOfSodaInStock -= 1;
        }
    }

    void insertACoin(int coinSize) {
        Coin coin = new Coin(coinSize);
        if (coinSize == 4){
            giveChange(valueOfMoneyInserted);
            amountOfMoneyNeeded = 0;
        }else {
            valueOfMoneyInserted += coinAcceptor.determineCoinValue(coin);
        }
    }

    double determineChangeNeeded() {
        double changeNeeded;
        if (!Objects.equals(coinAcceptor.determineIfExactChangeIsNeeded(), "\nChange available") && valueOfMoneyInserted > amountOfMoneyNeeded) {
            System.out.println("Exact change needed. Coins Returned");
            changeNeeded = valueOfMoneyInserted;
        } else {
            changeNeeded = valueOfMoneyInserted - amountOfMoneyNeeded;
        }
        return Math.round(changeNeeded * 100.0d) / 100.0d;
    }

    String giveChange(double changeNeeded) {
        return coinAcceptor.giveChange(changeNeeded);
    }

    boolean checkForValidItemSelection() {
        boolean validItem = true;
        if (Objects.equals(itemChosen, "Chips") || Objects.equals(itemChosen, "Candy") || Objects.equals(itemChosen, "Soda")) {
            validItem = false;
        } else {
            displayOutOfStockOrItemNotSelected();
        }
        return validItem;
    }
}
