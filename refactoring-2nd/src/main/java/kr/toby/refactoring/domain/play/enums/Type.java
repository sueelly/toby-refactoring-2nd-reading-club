package kr.toby.refactoring.domain.play.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

@Getter
@RequiredArgsConstructor
public enum Type {

    TRAGEDY("tragedy"),
    COMEDY("comedy"),
    ;

    private final String type;

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
