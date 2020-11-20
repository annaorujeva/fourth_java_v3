package app.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Account {
    private int id;
    private String holder;
    private int amount;

    public Account(){
    }

    public Account(int id, String holder, int amount){
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getHolder() {
        return holder;
    }

    public int getId() {
        return id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public void setId(int id) {
        this.id = id;
    }
}
