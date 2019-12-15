package com.example.dto

import javax.persistence.*

@Entity
@Table(name = "ProposalApproval")
open class ProposalApproval {
  @get:Id
  @get:Column(name = "ID", nullable = false)
  @get:GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int? = null
  @get:Basic
  @get:Column(name = "ProposalID", nullable = true, insertable = false, updatable = false)
  var proposalId: Int? = null
  @get:Basic
  @get:Column(name = "FirstAppstaff", nullable = true, insertable = false, updatable = false)
  var firstAppStaff: String? = null
  @get:Basic
  @get:Column(name = "SecondAppstaff", nullable = true, insertable = false, updatable = false)
  var secondAppStaff: String? = null
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
  var firstAppStartTime: String? = null
  @get:Basic
  @get:Column(name = "FirstAPPFinishtime", nullable = true)
  var firstAppFinishTime: String? = null
  @get:Basic
  @get:Column(name = "SecondAPPStarttime", nullable = true)
  var secondAppStartTime: String? = null
  @get:Basic
  @get:Column(name = "SecondAPPFinishtime", nullable = true)
  var secondAppFinishTime: String? = null

  @get:ManyToOne(fetch = FetchType.LAZY)
  @get:JoinColumn(name = "ProposalID", referencedColumnName = "ProposalID")
  var refProposalEntity: Proposal? = null
  @get:ManyToOne(fetch = FetchType.LAZY)
  @get:JoinColumn(name = "FirstAppstaff", referencedColumnName = "EmployeeID")
  var refEmployee: Employee? = null


  override fun toString(): String =
      "Entity of type: ${javaClass.name} ( " +
          "ID = $id " +
          "proposalID = $proposalId " +
          "firstAppstaff = $firstAppStaff " +
          "secondAppstaff = $secondAppStaff " +
          "firstAppResult = $firstAppResult " +
          "firstAppRemark = $firstAppRemark " +
          "secondAppResult = $secondAppResult " +
          "secondAppRemark = $secondAppRemark " +
          "firstAPPStarttime = $firstAppStartTime " +
          "firstAPPFinishtime = $firstAppFinishTime " +
          "secondAPPStarttime = $secondAppStartTime " +
          "secondAPPFinishtime = $secondAppFinishTime " +
          ")"

  // constant value returned to avoid entity inequality to itself before and after it's update/merge
  override fun hashCode(): Int = 42

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as ProposalApproval

    if (id != other.id) return false
    if (proposalId != other.proposalId) return false
    if (firstAppStaff != other.firstAppStaff) return false
    if (secondAppStaff != other.secondAppStaff) return false
    if (firstAppResult != other.firstAppResult) return false
    if (firstAppRemark != other.firstAppRemark) return false
    if (secondAppResult != other.secondAppResult) return false
    if (secondAppRemark != other.secondAppRemark) return false
    if (firstAppStartTime != other.firstAppStartTime) return false
    if (firstAppFinishTime != other.firstAppFinishTime) return false
    if (secondAppStartTime != other.secondAppStartTime) return false
    if (secondAppFinishTime != other.secondAppFinishTime) return false

    return true
  }

}

