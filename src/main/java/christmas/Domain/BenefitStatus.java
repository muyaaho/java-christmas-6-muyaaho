package christmas.Domain;

import java.util.Map;

public record BenefitStatus(Map<String, Integer> benefitList, int totalBenefitCost, int finalCost, String badge) {

}
