public class Paper_Book extends Book implements HandleServiceAndQuantityChecking {
    //this type of books it has stock and it may be shipped --> so we need to store the stock of this type
    //the may be shipped in the requirements i assume that related to the shipped service that there is some paper books may be bought and when it is bought they sent to the shipeed service
    //and there is will be another paper books will not be bought which will make them not shipped thats why it is called may be
    private int stock;
//    private double shipped;
//    private boolean isShipped;//we will use it to know if this book can be shipped or no

    //this constructor will be use if the book is paper and in the same time is not shipped
    public Paper_Book(String ISBN, String title, int year, double price, int stock,int quantity) {
        super(ISBN, title, year, price, quantity);
        this.stock = stock;
//        this.shipped = 0.0;
//        this.isShipped = false;
    }

    //this constructor will be use if the book is paper and in the same time is shipped
//    public Paper_Book(String ISBN, String title, int year, double price, int stock, double shipped, int quantity) {
//        super(ISBN, title, year, price, quantity);
//        this.stock = stock;
//        this.isShipped = true;
//        this.shipped = shipped;
//    }
//
//    public boolean isShipped() {
//        return isShipped;
//    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    @Override
    public boolean canBeSold() {
        return true;
    }


    //now we will need to override the delivery logic here to can sent the paper books by the shipping service
    @Override
    public void serviceDelivery(String email, String address) {
        //the implementation is not required in the function so we will just print to know in the testing it is working correctly
        System.out.println(" Shipping paper book '" + title + "' to address: " + address + "done successfully ");
    }

    @Override
    public boolean isValidQuantity(int quantity) {
        if (this.getQuantity()-quantity < 0) {
            // I will assume that we will can use the stock in the buying operation if the quantity will be not enough and that to use the stock that mentioned in the requirements
                int copy=quantity;
                quantity -= this.getQuantity();
                this.setQuantity(0);
                //and now we will take the rest from the stock if it is available and will update the stock
                if(this.getStock()>quantity)
                {
                    this.setStock(this.getStock()-quantity);
                }
                else
                {
                    //that means the stock will not be enough too so we will return the book quantity as it is in the start and this book can not be sold
                    this.setQuantity(copy);
                    return false;//no enough stock stock and no enough quantity
                }

        }
        return true;


    }


}

