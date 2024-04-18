package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.User;
import in.ashokit.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userservice;

	// login page - display
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}

	// login - button handle
	@PostMapping("/login")
	public String handleLogin(User user, Model model) {
		User userobj = userservice.getUser(user.getEmail(), user.getPwd());
		if (userobj == null) {
			model.addAttribute("emsg", "Invalid ");
			return "index";
		} else {
			model.addAttribute("msg", userobj.getName() + " ,Welcome to Ashok IT");
			return "dashboard";
		}
	}

	// register page display
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "registerView";
	}

	// register button handled

	@PostMapping("/register")
	public String handleRegister(User user, Model model) {
		boolean status = userservice.saveUser(user);
		if (status) {
			model.addAttribute("msg", "User Registered");
		} else {
			model.addAttribute("emsg", "Registration failed");
		}
		return "registerView";
	}

	// logout method
	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
}
