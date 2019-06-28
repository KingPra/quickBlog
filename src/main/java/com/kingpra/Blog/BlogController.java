package com.kingpra.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

	@Autowired
	private BlogRepository blogRepo;

	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}

	@GetMapping("/new")
	public String getNewPage() {
		return "new";
	}

	@PostMapping("/new")
	public String postNewBlog(Blog blog, Model model) {
		blogRepo.save(blog);
		model.addAttribute("blogs", blogRepo.findAll());
		return "index";
	}

	@DeleteMapping("/{id}")
	public String deletePostById(@PathVariable Long id, Model model) {
		blogRepo.deleteById(id);
		model.addAttribute("blogs", blogRepo.findAll());
		return "index";
	}
}
