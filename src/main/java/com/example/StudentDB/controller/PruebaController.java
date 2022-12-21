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
	
	//Muestra la lista de estudiantes
	@GetMapping({"/","/estudiantes"})
	public String Students(Model model) {
		model.addAttribute("estudiantes", repositorio.getStudents());
		return "index";
	}
	
	//Coje el id del estudiante y recupera el objeto estudiante Y
	//Nos lleva a una pagina de confirmación para borrar al estudiante
	@GetMapping("/delete")
	public String deleteStudent(Model model,
			@RequestParam(name="id") Long id)
	{	
		Student student = repositorio.getStudent(id);
		model.addAttribute("studentDel", student);
		//repositorio.delete(student);
		return "delete";
	}
	
	//Nos borra el estudiante y nos lleva a la lista de estudiantes
	@PostMapping("/deleteSubmit")
	public String deleteSubmit(@ModelAttribute("studentDel") Student student) {
		repositorio.delete(student);
		return "redirect:/estudiantes";
	}
	
	//nos lleva a 
	@GetMapping("/add")	 
	public String addStudent(Model model) {
			Student student = new Student();
			model.addAttribute("studentAdd",student);
			return "add";
	}
	
	@PostMapping("/addSubmit")
	public String addSubmit(@ModelAttribute("studentAdd")Student student) {
		repositorio.add(student);
		return "redirect:/estudiantes";
	}
	
	
	@GetMapping("/edit")
	public String editStudent (Model model,@RequestParam(name="id") Long id) {
		Student estudiante = repositorio.getStudent(id);
		return "edit";
		}

}
