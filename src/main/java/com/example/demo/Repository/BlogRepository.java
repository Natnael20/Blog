package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
	
	Optional<Blog> findBlogByAuthor(String author);
	List<Blog> findBlogByBlogTitel(String blogTitel);
	Optional<Blog> findBlogByContent(String content);
}
