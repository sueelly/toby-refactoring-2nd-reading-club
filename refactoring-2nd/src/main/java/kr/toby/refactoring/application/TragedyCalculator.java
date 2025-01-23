package kr.toby.refactoring.application;

import kr.toby.refactoring.domain.invoice.Performance;

public class TragedyCalculator extends PerformanceCalculator {

    public TragedyCalculator(Performance performance) {
        super(performance);
    }

    @Override
    public int amountFor() {
        int result = 40000;

        if (performance.getAudience() > 30) {
            result += 1000 * (performance.getAudience() - 30);
        }
        return result;
    }

    @Override
    public int volumeCreditFor() {
        return Math.max(performance.getAudience() - 30, 0);
    }
}

