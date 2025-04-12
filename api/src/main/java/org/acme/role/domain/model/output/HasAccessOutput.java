package org.acme.role.domain.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HasAccessOutput {
    private Boolean ok;

    public static HasAccessOutput ok() {
        return new HasAccessOutput(true);
    }

    public static HasAccessOutput notOk() {
        return new HasAccessOutput(false);
    }
}
