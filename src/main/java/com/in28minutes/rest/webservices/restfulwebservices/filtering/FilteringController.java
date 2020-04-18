package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	/*
	 * for static filtering
	 * 
	 * @GetMapping("/filtering") public SomeBean retrieveSomeBean() { SomeBean
	 * someBean = new SomeBean("1", "2", "3");
	 * 
	 * return someBean; }
	 * 
	 * @GetMapping("/filtering-list") public List<SomeBean>
	 * retrieveSomeBeanList() { List<SomeBean> someBeanList = new
	 * ArrayList<SomeBean>(); someBeanList.add(new SomeBean("1", "2", "3"));
	 * someBeanList.add(new SomeBean("11", "22", "33"));
	 * 
	 * return someBeanList; }
	 */

	// -------------- DYNAMIC FILTERING ----------------
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("1", "2", "3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveSomeBeanList() {
		List<SomeBean> someBeanList = new ArrayList<SomeBean>();
		someBeanList.add(new SomeBean("1", "2", "3"));
		someBeanList.add(new SomeBean("11", "22", "33"));

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBeanList);
		mapping.setFilters(filters);
		
		
		return mapping;
	}
}
