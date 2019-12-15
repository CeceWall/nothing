package com.example.dto

import javax.persistence.*

@Entity
@Table(name = "ProposalApproval", schema = "dbo", catalog = "TNMYTAGSDB")
open class ProposalApprovalEntity {
  @get:Id
  @get:Column(name = "ID", nullable = false)
  var ID: Int? = null
  @get:Basic
  @get:Column(name = "ProposalID", nullable = true, insertable = false, updatable = false)
  var proposalID: Int? = null
  @get:Basic
  @get:Column(name = "FirstAppstaff", nullable = true, insertable = false, updatable = false)
  var firstAppstaff: String? = null
  @get:Basic
  @get:Column(name = "SecondAppstaff", nullable = true, insertable = false, updatable = false)
  var secondAppstaff: String? = null
  @get:Basic
  @get:Column(name = "FirstAppResult", nullable = true)
  var firstAppResult: String? = null
  @get:Basic
  @get:Column(name = "FirstAppRemark", nullable = true)
  var firstAppRemark: String? = null
  @get:Basic
  @get:Column(name = "SecondAppResult", nullable = true)
  var secondAppResult: String? = null
  @get:Basic
  @get:Column(name = "SecondAppRemark", nullable = true)
  var secondAppRemark: String? = null
  @get:Basic
  @get:Column(name = "FirstAPPStarttime", nullable = true)
  var firstAPPStarttime: String? = null
  @get:Basic
  @get:Column(name = "FirstAPPFinishtime", nullable = true)
  var firstAPPFinishtime: String? = null
  @get:Basic
  @get:Column(name = "SecondAPPStarttime", nullable = true)
  var secondAPPStarttime: String? = null
  @get:Basic
  @get:Column(name = "SecondAPPFinishtime", nullable = true)
  var secondAPPFinishtime: String? = null

  @get:ManyToOne(fetch = FetchType.LAZY)
  @get:JoinColumn(name = "ProposalID", referencedColumnName = "ProposalID")
  var refProposalEntity: Proposal? = null
  @get:ManyToOne(fetch = FetchType.LAZY)
  @get:JoinColumn(name = "FirstAppstaff", referencedColumnName = "EmployeeID")
  var refEmployee: Employee? = null


  override fun toString(): String =
      "Entity of type: ${javaClass.name} ( " +
          "ID = $ID " +
          "proposalID = $proposalID " +
          "firstAppstaff = $firstAppstaff " +
          "secondAppstaff = $secondAppstaff " +
          "firstAppResult = $firstAppResult " +
          "firstAppRemark = $firstAppRemark " +
          "secondAppResult = $secondAppResult " +
          "secondAppRemark = $secondAppRemark " +
          "firstAPPStarttime = $firstAPPStarttime " +
          "firstAPPFinishtime = $firstAPPFinishtime " +
          "secondAPPStarttime = $secondAPPStarttime " +
          "secondAPPFinishtime = $secondAPPFinishtime " +
          ")"

  // constant value returned to avoid entity inequality to itself before and after it's update/merge
  override fun hashCode(): Int = 42

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as ProposalApprovalEntity

    if (ID != other.ID) return false
    if (proposalID != other.proposalID) return false
    if (firstAppstaff != other.firstAppstaff) return false
    if (secondAppstaff != other.secondAppstaff) return false
    if (firstAppResult != other.firstAppResult) return false
    if (firstAppRemark != other.firstAppRemark) return false
    if (secondAppResult != other.secondAppResult) return false
    if (secondAppRemark != other.secondAppRemark) return false
    if (firstAPPStarttime != other.firstAPPStarttime) return false
    if (firstAPPFinishtime != other.firstAPPFinishtime) return false
    if (secondAPPStarttime != other.secondAPPStarttime) return false
    if (secondAPPFinishtime != other.secondAPPFinishtime) return false

    return true
  }

}

