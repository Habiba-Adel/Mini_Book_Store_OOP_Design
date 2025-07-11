public abstract class Book extends Product{
     String title;
     int year;

    public Book(String ISBN, String title, int year, double price,int quantity) {
        super(ISBN, price, quantity);
        this.title = title;
        this.year = year;
    }

    public int getYear() {
        return year;
    }
    public String getTitle() {
        return title;
    }

//we will put this abstract function cause its implementation differ from book type to another cause there is book types can be sold and there is another types no
    public abstract boolean canBeSold();

}
