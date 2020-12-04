package com.vermeg.ams.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vermeg.ams.entities.User;
import com.vermeg.ams.repositories.UserRepository;

@Controller
@RequestMapping("/client/")
public class UserController {
	private UserRepository userRepository;
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("list")
	public String listclients(Model model) {
		List<User> liste = (List<User>) userRepository.findAll();
		if (liste.size() == 0)
			liste = null;
		model.addAttribute("clients", liste);
		return "client/listClients";
	}

	@GetMapping("add")
	public String showaddform(Model model) {
		User client = new User();
		model.addAttribute("client", client);
		return "client/addclient";
	}

	@GetMapping("show/{id}")
	public String ShowclientDetails(@PathVariable("id") int id, Model m) {
		User b = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid client id" + id));
		m.addAttribute("client", b);
		return "client/showclient";
	}

	@PostMapping("add")
	public String addclient(@Valid User client, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "client/addclient";
		}

		userRepository.save(client);
		return "redirect:list";

	}

	@GetMapping("delete/{id}")
	public String deleteclient(@PathVariable("id") int id, Model model) {

		User client = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
		userRepository.delete(client);

		return "redirect:../list";
	}

	@GetMapping("edit/{id}")
	public String showclientFormToUpdate(@PathVariable("id") int id, Model model) {
		User client = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
		model.addAttribute("client", client);
		return "client/updateclient";
	}

	@PostMapping("update")
	public String updateclient(@Valid User client, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "client/updateclient";
		}

		userRepository.save(client);
		return "redirect:list";
	}

	@GetMapping("enable/{id}")
	// @ResponseBody
	public String enableUserAcount(@PathVariable("id") int id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
		user.setActive(1);
		userRepository.save(user);
		return "redirect:../list";
	}

	@GetMapping("disable/{id}")
	// @ResponseBody
	public String disableUserAcount(@PathVariable("id") int id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
		user.setActive(0);
		userRepository.save(user);
		return "redirect:../list";
	}
	
	
}
