package com.student.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(nullable = false)
	    private String name;
	    
	    @Column(name = "date_of_birth", nullable = false)
	    private LocalDate dateOfBirth;
	    
	    @Enumerated(EnumType.STRING)
	    private Gender gender;
	    
	    @Column(name = "unique_student_code", nullable = false, unique = true)
	    private String uniqueStudentCode;
	    
	    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Address> addresses = new ArrayList<>();
	    
	    @ManyToMany(mappedBy = "students")
	    private List<Course> courses = new ArrayList<>();
	    
}
