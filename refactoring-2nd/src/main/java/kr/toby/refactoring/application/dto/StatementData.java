package kr.toby.refactoring.application.dto;

import java.util.List;

import lombok.AllArgsConstructor;

import kr.toby.refactoring.common.exception.RefactoringException;
import kr.toby.refactoring.domain.invoice.Invoice;
import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.domain.play.Play;
import kr.toby.refactoring.domain.play.Plays;
import kr.toby.refactoring.domain.play.enums.Type;
import kr.toby.refactoring.application.PerformanceCalculator;

@AllArgsConstructor
public class StatementData {

    private Invoice invoice;
    private Plays plays;

    public List<Performance> getPerformances() {
        return invoice.getPerformances();
    }

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public Play playFor(Performance performance) {
        return plays.get(performance);
    }

    public int amountFor(Performance performance) throws RefactoringException {
        return new PerformanceCalculator(performance, playFor(performance))
                .amountFor();
    }

    public int volumeCreditFor(Performance performance) {
        return new PerformanceCalculator(performance, playFor(performance))
                .volumeCreditFor();
    }

    public int totalAmount() throws RefactoringException {
        int result = 0;

        for (Performance performance : invoice.getPerformances()) {
            result += amountFor(performance);
        }
        return result / 100;
    }

    public int totalVolumeCredit() {
        int result = 0;

        for (Performance performance : invoice.getPerformances()) {
            result += volumeCreditFor(performance);
        }
        return result;
    }
}
