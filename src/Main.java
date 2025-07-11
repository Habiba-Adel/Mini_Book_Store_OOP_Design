import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
       //lets test the functions
        //first lets test the add book function and call it from the testing class
        Inventory ourInventory = new Inventory();
        TestingTheWholeFunctions.testAddBook(ourInventory);//we can call it by using the class name casue we make the function static
       // TestingTheWholeFunctions.testRemoveBooks(ourInventory);
        TestingTheWholeFunctions.testBuyBook(ourInventory);
    }
}
