package ru.lesson.lessons;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ru.lesson.lessons.SimpleArrayList;

/**
 * Created by trit on 03.03.2016.
 */
public class ClinicSimpleArrayList {


    /**
     * This class describes Clinic and makes operations
     * with clients and their pets.
     */

    SimpleArrayList<Client> clients;

    private final String[] menu = {"Choose your action:", "", "1 - Add a new client;", "2 - Find client by pet's name;",
            "3 - Find pet by client's name;", "4 - Edit client's name;", "5 - Edit pet's name;", "6 - Delete client by his name;",
            "7 - Delete client by pet's name;", "8 - Print all clients;", "9 - Quit."};

    /**
     * Class constructor;
     */

    public ClinicSimpleArrayList(){
        clients = new SimpleArrayList<Client>();

    }

    public SimpleArrayList<Client> getClients(){

        return clients;
    }

    public Client getClient (int index){
        return clients.get(index);
    }

    /**
     * This method adds a new client to clinic list.
     * @param client
     */

    public void addClient(Client client){
        this.clients.add(client);
    }

    public int size(){
        return this.clients.size();
    }

    public boolean isEmpty(){
        return this.clients.isEmpty();
    }

    /**
     * This method checks client's existing in clinic list.
     * @param clientsName
     * @return true, if client with name clientsName exists in clinic list, otherwise return false.
     */
    public boolean isClientExist(String clientsName){
        for (int i = 0; i < clients.size(); i++) {
            if (clientsName.equalsIgnoreCase(clients.get(i).getName())) return true;
        }
        return false;
    }

    /**
     * This method checks pet's existing in clinic list.
     * @param petsName
     * @return true, if pet with name petsName exists in clinic list, otherwise return false.
     */
    public boolean isPetExist(String petsName){
        for(Client cl: clients){
            if (petsName.equalsIgnoreCase(cl.getPet().getName())) return true;
        }
        return false;
    }

    /**
     * This method searchs the client by pet's name in the clinic list.
     * @param petsName
     * @return client who has pet with name petsName, otherwise return Null.
     */
    public Client findClientByPetsName (final String petsName){
        for(Client cl: clients){
            if (petsName.equalsIgnoreCase(cl.getPet().getName())) return cl;}
        return null;
    }

    /**
     * This method searchs the pet by client's name in the clinic list.
     * @param clientsName
     * @return pet that's owner has name clientsName, otherwise return Null.
     */

    public Pet findPetByClientsName (final String clientsName){
        for(Client cl: clients){
            if (clientsName.equalsIgnoreCase(cl.getName())) return cl.getPet();}
        return null;
    }

    /**
     * This method changes the client's old name to new in the clinic list.
     * @param clientsOldName
     * @param clientsNewName
     */

    public void editClientsName (final String clientsOldName, final String clientsNewName){
        for(Client cl: clients){
            if (clientsOldName.equalsIgnoreCase(cl.getName())) {

                clients.add(new Client(clientsNewName, cl.getPet()));
                clients.remove(cl);
                break;}
        }
    }

    /**
     * This method changes the pet's old name to new in the clinic list.
     * @param petsOldName
     * @param petsNewName
     */

    public void editPetsName (final String petsOldName, final String petsNewName){
        for(Client cl: clients){
            if (petsOldName.equalsIgnoreCase(cl.getPet().getName())) {
                cl.getPet().setName(petsNewName);
                break;}
        }
    }

    /**
     * This method deletes the client via his name from the clinic list.
     * @param clientsName
     */

    public void deleteClientByHisName (final String clientsName){
        for(Client cl: clients){
            if (clientsName.equalsIgnoreCase(cl.getName())) {
                clients.remove(cl);
                break;}
        }
    }

    /**
     * This method deletes the client via his pet's name from the clinic list.
     * @param petsName
     */

    public void deleteClientByPetsName (final String petsName){
        for (int i = 0; i < clients.size(); i++) {

            if (petsName.equalsIgnoreCase(clients.get(i).getPet().getName())) {
                clients.remove(i);
                break;}
        }
    }

    /**
     * This method prints menu of operations with cliet's list.
     */

    public void printMenu(){
        for(String iter: menu){
            System.out.println(iter);
        }
    }

    public void printClientsDataBase(){
        if (this.isEmpty()) System.out.println("Clinic list is empty.");
        else for (int i = 0; i < clients.size(); i++) {
            System.out.println(clients.elementData[i]);}
    }

