package ru.lesson.lessons;

import java.util.Scanner;

/**
* Clinic's working part
*/

public class ClinicRunner{

	public static void main (String[] args){
		
		final ClinicSimpleArrayList clinic = new ClinicSimpleArrayList();
		Scanner reader = new Scanner(System.in);
		Boolean quit = false;
		String choice;
		
		while(!quit){
		
			clinic.printMenu();

			try {
				choice = reader.next();

				if ("9".equals(choice)) {
					quit = true;
					continue;
				}

				clinic.operationChoice(choice);
				System.out.println();
			}
			catch (UserException e){
				System.out.println(e.getMessage());
				System.out.println();
			}
		}

	reader.close();
	System.out.print("Bye!");
		
	}
}
