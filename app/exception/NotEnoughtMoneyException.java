package app.exception;

public class NotEnoughtMoneyException extends Exception{
    public NotEnoughtMoneyException(){
        System.out.println("На выбранном счете недостаточно денег для выполнения операции!");
    }
}
