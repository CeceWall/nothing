package com.example.controller

import com.example.constants.ApprovalStage
import com.example.dto.Department
import com.example.dto.Proposal
import com.example.dto.User
import com.example.exception.BaseException
import com.example.exception.DepartmentInvalidException
import com.example.service.DepartmentService
import com.example.service.ProposalService
import com.example.utils.ResultBean
import com.example.utils.TimeUtils
import com.example.vo.ProposalVo
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.time.*
import javax.validation.Valid

@RestController
@RequestMapping("/proposal")
open class ProposalController {
    @Autowired
    lateinit var proposalService: ProposalService
    @Autowired
    lateinit var departmentService: DepartmentService;
    @Autowired
    lateinit var timeUtils: TimeUtils;

    @PostMapping("/add")
    fun add(@Valid @RequestBody proposalVo: ProposalVo, principal: UsernamePasswordAuthenticationToken): ResultBean<ProposalVo> {
        val proposal = Proposal()
        BeanUtils.copyProperties(proposalVo, proposal)
        proposal.inputTime = LocalDateTime.now()
        proposal.inputStaff = (principal.principal as User).employeeId
        proposal.deptId = (principal.principal as User).userDeptId
        proposal.approveState = ApprovalStage.NOT_APPROVE.value
        val proposalEntity = this.proposalService.addProposal(proposal)
        return ResultBean.success(ProposalVo(proposalEntity))
    }

    @PostMapping("/edit/{proposalId}")
    fun editProposal(@PathVariable proposalId: Long, @Valid @RequestBody proposalVo: ProposalVo): ResultBean<ProposalVo> {
        val proposal = this.proposalService.getProposalById(proposalId)
        BeanUtils.copyProperties(proposalVo, proposal, "proposalId")
        val proposalEntity = this.proposalService.saveProposal(proposal)
        return ResultBean.success(ProposalVo(proposalEntity))
    }

    @GetMapping("/my")
    fun getProposalList(
            @RequestParam(name = "startDate", required = false) startDate: String?,
            @RequestParam(name = "endDate", required = false) endDate: String?,
            @RequestParam(required = false, defaultValue = "0") pageNo: Int,
            @RequestParam(required = false, defaultValue = "10") pageSize: Int,
            principal: UsernamePasswordAuthenticationToken
    ): ResultBean<Page<Proposal>> {
        val pageable = PageRequest.of(pageNo, pageSize)
        val employeeId = (principal.principal as User).employeeId
        val proposalList = this.proposalService.getProposalByEmployIdAndProposalDate(
                employeeId = employeeId,
                startDate = timeUtils.dateToDateTime(startDate),
                endDate = timeUtils.dateToDateTime(endDate),
                pageable = pageable
        )
        return ResultBean.success(proposalList)
    }

    private fun getDepartmentApproveEmployeeIdByStage(department: Department, stage: Int): String? {
        return when (stage) {
            1 -> department.firstApprover
            2 -> department.secondApprover
            else -> throw DepartmentInvalidException("部门缺少审批员, 部门id: ${department.deptID}, 阶段: $stage")
        }
    }

    @PostMapping("/confirm/{proposalId}")
    fun confirmProposal(@PathVariable proposalId: Long): ResultBean<String> {
        val proposal = this.proposalService.getProposalById(proposalId)
        proposal.approveState = ApprovalStage.APPROVING_1ST.value
        this.proposalService.saveProposal(proposal)
        return ResultBean.success("操作成功")
    }

    @GetMapping("/approval/current")
    fun getShouldApprovalListByCurrentUser(
            @RequestParam(name = "stage", defaultValue = "1") stage: Int,
            @RequestParam(name = "startDate", required = false) startDate: String?,
            @RequestParam(name = "endDate", required = false) endDate: String?,
            @RequestParam(required = false, defaultValue = "0") pageNo: Int,
            @RequestParam(required = false, defaultValue = "10") pageSize: Int,
            principal: UsernamePasswordAuthenticationToken
    ): ResultBean<Page<Proposal>> {
        val user = (principal.principal as User)
        val deptId = user.userDeptId
        val department = this.departmentService.findDepartmentByID(deptId)
        if (user.employeeId != this.getDepartmentApproveEmployeeIdByStage(department, stage)) {
            throw BaseException("无权限")
        }
        val result = this.proposalService.getShouldApproveProposalList(
                deptId,
                startDate = timeUtils.dateToDateTime(startDate),
                endDate = timeUtils.dateToDateTime(endDate, LocalTime.MAX),
                pageable = PageRequest.of(pageNo, pageSize)
        )
        return ResultBean.success(result)
    }


}
