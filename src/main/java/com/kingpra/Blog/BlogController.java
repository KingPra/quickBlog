package com.kingpra.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class BlogController {

	@Autowired
	private BlogRepository blogRepo;

	@GetMapping("/")
	// this displays the index page
	public String getIndexPage(Model model) {
		// this populates the model with all data from the database. Our key to access
		// this data is "blogs". "blogs" is what we use for thymeleaf th:each
		model.addAttribute("blogs", blogRepo.findAll());
		// we return our index.html page and inject "blogs" into the view.
		return "index";
	}

	@GetMapping("/new")
	// method for getting new.html page
	public String getNewPage() {
		// returns new.html page
		return "new";
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable Long id, Model model, Blog blog) {
		Blog post = blogRepo.findById(id).orElse(null);
		model.addAttribute("blog", post);
		return "edit";
	}

	@PutMapping("/edit/{id}")
	public String editPost(@PathVariable Long id, Model model, Blog blog) {
		Blog post = blogRepo.findById(id).orElse(null);
		post.setAuthor(blog.getAuthor());
		post.setTitle(blog.getTitle());
		post.setContent(blog.getContent());
		blogRepo.save(post);
		model.addAttribute("blogs", blogRepo.findAll());
		return "index";
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
