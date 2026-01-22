package org.example.event;

import lombok.Getter;

@Getter
public class Event implements Comparable<Event> {
    public enum Type {
        INFO(2),       // General game flow messages
        HIT(8),        // Successful hit
        MISS(5),       // Missed shot
        SUNK(7),      // Ship sunk
        ERROR(9),      // Invalid input/moves
        GAME_OVER(1), // Win condition
        INPUT(10),
        BOARD_VIEW(2); // For printing the board (special handling)

        final int defaultPriority;

        Type(int defaultPriority) {
            this.defaultPriority = defaultPriority;
        }
    }

    private final Type type;
    private final String message;
    private final int priority;
    private final long timestamp;

    public Event(Type type, String message) {
        this.type = type;
        this.message = message;
        this.priority = type.defaultPriority;
        this.timestamp = System.currentTimeMillis();
    }

    // Constructor allowing custom priority override
    public Event(Type type, String message, int priority) {
        this.type = type;
        this.message = message;
        this.priority = priority;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public int compareTo(Event other) {
        // Sort by Priority DESC (High first), then Timestamp ASC (Old first)
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }
        return Long.compare(this.timestamp, other.timestamp);
    }
}
