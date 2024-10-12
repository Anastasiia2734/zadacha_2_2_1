package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

            UserService userService = context.getBean(UserService.class);

            Car car1 = new Car("Volkswagen Golf", 2018);
            Car car2 = new Car("Opel Astra", 2012);
            Car car3 = new Car("Land Cruiser Prado", 2021);
            Car car4 = new Car("Volkswagen Passat", 2010);

            User user1 = new User("Андрей", "Иванов", "a.ivanov@mail.ru");
            User user2 = new User("Ирина", "Перина", "i.perina@mail.ru");
            User user3 = new User("Ольга", "Петрова", "o.petrova@mail.ru");
            User user4 = new User("Иван", "Иванов", "i.ivanov@mail.ru");

            userService.add(user1.setCar(car1).setUser(user1));
            userService.add(user2.setCar(car2).setUser(user2));
            userService.add(user3.setCar(car3).setUser(user3));
            userService.add(user4.setCar(car4).setUser(user4));
            List<User> users = userService.listUsers();
            for (User user : users) {
                System.out.println("Id = " + user.getId());
                System.out.println("First Name = " + user.getFirstName());
                System.out.println("Last Name = " + user.getLastName());
                System.out.println("Email = " + user.getEmail());
                System.out.println();
            }
            System.out.println(userService.getUserByCar("Opel Astra", 2012));
            context.close();
        }
    }
}
