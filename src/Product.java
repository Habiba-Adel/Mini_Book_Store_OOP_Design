 public class Product {

    //this class will have the main information for any product will be existed in the inventory
     //and this will make the system more flexiable for any new product need to be added to our system
     double price;
     String ISBN;
     int quantity;

    public Product(String ISBN, double price, int quantity) {
        this.ISBN = ISBN;
        this.price = price;
        this.quantity = quantity;
    }

    public String getISBN() {
        return ISBN;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }


}
