package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Blog;
import com.example.demo.Repository.BlogRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blog")
public class blogController {
	
	@Autowired
	public BlogRepository blogRepository;

	public blogController(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

    @GetMapping("/Blog/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable(value = "id") Long blogId)
        throws ResourceNotFoundException {
        Blog blog = blogRepository.findById(blogId)
          .orElseThrow(() -> new ResourceNotFoundException("Blog not found for this id :: " + blogId));
        return ResponseEntity.ok().body(blog);
    }
    
    @PostMapping("/Blog")
    public @Valid Blog createBlog(@Valid @RequestBody Blog blog) {
    	return blogRepository.save(blog);
    }
    
    @PutMapping("/Blog/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable(value = "id")Long blogId,
    		@Valid @RequestBody Blog updatedBlog) throws ResourceNotFoundException {
    	Blog existedBlog = blogRepository.findById(blogId)
    			.orElseThrow(() -> new ResourceNotFoundException("Blog not found for this id " + blogId));
    	
    	existedBlog.setBlogTitel(updatedBlog.getBlogTitel());
    	existedBlog.setAuthor(updatedBlog.getAuthor());
    	existedBlog.setContent(updatedBlog.getContent());
    	existedBlog.setPublicationDate(updatedBlog.getPublicationDate());
    	final Blog upgradedBlog = blogRepository.save(existedBlog);
    	return ResponseEntity.ok(upgradedBlog);
    }
    
    @DeleteMapping("/blog/{id}")
    public Map<String, Boolean> deleteBlog(@PathVariable(value = "id") Long employeeId)
         throws ResourceNotFoundException {
    	Blog blog = blogRepository.findById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("Blog not found for this id :: " + employeeId));

    	blogRepository.delete(blog);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    @GetMapping("/blog/byAuthor")
    public ResponseEntity<Blog> getBlogByAuthor(@Valid @RequestBody String Author)
    	{
    	Optional<Blog> blogs = blogRepository.findBlogByAuthor(Author);
    	
    	if(blogs.isPresent()) {
    		return ResponseEntity.ok().body(blogs.get());
    	}
    	else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @GetMapping("/blogs/byTitle")
    public ResponseEntity<Blog> getBlogByTitle(@RequestBody String blogTitle) {
    	List<Blog> blogs = blogRepository.findBlogByBlogTitel(blogTitle);
    	
    	if(!blogs.isEmpty()) {
    		return ResponseEntity.ok().body(blogs.get(0));
    	}
    	else {
    		return ResponseEntity.notFound().build();
    	}
    }
    @GetMapping("/Blogs/byContent")
    public ResponseEntity<Blog> getBlogByContent(@RequestBody String content) {
    	Optional<Blog> blogs = blogRepository.findBlogByContent(content);
    	
    	if(blogs.isPresent()) {
    		return ResponseEntity.ok().body(blogs.get());
    	}
    	else {
    		return ResponseEntity.notFound().build();
    	}
    }
}