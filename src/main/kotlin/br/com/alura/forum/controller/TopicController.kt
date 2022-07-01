package br.com.alura.forum.controller


import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.service.TopicService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

  @GetMapping
  @Cacheable("topicList")
  fun getTopicList(
    @RequestParam(required = false) courseName: String?,
    @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) page: Pageable
  ): Page<TopicView> {
    return service.topicList(courseName,page)
  }

  @GetMapping("/{id}")
  fun getTopic(@PathVariable id: Long): TopicView {
    return service.topicById(id)
  }

  @PostMapping
  @Transactional
  @CacheEvict(value = ["topicList"], allEntries = true)
  fun postTopic(
    @RequestBody @Valid form: NewTopicForm,
    uriBuilder: UriComponentsBuilder
  ) : ResponseEntity<TopicView> {
    val topicView = service.register(form)
    val uri = uriBuilder.path("topics/${topicView.id}").build().toUri()
    return ResponseEntity.created(uri).body(topicView)
  }

  @PutMapping
  @Transactional
  @CacheEvict(value = ["topicList"], allEntries = true)
  fun updateTopic(
    @RequestBody @Valid form: UpdateTopicForm,
    uriBuilder: UriComponentsBuilder
  ) : ResponseEntity<TopicView>{
    val updatedTopicView = service.update(form)
    val uri = uriBuilder.path("topics/${updatedTopicView.id}").build().toUri()
    return ResponseEntity.created(uri).body(updatedTopicView)
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Transactional
  @CacheEvict(value = ["topicList"], allEntries = true)
  fun deleteTopic(@PathVariable id: Long){
    service.delete(id)
  }

  @GetMapping("/dashboard")
  fun getTopicDashboard(): List<TopicByCategoryDto> {
    return service.dashboard()
  }

}
