package kr.toby.refactoring.application;

import kr.toby.refactoring.common.exception.ResultCode;
import kr.toby.refactoring.common.exception.RefactoringException;
import kr.toby.refactoring.domain.invoice.Invoice;
import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.domain.play.Plays;
import kr.toby.refactoring.domain.play.Play;
import kr.toby.refactoring.domain.play.enums.Type;

public class Statement {

    public String statement(Invoice invoice, Plays plays) 
            throws RefactoringException {
        int totalAmount = 0;
        int volumeCredit = 0;
        StringBuilder result =
                new StringBuilder(String.format("청구내역 (고객명: %s)\n", invoice.getCustomer()));

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance);

            int thisAmount = amountFor(performance, play);

            // Add volume credits for each performance
            volumeCredit += Math.max(performance.getAudience() - 30, 0);

            // Add extra volume credits for every 5 comedy attendees
            if (play.getType() == Type.COMEDY) {
                volumeCredit += Math.floor(performance.getAudience() / 5);
            }

            // Print invoice line for this order
            result.append(String.format("%s: $%d %d석\n", play.getName(), thisAmount / 100,
                    performance.getAudience()));
            totalAmount += thisAmount;
        }
        result.append(String.format("총액: $%d\n", totalAmount / 100));
        result.append(String.format("적립 포인트: %d점", volumeCredit));
        return result.toString();
    }

    private int amountFor(Performance performance, Play play) 
        throws RefactoringException {
        int result;

        switch (play.getType()) {
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
}
