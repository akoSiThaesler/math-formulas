package dev.gugel.mathformulas.common;

public class MessageOutput {

    private String message;
    private MessageType messageType;

    public MessageOutput( String message, MessageType messageType ) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
