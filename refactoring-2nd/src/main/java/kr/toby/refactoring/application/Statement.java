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
        return renderPlainText(invoice, plays);
    }
    
    private String renderPlainText(Invoice invoice, Plays plays) 
            throws RefactoringException {
        StringBuilder result =
                new StringBuilder(String.format("청구내역 (고객명: %s)\n", invoice.getCustomer()));

        for (Performance performance : invoice.getPerformances()) {
            // Print invoice line for this order
            result.append(String.format("%s: $%d %d석\n", playFor(plays, performance).getName(),
                    amountFor(performance, plays) / 100, performance.getAudience()));
        }
        result.append(String.format("총액: $%d\n", totalAmount(invoice, plays)));
        result.append(String.format("적립 포인트: %d점", totalVolumeCredit(invoice, plays)));
        return result.toString();
    }

    private String renderHtml(Invoice invoice, Plays plays) {
        StringBuilder result =
                new StringBuilder(String.format("<h1>청구내역 (고객명: %s)</h1>\n", invoice.getCustomer()));

        result.append("<table>\n");
        result.append("<tr><th> 연극 </th> <th> 좌석 수 </th> <th> 금액 </th></tr>\n");
        for (Performance performance : invoice.getPerformances()) {
            result.append(String.format("<tr><td> %s </td> <td> %d석 </td> <td> $%d </td></tr>\n",
                    playFor(plays, performance).getName(), performance.getAudience(),
                    amountFor(performance, plays) / 100));
        }
        result.append(String.format(
            "<tr><td> 총액 </td> <td> $%d </td> <td> </td></tr>\n", totalAmount(invoice, plays)));
        result.append(String.format(
            "<tr><td> 적립 포인트 </td> <td> %d점 </td> <td> </td></tr>\n", totalVolumeCredit(invoice, plays)));
        result.append("</table>\n");
        return result.toString();
    }

    private int amountFor(Performance performance, Plays plays) 
            throws RefactoringException {
        int result;

        switch (playFor(plays, performance).getType()) {
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

    private Play playFor(Plays plays, Performance performance) {
        return plays.get(performance);
    }

    private int volumeCreditFor(Performance performance, Plays plays) {
        int result = 0;

        // Add volume credits for each performance
        result += Math.max(performance.getAudience() - 30, 0);

        // Add extra volume credits for every 5 comedy attendees
        if (playFor(plays, performance).getType() == Type.COMEDY) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }

    private int totalAmount(Invoice invoice, Plays plays) throws RefactoringException {
        int result = 0;

        for (Performance performance : invoice.getPerformances()) {
            result += amountFor(performance, plays);
        }
        return result / 100;
    }

    private int totalVolumeCredit(Invoice invoice, Plays plays) {
        int result = 0;

        for (Performance performance : invoice.getPerformances()) {
            result += volumeCreditFor(performance, plays);
        }
        return result;
    }
}
