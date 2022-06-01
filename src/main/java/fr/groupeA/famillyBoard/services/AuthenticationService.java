package fr.groupeA.famillyBoard.services;

public class AuthenticationService {

    private String message;

    public  AuthenticationService(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String toString(){
        return String.format("Hello World [message=%s]", message);
    }
}
