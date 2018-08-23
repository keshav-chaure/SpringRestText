package org.kc.rest.utils;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.kc.rest.model.FlightDetail;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReaderBuilder;

@Component
public class CSVProcessor {

	private Set<FlightDetail> flights1 = new TreeSet<>();
	private static final String PATH = "f:/temp";

	public Set<FlightDetail> getFlightDetailsSet() {
		String dirPath = PATH;
		File dir = new File(dirPath);
		if (dir.isDirectory()) {

			String[] files = dir.list();
			if (files.length == 0) {
				System.out.println("The directory is empty");
			} else {
				for (String aFile : files) {
					readFile(PATH + "/" + aFile);
				}
			}
		}
		return flights1;
	}

	private void readFile(String fileName) {

		try {
			System.out.println(fileName);
			FileReader filereader = new FileReader(fileName);
			com.opencsv.CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll();
			FlightDetail fd;
			for (String[] row : allData) {
				fd = createFlightDetail(row);
				flights1.add(fd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FlightDetail createFlightDetail(String[] row) {
		FlightDetail fd;
		String departureDateString = row[1];
		String destinationDateString = row[3];
		boolean isNumeric = row[4].chars().allMatch(Character::isDigit);		
		BigDecimal fair = new BigDecimal(row[4].substring(1).toString());
		String origin = row[0];
		String destination = row[2];
		org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
		DateTime departureDate = formatter.parseDateTime(departureDateString);
		DateTime destinationDate = formatter.parseDateTime(destinationDateString);
		fd = new FlightDetail(origin, destination, departureDate, destinationDate, fair);
		return fd;
	}

}
