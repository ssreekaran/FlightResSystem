//Sarmilan Sreekaran
//500758148
import java.util.ArrayList;
import java.util.Scanner;
// Flight System for one single day at YYZ (Print this in title) Departing flights!!


public class FlightReservationSystem
{
	public static void main(String[] args)
	{
		// Create a FlightManager object
		FlightManager manager = new FlightManager();

		// List of reservations that have been made
		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();	// my flight reservations

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine())
		{
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;

			// The command line is a scanner that scans the inputLine string
			// For example: list AC201
			Scanner commandLine = new Scanner(inputLine);
			
			// The action string is the command to be performed (e.g. list, cancel etc)
			String action = commandLine.next();

			if (action == null || action.equals("")) continue;

			if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			// List all flights
			else if (action.equalsIgnoreCase("LIST"))
			{
				manager.printAllFlights(); 
			}
			// Reserve a flight based on Flight number string (example input: res AC220)
			else if (action.equalsIgnoreCase("RES"))
			{
				String flightNum;
				// Get the flight number string from the commndLine scanner (use hasNext() to check if there is a
				// flight number string entered
				if(commandLine.hasNext()) {
					flightNum=commandLine.next();
				// call reserveSeatOnFlight() method in manager passing in the flight number string
				// A reference to a Reservation object is returned. Check to make sure it is not == null
				// If it is null, then call manager.getErrorMessage() and print the message
				// If it is not null, add the reservation to the myReservations array list and print the reservation (see class Reservation)
					Reservation res = manager.reserveSeatOnFlight(flightNum, LongHaulFlight.economy);//make res object with the data
					if(res==null)
						System.out.println(manager.getErrorMessage());
					else
						myReservations.add(res);//adds res object into list
				}
			}
		  // Reserve a first class seat on a flight based on Flight number string (example input: res AC220)
			else if (action.equalsIgnoreCase("RESFCL"))
			{
				// Same as above except call 
				// manager.reserveSeatOnFlight() and pass in the flight number string as well as the string constant:
				// LongHaulFlight.firstClass (see class LongHaulFlight)
				// Do the LongHaulFlight class and this command after all the basic functionality is working for regular flights
				String flightNum;
				if(commandLine.hasNext()) {
					flightNum=commandLine.next();
					Reservation res = manager.reserveSeatOnFlight(flightNum, LongHaulFlight.firstClass);//make res object with the data
					if(res==null)
						System.out.println(manager.getErrorMessage());
					else
						myReservations.add(res);//adds res object into list
				}
			}
			// Query the flight manager to see if seats are still available for a specific flight (example input: seats AC220)
		  // This one is done for you as a guide for other commands
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum = null;

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();

					if (manager.seatsAvailable(flightNum))
					{
						System.out.println("Seats are available");
					}
					else
					{
						System.out.println(manager.getErrorMessage());
					}
				}
			}
			// Cancel an existing reservation (example input: cancel AC220) 
			else if (action.equalsIgnoreCase("CANCEL"))
			{
				boolean wasreached=false;
				String flightNum;
				if(commandLine.hasNext()) {
					flightNum=commandLine.next();
					for(int i=0;i<myReservations.size();i++) {
						if(myReservations.get(i).getFlightNum().equals(flightNum)) {//finds the object with the flightnum
							manager.cancelReservation(myReservations.get(i));
							myReservations.remove(i);
							wasreached=true;
						}
					}
					if(!wasreached)//if the cancellation didn't occur
						System.out.println(manager.getErrorMessage());//print error
				}
        // get the flight number string from commandLine scanner (check if there is input first)
				// Use the flight number to find the Reservation object in the myReservations array list
				// If the reservation is found,  
				// 		call cancelReservation() method in the flight manager
				//    remove the reservation from myReservations
				// If the reservation is not found, print a message (see video)
			}
      // Print all the reservations in array list myReservations
			else if (action.equalsIgnoreCase("MYRES"))
			{
				for(int i=0;i<myReservations.size();i++) {
					System.out.println(myReservations.get(i).flightInfo);//prints myres info
				}
			}
			// Print the list of aircraft (see class Manager)
			else if (action.equalsIgnoreCase("CRAFT"))
		  {
			  manager.printAllAircraft();
			}
			// These commands can be left until we study Java interfaces
			// Feel free to implement the code in class Manager if you already understand interface Comparable
			// and interface Comparator
			else if (action.equalsIgnoreCase("SORTCRAFT"))
		  {
		  	manager.sortAircraft();
		  }
		  else if (action.equalsIgnoreCase("SORTBYDEP"))
		  {
			  manager.sortByDeparture();
			  
		  }
		  else if (action.equalsIgnoreCase("SORTBYDUR"))
		  {
			  manager.sortByDuration();
		  }
		  else if (action.equalsIgnoreCase("RESPSNGR"))
		  {
			  manager.sortByDuration();
		  }
		  else if (action.equalsIgnoreCase("CNCLPSNGR"))
		  {
			  manager.sortByDuration();
		  }
		  else if (action.equalsIgnoreCase("PSNGRS"))
		  {
			  manager.sortByDuration();
		  }
	  
			System.out.print("\n>");
		}
	}

	
}