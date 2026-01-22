package org.example.event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testConstructorWithDefaultPriority() {
        String message = "Informational message";
        Event event = new Event(Event.Type.INFO, message);
        
        assertEquals(Event.Type.INFO, event.getType());
        assertEquals(message, event.getMessage());
        assertEquals(Event.Type.INFO.defaultPriority, event.getPriority());
        assertTrue(event.getTimestamp() > 0);
    }

    @Test
    void testConstructorWithCustomPriority() {
        int customPriority = 15;
        Event event = new Event(Event.Type.HIT, "Custom priority hit", customPriority);
        
        assertEquals(customPriority, event.getPriority());
        assertEquals(Event.Type.HIT, event.getType());
    }

    @Test
    void testCompareToPrioritizesHigherValue() {
        // ERROR defaultPriority is 9, INFO defaultPriority is 2
        Event highPriorityEvent = new Event(Event.Type.ERROR, "High");
        Event lowPriorityEvent = new Event(Event.Type.INFO, "Low");

        // compareTo should return negative if 'this' (high) comes before 'other' (low)
        assertTrue(highPriorityEvent.compareTo(lowPriorityEvent) < 0, 
            "Higher priority should come first (negative result)");
        assertTrue(lowPriorityEvent.compareTo(highPriorityEvent) > 0, 
            "Lower priority should come last (positive result)");
    }

    @Test
    void testCompareToSamePrioritySortsByTimestamp() throws InterruptedException {
        Event event1 = new Event(Event.Type.INFO, "Message 1");
        Thread.sleep(5); // Ensure distinct timestamps
        Event event2 = new Event(Event.Type.INFO, "Message 2");

        // Same priority, earlier timestamp should come first
        assertTrue(event1.compareTo(event2) < 0, 
            "Earlier event should come first when priorities are equal");
        assertTrue(event2.compareTo(event1) > 0, 
            "Later event should come last when priorities are equal");
    }

    @Test
    void testCompareToSamePriorityAndTimestamp() {
        Event event = new Event(Event.Type.INFO, "Message");
        assertEquals(0, event.compareTo(event), "Comparing event to itself should return 0");
    }
}
