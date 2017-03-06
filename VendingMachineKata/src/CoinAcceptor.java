class CoinAcceptor {
    private int numberOfQuartersCurrentlyInMachine = 3;
    private int numberOfDimesCurrentlyInMachine = 0;
    private int numberOfNicklesCurrentlyInMachine = 1;

    int getNumberOfQuartersCurrentlyInMachine() {
        return numberOfQuartersCurrentlyInMachine;
    }

    int getNumberOfDimesCurrentlyInMachine() {
        return numberOfDimesCurrentlyInMachine;
    }

    int getNumberOfNicklesCurrentlyInMachine() {
        return numberOfNicklesCurrentlyInMachine;
    }

    void emptyCoins() {
        numberOfDimesCurrentlyInMachine = 0;
        numberOfNicklesCurrentlyInMachine = 0;
        numberOfQuartersCurrentlyInMachine = 0;
    }

    double determineCoinValue(Coin coin) {
        //These are not real coin sizes
        final int quarterSize = 1;
        final int dimeSize = 2;
        final int nickleSize = 3;
        double coinValue = 0.0d;
        int coinSize = coin.getCoinSize();
        if (coinSize == quarterSize) {
            coinValue = 0.25d;
            numberOfQuartersCurrentlyInMachine += 1;
        }
        if (coinSize == dimeSize) {
            coinValue = 0.1d;
            numberOfDimesCurrentlyInMachine += 1;
        }
        if (coinSize == nickleSize) {
            coinValue = 0.05d;
            numberOfNicklesCurrentlyInMachine += 1;
        }
        return coinValue;
    }


    String determineIfExactChangeIsNeeded() {
        String message = "\nChange available";
        if ((numberOfNicklesCurrentlyInMachine < 2) || (numberOfDimesCurrentlyInMachine < 1)) {
            message = "\nExact change is needed";
        }
        return message;
    }

    String giveChange(double changeNeeded) {
        String changeMessage = "Change : $" + String.format("%.2f", changeNeeded) + "\n";
        double quartersToReturn = (changeNeeded - (changeNeeded % 0.25d)) / 0.25d;
        changeMessage += ((int) quartersToReturn) + " quarters returned\n";
        changeNeeded = (changeNeeded % 0.25d);
        double dimesToReturn = (changeNeeded - (changeNeeded % 0.1d)) / 0.1d;
        changeMessage += ((int) dimesToReturn) + " dimes returned\n";
        changeNeeded = changeNeeded % 0.10d;
        double nicklesToReturn = (changeNeeded - (changeNeeded % .05d)) / 0.05d;
        changeMessage += ((int) nicklesToReturn) + " nickles returned\n";
        numberOfQuartersCurrentlyInMachine -= quartersToReturn;
        numberOfDimesCurrentlyInMachine -= dimesToReturn;
        numberOfNicklesCurrentlyInMachine -= nicklesToReturn;
        return changeMessage;
    }


}