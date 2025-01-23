package kr.toby.refactoring.application;

import kr.toby.refactoring.common.exception.RefactoringException;
import kr.toby.refactoring.domain.invoice.Invoice;
import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.domain.play.Plays;
import kr.toby.refactoring.application.dto.StatementData;

public class Statement {

    public String statement(Invoice invoice, Plays plays) 
            throws RefactoringException {
        StatementData statementData = new StatementData(invoice, plays);
        return renderPlainText(statementData);
    }

    private String renderPlainText(StatementData statementData) 
            throws RefactoringException {
        StringBuilder result =
                new StringBuilder(String.format("청구내역 (고객명: %s)\n", statementData.getCustomer()));

        for (Performance performance : statementData.getPerformances()) {
            // Print invoice line for this order
            result.append(String.format("%s: $%d %d석\n", statementData.playFor(performance).getName(),
                    statementData.amountFor(performance) / 100, performance.getAudience()));
        }
        result.append(String.format("총액: $%d\n", statementData.totalAmount()));
        result.append(String.format("적립 포인트: %d점", statementData.totalVolumeCredit()));
        return result.toString();
    }

    private String renderHtml(StatementData statementData) {
        StringBuilder result =
                new StringBuilder(String.format("<h1>청구내역 (고객명: %s)</h1>\n", statementData.getCustomer()));

        result.append("<table>\n");
        result.append("<tr><th> 연극 </th> <th> 좌석 수 </th> <th> 금액 </th></tr>\n");
        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("<tr><td> %s </td> <td> %d석 </td> <td> $%d </td></tr>\n",
                    statementData.playFor(performance).getName(), performance.getAudience(),
                    statementData.amountFor(performance) / 100));
        }
        result.append(String.format(
            "<tr><td> 총액 </td> <td> $%d </td> <td> </td></tr>\n", statementData.totalAmount()));
        result.append(String.format(
            "<tr><td> 적립 포인트 </td> <td> %d점 </td> <td> </td></tr>\n", statementData.totalVolumeCredit()));
        result.append("</table>\n");
        return result.toString();
    }
}
