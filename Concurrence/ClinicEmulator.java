package ru.lesson.lessons.ConcurrenceClinic;
import ru.lesson.lessons.ClinicProject.ClinicSimpleArrayList;

class ClinicEmulator {

    public static void main(String[] args) throws InterruptedException {
        ClinicSimpleArrayList cl = new ClinicSimpleArrayList();
	//Запуск админа
        Admin admin = new Admin(cl);
        Thread.sleep(1000);
        cl.printClientsDataBase();
        System.out.println();
	//создание и запуск 4 потоков User
        for (int i = 0; i < 4; i++) {
            String userName = "User"+i;
            new User(cl, i, userName);
        }
    }
}
