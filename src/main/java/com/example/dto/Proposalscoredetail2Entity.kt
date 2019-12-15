package com.example.dto

import javax.persistence.*

@Entity
@Table(name = "Proposalscoredetail2", schema = "dbo", catalog = "TNMYTAGSDB")
open class Proposalscoredetail2Entity {
  @get:Id
  @get:Column(name = "Proscodetail2ID", nullable = false)
  var proscodetail2ID: Int? = null
  @get:Basic
  @get:Column(name = "Proscodetail1ID", nullable = true, insertable = false, updatable = false)
  var proscodetail1ID: String? = null
  @get:Basic
  @get:Column(name = "Score", nullable = true)
  var score: java.math.BigDecimal? = null
  @get:Basic
  @get:Column(name = "Evaluationitem", nullable = true)
  var evaluationitem: String? = null
  @get:Basic
  @get:Column(name = "Evaluationstandard", nullable = true)
  var evaluationstandard: String? = null

  @get:ManyToOne(fetch = FetchType.LAZY)
  @get:JoinColumn(name = "Proscodetail1ID", referencedColumnName = "Proscodetail1ID")
  var refProposalscoredetail1Entity: Proposalscoredetail1Entity? = null

  override fun toString(): String =
      "Entity of type: ${javaClass.name} ( " +
          "proscodetail2ID = $proscodetail2ID " +
          "proscodetail1ID = $proscodetail1ID " +
          "score = $score " +
          "evaluationitem = $evaluationitem " +
          "evaluationstandard = $evaluationstandard " +
          ")"

  // constant value returned to avoid entity inequality to itself before and after it's update/merge
  override fun hashCode(): Int = 42

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as Proposalscoredetail2Entity

    if (proscodetail2ID != other.proscodetail2ID) return false
    if (proscodetail1ID != other.proscodetail1ID) return false
    if (score != other.score) return false
    if (evaluationitem != other.evaluationitem) return false
    if (evaluationstandard != other.evaluationstandard) return false

    return true
  }

}

