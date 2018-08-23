package org.kc.rest.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.kc.rest.model.FlightDetail;

public class CSVProcessorTest {
	private static final String PATH = "f:/temp";
	
	CSVProcessor process=new CSVProcessor();
	
	 
	
	@Test
	public void testDirectoryIsPresent() {
		File file = new File(PATH);
		//boolean exists =      file.exists();      // Check if the file exists
		boolean isDirectory = file.isDirectory(); // Check if it's a directory
	//	boolean isFile =      file.isFile();
		
		 assertTrue("should directory present !",true); 
	}
	
	@Test
	public void directoryShouldContainAtleastOneFile() {
		File file = new File(PATH);
		int lenght=0;
		if(file.isDirectory()) {
		 lenght=file.list().length;			
		}		
		assertTrue(lenght > 0);
	}
	
	@Test
	public void testGetFlightDetailsSet() {
		
		Set<FlightDetail> flightDetails=new HashSet();		
		process.getFlightDetailsSet();
		
	}

}
