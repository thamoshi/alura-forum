package br.com.alura.forum.service

import br.com.alura.forum.model.Course
import br.com.alura.forum.repository.CourseRepository
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CourseService(private var repository: CourseRepository) {

  fun courseById(id: Long): Course {
    return repository.getReferenceById(id)
  }

}
