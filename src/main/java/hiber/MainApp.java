package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("John", "Doe", "john@mail.com", new Car("BMW", 5));
        User user2 = new User("Jane", "Smith", "jane@mail.com", new Car("Audi", 7));
        User user3 = new User("Max", "Payne", "max@mail.com", new Car("Tesla", 3));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        System.out.println("--- All Users ---");
        userService.listUsers().forEach(u ->
                System.out.println(u.getFirstName() + " " + u.getLastName()
                        + " -> " + u.getCar().getModel() + " " + u.getCar().getSeries()));

        System.out.println("--- Find User by Car ---");
        User foundUser = userService.getUserByCar("Tesla", 3);
        System.out.println("Owner of Tesla 3: " + foundUser.getFirstName());

        context.close();
    }
}