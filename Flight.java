import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Flight
{
	public enum Status {DELAYED, ONTIME, ARRIVED, INFLIGHT};
	public static enum Type {SHORTHAUL, MEDIUMHAUL, LONGHAUL};
	public static enum SeatType {ECONOMY, FIRSTCLASS, BUSINESS};

	private String flightNum;
	private String airline;
	private String origin, dest;
	private String departureTime;
	private Status status;
	private int flightDuration;
	protected Aircraft aircraft;
	protected int numPassengers;
	protected Type type;
	protected ArrayList<Passenger> manifest;
	protected TreeMap<String, Passenger> seatMap = new TreeMap<String, Passenger>();
	int cap;
	protected Random random = new Random();
	
	private String errorMessage = "";
	  
	public String getErrorMessage()
	{
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public Flight()
	{
		this.flightNum = "";
		this.airline = "";
		this.dest = "";
		this.origin = "Toronto";
		this.departureTime = "";
		this.flightDuration = 0;
		this.aircraft = null;
		numPassengers = 0;
		status = Status.ONTIME;
		type = Type.MEDIUMHAUL;
		manifest = new ArrayList<Passenger>();
		
	}
	
	public Flight(String flightNum)
	{
		this.flightNum = flightNum;
	}
	
	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft, int cap)
	{
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = aircraft;
		numPassengers = 0;
		status = Status.ONTIME;
		type = Type.MEDIUMHAUL;
		manifest = new ArrayList<Passenger>();
		this.seatMap = fillSeats(cap);
		this.cap=cap;
	}
	
	public Type getFlightType()
	{
		return type;
	}
	
	public String getFlightNum()
	{
		return flightNum;
	}
	public void setFlightNum(String flightNum)
	{
		this.flightNum = flightNum;
	}
	public String getAirline()
	{
		return airline;
	}
	public void setAirline(String airline)
	{
		this.airline = airline;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public String getDest()
	{
		return dest;
	}
	public void setDest(String dest)
	{
		this.dest = dest;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	public int getFlightDuration()
	{
		return flightDuration;
	}
	public void setFlightDuration(int dur)
	{
		this.flightDuration = dur;
	}
	
	public int getNumPassengers()
	{
		return numPassengers;
	}
	public void setNumPassengers(int numPassengers)
	{
		this.numPassengers = numPassengers;
	}
	
	public void assignSeat(Passenger p)
	{
		int seat = random.nextInt(aircraft.numEconomySeats);
		p.setSeat("ECO"+ seat);
	}
	
	public String getLastAssignedSeat()
	{
		if (!manifest.isEmpty())
			return manifest.get(manifest.size()-1).getSeat();
		return "";
	}
	
	public boolean seatsAvailable(String seatType)
	{
		if (!seatType.equalsIgnoreCase("ECO")) return false;
		return numPassengers < aircraft.numEconomySeats;
	}
	
	public void cancelSeat(String name, String passport, String seatType) throws Flight.PassengerNotInManifestException, Flight.SeatTypeInvalidException
	{
		if (!seatType.equalsIgnoreCase("ECO")) 
		{
			throw new SeatTypeInvalidException("Flight " + flightNum + " Invalid Seat Type Request");
		}

		Passenger p = new Passenger(name, passport);
		
		if (manifest.indexOf(p) == -1) 
		{
			throw new PassengerNotInManifestException("Passenger " + name + " " + passport + " Not Found");														
		}

		manifest.remove(p);
		if (numPassengers > 0) numPassengers--;
	}
	
	public void reserveSeat(String name, String passport, String seatType) throws Flight.DuplicatePassengerException, Flight.SeatTypeInvalidException, Flight.FlightFullException
	{
		if (numPassengers >= aircraft.getNumSeats())
		{
			throw new FlightFullException("Flight " + flightNum + " Full");
		}
		if (!seatType.equalsIgnoreCase("ECO")) 
		{
			throw new SeatTypeInvalidException("Flight " + flightNum + " Invalid Seat Type Request");
		}	
		// Check for duplicate passenger
		Passenger p = new Passenger(name, passport, "", seatType);
	
		if (manifest.indexOf(p) >=  0)
		{
			throw new DuplicatePassengerException("Duplicate Passenger " + p.getName() + " " + p.getPassport());
		}
		assignSeat(p);
		manifest.add(p);
		numPassengers++;
	}
	
	public boolean equals(Object other)
	{
		Flight otherFlight = (Flight) other;
		return this.flightNum.equals(otherFlight.flightNum);
	}
	
	public String toString()
	{
		 return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status;
	}
	public TreeMap<String, Passenger> fillSeats(int cap) {
		String seat="";
		for (int j=0;j<4;j++) {
			  for (int i=0;i<cap/4;i++) {
				  char test = (char)(j+65);
				  seat= i+1+"";
				  seat+=test;
				  seatMap.put(seat, null);
			  }
		  }
		return seatMap;
	}
	public void printSeatMap() {
		Set<String> keys = seatMap.keySet();
		int counter = 0;
		for(String key: keys){
			/*counter++;
			if(counter>seatMap.size()/4) {
				System.out.println();
				counter=1;
			}*/
			System.out.print(key+" ");
		}
	}
	public void printPassengerManifest() {
		for(int i=0;i<manifest.size();i++) {
			System.out.println(manifest.get(i).getName()+" "+manifest.get(i).getPassport()+" "+manifest.get(i).getSeat());
		}
	}
	public class DuplicatePassengerException extends Exception {
	    public DuplicatePassengerException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	public class PassengerNotInManifestException extends Exception {
	    public PassengerNotInManifestException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	public class SeatTypeInvalidException extends Exception {
	    public SeatTypeInvalidException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	public class FlightFullException extends Exception {
	    public FlightFullException(String errorMessage) {
	        super(errorMessage);
	    }
	}
}