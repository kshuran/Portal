package hello.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import hello.dao.ProblemRepository;
import hello.entity.Problem;
import hello.entity.Product;

@Controller
public class ProblemController {
	
	@Autowired
	private ProblemRepository problemRepository;
	
	@GetMapping({"/", "/index", "/home"})
	public String index() {
        return "index";
	}
	
	@GetMapping("/user")
	public String retrieveAllByPerson(Model model, Principal principal) {		
		model.addAttribute("problems", problemRepository
				.findByPersonOrderByProblemDateTimeDesc(principal.getName()));
        return "user";
	}
     
	@GetMapping("/user/{product}")
	public String retrieveAllByPersonAndProduct(@PathVariable Product product, Model model, Principal principal) {
		model.addAttribute("problems", problemRepository
				.findByProductOrderByProblemDateTimeDesc(product));
        return "product";
	}
	
    @GetMapping("/user/{product}/add")
    public String showSignUpForm(@RequestHeader("referer") String referer, Model model,
    		@PathVariable Product product, Problem problem) {
    	model.addAttribute("prevUri", referer);
    	return "add-problem";
    }
     
    @PostMapping("/user/{product}/add")
    public String addProblem( 
    						@RequestParam("prevUri") String prevUri,
    						@PathVariable Product product, 
    						@Valid Problem problem, 
    						BindingResult result, 
    						Model model, 
    						Principal principal) {
        if (result.hasErrors()) {
            return "add-problem";
        }

        problem.setPerson(principal.getName());
        problemRepository.save(problem);
        
        System.out.println("Ref: " + prevUri);
        return "redirect:/user/{product}";
    }
}
