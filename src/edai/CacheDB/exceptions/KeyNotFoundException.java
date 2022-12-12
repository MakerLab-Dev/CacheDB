package edai.CacheDB.exceptions;

public class KeyNotFoundException extends Exception {
    public KeyNotFoundException() {
        super("Key not found");
    }
    public KeyNotFoundException(String message) {
        super(message);
    }
}
