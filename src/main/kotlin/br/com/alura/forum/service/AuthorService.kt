package br.com.alura.forum.service

import br.com.alura.forum.model.Student
import br.com.alura.forum.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(private var repository: StudentRepository) {

  fun authorById(id: Long): Student {
    return repository.getReferenceById(id)
  }

}
