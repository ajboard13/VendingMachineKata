import org.junit.jupiter.api.Test;

import static org.testng.Assert.*;


public class VendingMachineTest {
    VendingMachine vm = new VendingMachine();


    @Test
    public void whenOneIsSelectedChipsAreChosen(){
        vm.determineItemSelected(1);
        assertEquals("Chips", vm.getItemChosen());
    }

    @Test
    public void whenTwoIsSelectedCandyIsChosen(){
        vm.determineItemSelected(2);
        assertEquals("Candy", vm.getItemChosen());
    }

    @Test
    public void whenThreeIsSelectedSodaIsChosen(){
        vm.determineItemSelected(3);
        assertEquals("Soda", vm.getItemChosen());
    }

    @Test
    public void whenChoiceIsOutOfRangeReturnNoItemSelected(){

        vm.determineItemSelected(5);
        assertEquals("No item selected", vm.getItemChosen());
    }

    @Test
   public void returnAMessageWhenItemIsDispensed(){
        vm.determineItemSelected(1);
        String itemChosen = vm.getItemChosen();
       assertEquals(itemChosen + " has been dispensed, enjoy!", vm.dispenseAnItem());
   }

   @Test
    public void returnNoItemSelectedWhenAttemptingToDispense() {
       vm.determineItemSelected(4);
       assertEquals("Please select an item first", vm.dispenseAnItem());
   }

   @Test
    public void insertCoinAndDetermineItToBeAQuarter(){
       vm.insertACoin(1);
       assertEquals(.25d, vm.getValueOfMoneyInserted());

   }

    @Test
    public void insertCoinAndDetermineItToBeADime(){
        vm.insertACoin(2);
        assertEquals(.1d, vm.getValueOfMoneyInserted());

    }

    @Test
    public void insertCoinAndDetermineItToBeANickle(){
        vm.insertACoin(3);
        assertEquals(.05d, vm.getValueOfMoneyInserted());

    }

    @Test
    public void insertCoinAndDetermineItToBeFake(){
        assertEquals(0.0d, vm.getValueOfMoneyInserted());
    }

    @Test
    public void addCoinValueToCurrentAmountInMachine(){
        vm.insertACoin(1);
        assertEquals(0.25, vm.getValueOfMoneyInserted());
    }

    @Test
    public void addTwoQuartersToSetValueOfMMoneyInsertedToFiftyCents(){
        vm.insertACoin(1);
        vm.insertACoin(1);
        assertEquals(.50d, vm.getValueOfMoneyInserted());
    }

    @Test
    public void addTwoDimesToSetValueOfMMoneyInsertedToTwentyCents(){
        vm.insertACoin(2);
        vm.insertACoin(2);
        assertEquals(.20d, vm.getValueOfMoneyInserted());
    }

    @Test
    public void addTwoNicklesToSetValueOfMMoneyInsertedToTenCents(){
        vm.insertACoin(3);
        vm.insertACoin(3);
        assertEquals(.10d, vm.getValueOfMoneyInserted());
    }

    @Test
    public void whenChipsAreSelectedValueOfMoneyNeededIsSetToFiftyCents(){
        vm.determineItemSelected(1);
        assertEquals(0.50d, vm.getAmountOfMoneyNeeded());
    }


}