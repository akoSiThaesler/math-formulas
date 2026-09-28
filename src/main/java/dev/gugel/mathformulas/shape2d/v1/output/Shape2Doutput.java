package dev.gugel.mathformulas.shape2d.v1.output;

import dev.gugel.mathformulas.common.MessageOutput;

import java.util.List;

public record Shape2Doutput( Double area,
                             Double perimeter,
                             List<MessageOutput> messageOutputList ) {
}
