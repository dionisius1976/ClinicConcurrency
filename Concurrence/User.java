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
        System.out.println(threadsName + " is started.");
        System.out.println();

        synchronized (cl) {
            while (cl.isEmpty()) {
                try {
                    cl.wait();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }

        }
        //цикл, изменение правильного имени животного на его зеркальное отражение
        for (int i = 0; i < 5; i++) {
            oldPetsName = cl.getClient(clientsIndex).getPet().getName();
            char tempChar = oldPetsName.charAt(0);
            if (!Character.isLowerCase(tempChar)) {
                newPetsName = new StringBuilder(oldPetsName).reverse().toString();
                cl.editPetsName(oldPetsName, newPetsName);
                System.out.println("User" + clientsIndex + ": Pet " + oldPetsName + " is renamed to " + newPetsName);
                System.out.println();
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
