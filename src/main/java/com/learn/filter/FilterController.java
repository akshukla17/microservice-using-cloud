package com.learn.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
/**
 * purpose of this controller to do static and dynamic filter of the response attribute
 * @author raghav
 *
 */
@RestController
public class FilterController {

	@GetMapping("/single-filter")
	// allow here only id and name  (filtering at run time) dynamic filtering
	public MappingJacksonValue singleFilter() {
		FilterBean bean = new FilterBean(1, "filter1", "description 1");
		return excludeBeanProperties(new ArrayList<>(Arrays.asList(bean)), "id", "name");
	}

	private MappingJacksonValue excludeBeanProperties(List<FilterBean> bean, String prop1, String prop2) {
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(prop1, prop2);
		FilterProvider filters = new SimpleFilterProvider().addFilter("BeanFilter", filter );
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("filter-list")
	// allow here name  and description (filtering at run time)
	public MappingJacksonValue listOfFilter(){
		List<FilterBean> list =  new ArrayList<FilterBean>(Arrays.asList(new FilterBean(1, "filter1", "description1"), 
										new FilterBean(2, "filter2", "description2")));
		return excludeBeanProperties(list, "name", "description");
		
	}
}
