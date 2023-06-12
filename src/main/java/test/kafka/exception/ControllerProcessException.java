package test.kafka.exception;

public class ControllerProcessException extends Exception {
    public ControllerProcessException(){
        super();
    }

    public ControllerProcessException(String message){
        super(message);
    }
}
