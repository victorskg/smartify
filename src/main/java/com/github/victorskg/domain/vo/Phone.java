package com.github.victorskg.domain.vo;

import javax.validation.constraints.Pattern;

public record Phone(@Pattern(regexp = "\\d{11,12}", message = "{phone.value.pattern}") String value) {
}
