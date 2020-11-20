package app;
import app.exception.NotEnoughtMoneyException;
import app.exception.UnknownAccountException;
import app.service.*;
import app.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    Menu mn;

    @Autowired
    StoreService servicestore;

    public static void main(String[] args) throws NotEnoughtMoneyException, UnknownAccountException {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        servicestore.startofwork();
        mn.showactions();
        for (;;){
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            mn.start(command);
            if (mn.flagexit) break;
       }
    }

}
