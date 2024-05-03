package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        User user1 = new User("Anton", "Mak", "email@liame.ur");
        Car car = new Car("Peugeot", 2);
        user1.setCar(car);
        car.setUser(user1);
        userService.saveCar(user1, car);

        User user2 = new User("Kirill", "Naum", "email@yandex.ru");
        Car car2 = new Car("Mercedes", 1);
        user2.setCar(car2);
        car2.setUser(user2);
        userService.saveCar(user2, car2);

        User user3 = new User("Igor", "Svet", "email@mail.ru");
        Car car3 = new Car("BMW", 3);
        user3.setCar(car3);
        car3.setUser(user3);
        userService.saveCar(user3, car3);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        User user4 = userService.getUser(car3);
        System.out.println(user4.getFirstName());
        System.out.println(user4.getLastName());
        System.out.println(user4.getEmail());
    }
}
