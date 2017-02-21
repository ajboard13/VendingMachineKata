class CoinAcceptor {
    private int numberOfQuartersCurrentlyInMachine = 3;
    private int numberOfDimesCurrentlyInMachine = 1;
    private int numberOfNicklesCurrentlyInMachine = 3;
    private String message = "";



    double determineCoinValue(Coin coin) {
        //These are not real coin sizes
        final int quarterSize = 1;
        final int dimeSize = 2;
        final int nickleSize = 3;
        double coinValue = 0.0d;
        int coinSize = coin.getCoinSize();
        if(coinSize == quarterSize){
            coinValue = 0.25d;
        }
        if(coinSize == dimeSize){
            coinValue = 0.1d;
        }
        if(coinSize == nickleSize){
            coinValue = 0.05d;
        }
        return coinValue;
    }


    String determineIfExactChangeIsNeeded() {
        message = "\nChange available";
        if ((numberOfNicklesCurrentlyInMachine < 2) || (numberOfDimesCurrentlyInMachine < 1)) {
            message = "\nExact change is needed";
        }
        return message;
    }

    String giveChange(double changeNeeded) {
        String changeMessage = "Change : $" + String.format("%.2f", changeNeeded) + "\n";
        double quartersToReturn = (changeNeeded - (changeNeeded % 0.25)) / 0.25;
        changeMessage += ((int) quartersToReturn) + " quarters returned\n";
        changeNeeded = (changeNeeded % 0.25) + .0001;
        double dimesToReturn = (changeNeeded - (changeNeeded % 0.1)) / 0.1;
        changeMessage += ((int) dimesToReturn) + " dimes returned\n";
        changeNeeded = changeNeeded % 0.10;
        double nicklesToReturn = (changeNeeded - (changeNeeded % .05)) / 0.05;
        changeMessage += ((int) nicklesToReturn) + " nickles returned\n";
        numberOfQuartersCurrentlyInMachine -= quartersToReturn;
        numberOfDimesCurrentlyInMachine -= dimesToReturn;
        numberOfNicklesCurrentlyInMachine -= nicklesToReturn;
        return changeMessage;
    }

    String getMessage() {
        return message;
    }

}