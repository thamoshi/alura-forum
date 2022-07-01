package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.exceptions.NotFoundException
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
  private var repository: TopicRepository,
  private val topicViewMapper: TopicViewMapper,
  private val topicFormMapper: TopicFormMapper,
  private val notFoundMessage: String? = "Topic not found!"
) {

  fun topicList(
    courseName: String?,
    page: Pageable
  ): Page<TopicView> {
    val topics = if(courseName == null) {
      repository.findAll(page)
    } else {
      repository.findByCourseName(courseName, page)
    }
    return topics.map { t ->
      topicViewMapper.map(t)
    }
  }

  fun topicById(id: Long): TopicView {
    val topic = repository.findById(id)
      .orElseThrow { NotFoundException(notFoundMessage) }
    return topicViewMapper.map(topic)
  }

  fun register(form: NewTopicForm): TopicView{
    val topic = topicFormMapper.map(form)
    repository.save(topic)
    return topicViewMapper.map(topic)
  }

  fun update(form: UpdateTopicForm): TopicView {
    val topic = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
    topic.title = form.title
    topic.message = form.message
    return topicViewMapper.map(topic)
  }

  fun delete(id: Long) {
    repository.deleteById(id)
  }

  fun dashboard(): List<TopicByCategoryDto> {
    return repository.dashboard()
  }

}
