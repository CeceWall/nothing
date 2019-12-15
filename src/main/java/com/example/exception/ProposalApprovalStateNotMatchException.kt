package com.example.exception

import com.example.constants.ApprovalStage

class ProposalApprovalStateNotMatchException(currentState: ApprovalStage): BaseException("${currentState.value}状态的审批无法执行此操作")