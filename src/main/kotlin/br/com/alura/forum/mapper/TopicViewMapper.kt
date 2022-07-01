package br.com.alura.forum.mapper

import br.com.alura.forum.model.Topic
import br.com.alura.forum.dto.TopicView
import org.springframework.stereotype.Component

@Component
class TopicViewMapper(
): Mapper<Topic, TopicView> {
  override fun map(t: Topic): TopicView {
    return TopicView(
      id= t.id,
      title= t.title,
      message= t.message,
      course= t.course.name,
      author= t.author.name,
      createdAt= t.createdAt,
      status= t.status
    )
  }
}