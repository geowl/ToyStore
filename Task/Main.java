package Task;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();

        toyShop.addToy(new Toy(1, "Детский конструктор", 350, 5));
        toyShop.addToy(new Toy(2, "Кукла", 200, 50));
        toyShop.addToy(new Toy(3, "Мягкая игрушка", 1000, 95));
        toyShop.addToy(new Toy(4, "Игрушечный транспорт", 200, 20));
        toyShop.addToy(new Toy(5, "Игрушка на радиоуправлении", 290, 20));
        toyShop.addToy(new Toy(6, "Игрушка музыкальный инструмент", 100, 40));
        toyShop.addToy(new Toy(7, "Игрушка бытовая техника", 100, 25));

        toyShop.saveToFile("Task/toys.dat");

        ToyShop loadedToyShop = new ToyShop();
        loadedToyShop.loadFromFile("Task/toys.dat");

        loadedToyShop.updateWeight(1, 40);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Меню:");
            System.out.println("1. Информация о текущих игрушках");
            System.out.println("2. Добавить новую игрушку");
            System.out.println("3. Провести розыгрыш");
            System.out.println("4. Выйти из программы");

            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Информация о текущих игрушках:");
                    for (Toy toy : loadedToyShop.getToys()) {
                        System.out.println(toy);
                    }
                    break;
                case 2:
                    System.out.println("Добавление новой игрушки:");
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите название: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите количество: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Введите вес: ");
                    double weight = scanner.nextDouble();
                    loadedToyShop.addNewToy(id, name, quantity, weight);
                    System.out.println("Новая игрушка добавлена.");
                    break;
                case 3:
                    System.out.println("Розыгрыш игрушки:");
                    loadedToyShop.drawToys();
                    choice = 4;
                    break;
                case 4:
                    System.out.println("Завершение работы программы.");
                    break;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста, выберите действие от 1 до 4.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}
