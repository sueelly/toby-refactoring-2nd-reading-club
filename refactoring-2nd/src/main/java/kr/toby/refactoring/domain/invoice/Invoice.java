package kr.toby.refactoring.domain.invoice;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private String customer;
    private List<Performance> performances;
}
