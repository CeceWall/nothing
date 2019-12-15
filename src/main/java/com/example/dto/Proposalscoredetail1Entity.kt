package com.example.dto

import javax.persistence.*

@Entity
@Table(name = "Proposalscoredetail1", schema = "dbo", catalog = "TNMYTAGSDB")
open class Proposalscoredetail1Entity {
  @get:Id
  @get:Column(name = "Proscodetail1ID", nullable = false, insertable = false, updatable = false)
  var proscodetail1ID: String? = null
  @get:Basic
  @get:Column(name = "ProscomainID", nullable = true, insertable = false, updatable = false)
  var proscomainID: String? = null
  @get:Basic
  @get:Column(name = "Expertid", nullable = true, insertable = false, updatable = false)
  var expertid: String? = null
  @get:Basic
  @get:Column(name = "ExpertScore", nullable = true)
  var expertScore: java.math.BigDecimal? = null
  @get:Basic
  @get:Column(name = "Inputtime", nullable = true)
  var inputtime: String? = null

  @get:ManyToOne(fetch = FetchType.LAZY)
  @get:JoinColumn(name = "ProscomainID", referencedColumnName = "ProscomainID")
  var refProposalscoremainEntity: ProposalscoremainEntity? = null
  @get:ManyToOne(fetch = FetchType.LAZY)
  @get:JoinColumn(name = "Expertid", referencedColumnName = "EmployeeID")
  var refEmployee: Employee? = null
  @get:OneToMany(mappedBy = "refProposalscoredetail1Entity")
  var refProposalscoredetail2Entities: List<Proposalscoredetail2Entity>? = null

  override fun toString(): String =
      "Entity of type: ${javaClass.name} ( " +
          "proscodetail1ID = $proscodetail1ID " +
          "proscomainID = $proscomainID " +
          "expertid = $expertid " +
          "expertScore = $expertScore " +
          "inputtime = $inputtime " +
          ")"

  // constant value returned to avoid entity inequality to itself before and after it's update/merge
  override fun hashCode(): Int = 42

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as Proposalscoredetail1Entity

    if (proscodetail1ID != other.proscodetail1ID) return false
    if (proscomainID != other.proscomainID) return false
    if (expertid != other.expertid) return false
    if (expertScore != other.expertScore) return false
    if (inputtime != other.inputtime) return false

    return true
  }

}

