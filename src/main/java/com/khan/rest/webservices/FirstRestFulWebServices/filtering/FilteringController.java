package com.khan.rest.webservices.FirstRestFulWebServices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	//********************** STATIC FILTERING***********//
	@GetMapping("/filtering")
	public SomeBean filtering() {
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));
	}
	//********************** STATIC FILTERING***********//
	
	//********************** DYNAMIC FILTERING***********//
	@GetMapping("/Dyfiltering")
	public MappingJacksonValue dynamicFiltering() {
		SomeBean someBean =  new SomeBean("val1", "val2", "val3");
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	@GetMapping("/Dyfiltering-list")
	public MappingJacksonValue DynamicfilteringList() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), 
				new SomeBean("value4", "value5", "value6"));
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}

	//********************** DYNAMIC FILTERING***********//
}
