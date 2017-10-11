package com.jadendong.web.UserController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jadendong.dto.User;
import com.jadendong.dto.UserQueryCondition;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@PostMapping
	public User create(@Valid @RequestBody User user,BindingResult errors) {
		
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		return user;
	}

	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user,BindingResult errors) {
		
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error-> {
				
				FieldError fieldError=(FieldError)error;
				String message=fieldError.getField()+" "+error.getDefaultMessage();
				
				System.out.println(message);
			});
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId(1);
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}
	
	@GetMapping
	@JsonView(User.UserSimpleView.class)	public List<User> query(UserQueryCondition condition,
							@PageableDefault(page=2,size=10,sort="age,desc") Pageable pageable){
		
		System.out.println(ReflectionToStringBuilder.toString(condition,ToStringStyle.MULTI_LINE_STYLE));
		
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		
		List<User> users=new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		
		return users;
	}
	
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable String id) {
		
		System.out.println(id);
		User user=new User();
		user.setUsername("tom");
		return user;
		
	}
}