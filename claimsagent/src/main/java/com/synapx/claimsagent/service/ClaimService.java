package com.synapx.claimsagent.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.synapx.claimsagent.model.ClaimResult;

@Service
public class ClaimService {

    public ClaimResult process(String fnolText) {
         ClaimResult result = new ClaimResult();

    result.setStatus("RECEIVED");
    result.setMessage("FNOL processed successfully");

    // ✅ Initialize missingFields FIRST
    result.setMissingFields(new ArrayList<>());

    Map<String, Object> extractedFields = new HashMap<>();
    extractedFields.put("rawText", fnolText);

    // Extract policy number
    if (fnolText.contains("Policy")) {
        String policyPart = fnolText.substring(fnolText.indexOf("Policy") + 7).trim();
        String policyNumber = policyPart.split(" ")[0].replaceAll("[^A-Za-z0-9]", "");
        extractedFields.put("policyNumber", policyNumber);
    }

    // Extract estimated damage
    if (fnolText.contains("Estimated Damage")) {
        String damage = fnolText
                .substring(fnolText.indexOf("Estimated Damage") + 17)
                .replaceAll("[^0-9]", "")
                .trim();
        extractedFields.put("estimatedDamage", Integer.parseInt(damage));
    }

    // Missing policy number check
    if (!extractedFields.containsKey("policyNumber")) {
        result.getMissingFields().add("policyNumber");
        result.setRecommendedRoute("Manual Review");
        result.setReasoning("Policy number is missing");
    }

    result.setExtractedFields(extractedFields);

    // Damage-based routing
    if (extractedFields.containsKey("estimatedDamage")
            && result.getMissingFields().isEmpty()) {

        int damage = (int) extractedFields.get("estimatedDamage");

        if (damage < 25000) {
            result.setRecommendedRoute("Fast-track");
            result.setReasoning("Estimated damage below 25,000");
        }
    }

    return result; 
    }
}