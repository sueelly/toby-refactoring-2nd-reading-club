package kr.toby.refactoring.application;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import kr.toby.refactoring.utils.JsonReader;
import kr.toby.refactoring.domain.invoice.Invoice;
import kr.toby.refactoring.domain.play.Plays;
import kr.toby.refactoring.domain.play.Play;

public class StatementTest {

    private List<Invoice> invoices;
    private Plays plays;

    @BeforeEach
    public void setUp() throws IOException {
        Map<String, Play> playMap;
        String playsJson = "src/main/resources/chapter-01/plays.json";
        JsonReader playsJsonReader = new JsonReader(playsJson);
        playMap = playsJsonReader.readJson(new TypeReference<Map<String, Play>>() {});
        plays = new Plays(playMap);

        String invoiceJson = "src/main/resources/chapter-01/invoice.json";
        JsonReader invoiceJsonReader = new JsonReader(invoiceJson);
        invoices = invoiceJsonReader.readJson(new TypeReference<List<Invoice>>() {});
    }

    @Test
    @DisplayName("Statement 테스트")
    public void statementTest() throws IOException {
        // given
        Invoice invoice = invoices.get(0);
        Statement statement = new Statement();
        String expected = "청구내역 (고객명: BigCo)\n" + //
                        "hamlet: $650 55석\n" + //
                        "As You Like It: $580 35석\n" + //
                        "Othello: $500 40석\n" + //
                        "총액: $1730\n" + //
                        "적립 포인트: 47점";

        // when
        String result = statement.statement(invoice, plays);

        // then
        assertEquals(expected, result);
    }
}

