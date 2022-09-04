package rs.ac.ni.pmf.webproject.laptopstore.exceptions;

public class AlreadySoldException extends RuntimeException{
    public AlreadySoldException(String message) {
        super(message);
    }
}
