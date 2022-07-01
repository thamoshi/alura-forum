package br.com.alura.forum.repository

import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository: JpaRepository<Topic, Long> {

  fun findByCourseName(
    courseName: String,
    page: Pageable
  ): Page<Topic>

  //JPQL, not a native query
  @Query("SELECT new br.com.alura.forum.dto.TopicByCategoryDto(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
  fun dashboard(): List<TopicByCategoryDto>

}
