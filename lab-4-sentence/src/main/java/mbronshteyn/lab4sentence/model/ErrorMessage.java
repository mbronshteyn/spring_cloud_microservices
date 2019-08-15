package mbronshteyn.lab4sentence.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ErrorMessage {

    private LocalDateTime timestamp;
    private String message;

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public static final class Builder {
        private LocalDateTime timestamp;
        private String message;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorMessage build() {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setTimestamp(timestamp);
            errorMessage.setMessage(message);
            return errorMessage;
        }
    }
}
