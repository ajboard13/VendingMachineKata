import org.junit.jupiter.api.Test;

import static org.testng.Assert.*;


public class VendingMachineTest {
    private VendingMachine vm = new VendingMachine();


    @Test
    public void whenOneIsSelectedChipsAreChosen() {
        vm.determineItemSelected(1);
        assertEquals("Chips", vm.getItemChosen());
    }

    @Test
    public void whenTwoIsSelectedCandyIsChosen() {
        vm.determineItemSelected(2);
        assertEquals("Candy", vm.getItemChosen());
    }

    @Test
    public void whenThreeIsSelectedSodaIsChosen() {
        vm.determineItemSelected(3);
        assertEquals("Soda", vm.getItemChosen());
    }

    @Test
    public void whenChoiceIsOutOfRangeReturnNoItemSelected() {

        vm.determineItemSelected(5);
        assertEquals("No item selected", vm.getItemChosen());
    }

    @Test
    public void returnNoItemSelectedWhenAttemptingToDispense() {
        vm.determineItemSelected(4);
        assertEquals("Please select an item first", vm.dispenseAnItem());
    }

    @Test
    public void insertCoinAndDetermineItToBeAQuarter() {
        vm.insertACoin(1);
        assertEquals(.25d, vm.getValueOfMoneyInserted());

    }

    @Test
    public void insertCoinAndDetermineItToBeADime() {
        vm.insertACoin(2);
        assertEquals(.1d, vm.getValueOfMoneyInserted());

    }

    @Test
    public void insertCoinAndDetermineItToBeANickle() {
        vm.insertACoin(3);
        assertEquals(.05d, vm.getValueOfMoneyInserted());

    }

    @Test
    public void insertCoinAndDetermineItToBeFake() {
        assertEquals(0.0d, vm.getValueOfMoneyInserted());
    }

    @Test
    public void addCoinValueToCurrentAmountInMachine() {
        vm.insertACoin(1);
        assertEquals(0.25, vm.getValueOfMoneyInserted());
    }

    @Test
    public void addTwoQuartersToSetValueOfMMoneyInsertedToFiftyCents() {
        vm.insertACoin(1);
        vm.insertACoin(1);
        assertEquals(.50d, vm.getValueOfMoneyInserted());
    }

    @Test
    public void addTwoDimesToSetValueOfMMoneyInsertedToTwentyCents() {
        vm.insertACoin(2);
        vm.insertACoin(2);
        assertEquals(.20d, vm.getValueOfMoneyInserted());
    }

    @Test
    public void addTwoNicklesToSetValueOfMMoneyInsertedToTenCents() {
        vm.insertACoin(3);
        vm.insertACoin(3);
        assertEquals(.10d, vm.getValueOfMoneyInserted());
    }

    @Test
    public void whenChipsAreSelectedValueOfMoneyNeededIsSetToFiftyCents() {
        vm.determineItemSelected(1);
        assertEquals(0.50d, vm.getAmountOfMoneyNeeded());
    }

    @Test
    public void whenChipsAreSelectedAndFiftyCentsAreInsertedDispenseChips() {
        vm.determineItemSelected(1);
        vm.insertACoin(1);
        vm.insertACoin(1);
        assertEquals("Chips has been dispensed\n THANK YOU", vm.dispenseAnItem());
    }

    @Test
    public void whenCandyIsSelectedAndSixtyFiveCentsAreInsertedDispenseChips() {
        vm.determineItemSelected(2);
        vm.insertACoin(1);
        vm.insertACoin(1);
        vm.insertACoin(2);
        vm.insertACoin(3);
        assertEquals("Candy has been dispensed\n THANK YOU", vm.dispenseAnItem());
    }

    @Test
    public void whenSodaIsSelectedAndOneDollarAreInsertedDispenseChips() {
        vm.determineItemSelected(3);
        vm.insertACoin(1);
        vm.insertACoin(1);
        vm.insertACoin(1);
        vm.insertACoin(1);
        assertEquals("Soda has been dispensed\n THANK YOU", vm.dispenseAnItem());
    }

    @Test
    public void afterDispensingSetAmountsToZero() {
        vm.determineItemSelected(3);
        vm.insertACoin(1);
        vm.insertACoin(1);
        vm.insertACoin(1);
        vm.insertACoin(1);
        assertEquals(1.00d, vm.getAmountOfMoneyNeeded());
        assertEquals(1.00d, vm.getValueOfMoneyInserted());
        vm.dispenseAnItem();
        assertEquals(0.0d, vm.getAmountOfMoneyNeeded());
        assertEquals(0.0d, vm.getValueOfMoneyInserted());
    }

    @Test
    public void whenTheValueOfMoneyInsertedIsMoreThanNeededDetermineChange() {
        vm.insertACoin(2);
        vm.insertACoin(3);
        vm.insertACoin(3);
        vm.determineItemSelected(2);
        vm.insertACoin(1);
        vm.insertACoin(1);
        vm.insertACoin(1);
        assertEquals(0.3, vm.determineChangeNeeded());
    }

    @Test
    public void whenAnItemIsSoldOutLetTheCustomerKnow() {
        int i = 0;
        while (i < 3) {
            vm.determineItemSelected(1);
            vm.insertACoin(1);
            vm.insertACoin(1);
            vm.dispenseAnItem();
            i++;
        }
        vm.determineItemSelected(1);
        assertEquals("Item is out of stock, sorry", vm.getItemChosen());
    }

    @Test
    public void informCustomerWhenChangeIsNotAvailable() {
        CoinAcceptor coinAcceptor = new CoinAcceptor();
        coinAcceptor.emptyCoins();
        assertEquals("\nExact change is needed", coinAcceptor.determineIfExactChangeIsNeeded());
    }

    @Test
    public void emptyCoinsFromMachine() {
        CoinAcceptor coinAcceptor = new CoinAcceptor();
        coinAcceptor.emptyCoins();
        assertEquals(0, coinAcceptor.getNumberOfDimesCurrentlyInMachine());
        assertEquals(0, coinAcceptor.getNumberOfNicklesCurrentlyInMachine());
        assertEquals(0, coinAcceptor.getNumberOfQuartersCurrentlyInMachine());
    }

    @Test
    public void checkToSeeIfItemIsSelected() {
        assertTrue(vm.checkForValidItemSelection());
    }

    @Test
    public void ifExactChangeIsNotInsertedReturnCoins() {
        vm.determineItemSelected(2);
        vm.insertACoin(1);
        vm.insertACoin(1);
        vm.insertACoin(1);
        assertEquals(0.75, vm.determineChangeNeeded());
    }

}