package com.example.dto

import java.time.LocalDateTime
import javax.persistence.*

enum class ProposalAction {
    EDIT,
    CONFIRM,
    SCORING,
}

interface ProposalActionDispatch {
    fun canDoAction(proposalAction: ProposalAction): Boolean
}

class InitialProposal : ProposalActionDispatch {
    override fun canDoAction(proposalAction: ProposalAction): Boolean {
        return when (proposalAction) {
            ProposalAction.SCORING -> false
            else -> true
        }
    }
}

class ConfirmedProposal: ProposalActionDispatch {
    override fun canDoAction(proposalAction: ProposalAction): Boolean {
        return when(proposalAction) {
            ProposalAction.SCORING -> true
            else -> false
        }
    }
}

class W

@Entity
@Table(name = "Proposal")
open class Proposal : ProposalActionDispatch {
    @get:Id
    @get:Column(name = "ProposalID")
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var proposalId: Long? = null
    @get:Column(name = "Proposalname")
    var proposalName: String? = null
    @get:Column(name = "Proposaltype")
    var proposalTypeId: Long? = null
    @get:Column(name = "Proposalstaff")
    var proposalStaff: String? = null;
    @get:Column(name = "Beforeimprov")
    var beforeImprove: String? = null
    @get:Column(name = "Beforeimprovattach")
    var beforeImproveAttach: String? = null
    @get:Column(name = "Afterimprov")
    var afterImprove: String? = null
    @get:Column(name = "Afterimprovattach")
    var afterImproveAttach: String? = null
    @get:Column(name = "countermeasure")
    var counterMeasure: String? = null
    @get:Basic
    @get:Column(name = "Effect", nullable = true)
    var effect: String? = null
    @get:Basic
    @get:Column(name = "Effectvalue", nullable = true)
    var effectValue: java.math.BigDecimal? = null
    @get:Basic
    @get:Column(name = "Inputtime", nullable = true)
    var inputTime: LocalDateTime? = null
    @get:Basic
    @get:Column(name = "Inputstaff", nullable = true)
    var inputStaff: String? = null
    @get:Basic
    @get:Column(name = "Awardrank", nullable = true)
    var awardRank: String? = null
    @get:Basic
    @get:Column(name = "Approvestate", nullable = true)
    lateinit var approveState: String
    @get:Basic
    @get:Column(name = "Deptid", nullable = true, insertable = false, updatable = false)
    var deptId: String? = null
    @get:Basic
    @get:Column(name = "Jobname", nullable = true)
    var jobName: String? = null
    @get:Basic
    @get:Column(name = "Attachment", nullable = true)
    var attachment: String? = null
    @get:Basic
    @get:Column(name = "bonus", nullable = true)
    var bonus: java.math.BigDecimal? = null

    override fun toString(): String =

            "Entity of type: ${javaClass.name} ( " +
                    "proposalID = $proposalId" +
                    "proposalname = $proposalName " +
                    "proposalstaff = $proposalStaff " +
                    "proposaltypeId = $proposalTypeId " +
                    "beforeimprov = $beforeImprove " +
                    "beforeimprovattach = $beforeImproveAttach " +
                    "afterimprov = $afterImprove" +
                    "afterimprovattach = $afterImproveAttach" +
                    "countermeasure = $counterMeasure" +
                    "effect = $effect " +
                    "effectvalue = $effectValue" +
                    "inputtime = $inputTime" +
                    "inputstaff = $inputStaff" +
                    "awardrank = $awardRank" +
                    "approvestate = $approveState" +
                    "deptid = $deptId" +
                    "jobname = $jobName" +
                    "attachment = $attachment " +
                    "bonus = $bonus " +
                    ")"


    @Transient
    override fun canDoAction(proposalAction: ProposalAction): Boolean {
        return true
    }
}
