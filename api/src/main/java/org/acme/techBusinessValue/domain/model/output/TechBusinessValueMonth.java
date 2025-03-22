package org.acme.techBusinessValue.domain.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechBusinessValueMonth {
    private String month;
    private int monthValue;
    private TechBusinessValueOutput data;
}
