package com.example.ams.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.ams.entities.commande;
import com.example.ams.repositories.commandeRepository;
import java.util.List;
import javax.validation.Valid;
@Controller
@RequestMapping("/commande/")

public class commandeController {
	private final commandeRepository commandeRepository ;
	@Autowired
	public commandeController(commandeRepository commandeRepository ) {
	this.commandeRepository  = commandeRepository ;
	}
	@GetMapping("list")
	//@ResponseBody
	public String listcommandes(Model model) {
		List<commande> com = (List<commande>) commandeRepository.findAll();
		if (com.size() == 0)
		com = null;
		model.addAttribute("commandes", com);
		return "commande/commande";
		// System.out.println(lp);
		// return "Nombre de fournisseur = " + lp.size();
		}
	@GetMapping("add")
	public String showAddcommandeForm(Model model) {
		commande commande = new commande();// object dont la valeur des attributs par defaut
	model.addAttribute("commande", commande);
	return "commande/addcommande";
	}
	
	@PostMapping("add")
	public String addProvider(@Valid commande commande, BindingResult result, Model
	model) {
	if (result.hasErrors()) {
	return "commande/addcommande";
	}
	commandeRepository.save(commande);
	return "redirect:list";
	}
	
	
	
	@GetMapping("delete/{id}")
	public String deletecommande(@PathVariable("id") long id, Model model) {
	// long id2 = 100L;
		commande commande =commandeRepository.findById(id)
	.orElseThrow(() -> new IllegalArgumentException("Invalid commande Id:" + id));
	System.out.println("suite du programme...");
	commandeRepository.delete(commande);
	/*
	* model.addAttribute("providers", providerRepository.findAll()); return
	* "provider/listProviders";
	*/
	return "redirect:../list";
	}
	@GetMapping("edit/{id}")
	public String showcommandeFormToUpdate(@PathVariable("id") long id, Model model)
	{
		commande commande = commandeRepository.findById(id)
	.orElseThrow(() -> new IllegalArgumentException("Invalid commande Id:" + id));
	
	model.addAttribute("commande", commande);
	return "commande/updatecommande";
	}
	@PostMapping("update")
	public String updatecommande(@Valid commande commande, BindingResult result, Model
	model) {
	if (result.hasErrors()) {
	return "commande/updatecommande";
	}
	commandeRepository.save(commande);
	return "redirect:list";
	}

	
}
