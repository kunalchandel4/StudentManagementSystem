package com.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long> {
	public Optional<Admin> findByEmail(String email);
}
