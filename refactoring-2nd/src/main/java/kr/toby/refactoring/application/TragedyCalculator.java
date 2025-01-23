package kr.toby.refactoring.application;

import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.domain.play.Play;

public class TragedyCalculator extends PerformanceCalculator {

    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }
}

