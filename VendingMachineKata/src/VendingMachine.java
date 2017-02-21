import java.util.Hashtable;

public class VendingMachine {
    private static String itemChosen = "No item selected";
    private double valueOfMoneyInserted = 0.0d;
    private double amountOfMoneyNeeded = 0.0d;
    private CoinAcceptor coinAcceptor = new CoinAcceptor();


    public static String getItemChosen() {
        return itemChosen;
    }

    public double getValueOfMoneyInserted() {
        return valueOfMoneyInserted;
    }
    public double getAmountOfMoneyNeeded() {
        return amountOfMoneyNeeded;
    }

    public void determineItemSelected(int choice){
        switch (choice){
            case 1:
                itemChosen = "Chips";
                break;
            case 2:
                itemChosen = "Candy";
                break;
            case 3:
                itemChosen = "Soda";
                break;
            default:
                itemChosen = "No item selected";
                break;
        }
        this.setAmountOfMoneyNeeded();
    }

    private void setAmountOfMoneyNeeded(){
        if (itemChosen == "Chips"){
            amountOfMoneyNeeded = 0.5d;
        }
        if (itemChosen == "Candy"){
            amountOfMoneyNeeded = 0.65d;

        }if (itemChosen == "Soda"){
            amountOfMoneyNeeded = 1.0d;
        }

    }


    public static String dispenseAnItem(){
        String message;
        if (itemChosen == "No item selected"){
            message = "Please select an item first";
        }else {
            message = itemChosen + " has been dispensed, enjoy!";
        }
        return message;
    }

    public void insertACoin(int coinSize){
        Coin coin = new Coin(coinSize);
        valueOfMoneyInserted += coinAcceptor.determineCoinValue(coin);
    }


}
