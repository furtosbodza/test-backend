package hu.partstore.controller;

import hu.partstore.model.dto.AppUserDto;
import hu.partstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/item/api")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/user/login")
	public ResponseEntity<AppUserDto> loginUser(@RequestBody AppUserDto userDto) {
		AppUserDto user = authService.loginUser(userDto);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/user/regist")
	public ResponseEntity<AppUserDto> registerUser(@RequestBody AppUserDto userDto) {
		return new ResponseEntity<>(authService.registerUser(userDto),
			HttpStatus.OK);
	}


}
