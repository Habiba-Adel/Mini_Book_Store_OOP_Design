public class BookAlreadyExisted extends RuntimeException {
    public BookAlreadyExisted(String message) {
        super(message);
    }
}