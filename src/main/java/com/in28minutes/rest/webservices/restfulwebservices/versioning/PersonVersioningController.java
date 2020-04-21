package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	/*@GetMapping("/v1/person")
	public PersonV1 persionV1(){
		return new PersonV1("np");
		
	}

	@GetMapping("/v2/person")
	public PersonV2 persionV2(){
		return new PersonV2(new Name("np","ekanayake"));
		
	}*/
	
	//url -> localhost:8085/persion/param?verion=1
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 persionV1(){
		return new PersonV1("np");
		
	}
	//url -> localhost:8085/persion/param?verion=2
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 persionV2(){
		return new PersonV2(new Name("np","ekanayake"));
		
	}
	//url -> localhost:8085/header
	//when calling this method from postmen, should use header key as X-API_VERSION and value as 1
	@GetMapping(value="/person/header",headers="X-API_VERSION=1")
	public PersonV1 headerV1(){
		return new PersonV1("np");
		
	}

	@GetMapping(value="/person/header",headers="X-API_VERSION=2")
	public PersonV2 headerV2(){
		return new PersonV2(new Name("np","ekanayake"));
		
	}
	//url -> localhost:8085/produces
	//when calling this method from postmen, should use header key as accept and value as application/vnd.company.app-v1+json
		@GetMapping(value="/person/produces",produces="application/vnd.company.app-v1+json")
		public PersonV1 producesV1(){
			return new PersonV1("np");
			
		}

		@GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
		public PersonV2 producesV2(){
			return new PersonV2(new Name("np","ekanayake"));
			
		}
		
}
