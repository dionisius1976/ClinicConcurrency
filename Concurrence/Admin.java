package ru.lesson.Concurrence;

import ru.lesson.lessons.Cat;
import ru.lesson.lessons.Client;
import ru.lesson.lessons.ClinicSimpleArrayList;
import ru.lesson.lessons.Dog;

import java.util.Random;

/**
 * Created by trit on 11.03.2016.
 */
public class Admin extends Thread {

    private Random rand;
    private ClinicSimpleArrayList cl;
    public static String[] clientsNames = {"Ivan", "Alexandr", "Victor", "Maria"};
    private String[] petsNames = {"Vesta", "Neptun", "Butch", "Mars"};

     Admin(ClinicSimpleArrayList cl, String threadName) {
        super(threadName);
        this.rand = new Random();
        this.cl = cl;
    }

    @Override
    public void run() {
        System.out.println("Admin is started.");
        synchronized (cl) {

            for (int i = 0; i < clientsNames.length; i++) {
                if (rand.nextInt(2) == 0) cl.addClient(new Client(clientsNames[i], new Dog(petsNames[i])));
                else cl.addClient(new Client(clientsNames[i], new Cat(petsNames[i])));

                System.out.println("Admin: client " + clientsNames[i] + " is added! Pets name is "+ petsNames[i]);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cl.notifyAll();
            }
        }
    }
}
