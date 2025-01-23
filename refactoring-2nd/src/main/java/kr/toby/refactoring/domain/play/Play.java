package kr.toby.refactoring.domain.play;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import kr.toby.refactoring.domain.play.enums.Type;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Play {

    private String name;
    private Type type;
}
