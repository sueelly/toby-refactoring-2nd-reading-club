package kr.toby.refactoring.application;

import kr.toby.refactoring.domain.invoice.Performance;

public class ComedyCalculator extends PerformanceCalculator {

    public ComedyCalculator(Performance performance) {
        super(performance);
    }

    @Override
    public int amountFor() {
        int result = 30000;

        if (performance.getAudience() > 20) {
            result += 10000 + 500 * (performance.getAudience() - 20);
        }
        result += 300 * performance.getAudience();
        return result;
    }

    @Override
    public int volumeCreditFor() {
        return Math.max(performance.getAudience() - 30, 0)
                + (int) Math.floor(performance.getAudience() / 5);
    }
}
