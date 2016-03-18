package ru.lesson.lessons.ConcurrenceClinic;

import ru.lesson.lessons.ClinicProject.ClinicSimpleArrayList;

class ClinicEmulator {

    public static void main(String[] args) throws InterruptedException {
        ClinicSimpleArrayList cl = new ClinicSimpleArrayList();
        Admin admin = new Admin(cl, "Admin");
       
        for (int i = 0; i < admin.clientsNames.length ; i++) {
            String userName = "User"+i;
            new User(cl, i, userName);
            Thread.sleep(200);
        }

        admin.start();
        cl.printClientsDataBase();
    }
}
