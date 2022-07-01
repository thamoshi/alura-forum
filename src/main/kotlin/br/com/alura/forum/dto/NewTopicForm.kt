package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewTopicForm(
  @field:NotEmpty
  @field:Size(min=1,max=100, message = "Title must have between 1 and 100 characters")
  val title: String,
  @field:NotEmpty
  val message: String,
  @field:NotNull
  val courseId: Long,
  @field:NotNull
  val authorId: Long
)