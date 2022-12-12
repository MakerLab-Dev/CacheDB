package edai.CacheDB.exceptions;

public class DuplicatedKeyException extends Exception {
    public DuplicatedKeyException() {
        super("Duplicated key");
    }
    public DuplicatedKeyException(String message) {
        super(message);
    }
}
