//Sarmilan Sreekaran
//500758148
/*
 * A simple class to model an electronic airline flight reservation
 * 
 * This class has been done for you
 */
public class Reservation
{
	String flightNum;
	String flightInfo;
	boolean firstClass;
	
	
	public Reservation(String flightNum, String info)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.firstClass = false;
	}
	
	public boolean isFirstClass()
	{
		return firstClass;
	}

	public void setFirstClass()
	{
		this.firstClass = true;
	}

	public String getFlightNum()
	{
		return flightNum;
	}
	
	public String getFlightInfo()
	{
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo)
	{
		this.flightInfo = flightInfo;
	}

	public void print()
	{
		System.out.println(flightInfo);
	}
}