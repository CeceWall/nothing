package com.example.exception

class ProposalNotFoundException(proposalId: Long?): BaseException("未找到对应的提案")