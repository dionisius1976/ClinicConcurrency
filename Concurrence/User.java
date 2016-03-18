package ru.lesson.lessons.ConcurrenceClinic;

import ru.lesson.lessons.ClinicProject.ClinicSimpleArrayList;
import ru.lesson.lessons.ClinicProject.Pet;

public class User extends Thread {

    private final ClinicSimpleArrayList cl;
    private final int clientsIndex;
    private Pet clientsPet;
    private String oldPetsName;
    private String newPetsName;
    private String threadsName;

    public User(final ClinicSimpleArrayList cl, final int clientsIndex, final String threadsName) {
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
