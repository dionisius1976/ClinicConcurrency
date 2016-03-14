package ru.lesson.Concurrence;


import ru.lesson.lessons.ClinicSimpleArrayList;

/**
 * Created by trit on 11.03.2016.
 */
class ClinicEmulator {


    public static void main(String[] args) throws InterruptedException {
        ClinicSimpleArrayList cl = new ClinicSimpleArrayList();
        Admin admin = new Admin(cl, "Admin");
        for (int i = 0; i < admin.clientsNames.length ; i++) {
            String userName = "User"+i;
            new User(cl, i, userName);
            Thread.sleep(500);
        }

        admin.start();
        Thread.sleep(5000);
        cl.printClientsDataBase();

//        for (int i = 0; i < admin.clientsNames.length ; i++) {
//            String userName = "User"+i;
//            new User(cl, i, userName);
//        }
//
//        Thread.sleep(5000);
//        cl.printClientsDataBase();

    }
}
