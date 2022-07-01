package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Response(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
  val message: String,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  @ManyToOne
  val author: Student,
  @ManyToOne
  val topic: Topic,
  val solution: Boolean
)
