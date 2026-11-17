package com.greatmindsworking.jedison;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RAListTest {

    private static RAList region(int label) {
        RAList node = new RAList();
        node.label = label;
        return node;
    }

    @Test
    void defaultNodeStartsEmpty() {
        RAList head = new RAList();
        assertEquals(-1, head.label);
        assertNull(head.next);
        assertEquals(0.0f, head.edgeStrength);
        assertEquals(0, head.edgePixelCount);
    }

    @Test
    void insertsSortedAndRejectsDuplicateLabels() {
        RAList head = new RAList();
        RAList ten = region(10);
        RAList twenty = region(20);
        RAList thirty = region(30);
        RAList fifteen = region(15);

        assertEquals(0, head.Insert(twenty));
        assertEquals(0, head.Insert(ten));
        assertEquals(0, head.Insert(thirty));
        assertEquals(0, head.Insert(fifteen));
        assertEquals(1, head.Insert(region(20)), "Existing label must be rejected");

        assertSame(ten, head.next);
        assertSame(fifteen, ten.next);
        assertSame(twenty, fifteen.next);
        assertSame(thirty, twenty.next);
        assertNull(thirty.next);
    }
}
