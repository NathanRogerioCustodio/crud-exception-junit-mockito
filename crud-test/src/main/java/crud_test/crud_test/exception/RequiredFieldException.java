package crud_test.crud_test.exception;

public class RequiredFieldException extends RuntimeException{
    public RequiredFieldException(String message){
        super(message);
    }
}
