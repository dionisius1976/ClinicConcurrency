package ru.lesson.Concurrence;


import ru.lesson.lessons.ClinicSimpleArrayList;
import ru.lesson.lessons.Pet;

/**
 * Created by trit on 11.03.2016.
 */
public class User extends Thread {
    private ClinicSimpleArrayList cl;
    private int clientsIndex;
    private Pet clientsPet;
    private String oldPetsName;
    private String newPetsName;
    private String threadsName;

    public User(ClinicSimpleArrayList cl, int clientsIndex, String threadsName) {
        super(threadsName);
        this.threadsName = threadsName;
        this.clientsIndex = clientsIndex;
        this.cl = cl;
        start();
    }

    @Override
    public void run() {
        System.out.println(threadsName+" is started.");
            synchronized (cl) {
                while (cl.isEmpty()) {
                    try {
                        cl.wait();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }

            }
                clientsPet = cl.getClient(clientsIndex).getPet();
                oldPetsName = clientsPet.getName();
                newPetsName = new StringBuilder(oldPetsName).reverse().toString();
                cl.editPetsName(clientsPet.getName(), newPetsName);
                System.out.println("User"+clientsIndex+": Pet " + oldPetsName + " is renamed to "+newPetsName);
            }
}
