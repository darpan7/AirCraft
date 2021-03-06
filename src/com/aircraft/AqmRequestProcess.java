package com.aircraft;

import java.util.Scanner;

import com.aircraft.model.AirCraft;
import com.aircraft.service.ACService;
import com.aircraft.utils.Covnerter;
/**
 * 
 * @author DARPAN
 *
 */
public class AqmRequestProcess {
	private static int counter = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t Queue of AirCraft(AC)");
		System.out.println("\t\t\t =====================");
		boolean loop = true;
		ACService ac = null;
		while(loop) {
			System.out.println("Options: \t1. start \t2. enqueue \t3. dequeue \t4. stop");
			String input = sc.next();
			if("Start".equalsIgnoreCase(input)) {
				ac = new ACService();
				ac.reboot();
				counter = 0;
				System.out.println(ac.print("start", "System started!"));
			}else if("enqueue".equalsIgnoreCase(input)) {
				if(ac == null) {
					System.out.println(ACService.printError("enqueue", "Please start aircraft system first!"));
				}else {
					System.out.println("AC Type (Passenger/Cargo): ");
					String type = sc.next();
					System.out.println("AC Size (Small/Large): ");
					String size = sc.next();
					ac.enqueue(new AirCraft(Covnerter.convertToType(type), Covnerter.convertToSize(size), null, ++counter));
					System.out.println(ac.print("enqueue", null));					
				}
			}else if("dequeue".equalsIgnoreCase(input)) {
				if(ac == null) {
					System.out.println(ACService.printError("dequeue", "Please start aircraft system first!"));
				}else {
					System.out.println(ac.print("dequeue", ac.dequeue()));					
				}
			}else if("stop".equalsIgnoreCase(input)){
				System.out.println("Bye!");
				loop = false;
			}else {
				System.out.println("Invalid input.");
			}
			
		}
		sc.close();
	}

}
