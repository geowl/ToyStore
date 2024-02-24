package Task;

import Task.Toy;

import java.io.*;
import java.util.*;

public class ToyShop implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Toy> toys;

    public ToyShop() {
        toys = new ArrayList<>();
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void addNewToy(int id, String name, int quantity, double weight) {
        Toy toy = new Toy(id, name, quantity, weight);
        toys.add(toy);
    }

    public void updateWeight(int toyId, double weight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(weight);
                return;
            }
        }
        System.out.println("Игрушка с ID " + toyId + " не найдена.");
    }

    public void drawToys() {
        Random random = new Random();
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        double randomNum = random.nextDouble() * totalWeight;
        double weightSum = 0;
        for (Toy toy : toys) {
            weightSum += toy.getWeight();
            if (randomNum <= weightSum) {
                System.out.println("Поздравляем! Вы получили: " + toy.getName());
                return;
            }
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(toys);
            System.out.println("Данные об игрушках успешно сохранены.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            toys = (List<Toy>) ois.readObject();
            System.out.println("Данные об игрушках успешно загружены.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
