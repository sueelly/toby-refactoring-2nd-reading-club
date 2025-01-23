package kr.toby.refactoring.domain.play.enums;

import java.util.Arrays;
import java.util.function.BiFunction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.domain.play.Play;
import kr.toby.refactoring.application.PerformanceCalculator;
import kr.toby.refactoring.application.ComedyCalculator;
import kr.toby.refactoring.application.TragedyCalculator;

@Getter
@RequiredArgsConstructor
public enum Type {

    TRAGEDY("tragedy", (performance, play) -> new TragedyCalculator(performance, play)),
    COMEDY("comedy", (performance, play) -> new ComedyCalculator(performance, play)),
    ;

    private final String type;
    private final BiFunction<Performance, Play, PerformanceCalculator> getPerformanceCalculator;

    @JsonValue
    public String getType() {
        return type;
    }

    @JsonCreator
    public static Type from(String value) {
        return Arrays.stream(Type.values())
            .filter(t -> t.getType().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid type: " + value));
    }
}
