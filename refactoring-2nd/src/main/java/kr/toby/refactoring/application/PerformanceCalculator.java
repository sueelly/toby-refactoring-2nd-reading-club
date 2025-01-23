package kr.toby.refactoring.application;

import lombok.AllArgsConstructor;
import kr.toby.refactoring.common.exception.RefactoringException;
import kr.toby.refactoring.common.exception.ResultCode;
import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.domain.play.Play;
import kr.toby.refactoring.domain.play.enums.Type;

@AllArgsConstructor
public class PerformanceCalculator {

    private Performance performance;
    private Play play;

    public int amountFor() throws RefactoringException {
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

    public int volumeCreditFor() {
        int result = 0;

        // Add volume credits for each performance
        result += Math.max(performance.getAudience() - 30, 0);

        // Add extra volume credits for every 5 comedy attendees
        if (play.getType() == Type.COMEDY) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }
}
