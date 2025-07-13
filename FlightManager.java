import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


public class FlightManager
{
  ArrayList<Flight> flights = new ArrayList<Flight>();
  
  String[] cities 	= 	{"Dallas", "New York", "London", "Paris", "Tokyo"};
  final int DALLAS = 0;  final int NEWYORK = 1;  final int LONDON = 2;  final int PARIS = 3; final int TOKYO = 4;
  
  int[] flightTimes = { 					3, // Dallas
  											1, // New York
  											7, // London
  											8, // Paris
  											16,// Tokyo
  										};
  
  ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>();  
  ArrayList<String> flightNumbers = new ArrayList<String>();
  
  String errMsg = null;
  Random random = new Random();
  File flightData = new File("flights.txt");
  
  public FlightManager() throws FileNotFoundException
  {
    // Create some aircraft types with max seat capacities
    airplanes.add(new Aircraft(85, "Boeing 737"));
    airplanes.add(new Aircraft(180,"Airbus 320"));
    airplanes.add(new Aircraft(37, "Dash-8 100"));
    airplanes.add(new Aircraft(4, "Bombardier 5000"));
    airplanes.add(new Aircraft(592, 14, "Boeing 747"));
    
    try (Scanner in = new Scanner(flightData)) {
        while(in.hasNext()) {
            String airline = space(in.next());
            String flightNum = generateFlightNumber(airline);
            String dest = in.next();
            String departure = in.next();
            int flightTimesPos = 0;
            for(int i=0;i<cities.length;i++) {
                if(cities[i].equals(dest)) {
                    flightTimesPos = i;
                }
            }
            int cap = in.nextInt();
            Aircraft air = findAircraft(cap);
            Flight flight = new Flight(flightNum, airline, dest, departure, flightTimes[flightTimesPos], air, cap);
            flights.add(flight);		
        }
    }
  	/*
  	// Populate the list of flights with some random test flights
  	String flightNum = generateFlightNumber("United Airlines");
  	Flight flight = new Flight(flightNum, "United Airlines", "Dallas", "1400", flightTimes[DALLAS], airplanes.get(0));
  	flights.add(flight);
  	
  	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "London", "2300", flightTimes[LONDON], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "Paris", "2200", flightTimes[PARIS], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Porter Airlines");
   	flight = new Flight(flightNum, "Porter Airlines", "New York", "1200", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "New York", "0900", flightTimes[NEWYORK], airplanes.get(3));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "New York", "0600", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
     	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "Paris", "2330", flightTimes[PARIS], airplanes.get(0));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new LongHaulFlight(flightNum, "Air Canada", "Tokyo", "2200", flightTimes[TOKYO], airplanes.get(4));
   	flights.add(flight);
   	*/
  }
  
  private String generateFlightNumber(String airline)
  {
  	String word1, word2;
  	try (Scanner scanner = new Scanner(airline)) {
  	  word1 = scanner.next();
  	  word2 = scanner.next();
  	  String letter1 = word1.substring(0, 1).toUpperCase();
  	  String letter2 = word2.substring(0, 1).toUpperCase();
  	  
  	  // Generate random number between 101 and 300
  	  int flight = random.nextInt(200) + 101;
  	  String flightNum = letter1 + letter2 + flight;
  	  
  	  // Check for duplicate flight numbers
  	  while (flightNumbers.contains(flightNum)) {
  	    flight = random.nextInt(200) + 101;
  	    flightNum = letter1 + letter2 + flight;
  	  }
  	  
  	  flightNumbers.add(flightNum);
  	  return flightNum;
  	}
  }

  public String getErrorMessage()
  {
  	return errMsg;
  }
  
  public void printAllFlights()
  {
  	for (Flight f : flights)
  		System.out.println(f);
  }
  
  /*public boolean seatsAvailable(String flightNum)
  {
    int index = flights.indexOf(new Flight(flightNum));
    if (index == -1)
  	{
  		errMsg = "Flight " + flightNum + " Not Found";
  		return false;
  	}
   	return flights.get(index).seatsAvailable(seatType);
  }*/
  public String seatsAvailable(String flightNum) {
	  String seats="";
	  int index = flights.indexOf(new Flight(flightNum));
	  if (index == -1)
	  {
	  	errMsg = "Flight " + flightNum + " Not Found";
	  	return null;
	  }
	  //flights.get(index).printSeatMap();
	  for (int j=0;j<4;j++) {
		  for (int i=0;i<flights.get(index).cap/4;i++) {
			  char test = (char)(j+65);
			  seats+= i + 1;
			  seats+=test +" ";
		  }
		  seats+="\n";
	  }
	  return seats;
  }
  
  public Reservation reserveSeatOnFlight(String flightNum, String name, String passport, String seatType)
  {
  	int index = flights.indexOf(new Flight(flightNum));
  	if (index == -1)
  	{
  		errMsg = "Flight " + flightNum + " Not Found";
  		return null;
  	}
  	Flight flight = flights.get(index);
  	
  	String seat = flight.getLastAssignedSeat();
  	return new Reservation(flightNum, flight.toString(), name, passport, seat, seatType);
  }
  
  public boolean cancelReservation(Reservation res) throws Flight.PassengerNotInManifestException, Flight.SeatTypeInvalidException
  {
    int index = flights.indexOf(new Flight(res.getFlightNum()));
  	if (index == -1)
    {
		  errMsg = "Flight " + res.getFlightNum() + " Not Found";
		  return false;
		}
  	Flight flight = flights.get(index);
  	flight.cancelSeat(res.name, res.passport, res.seatType);
   	return true;
  }
  
    
  public void sortByDeparture()
  {
	  Collections.sort(flights, new DepartureTimeComparator());
  }
  
  private class DepartureTimeComparator implements Comparator<Flight>
  {
  	public int compare(Flight a, Flight b)
  	{
  	  return a.getDepartureTime().compareTo(b.getDepartureTime());	  
  	}
  }
  
  public void sortByDuration()
  {
	  Collections.sort(flights, new DurationComparator());
  }
  
  private class DurationComparator implements Comparator<Flight>
  {
  	public int compare(Flight a, Flight b)
  	{
  	  return a.getFlightDuration() - b.getFlightDuration();
   	}
  }
  
  public void printAllAircraft()
  {
  	for (Aircraft craft : airplanes)
  		craft.print();
   }
  
  public void sortAircraft()
  {
  	Collections.sort(airplanes);
  }
  public Flight getFlight(String num) {
	  for(int i=0;i<flights.size();i++) {
		  if(flights.get(i).getFlightNum().equals(num)) {
			  return flights.get(i);
		  }
	  }
	return null;
  }
  public String space(String s) {
	  return s.substring(0,s.indexOf('_')) +" "+s.substring(s.indexOf('_')+1);
  }
  public Aircraft findAircraft(int a) {
	  int diff=airplanes.get(0).getTotalSeats()-a;
	  int pos=0;
	  for(int i=1;i<airplanes.size();i++) {
		  if (diff<0 && airplanes.get(i).getTotalSeats()-a>=0) {//if diff is negative change it to be positive
			  diff=airplanes.get(i).getTotalSeats()-a;
			  pos=i;
		  }else if(diff>airplanes.get(i).getTotalSeats()-a && airplanes.get(i).getTotalSeats()-a>=0) {//if diff is positive try to get the smallest diff to return the best airplane
			  diff=airplanes.get(i).getTotalSeats()-a;
			  pos=i;
		  }
	  }
	return airplanes.get(pos);  
  }
}