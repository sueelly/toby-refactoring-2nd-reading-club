package kr.toby.refactoring.domain.play.enums;

import java.util.Arrays;
import java.util.function.Function;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import kr.toby.refactoring.common.exception.RefactoringException;
import kr.toby.refactoring.common.exception.ResultCode;
import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.application.PerformanceCalculator;
import kr.toby.refactoring.application.ComedyCalculator;
import kr.toby.refactoring.application.TragedyCalculator;

@Getter
@RequiredArgsConstructor
public enum Type {

    TRAGEDY("tragedy", (performance) -> new TragedyCalculator(performance)),
    COMEDY("comedy", (performance) -> new ComedyCalculator(performance)),
    ;

    private final String type;
    private final Function<Performance, PerformanceCalculator> getPerformanceCalculator;

    @JsonValue
    public String getType() {
        return type;
    }

    public PerformanceCalculator getPerformanceCalculator(Performance performance) {
        return getPerformanceCalculator.apply(performance);
    }

    @JsonCreator
    public static Type from(String value) {
        return Arrays.stream(Type.values())
            .filter(t -> t.getType().equals(value))
            .findFirst()
            .orElseThrow(() -> new RefactoringException(ResultCode.INVALID_PLAY_TYPE));
    }
}
