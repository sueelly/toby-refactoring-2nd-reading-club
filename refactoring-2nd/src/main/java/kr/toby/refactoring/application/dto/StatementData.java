package kr.toby.refactoring.application.dto;

import java.util.List;

import lombok.AllArgsConstructor;

import kr.toby.refactoring.common.exception.RefactoringException;
import kr.toby.refactoring.common.exception.ResultCode;
import kr.toby.refactoring.domain.invoice.Invoice;
import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.domain.play.Play;
import kr.toby.refactoring.domain.play.Plays;
import kr.toby.refactoring.domain.play.enums.Type;

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

    public int amountFor(Performance performance) 
            throws RefactoringException {
        int result;

        switch (playFor(performance).getType()) {
            case TRAGEDY:
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case COMEDY:
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new RefactoringException(ResultCode.INVALID_PLAY_TYPE);
        }
        return result;
    }

    public Play playFor(Performance performance) {
        return plays.get(performance);
    }

    public int volumeCreditFor(Performance performance) {
        int result = 0;

        // Add volume credits for each performance
        result += Math.max(performance.getAudience() - 30, 0);

        // Add extra volume credits for every 5 comedy attendees
        if (playFor(performance).getType() == Type.COMEDY) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
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
