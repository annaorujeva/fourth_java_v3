package app.service;

import app.exception.UnknownAccountException;
import app.exception.NotEnoughtMoneyException;
import app.repository.Repository;
import app.store.StoreAccounts;
import org.springframework.beans.factory.annotation.Autowired;

public class FileAccountService implements AccountService {


    @Autowired
    private Repository fr;

    public void setFr(Repository fr) {
        this.fr = fr;
    }

    @Override //снять указанную сумму
    public void withdraw(int accountId, int amount) throws NotEnoughtMoneyException, UnknownAccountException {
        boolean findId = false;
        int foundId = 0;
        for (int i = 0; i < StoreAccounts.accounts.size(); i++) {
            if (StoreAccounts.accounts.get(i).getId() == accountId) {
                findId = true;
                foundId = i;
            }
        }
        if (findId) {
            if (StoreAccounts.accounts.get(foundId).getAmount() >= amount) {
                StoreAccounts.accounts.get(foundId).setAmount((int) (StoreAccounts.accounts.get(foundId).getAmount() - amount));
                fr.write(StoreAccounts.accounts.get(foundId));
            } else throw new NotEnoughtMoneyException();
        } else throw new UnknownAccountException();
    }

    @Override //вывести баланс
    public void balance(int accountId) throws UnknownAccountException {
        boolean findId = false;
        int foundId = 0;
        for (int i = 0; i < StoreAccounts.accounts.size(); i++) {
            if (StoreAccounts.accounts.get(i).getId() == accountId) {
                findId = true;
                foundId = i;
            }
        }
        if (findId) {
            System.out.println(StoreAccounts.accounts.get(foundId).getAmount());
        }
        else throw new UnknownAccountException();
    }

    @Override //внести сумму на указанный счет
    public void deposit(int accountId, int amount) throws UnknownAccountException {
        boolean findId = false;
        int foundId = 0;
        for (int i = 0; i < StoreAccounts.accounts.size(); i++) {
            if (StoreAccounts.accounts.get(i).getId() == accountId) {
                findId = true;
                foundId = i;
            }
        }
        if (findId) {
            StoreAccounts.accounts.get(foundId).setAmount((int) (StoreAccounts.accounts.get(foundId).getAmount() + amount));
            fr.write(StoreAccounts.accounts.get(foundId));
        } else throw new UnknownAccountException();
    }

    @Override //перевести сумму с одного на другой
    public void transfer(int accountFrom, int accountTo, int amount) throws NotEnoughtMoneyException, UnknownAccountException {
        boolean findIdFrom = false;
        boolean findIdTo = false;
        int foundIdFrom = 0;
        int foundIdTo = 0;
        for (int i = 0; i < StoreAccounts.accounts.size(); i++) {
            if (StoreAccounts.accounts.get(i).getId() == accountFrom) {
                findIdFrom = true;
                foundIdFrom = i;
            }
            if (StoreAccounts.accounts.get(i).getId() == accountTo) {
                findIdTo = true;
                foundIdTo = i;
            }
        }
        if (findIdFrom & findIdTo) {
            if (StoreAccounts.accounts.get(foundIdFrom).getAmount() >= amount) {
                StoreAccounts.accounts.get(foundIdTo).setAmount((int) (StoreAccounts.accounts.get(foundIdTo).getAmount() + amount));
                StoreAccounts.accounts.get(foundIdFrom).setAmount((int) (StoreAccounts.accounts.get(foundIdFrom).getAmount() - amount));
                fr.write(StoreAccounts.accounts.get(foundIdTo));
                fr.write(StoreAccounts.accounts.get(foundIdFrom));
            }
            else throw new NotEnoughtMoneyException();
        }
        else throw new UnknownAccountException();
    }
}
