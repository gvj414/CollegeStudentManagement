package net.javasapproj.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javasapproj.sms.entity.Teacher;
import net.javasapproj.sms.service.TeacherService;

@Controller
public class TeacherController {
	
	private TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		super();
		this.teacherService = teacherService;
	}
	
	// handler method to handle list teachers and return mode and view
	@GetMapping("/Teachers")
	public String listTeachers(Model model) {
		model.addAttribute("teachers", teacherService.getAllTeachers());
		return "teachers";
	}
	
	@GetMapping("/Teachers/new")
	public String createTeacherForm(Model model) {
		
		// create teacher object to hold teacher form data
		Teacher teacher = new Teacher();
		model.addAttribute("Teacher", teacher);
		return "create_teacher";
		
	}
	
	@PostMapping("/Teachers")
	public String saveTeacher(@ModelAttribute("teacher") Teacher teachers) {
		teacherService.saveTeacher(teachers);
		return "redirect:/teachers";
	}
	
	@GetMapping("/Teachers/edit/{id}")
	public String editTeacherForm(@PathVariable Long id, Model model) {
		model.addAttribute("teacher", teacherService.getTeacherById(id));
		return "edit_teacher";
	}

	@PostMapping("/Teachers/{id}")
	public String updateTeacher(@PathVariable Long id,
			@ModelAttribute("teacher") Teacher teacher,
			Model model) {
		
		// get Teacher from database by id
		Teacher existingTeacher = teacherService.getTeacherById(id);
		existingTeacher.setId(id);
		existingTeacher.setFirstName(teacher.getFirstName());
		existingTeacher.setLastName(teacher.getLastName());
		existingTeacher.setEmail(teacher.getEmail());
		
		// save updated Teacher object
		teacherService.updateTeacher(existingTeacher);
		return "redirect:/teachers";		
	}
	
	// handler method to handle delete Teacher request
	
	@GetMapping("/Teachers/{id}")
	public String deleteteacher(@PathVariable Long id) {
		teacherService.deleteTeacherById(id);
		return "redirect:/teachers";
	}
	
}
