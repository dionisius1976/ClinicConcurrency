package ru.lesson.lessons.ConcurrenceClinic;

import ru.lesson.lessons.ClinicProject.Cat;
import ru.lesson.lessons.ClinicProject.Client;
import ru.lesson.lessons.ClinicProject.ClinicSimpleArrayList;
import ru.lesson.lessons.ClinicProject.Dog;
import java.util.Random;

public class Admin extends Thread {

    private final Random rand;
    private final ClinicSimpleArrayList cl;
    public final static String[] clientsNames = {"Ivan", "Alexandr", "Victor", "Maria"};
    private final String[] petsNames = {"Vesta", "Neptun", "Butch", "Mars"};

     Admin(final ClinicSimpleArrayList cl, final String threadName) {
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

                cl.notifyAll();
            }
        }
    }
}
