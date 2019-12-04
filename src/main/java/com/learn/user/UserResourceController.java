package com.learn.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learn.post.Post;

@RestController
public class UserResourceController {

	@Autowired
	private UserDAOService userDAOService;

	// findAll
	@GetMapping("/users")
	public List<User> findAllUser() {
		return userDAOService.findAll();
	}

	// Find user by id
	@GetMapping("/users/{id}")
	public User findByUserId(@PathVariable int id) {
		Optional<User> u = userDAOService.findOne(id);
		if (!u.isPresent())
			throw new UserNotFoundException("id : " + id + " not found");
		// implement HATEOAS to return link for all user along with the data
//		EntityModel<User> model = new EntityModel<>(u);
//		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAllUser());
//		model.add(linkTo.withRel("all-users")); 
		return u.get();
	}

	// Save new user
	// INPUT-- user
	// OUTPUT -- return created and newly created user URI
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) throws Exception {
		User savedUser = userDAOService.save(user);
		if (savedUser == null)
			throw new Exception("not able to save user");

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/users/{id}")
	public HeadersBuilder<BodyBuilder> deleteById(@PathVariable int id) throws Exception {
		try {
			userDAOService.deleteUser(id);
			return ResponseEntity.ok();
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("no user found with given id: " + id);
		}
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> allPostOfUserId(@PathVariable int id){
		Optional<User> u = userDAOService.allPostOfuser(id);
		if(!u.isPresent()) {
			throw new UserNotFoundException("no user found with given id: " + id);
		}
		List<Post> posts = u.get().getPosts();
		
		return posts;
	}
}