    /**
     * This method performs the operation that was selected by user.
     * @param choice
     * @throws UserException if user enter incorrect symbol.
     */
    public void operationChoice (String choice)throws UserException {

        Scanner reader = new Scanner(System.in);

        //Add new client:
        if ("1".equals(choice)) {
            System.out.print("Enter new client's name: ");
            String clientsName = reader.next();
            if (!isClientExist(clientsName)){
                System.out.println();
                System.out.print("Enter pet's name: ");
                String petsName = reader.next();
                System.out.println();
                if (!isPetExist(petsName)){
                    System.out.print("Enter pet's type (dog/cat): ");
                    String petsType = reader.next();
                    System.out.println();
                    if ("cat".equalsIgnoreCase(petsType)) addClient((new Client(clientsName, new Cat(petsName))));
                    else if ("dog".equalsIgnoreCase(petsType)) addClient((new Client(clientsName, new Dog(petsName))));
                    else System.out.println("Wrong pet's type! Client " + clientsName + " is not added. Try again.");
                }
                else System.out.println("Pet " + petsName + " is already exist. Client " + clientsName + " is not added. Try again.");
            }
            else System.out.println("Client " + clientsName + " is already exist. Try again.");
        }
        // Find client by pet's name:
        else if ("2".equals(choice)) {
            if (clients.isEmpty()) System.out.println("Clinic list is empty.");
            else {System.out.print("Enter pet's name: ");
                String petsName = reader.next();
                System.out.println();
                if (isPetExist(petsName))System.out.println("Client's name is " + findClientByPetsName(petsName).getName());
                else System.out.println("Pet " + petsName + " doesn't exist. Try again.");
            }}
        //Find pet by client's name:
        else if ("3".equals(choice)) {
            if (clients.isEmpty()) System.out.println("Clinic list is empty.");
            else {System.out.print("Enter client's name: ");
                String clientsName = reader.next();
                System.out.println();
                if (isClientExist(clientsName)) System.out.println("Pet's name is " + findPetByClientsName(clientsName).getName());
                else System.out.println("Client " +  clientsName + " doesn't exist. Try again.");
            }}
        //Edit client's name:
        else if ("4".equals(choice)) {
            if (clients.isEmpty()) System.out.println("Clinic list is empty.");
            else {System.out.print("Enter old client's name: ");
                String clientsOldName = reader.next();
                System.out.println();
                if (isClientExist(clientsOldName)) {
                    System.out.print("Enter new client's name: ");
                    String clientsNewName = reader.next();
                    System.out.println();
                    if (!isClientExist(clientsNewName)) {
                        editClientsName(clientsOldName, clientsNewName);
                        System.out.println("Client's name is changed.");
                    }
                    else System.out.println("Client " +  clientsNewName + " is already exist. Try again.");
                }
                else System.out.println("Client " +  clientsOldName + " doesn't exist. Try again.");
            }}
        //Edit pet's name:
        else if ("5".equals(choice)) {
            if (clients.isEmpty()) System.out.println("Clinic list is empty.");
            else {System.out.print("Enter old pet's name: ");
                String oldPetsName = reader.next();
                System.out.println();
                if (isPetExist(oldPetsName)){
                    System.out.print("Enter new pet's name: ");
                    String newPetsName = reader.next();
                    System.out.println();
                    if (!isPetExist(newPetsName)){
                        editPetsName (oldPetsName, newPetsName);
                        System.out.println("Pet's name is changed.");
                    }
                    else System.out.println("Pet " + newPetsName + " is already exist. Try again.");
                }
                else System.out.println("Pet " + oldPetsName + " doesn't exist. Try again.");
            }}
        //Delete client by his name:
        else if ("6".equals(choice)) {
            if (clients.isEmpty()) System.out.println("Clinic list is empty.");
            else {System.out.print("Enter client's name: ");
                String clientsName = reader.next();
                System.out.println();
                if (isClientExist(clientsName)) {
                    deleteClientByHisName(clientsName);
                    System.out.println("Client " + clientsName + " is deleted.");
                }
                else System.out.println("Client " + clientsName + " doesn't exist. Try again.");
            }}
        //Delete client by his pet's name:
        else if ("7".equals(choice)) {
            if (clients.isEmpty()) System.out.println("Clinic list is empty.");
            else{System.out.print("Enter pet's name: ");
                String petsName = reader.next();
                System.out.println();
                if (isPetExist(petsName)){
                    deleteClientByPetsName(petsName);
                    System.out.println("Client is deleted.");
                }
                else System.out.println("Pet " + petsName + " doesn't exist. Try again.");
            }}
        //Print all clients
        else if ("8".equals(choice)) {printClientsDataBase();}

        // Invalid enter
        else  throw new UserException ("Invalid enter. Try again.");

    }
}
