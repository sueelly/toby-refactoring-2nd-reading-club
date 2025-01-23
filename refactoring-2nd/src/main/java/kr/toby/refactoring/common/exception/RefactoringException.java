package kr.toby.refactoring.common.exception;

import lombok.Getter;

@Getter
public class RefactoringException extends RuntimeException {

    private final ResultCode resultCode;

    public RefactoringException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public RefactoringException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
}
