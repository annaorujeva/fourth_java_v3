package app.service;
import app.exception.NotEnoughtMoneyException;
import app.exception.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Menu {

    @Autowired
    AccountService fileaccservice;

    public boolean flagexit;

    public  void showactions(){
        System.out.println("Что вы хотите сделать? Введите команды без скобок!\r\n" +
                " 1: при вводе в консоле команды нужно будет указать balance [id] – вывеси информацию о счёте\n" +
                " 2: при вводе в консоле команды withdraw [id] [amount] – снять указанную сумму\n" +
                " 3: при вводе в консоле команды deposite [id] [amount] – внести на счет указанную сумму\n" +
                " 4: при вводе в консоле команды transfer [from] [to] [amount] – перевести сумму с одного счета на другой\n"+
                " 5: при вводе в консоли команды exit - выйти из программы\n");
    }

    public void start(String command) throws NotEnoughtMoneyException, UnknownAccountException {
        String [] words = command.split(" ");
        /*В данном массиве хранятся ключевые слова, считанные с консоли. Например:
        Для нахождения баланса (balance) words[0] - команда, которую нужно сделать, words[1] - сам id
        Для снятия указанной суммы (withdraw) words[0] - команда, которую нужно сделать, words[1] - id, words[2] - сумма, которую нужно снять.
        И т.д. для остального.
         */
        flagexit = false;
        switch (words[0]){
            case "balance":
                System.out.println("На счете следующая сумма:");
                fileaccservice.balance(Integer.parseInt(words[1]));
                break;
            case "withdraw":
                System.out.println("Снять указанную сумму");
                fileaccservice.withdraw(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                System.out.println("Теперь на счете следующая сумма:");
                fileaccservice.balance(Integer.parseInt(words[1]));
                break;
            case "transfer":
                System.out.println("Перевести сумму с одного счета на другой");
                fileaccservice.transfer(Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]));
                System.out.println("Теперь на счете отправителя следующая сумма:");
                fileaccservice.balance(Integer.parseInt(words[1]));
                System.out.println("Теперь на счете получателя следующая сумма:");
                fileaccservice.balance(Integer.parseInt(words[2]));
                break;
            case "deposite":
                System.out.println("Внести на счет указанную сумму");
                fileaccservice.deposit(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                System.out.println("Теперь на счете следующая сумма:");
                fileaccservice.balance(Integer.parseInt(words[1]));
                break;
            case "exit":
                flagexit = true;
                break;
        }
    }
}
