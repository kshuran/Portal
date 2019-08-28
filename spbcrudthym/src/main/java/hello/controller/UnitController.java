package hello.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.dao.UnitRepository;
import hello.entity.Unit;

@Controller
@RequestMapping("/manager/units")
public class UnitController {

	@Autowired
	private UnitRepository unitRepo;
	
	@GetMapping
	public String retrieveAllUnits(Model model) {
		model.addAttribute("units", unitRepo.findAll());
		return "units";
	}
	
	@GetMapping("/{id}")
	public String showPersonsInUnit(@PathVariable("id") int id,
			Model model) {
		Unit unit = unitRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid unit ID: " + id));
		model.addAttribute("unit", unit);
		return "unitpersons";
	}
	
	@GetMapping("/addUnit")
	public String showAddUnitForm(Unit unit) {
		return "add-unit";
	}
	
	@PostMapping("/addUnit")
	public String addUnit(@Valid Unit unit, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-unit";
		}
		unitRepo.save(unit);
		return "redirect:/manager/units";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateUnitForm(@PathVariable("id") int id, Model model) {
		Unit unit = unitRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid unit ID: " + id));
		model.addAttribute("unit", unit);
		return "update-unit";
	}
	
	@PostMapping("/update/{id}")
	public String updateUnit(@PathVariable("id") int id, @Valid Unit unit,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "update-unit";
		}
		unitRepo.save(unit);
		return "redirect:/manager/units";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUnit(@PathVariable("id") int id, Model model) {
		Unit unit = unitRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid unit ID: " + id));
		unitRepo.delete(unit);
		return "redirect:/manager/units";
	}
	
}
