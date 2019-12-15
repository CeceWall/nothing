package com.example.dto

import javax.persistence.*

@Entity
@Table(name = "Proposalscoremain", schema = "dbo", catalog = "TNMYTAGSDB")
open class ProposalscoremainEntity {
  @get:Id
  @get:Column(name = "ProscomainID", nullable = false, insertable = false, updatable = false)
  var proscomainID: String? = null
  @get:Basic
  @get:Column(name = "Scoremonth", nullable = true)
  var scoremonth: String? = null
  @get:Basic
  @get:Column(name = "ProposalID", nullable = true, insertable = false, updatable = false)
  var proposalID: Int? = null
  @get:Basic
  @get:Column(name = "Deadline", nullable = true)
  var deadline: String? = null
  @get:Basic
  @get:Column(name = "FinalScore", nullable = true)
  var finalScore: Int? = null

  @get:OneToMany(mappedBy = "refProposalscoremainEntity")
  var refProposalscoredetail1Entities: List<Proposalscoredetail1Entity>? = null
  @get:ManyToOne(fetch = FetchType.LAZY)
  @get:JoinColumn(name = "ProposalID", referencedColumnName = "ProposalID")
  var refProposalEntity: Proposal? = null

  override fun toString(): String =
      "Entity of type: ${javaClass.name} ( " +
          "proscomainID = $proscomainID " +
          "scoremonth = $scoremonth " +
          "proposalID = $proposalID " +
          "deadline = $deadline " +
          "finalScore = $finalScore " +
          ")"

  // constant value returned to avoid entity inequality to itself before and after it's update/merge
  override fun hashCode(): Int = 42

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as ProposalscoremainEntity

    if (proscomainID != other.proscomainID) return false
    if (scoremonth != other.scoremonth) return false
    if (proposalID != other.proposalID) return false
    if (deadline != other.deadline) return false
    if (finalScore != other.finalScore) return false

    return true
  }

}

