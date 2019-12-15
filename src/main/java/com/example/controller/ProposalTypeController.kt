package com.example.controller

import com.example.dto.ProposalType
import com.example.service.ProposalService
import com.example.service.ProposalTypeService
import com.example.utils.ResultBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProposalTypeController {
  @Autowired
  lateinit var proposalTypeService: ProposalTypeService

  @GetMapping("/proposal-type/all")
  fun getProposalList(): ResultBean<List<ProposalType>> {
    return ResultBean.success(this.proposalTypeService.getProposalList());
  }

}
