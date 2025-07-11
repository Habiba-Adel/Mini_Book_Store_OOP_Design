
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;


public class Inventory {
    //as mentioned in the requirements that system will have an inventory which has many number of books and here we will just use the hash map
    // we use hash map cause its time for searching about specific book object based on ISBN will be very fast cause its search time is o(1)
    private HashMap<String, Book> books; //key: book ISBN and the value : BOOK OBJECT itself

    public Inventory() {
        books = new HashMap<>();
    }

    public void addBook(Book book) {
        //first we will need to check about if this isbn is existed before or no cause each book object will have its special own isbn and if we do not check the put will override the same book objects isbn
        if(books.containsKey(book.getISBN())) {
            //that means we will throw an error that this book isbn is already existed
            throw new BookAlreadyExisted("Book already exists and  each book must have its unique ISBN !");
        }
        else
        {
            //that means this book its isbn is unique so add it to the hash map
            books.put(book.getISBN(), book);
            System.out.println(" Added book '" + book.getTitle() + "' to inventory");
        }

    }

    public ArrayList<Book> removeBook(int numOfYears) {

        ArrayList<Book> returnedBooks = new ArrayList<>();
        LocalDate noww = LocalDate.now();
        int currentYear = noww.getYear();
//        for(Book book : books.values()) {  //THIS logic can not be used cause it will give you a concurrent exception cause you try to modify the hash map and the same time you use it
//            if((currentYear - book.getYear()) > numOfYears)
//            {
//                returnedBooks.add(book);
//                books.remove(book.getISBN());
//                System.out.println("Quantum book store: Removed expired book '" + book.getTitle() + "'");
//
//            }
//        }
       //here the corrected and workked remove logic but by using the iterators
        Iterator<Map.Entry<String, Book>> iterator = books.entrySet().iterator();//this points to nothing but points to the place before the first element exactly
        while (iterator.hasNext()) {
            Map.Entry<String, Book> entry = iterator.next();//to get the first key in the first time
            Book book = entry.getValue();

            if ((currentYear - book.getYear()) > numOfYears) {
                returnedBooks.add(book);
                iterator.remove();
            }
        }
        return returnedBooks;
    }

    public double buyBook(String isbn, int quantity, String email, String address) {
        Book book = books.get(isbn);
       //first we will need to check if the book is existed or no
        if (book == null) {
            throw new BookNotFoundException(" Book with ISBN '" + isbn + "' not found");
        }
        //the second important check we must know if it is demo or no and can be checked by checking if is the book is instance of demo object or no both will lead to the same result
        if (!book.canBeSold()) {//and this in the case of the demo books
            throw new IllegalStateException(" Book '" + book.getTitle() + "' is not for sale cause it is a DEMO book ");
        }
       //the third check we will need to check about the quantity if it is allowed or no
//the logic used in the quantity checking here is the delegation which makes the system more encapsulation and more flexable to the extendable
        boolean flag=false;
        if(book instanceof E_Book)//cause if it is paper book it will be already updated in the checking step
        {
           if(!(( E_Book) book).isValidQuantity(quantity))flag=true;
        }
        else if (book instanceof Paper_Book) {
            if(!((Paper_Book) book).isValidQuantity(quantity))flag=true;
        }
        if(flag)
        {
            throw new InsufficientQuantityException("You can not buy this book with this quantity cause there the existed quantity will not be enough");
        }
        //the fourth step is to calculate the total price and sent them to service and return the total paid amount
        double totalPrice = quantity * book.getPrice();
        if(book instanceof E_Book)
        {
            (( E_Book) book).serviceDelivery(email, address);//and we will sending it to the shipped service
        }
        else if (book instanceof Paper_Book) {
            ((Paper_Book) book).serviceDelivery(email, address);//and we will sending it to the mail service
        }
       //and now continue the common things
        System.out.println(" Purchase completed. Total paid amount is: $" + totalPrice);
        return totalPrice;
    }
//this is just from printing and testing
    public void displayInventory() {
        System.out.println(" Current Inventory:");
        for (Book book : books.values()) {
            System.out.println("- " + book.getTitle() + " by " +
                    " (ISBN: " + book.getISBN() + ", Year: " + book.getYear() +
                    ", Price: $" + book.getPrice() + ", Quantity: " + book.getQuantity() + ")");
        }
    }
}
