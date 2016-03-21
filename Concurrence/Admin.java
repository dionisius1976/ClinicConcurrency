package ru.lesson.lessons.ConcurrenceClinic;

import ru.lesson.lessons.ClinicProject.*;

public class Admin extends Thread {

    private final ClinicSimpleArrayList cl;
    public final static String[] clientsNames = {"Ivan", "Alexandr", "Victor", "Maria"};
    private final String[] petsNames = {"Vesta", "Neptun", "Butch", "Mars"};

     Admin(final ClinicSimpleArrayList cl) {
         super("Admin");
         this.cl = cl;
         start();
    }

    @Override
    public void run() {

        System.out.println("Admin is started.");
        // заполнение списка клиники из массива

            for (int i = 0; i < clientsNames.length; i++) {
                synchronized (cl) {
                cl.addClient(new Client(clientsNames[i], new Dog(petsNames[i])));
                System.out.println("Admin: client " + clientsNames[i] + " is added! Pets name is "+ petsNames[i]);
                cl.notifyAll();
            }
        }
        System.out.println();
		
        //цикл, если имя животного изменено, то ему присваивается изначальное значение
        for (int i = 0; i < 10; i++) {
            synchronized (cl) {
                for (int j = 0; j < cl.size(); j++) {
                    char tempChar = cl.getClient(j).getPet().getName().charAt(0);

                    if (Character.isLowerCase(tempChar)) {
                        String currentPetsName = cl.getClient(j).getPet().getName();
                        System.out.println(currentThread().getName()+": pet's wrong name "+currentPetsName+
                        " is changed to "+petsNames[j]);
                        cl.editPetsName(currentPetsName, petsNames[j]);
                        System.out.println();

                        }
                    }
                cl.notifyAll();
                }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cl.printClientsDataBase();

    }
}
