package org.kc.rest.dao;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.kc.rest.model.FlightDetail;
import org.kc.rest.utils.CSVProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReaderBuilder;

import sun.tools.jar.Main;

@Component
public class FlightDao {

	@Autowired
	private CSVProcessor csvProcessor;

	public Set<FlightDetail> listFlights() {
		return csvProcessor.getFlightDetailsSet();
	}

	public Set<FlightDetail> getFlights(Optional<String> org, Optional<String> dest) {
		Set<FlightDetail> flights = csvProcessor.getFlightDetailsSet();
		Set<FlightDetail> filteredflightDetailSet = new TreeSet<>();
		System.out.println("result " + flights);
		flights.forEach(f -> System.out.println(f.getOrigin() + " " + f.getDestination()));
		for (FlightDetail c : flights) {
			if (c.getOrigin().equals(org.get()) && c.getDestination().equals(dest.get())) {
				filteredflightDetailSet.add(c);
			}
		}
		return filteredflightDetailSet;
	}

}