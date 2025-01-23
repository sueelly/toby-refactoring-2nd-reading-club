package kr.toby.refactoring.application;

import kr.toby.refactoring.domain.invoice.Performance;
import kr.toby.refactoring.domain.play.Play;

public class ComedyCalculator extends PerformanceCalculator {

    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }
}
