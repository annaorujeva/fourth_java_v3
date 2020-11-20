package app.service;
import app.exception.NotEnoughtMoneyException;
import app.exception.UnknownAccountException;

public interface AccountService {

    void withdraw(int accountId, int amount) throws NotEnoughtMoneyException, UnknownAccountException;
    void balance(int accountId) throws UnknownAccountException;
    void deposit(int accountId, int amount) throws UnknownAccountException;
    void transfer(int accountFrom, int accountTo, int amount) throws NotEnoughtMoneyException, UnknownAccountException;

}
