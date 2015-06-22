package coffeeimport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeImportApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeImportApplication.class, args);

        
        ParseTemporary temp = new ParseTemporary();


    }
}
