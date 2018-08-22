package org.kc.rest.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.kc.rest.dao.CustomerDAO;
import org.kc.rest.model.FlightDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerDAO customerDAO; 

	@GetMapping(value = "/flights/{org}/{dest}")
	public String getFlights(@PathVariable(value = "org") Optional<String> org,
			@PathVariable(value = "dest") Optional<String> dest, HttpServletResponse response)
			throws JsonProcessingException {

		Set<FlightDetail> flights = null;
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		String serialized = null;
		if (org.isPresent() && dest.isPresent()) {
			flights = customerDAO.getFlights(org, dest);
		} else {
			serialized = objectMapper.writeValueAsString("Missing Origin or Destination");
		}

		if (flights.isEmpty()) {
			serialized = "No Flight found for "+org.get()+"-->"+dest.get();
		} else {
			module.addSerializer(FlightDetail.class, new FlightDetailSerializer());
			objectMapper.registerModule(module);

			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			serialized = objectMapper.writeValueAsString(flights).toString().replace("[", "").replace("]", "");
		}

		return serialized;
	}

 
 
	 

	 

}