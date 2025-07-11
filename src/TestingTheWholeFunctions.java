import java.util.ArrayList;

public class TestingTheWholeFunctions {
    //first thing is to test the add book functionality
    //and we make it static function to can call it with using the class name without needing to create an object from the class
    public static void testAddBook(Inventory inventory) {
        System.out.println("\n===== First Testing Add Book Function =====");

        // we will add  3 logically different Paper Books
        Paper_Book paperBook1 = new Paper_Book("PB001", "The Java Way", 2018, 150.0, 50, 100);   // large quantity
        Paper_Book paperBook2 = new Paper_Book("PB002", "Clean Code", 2015, 120.0, 20, 5);       // small quantity
        Paper_Book paperBook3 = new Paper_Book("PB003", "Design Patterns", 2012, 200.0, 3, 2);   // small quantity & small stock

        //  we will add  3 logically different ebooks Books
        E_Book ebook1 = new E_Book("EB001", "Learn Python", 2020, 60.0, "PDF", 100);
        E_Book ebook2 = new E_Book("EB002", "AI with Java", 2021, 80.0, "EPUB", 10);
        E_Book ebook3 = new E_Book("EB003", "Data Science 101", 2023, 95.0, "MOBI", 1);

        //  we will add  3 demo Books
        DemoBook demoBook1 = new DemoBook("DB001", "Machine Learning Preview", 2019);
        DemoBook demoBook2 = new DemoBook("DB002", "Quantum Computing Intro", 2022);
        DemoBook demoBook3 = new DemoBook("DB003", "Blockchain Basics Demo", 2021);

        // Add all books to the inventory this tests must not get any errors cause it is the happy path
        try {
            inventory.addBook(paperBook1);
            inventory.addBook(paperBook2);
            inventory.addBook(paperBook3);
            inventory.addBook(ebook1);
            inventory.addBook(ebook2);
            inventory.addBook(ebook3);
            inventory.addBook(demoBook1);
            inventory.addBook(demoBook2);
            inventory.addBook(demoBook3);
        } catch (BookAlreadyExisted e) {
            System.out.println("there is an Error - " + e.getMessage());
        }

        // now lets see the current inventory
        inventory.displayInventory();

        //second lets test the alternate path fot this function which is add another book with already have the sam isbn
        System.out.println("\n=====  Testing Existed book with the same ISBN =====");

        Paper_Book duplicateBook = new Paper_Book("PB001", "Advanced Java", 2024, 180.0, 20, 50);

        try {
            inventory.addBook(duplicateBook);//here it must display the errror
        } catch (BookAlreadyExisted e) {
            System.out.println("there is an Error - " + e.getMessage());
        }


    }

    //now the second is to test the remove and returned function
    public static void testRemoveBooks(Inventory inventory) {
        System.out.println("\n==== Second Testing remove and outdated books =====");
        int years = 10; // Remove books older than 10 years
        ArrayList<Book> returnnedBooks = inventory.removeBook(years);

        System.out.println("\n the returned removed Books that passed "+years+" years are :\n");
        for (Book book : returnnedBooks) {
            System.out.println("- " + book.getTitle() + " (" + book.getYear() + ")");
        }

        System.out.println("\n now lets see the Inventory after removal:");
        inventory.displayInventory();
    }

    //now the third and the last one to test is the buy a book function
    public static void testBuyBook(Inventory inventory) {
        System.out.println("\n=====  Testing Buy Book Functionality =====");

        // 1. Try to buy a paper book with insufficient stock (PB003)
        System.out.println("\n--- Test 1: Buy Paper Book (there is no enough quantity in the inventory and no enough stock) ---");
        try {
            inventory.buyBook("PB003", 10, "user@example.com", "123 Main Street");
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // 2. Try to buy a paper book with enough quantity + stock (PB002)
        System.out.println("\n--- Test 2: Buy Paper Book (there is no enough inventory quantity but there is stock) ---");
        try {
            inventory.buyBook("PB002", 6, "user@example.com", "456 Main Street");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 3. Buy an E-Book with enough quantity (EB001)
        System.out.println("\n--- Test 3: Buy EBook (there is enough quantity) ---");
        try {
            inventory.buyBook("EB001", 2, "reader@example.com", "N/A");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 4. Buy an E-Book with insufficient quantity (EB003)
        System.out.println("\n--- Test 4: Buy EBook (there is no enough quantity) ---");
        try {
            inventory.buyBook("EB003", 5, "reader@example.com", "N/A"); // only 1 available
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // 5. Try to buy a non-existent book (wrong ISBN)
        System.out.println("\n--- Test 5: Buy Non-existent Book ---");
        try {
            inventory.buyBook("XX999", 1, "test@example.com", "Some Address");
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // 6. Try to buy a demo book (not for sale) (DB001)
        System.out.println("\n--- Test 6: Buy Demo Book which is not for sale  ---");
        try {
            inventory.buyBook("DB001", 1, "demo@example.com", "N/A");
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }

         //here we will see the impace of the buying in the books quantities
        System.out.println("\n Final Inventory After Purchases:");
        inventory.displayInventory();
    }

}

