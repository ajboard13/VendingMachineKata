This is directly taken from the original kata repository at https://github.com/guyroyse/vending-machine-kata. I used it as sort of a Q&A to explain how I obtained the defined functionalities.

All my code was written using IntelliJ and testing was done using JUnit5 which is included with IntelliJ.
https://www.jetbrains.com/idea/




Vending Machine Kata

In this exercise you will build the brains of a vending machine. It will accept money, make change, maintain inventory, and dispense products. All the things that you might expect a vending machine to accomplish.

The point of this kata to to provide a larger than trivial exercise that can be used to practice TDD. A significant portion of the effort will be in determining what tests should be written and, more importantly, written next.

Features


Q:
Accept Coins

As a vendor
I want a vending machine that accepts coins
So that I can collect money from the customer

The vending machine will accept valid coins (nickels, dimes, and quarters) and reject invalid ones (pennies). When a valid coin is inserted the amount of the coin will be added to the current amount and the display will be updated. When there are no coins inserted, the machine displays INSERT COIN. Rejected coins are placed in the coin return.

NOTE: The temptation here will be to create Coin objects that know their value. However, this is not how a real vending machine works. Instead, it identifies coins by their weight and size and then assigns a value to what was inserted. You will need to do something similar. This can be simulated using strings, constants, enums, symbols, or something of that nature.

MY SOLUTION:
I started by making a class for coins. they don't know there value as suggested but instead contin an integer size that is used in another method to determine their value. the integer size that the inserted coins contain is dependent on the user input. After the coin selection is made, the machine adds the value to thier total and displays said total in the console.


Q:
Select Product

As a vendor
I want customers to select products
So that I can give them an incentive to put money in the machine

There are three products: cola for $1.00, chips for $0.50, and candy for $0.65. When the respective button is pressed and enough money has been inserted, the product is dispensed and the machine displays THANK YOU. If the display is checked again, it will display INSERT COIN and the current amount will be set to $0.00. If there is not enough money inserted then the machine displays PRICE and the price of the item and subsequent checks of the display will display either INSERT COIN or the current amount as appropriate.

MY SOLUTION:
when the program first runs it asks you to make a selection from a list. The options are the same as the suggested ones. The list to pick from is numbered and the user choice is determined by evaluating the number they input. the method to determine the item sets a global variable containing the item suggested to be used later on as well as the amount of money needed to purchase the item.


Q:
Make Change

As a vendor
I want customers to receive correct change
So that they will use the vending machine again

When a product is selected that costs less than the amount of money in the machine, then the remaining amount is placed in the coin return.

MY SOLUTION:
after product selection in the previous solution the user is prompted to insert coins. this loops untilthe money inserted is greater than or equal to the amount needed. after, the product is dispensed, change is given and the machine starts from the beginning or exits.


Q:
Return Coins

As a customer
I want to have my money returned
So that I can change my mind about buying stuff from the vending machine

When the return coins button is pressed, the money the customer has placed in the machine is returned and the display shows INSERT COIN.

MY SOLUTION:
When the list of coin options is given there is an extra option to return the coins. this will return the coins inserted and set the amount of money needed to 0 which will break the loop and the user can start again from the beginning.

Q:
Sold Out

As a customer
I want to be told when the item I have selected is not available
So that I can select another item

When the item selected by the customer is out of stock, the machine displays SOLD OUT. If the display is checked again, it will display the amount of money remaining in the machine or INSERT COIN if there is no money in the machine.

MY SOLUTION:
I used an integer variable for each item that corresponds with how many are left in the machine and they are set to 3 when the machine is initialized. after each purchase of an item, its variable is decremented by one. Once at zero, if the item is selected the machine will display an out of stock message and have the user select another item.


Q:
Exact Change Only

As a customer
I want to be told when exact change is required
So that I can determine if I can buy something with the money I have before inserting it

When the machine is not able to make change with the money in the machine for any of the items that it sells, it will display EXACT CHANGE ONLY instead of INSERT COIN.

MY SOLUTION:
I created a seperate coin accepter class. Inside it keeps a tally on how many quarters, dimes, and nickles are inserted into the machine. It starts at 3 quarters, 0 dimes and 1 nickle. Inside this class there is a method to determine if exact change is needed based on these amounts. if the number of nickles is less than 2 or the number of dimes is less than 1 than it will ask for exact change after a product is selected. If exact change is requested and the user puts in more money than the product, the coins the user inserted are returned.
