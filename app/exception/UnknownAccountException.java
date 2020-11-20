package app.exception;

public class UnknownAccountException extends Exception{
    public UnknownAccountException(){
        System.out.println("Данный счет не найден!");
    }

}
