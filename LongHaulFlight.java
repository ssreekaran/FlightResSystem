//Sarmilan Sreekaran
//500758148
/*
 * A long haul flight is a flight that travels thousands of kilometers and typically has separate seating areas 
 */
public class LongHaulFlight extends Flight
{
	int numFirstClassPassengers;
	String seatType;
	
	// Possible seat types
	public static final String firstClass = "First Class Seat";
	public static final String economy 		= "Economy Seat";  
	

	public LongHaulFlight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		super(flightNum, airline, dest, departure, flightDuration, aircraft);
	}

	public LongHaulFlight()
	{
     	super.flightNum = "";
     	super.airline = "";
     	super.dest = "";
     	super.departureTime = "";
     	super.flightDuration = 0;
     	super.aircraft = null;
     	this.numFirstClassPassengers=0;
     	this.seatType = firstClass;
	}
	
	/*
	 * Reserves a seat on a flight. Essentially just increases the number of (economy) passengers
	 */
	public boolean reserveSeat()
	{
		// override the inherited reserveSeat method and call the reserveSeat method below with an economy seatType
		// use the constants defined at the top
		return reserveSeat(economy);
	}

	/*
	 * Reserves a seat on a flight. Essentially just increases the number of passengers, depending on seat type (economy or first class)
	 */
	public boolean reserveSeat(String seatType)
	{
		// if seat type is economy 
		//			call the superclass method reserveSeat() and return the result
		// else if the seat type is first class then 
		// 			check to see if there are more first class seats available (use the aircraft method to get the max first class seats
		// 			of this airplane
		//    	if there is a seat available, increment first class passenger count (see instance variable at the top of the class)
		//    	return true;
		// else return false
		if(seatType.equals(economy)) {
			return super.reserveSeat();//uses Flights reserveSeat
		}
		else if(seatType.equals(firstClass)) {
			if(this.numFirstClassPassengers<super.aircraft.getNumFirstClassSeats()) {//checks if there is enough space in first class
				numFirstClassPassengers++;
				return true;
			}
		}
		return false;
	}
	
	// Cancel a seat 
	public void cancelSeat()
	{
	  // override the inherited cancelSeat method and call the cancelSeat method below with an economy seatType
		// use the constants defined at the top
		cancelSeat(economy);
	}
	
	public void cancelSeat(String seatType)
	{
		// if seat type is first class and first class passenger count is > 0
		//  decrement first class passengers
		// else
		// decrement inherited (economy) passenger count
		if(seatType.equals(firstClass)) {
			if(numFirstClassPassengers>0) 
				numFirstClassPassengers--;//cancels first class
			else
				super.passengers--;//cancels economy class
		}
		
	}
	// return the total passenger count of economy passengers *and* first class passengers
	// use instance variable at top and inherited method that returns economy passenger count
	public int getPassengerCount()
	{
		return super.getPassengers()+this.numFirstClassPassengers;//returns total passenger count
	}
}