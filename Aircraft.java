//Sarmilan Sreekaran
//500758148
import java.util.Comparator;
/*
 * 
 * This class models an aircraft type with a model name, a maximum number of economy seats, and a max number of first class seats 
 * 
 * Add code such that class Aircraft implements the Comparable interface
 * Compare two Aircraft objects by first comparing the number of economy seats. If the number is equal, then compare the
 * number of first class seats 
 */
public class Aircraft  implements Comparable<Aircraft>
{
  int numEconomySeats;
  int numFirstClassSeats;
  
  String model;
  
  public Aircraft(int seats, String model)
  {
  	this.numEconomySeats = seats;
  	this.numFirstClassSeats = 0;
  	this.model = model;
  }

  public Aircraft(int economy, int firstClass, String model)
  {
  	this.numEconomySeats = economy;
  	this.numFirstClassSeats = firstClass;
  	this.model = model;
  }
  
	public int getNumSeats()
	{
		return numEconomySeats;
	}
	
	public int getTotalSeats()
	{
		return numEconomySeats + numFirstClassSeats;
	}
	
	public int getNumFirstClassSeats()
	{
		return numFirstClassSeats;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void print()
	{
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

	/*
	 * Write a compareTo method that is part of the Comparable interface
	 */
	public int compareTo(Aircraft o2){
			if (this.getNumSeats()==o2.getNumSeats()) {//f the numseats for this Aircraft is the same as the other
				if (this.getNumFirstClassSeats()>o2.getNumFirstClassSeats())//if the numfirstclassseats are greater than the other
					return this.getNumFirstClassSeats();//returns positive value
				else if(this.getNumFirstClassSeats()<o2.getNumFirstClassSeats())//if the other is greater
					return -o2.getNumFirstClassSeats();//return negative value
				else
					return 0;//this should not be reached but if it does return 0
			}
			else if (this.getNumSeats()>o2.getNumSeats())//if this numseats is greater that the other
				return this.getNumSeats();//return positive value
			else
				return -o2.getNumSeats();//return negative value
	}
}