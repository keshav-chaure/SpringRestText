package org.kc.rest.controller;

import java.io.IOException;

import org.kc.rest.model.FlightDetail;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class FlightDetailSerializer extends JsonSerializer<FlightDetail> {

	public void serialize(FlightDetail f, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {	 
        gen.writeRaw(f.getOrigin()+ "===>"+f.getDestination()+" ("+ f.getDepartureTime()+"===>"+f.getDepartureTime()+" ) - $"+f.getFair()+"\r\n");
        gen.writeRaw('\n');
         

	}

}
