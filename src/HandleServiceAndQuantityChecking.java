public interface HandleServiceAndQuantityChecking {
 // THIS INTERFACE FOR HANDLING THE DIFFERENT IMPLEMENTATION FOR THE SERVICE AND THE CHECKING QUANTITY IF IT IS VALID OR NO
    //rather than putting this 2 functions as an anstract functions in the book and that will lead the demo class to inherit it but it will not use it
    //so the interface is the best logic here
   void serviceDelivery(String address,String email);

   boolean isValidQuantity(int quantity);
}
