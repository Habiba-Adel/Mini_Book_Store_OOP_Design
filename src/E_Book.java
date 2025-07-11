public class E_Book extends Book implements HandleServiceAndQuantityChecking {
    //the e book will having this specific attributes first the file type and the second one that the e book will have limited number of the quantity like the real systems
//the same here for the requirements part related to the e books can be sent via email
    private String fileType;

    public E_Book(String ISBN, String title, int year, double price, String fileType,int quantity) {
        super(ISBN, title, year, price,quantity);
        this.fileType = fileType;
    }


    public String getFileType() {
        return fileType;
    }
    @Override
    public boolean canBeSold() {
        return true;
    }



    //this cause in the requirements it is said it can be sent via the email
    @Override
    public void serviceDelivery(String email, String address) {
        //the implementation is not required in the function so we will just print to know in the testing it is working correctly
        System.out.println(" Sending ebook '" + title + "' (" + fileType + ") to email: " + email+" done successfully ");
    }

    @Override
    public boolean isValidQuantity(int quantity) {
        //here we just will check about the quantity cause it is doesnot have the stock sprcial feature like paper
        if (this.getQuantity()-quantity < 0) {
            return false;
        }
        //otherwise that measn there is enough quantity
        this.setQuantity(this.getQuantity()-quantity);
        return true;
    }



}
