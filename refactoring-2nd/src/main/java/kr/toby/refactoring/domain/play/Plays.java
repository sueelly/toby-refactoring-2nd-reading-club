package kr.toby.refactoring.domain.play;

import java.util.Map;

import lombok.Data;
import lombok.AllArgsConstructor;

import kr.toby.refactoring.domain.invoice.Performance;

@Data
@AllArgsConstructor
public class Plays {

    private Map<String, Play> plays;

    public Play get(Performance performance) {
        return plays.get(performance.getPlayID());
    }
}
