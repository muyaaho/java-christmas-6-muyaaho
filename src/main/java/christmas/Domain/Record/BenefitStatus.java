package christmas.Domain.Record;

import java.util.Map;

public record BenefitStatus(Map<String, Integer> benefitList, int totalBenefit, int finalPrice, String badge) {

}
