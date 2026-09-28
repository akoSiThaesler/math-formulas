package dev.gugel.mathformulas.common;

import java.util.ArrayList;
import java.util.List;

public abstract class InputValidation {

    private boolean valid = true;
    protected List<MessageOutput> messageOutputList;

    /**
     * sets validation to invalid and creates a message if value is smaller than 3
     *
     * @param value checked value
     * @param message set this message, if value is smaller than 3
     */
    protected void validateValueGreaterTwo( Object value, String message ) {
        if( value instanceof Number ) {
            int numericValue = ( (Number) value ).intValue();
            if( numericValue > 0 && numericValue < 3 ) {
                if( getMessageOutputList() == null ) {
                    setMessageOutputList( new ArrayList<>() );
                }
                if( isMessageNew( message, MessageType.ERROR ) ) {
                    addInvalidValueMessage( message, MessageType.ERROR );
                }
                setValid( false );
            }
        }
    }

    /**
     * sets validation to invalid and creates a message if value is null
     *
     * @param value checked value
     * @param message set this message, if value is null
     */
    protected void validateValueNotNull( Object value, String message ) {
        if( value == null ) {
            if( getMessageOutputList() == null ) {
                setMessageOutputList( new ArrayList<>() );
            }
            if( isMessageNew( message, MessageType.MANDATORY ) ) {
                addNullValueMessage( message, MessageType.MANDATORY );
            }
            setValid( false );
        }
    }

    /**
     * sets validation to invalid and creates a message if value is negative or 0.0
     *
     * @param value checked value
     * @param message set this message, if value is negative or 0.0
     */
    protected void validateValueNotNegative( Object value, String message ) {
        if( value instanceof Number ) {
            double numericValue = ( (Number) value ).doubleValue();
            if( numericValue <= 0.0 ) {
                if( getMessageOutputList() == null ) {
                    setMessageOutputList( new ArrayList<>() );
                }
                if( isMessageNew( message, MessageType.ERROR ) ) {
                    addNegativeValueMessage( message, MessageType.ERROR );
                }
                setValid( false );
            }
        }
    }

    /**
     * sets validation to invalid and creates a message if value is null, negative or 0.0
     *
     * @param value checked value
     * @param message set this message, if value is null, negative or 0.0
     */
    protected void validateValueNotNullOrNegative(Object value, String message ) {
        validateValueNotNull( value, message );
        validateValueNotNegative( value, message );
    }

    /**
     * check if message already exists
     *
     * @param message checked message
     * @param messageType checked messageType
     * @return true if message is new and false if message already exists
     */
    protected boolean isMessageNew( String message, MessageType messageType) {
        if( getMessageOutputList() == null || getMessageOutputList().isEmpty() ) {
            return true;
        }

        for( MessageOutput messageOutput : getMessageOutputList() ) {
            if( messageOutput == null ) continue;
            if( messageOutput.getMessage() != null && messageOutput.getMessageType() != null
                    && messageOutput.getMessage().contains( message ) && messageOutput.getMessageType().equals( messageType ) ) {
                return false;
            }
        }
        return true;
    }

    public List<MessageOutput> getMessageOutputList() {
        return messageOutputList;
    }

    public void setMessageOutputList( List<MessageOutput> messageOutputList ) {
        this.messageOutputList = messageOutputList;
    }

    /**
     * add message to list if value is null
     *
     * @param message first part of the message
     * @param messageType MANDATORY if a value is null
     */
    protected void addNullValueMessage( String message, MessageType messageType ) {
        this.messageOutputList.add( new MessageOutput(message + ".isNull", messageType) );
    }

    /**
     * add message to list if value is negative
     *
     * @param message first part of the message
     * @param messageType ERROR if a value is negative
     */
    protected void addNegativeValueMessage( String message, MessageType messageType ) {
        this.messageOutputList.add( new MessageOutput(message + ".isNegativeOrZero", messageType) );
    }

    /**
     * add message to list if value is invalid
     *
     * @param message first part of the message
     * @param messageType ERROR if a value is invalid
     */
    protected void addInvalidValueMessage( String message, MessageType messageType ) {
        this.messageOutputList.add( new MessageOutput(message + ".isInvalid", messageType) );
    }

    public boolean isValid() {
        return valid;
    }

    /**
     * @param valid if it is set to false it can not be overwritten to true
     */
    public void setValid( boolean valid ) {
        if( isValid() ) {
            this.valid = valid;
        }
    }
}
