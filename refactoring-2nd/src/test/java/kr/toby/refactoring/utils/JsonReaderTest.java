package kr.toby.refactoring.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import kr.toby.refactoring.domain.invoice.Invoice;
import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.domain.play.Play;
import kr.toby.refactoring.domain.play.Plays;
import kr.toby.refactoring.domain.play.enums.Type;

public class JsonReaderTest {

    private static Play hamlet;
    private static Play asLike;
    private static Play othello;

    private static Performance hamletPerformance;
    private static Performance asLikePerformance;
    private static Performance othelloPerformance;

    @BeforeAll
    public static void setUp() {
        hamlet = new Play("hamlet", Type.TRAGEDY);
        asLike = new Play("As You Like It", Type.COMEDY);
        othello = new Play("Othello", Type.TRAGEDY);
        hamletPerformance = new Performance("hamlet", 55);
        asLikePerformance = new Performance("as-like", 35);
        othelloPerformance = new Performance("othello", 40);
    }

    @DisplayName("Plays JsonReader Test")
    @Test
    public void test() {
        // given 
        String playsJson = "src/main/resources/chapter-01/plays.json";
        JsonReader playsJsonReader = new JsonReader(playsJson);

        try {
            // when
            Map<String, Play> playList =
                playsJsonReader
                    .readJson(new TypeReference<Map<String, Play>>() {
                    });
            Plays plays = new Plays(playList);

            // then
            assertEquals(hamlet, plays.get(hamletPerformance));
            assertEquals(asLike, plays.get(asLikePerformance));
            assertEquals(othello, plays.get(othelloPerformance));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Invoice JsonReader Test")
    @Test
    public void testInvoice() {
        // given
        String invoiceJson = "src/main/resources/chapter-01/invoice.json";
        JsonReader invoiceJsonReader = new JsonReader(invoiceJson);

        try {
            // when
            List<Invoice> invoices = invoiceJsonReader.readJson(new TypeReference<List<Invoice>>() {});
            Invoice invoice = invoices.get(0);

            // then
            assertEquals("BigCo", invoice.getCustomer());
            assertEquals(3, invoice.getPerformances().size());
            assertEquals(hamletPerformance, invoice.getPerformances().get(0));
            assertEquals(asLikePerformance, invoice.getPerformances().get(1));
            assertEquals(othelloPerformance, invoice.getPerformances().get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
