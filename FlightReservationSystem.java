import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Flight System for one single day at YYZ (Print this in title) Departing flights!!


public class FlightReservationSystem
{
	public static void main(String[] args) throws FileNotFoundException, Flight.PassengerNotInManifestException, Flight.SeatTypeInvalidException
	{
		FlightManager manager = new FlightManager();

		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();	// my flight reservations


		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print(">");

			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				if (inputLine == null || inputLine.equals("")) {
					System.out.print("\n>");
					continue;
				}

					try (Scanner commandLine = new Scanner(inputLine)) {
					String action = commandLine.next();

					if (action == null || action.equals("")) {
						System.out.print("\n>");
						continue;
					}

					else if (action.equals("Q") || action.equals("QUIT"))
						return;

					else if (action.equalsIgnoreCase("LIST")) {
						manager.printAllFlights();
					}
					else if (action.equalsIgnoreCase("RES")) {
						String flightNum = null;
						String passengerName = "";
						String passport = "";
						String seatType = "";

						if (commandLine.hasNext()) {
							flightNum = commandLine.next();
						}
						if (commandLine.hasNext()) {
							passengerName = commandLine.next();
						}
						if (commandLine.hasNext()) {
							passport = commandLine.next();
						}
						if (commandLine.hasNext()) {
							seatType = commandLine.next();

							Reservation res = manager.reserveSeatOnFlight(flightNum, passengerName, passport, seatType);
							if (res != null) {
								myReservations.add(res);
								res.print();
							}
							else {
								System.out.println(manager.getErrorMessage());
							}
						}
					}
					else if (action.equalsIgnoreCase("CANCEL")) {
						String flightNum = null;
						String passengerName = "";
						String passport = "";

						if (commandLine.hasNext()) {
							flightNum = commandLine.next();
						}
						if (commandLine.hasNext()) {
							passengerName = commandLine.next();
						}
						if (commandLine.hasNext()) {
							passport = commandLine.next();

							int index = myReservations.indexOf(new Reservation(flightNum, passengerName, passport));
							if (index >= 0) {
								manager.cancelReservation(myReservations.get(index));
								myReservations.remove(index);
							}
							else {
								System.out.println("Reservation on Flight " + flightNum + " Not Found");
							}
						}
					}
					else if (action.equalsIgnoreCase("SEATS")) {
						String flightNum = "";
						
						if (commandLine.hasNext()) {
							flightNum = commandLine.next();
						}
						System.out.println(manager.seatsAvailable(flightNum));
					}
					else if (action.equalsIgnoreCase("PASMAN")) {
						String flightNum = "";
						
						if (commandLine.hasNext()) {
							flightNum = commandLine.next();
						}
						manager.getFlight(flightNum).printPassengerManifest();
					}
					else if (action.equalsIgnoreCase("MYRES")) {
						for (Reservation myres : myReservations)
							myres.print();
					}
					System.out.print("\n>");
				} // End of commandLine try-with-resources
			} // End of while loop
		} // End of scanner try-with-resources
	} // End of main method
} // End of class