package com.example.StudentDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.StudentDB.model.Student;
import com.example.StudentDB.service.StudentService;

@Controller
public class PruebaController {
	
	@Autowired
	private StudentService repositorio;
	
	
	@GetMapping({"/","/estudiantes"})
	public String Students(Model model) {
		model.addAttribute("estudiantes", repositorio.getStudents());
		return "index";
	}
	
	@GetMapping("/delete")
	public String deleteStudent(Model model,
			@RequestParam(name="id") Long id)
	{	
		Student student = repositorio.getStudent(id);
		model.addAttribute("studentDel", student);
		//repositorio.delete(student);
		return "/delete";
	}
	
	@PostMapping("/deleteSubmit")
	public String deleteSubmit(@ModelAttribute("studentDel") Student student) {
		repositorio.delete(student);
		return "redirect:/estudiantes";
	}
	
	@GetMapping("/add")	 
	public String addStudent(@Validated Model model, BindingResult bindingResult,
			@RequestParam(name="name", required=false, defaultValue="") String name,
			@RequestParam(name="surname", required=false, defaultValue="") String surname,
			@RequestParam(name="age", required=false, defaultValue="") int age
			) {
		if(bindingResult.hasErrors()) {
			return "add";
		}else {			
			Student student = new Student(name,surname,age);
			repositorio.add(student);
			return "redirect:/estudiantes";
		}
	}
	@GetMapping("/edit")
	public String editStudent (Model model,@RequestParam(name="id") Long id) {
		Student estudiante = repositorio.getStudent(id);
		return "edit";
		}

}
