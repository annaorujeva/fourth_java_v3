package app;

import app.repository.FileRepository;
import app.repository.Repository;
import app.service.AccountService;
import app.service.FileAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Config {

    @Bean
    public Repository getRepository(){
        return new FileRepository();
    }

    @Bean
    public AccountService getAccountService(){
        return new FileAccountService();
    }


}
