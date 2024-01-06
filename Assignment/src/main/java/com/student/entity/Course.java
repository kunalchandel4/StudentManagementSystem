package com.student.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "course_name", nullable = false)
	private String courseName;

	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "course_type", nullable = false)
	private CourseType courseType;

	@Column(nullable = false)
	private int duration;

	@ElementCollection
	@CollectionTable(name = "course_topics", joinColumns = @JoinColumn(name = "course_id"))
	@Column(name = "topic")
	private List<String> topics = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students = new ArrayList<>();

}
