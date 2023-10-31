package com.example.demo.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long blogId;

	@Column(name = "blogTitel", nullable = false)
	private String blogTitel;

	@Column(nullable = false)
    private String author;

	@Column(nullable = false)
    private String content;

	@Column(name = "publication_date", nullable = false)
    private Date publicationDate;
    
    public Blog() {}
    
	public Blog(Long blogId, String blogTitel, String author, String content, Date publicationDate) {
		this.blogId = blogId;
		this.blogTitel = blogTitel;
		this.author = author;
		this.content = content;
		this.publicationDate = publicationDate;
	}
    
    public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitel() {
		return blogTitel;
	}

	public void setBlogTitel(String blogTitel) {
		this.blogTitel = blogTitel;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
}