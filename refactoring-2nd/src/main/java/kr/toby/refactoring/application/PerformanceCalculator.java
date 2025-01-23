package kr.toby.refactoring.application;

import lombok.AllArgsConstructor;

import kr.toby.refactoring.domain.invoice.Performance;

@AllArgsConstructor
public abstract class PerformanceCalculator {

    protected Performance performance;

    public abstract int amountFor();

    public abstract int volumeCreditFor();
}   

