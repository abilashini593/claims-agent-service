package com.synapx.claimsagent.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synapx.claimsagent.model.ClaimResult;
import com.synapx.claimsagent.service.ClaimService;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping("/process")
    public ClaimResult processClaim(@RequestBody String fnolText) {
        return claimService.process(fnolText);
    }
}