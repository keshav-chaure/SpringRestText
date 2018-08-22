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

import org.kc.rest.model.FlightDetail;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReaderBuilder;

import sun.tools.jar.Main;

@Component
public class CustomerDAO {

	private static List<FlightDetail> flights;
	private static Set<FlightDetail> flights1;

	{
		flights = new ArrayList();
		String sDate5 = "Thu, Dec 31 1998 23:37:50";
		SimpleDateFormat formatter5 = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
		Date date5 = null;
		try {
			date5 = formatter5.parse(sDate5);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flights.add(new FlightDetail("LAS", date5, "LAX", date5, new BigDecimal(135.69)));
		flights.add(new FlightDetail("LAS", date5, "LAX", date5, new BigDecimal(136.69)));
		flights.add(new FlightDetail("LAS", date5, "LAX", date5, new BigDecimal(137.69)));
		flights.add(new FlightDetail("NW", date5, "WW", date5, new BigDecimal(138.69)));
		flights.add(new FlightDetail("TKY", date5, "IND", date5, new BigDecimal(135.69)));
	}

	{
		flights1 = new HashSet<>();
		getFileList("/temp").forEach(fileName -> {			 
			readFile(fileName);
		});
		
	}

	private void readFile(String fileName) {
		
		try {

			File file = new File(getClass().getResource("/"+fileName).getFile());

			FileReader filereader = new FileReader(file);
			System.out.println("is file present : " + file.exists());
			// create csvReader object and skip first Line
			com.opencsv.CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll();

			FlightDetail fd;
			// print Data
			for (String[] row : allData) {				
				
				 String[] cells=row[0].split("\t");
				fd = prepareFlightDetails(cells);
				flights1.add(fd);
				//  System.out.println(cells[0] +" " +cells[1]+" " +cells[2]+" " +cells[3]+" "+cells[4]);
				
				// for (String cell : row) {
				// System.out.print(cell + "\t");
				// }
				// System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FlightDetail prepareFlightDetails(String[] cells) throws ParseException {
		FlightDetail fd;
		  System.out.println(cells[0]);
	//	String originDateString=cells[1];	
	//	String destinationDateString=cells[3];	
		
	//	boolean isNumeric = cells[4].chars().allMatch( Character::isDigit );
	//	BigDecimal fair=(isNumeric)?new BigDecimal(cells[4]):new BigDecimal(0);
		//BigDecimal fair=new BigDecimal(cells[4].substring(1).toString());
		String origin=cells[0];
		String destination=cells[2];				
		
	//	SimpleDateFormat formater=new SimpleDateFormat("mm/dd/yyyy hh:mm:ss"); 
	//	Date originDate=formater.parse(originDateString);
	//	Date destinationDate=formater.parse(destinationDateString);
		 
		
		//fd=new FlightDetail(origin,originDate,destination,destinationDate,fair);
		fd=new FlightDetail(origin, destination);
		return fd;
	}

	private List<String> getFileList(String path) {
		List<String> filenames = new LinkedList<String>();
		File folder = new File(getClass().getResource(path).getFile());
		for (final File fileEntry : folder.listFiles()) {			 
				if (fileEntry.getName().contains(".csv"))
					filenames.add(fileEntry.getName());			 
		}
		return filenames;
	}

	public List listFlights() {		 
		return flights;
	}

	public Set<FlightDetail> getFlights(Optional<String> org, Optional<String> dest) {
		Set<FlightDetail> flightDetailSet = new HashSet<>();
		for (FlightDetail c : flights1) {
			if (c.getOrigin().equals(org.get()) && c.getDestination().equals(dest.get())) {
				flightDetailSet.add(c);
			}
		}
		return flightDetailSet;
	}

}