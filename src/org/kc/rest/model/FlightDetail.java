package org.kc.rest.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import org.joda.time.DateTime;

public class FlightDetail implements Comparable<FlightDetail> {

	String origin;
	DateTime departureTime;
	String destination;
	DateTime destinationTime;
	BigDecimal fair;

 

	public FlightDetail(String origin2, String destination2,DateTime departureTime2,DateTime destinationDate, BigDecimal fair2) {
		super();
		this.origin = origin2;
		this.destination = destination2;
		this.fair = fair2;
		this.departureTime=departureTime2;
		this.destinationTime=destinationDate;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public DateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(DateTime departureTime) {
		this.departureTime = departureTime;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public DateTime getDestinationTime() {
		return destinationTime;
	}

	public void setDestinationTime(DateTime destinationTime) {
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
				.compare(this,o);
	}

}
