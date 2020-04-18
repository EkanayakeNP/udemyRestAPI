package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@Autowired
	private MessageSource messageSource; //for internationalization
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/user/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		
		User user =  service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id -"+id);
		
		//this is using to add all user link to response
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all_users"));
		 return resource;
	}

	@PostMapping("users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) { //@valid for validate requested data
		User savedUser = service.save(user);

		//for return created  url 
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		
		/*User user =  service.deleteById(id);
		if(user==null)
			throw new UserNotFoundException("id -"+id);*/
		
		if(!service.deleteById(id)){
			throw new UserNotFoundException("id -"+id);
		} 
		
	}
	
	//when configure this from property file this section don't need
	/*@GetMapping(path="/hellow-world-internationalized")
	public String HelloworldInternationalize(@RequestHeader(name="Accept-Language" ,required=false)Locale locale){
		return messageSource.getMessage("good.morning.message", null,locale);
				
	}*/
	
	@GetMapping(path="/hellow-world-internationalized")
	public String HelloworldInternationalize(){
		return messageSource.getMessage("good.morning.message", null,LocaleContextHolder.getLocale());
				
	}
}
