public  class DemoBook extends Book {
//this type can be just property in the book class to know if it is demo or no but it will not be flexable if in the future existed extra information related to the demo class
    //so making it as a seperate class is better solution and design
    //this is the book that not be sold for any one
    public DemoBook(String ISBN, String title, int year) {
        super(ISBN, title, year, 0.0,1);
    }


    @Override
    public boolean canBeSold() {
        return false;
    }

}
