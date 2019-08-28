package hello.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.dao.LdapUserRepository;
import hello.dao.PersonRepository;
import hello.dao.UnitRepository;
import hello.entity.Person;

@Controller
@RequestMapping("/manager/persons")
public class PersonController {

	@Autowired
	private UnitRepository unitRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private LdapUserRepository ldapUserRepo;
	
	@GetMapping
	public String retrieveAllPersons(Model model) {
		model.addAttribute("persons", personRepo.findAll());
		return "persons";
	}
	
	@GetMapping("/addPerson")
	public String showAddPersonForm(@RequestHeader("referer") String referer,
			Model model, Person person) {
		model.addAttribute("prevUri", referer);
		model.addAttribute("ldapPersons", ldapUserRepo.getAllPersonName());
		model.addAttribute("units", unitRepo.findAll());
		return "add-person";
	}
	
	@PostMapping("/addPerson")
	public String addPerson(@RequestParam("prevUri") String prevUri,
			@Valid Person person, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-person";
		}
		personRepo.save(person);
		if (prevUri.contains("/units")) {
			int unitId = Integer.valueOf(prevUri.substring(prevUri.lastIndexOf("/") + 1));
			return "redirect:/manager/units/" + unitId;
		}
		return "redirect:/manager/persons";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdatePersonForm(@PathVariable("id") int id, Model model) {
		Person person = personRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid person ID: " + id));
		model.addAttribute("person", person);
		model.addAttribute("ldapPersons", ldapUserRepo.getAllPersonName());
		model.addAttribute("units", unitRepo.findAll());
		return "update-person";
	}
	
	@PostMapping("/update/{id}")
	public String updatePerson(@PathVariable("id") int id, @Valid Person person,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "update-person";
		}
		personRepo.save(person);
		return "redirect:/manager/persons";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePerson(@PathVariable("id") int id, Model model) {
		Person person = personRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid person ID: " + id));
		personRepo.delete(person);
		return "redirect:/manager/persons";
	}
	
}
