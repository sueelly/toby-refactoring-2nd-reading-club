package kr.toby.refactoring.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultCode {

    SUCCESS("Success"),
    ERROR("Error"),

    // Play
    UNKOWN_PLAY_TYPE("Unknown play type"),
    ;

    private final String message;
}
