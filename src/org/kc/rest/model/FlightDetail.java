package org.kc.rest.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

public class FlightDetail implements Comparable<FlightDetail> {
	
	String origin;
	
	Date departureTime = new Date();
	String destination;
	Date destinationTime = new Date();
	BigDecimal fair;
	
	public FlightDetail(String origin, Date departureTime, String destination, Date destinationTime, BigDecimal fair) {
		super();
		this.origin = origin;
		this.departureTime = departureTime;
		this.destination = destination;
		this.destinationTime = destinationTime;
		this.fair = fair;
	}

public FlightDetail(String origin2, String destination2 ) {
	super();
	this.origin = origin2;
	 
	this.destination = destination2;
	 
	}

public String getOrigin() {
	return origin;
}
public void setOrigin(String origin) {
	this.origin = origin;
}
public Date getDepartureTime() {
	return departureTime;
}
public void setDepartureTime(Date departureTime) {
	this.departureTime = departureTime;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public Date getDestinationTime() {
	return destinationTime;
}
public void setDestinationTime(Date destinationTime) {
	this.destinationTime = destinationTime;
}
public BigDecimal getFair() {
	return fair;
}
public void setFair(BigDecimal fair) {
	this.fair = fair;
}

@Override
public int compareTo(FlightDetail o) {
	  return Comparator.comparing(FlightDetail::getFair)
              .thenComparing(FlightDetail::getDepartureTime)              
              .compare(this, o);
}

 


}
