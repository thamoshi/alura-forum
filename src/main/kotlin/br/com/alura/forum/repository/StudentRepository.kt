package br.com.alura.forum.repository

import br.com.alura.forum.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student,Long> {
}