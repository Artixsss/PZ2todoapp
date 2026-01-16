package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {

    @Test
    void addAndList() {
        TodoList t = new TodoList();
        t.add("  task1  ");
        assertEquals(1, t.size());
        assertEquals("task1", t.getAll().get(0));
        assertFalse(t.isDone(0));
    }

    @Test
    void remove() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertEquals("b", t.getAll().get(0));
        assertFalse(t.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add("   ");
        assertEquals(0, t.size());
    }

    @Test
    void clearRemovesAll() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertEquals(2, t.size());

        t.clear();
        assertEquals(0, t.size());
        assertTrue(t.getAll().isEmpty());
    }

    @Test
    void doneMarksTaskAsDone() {
        TodoList t = new TodoList();
        t.add("task");
        assertFalse(t.isDone(0));

        assertTrue(t.markDone(0));
        assertTrue(t.isDone(0));

        // проверим форматирование
        assertEquals("[x] task", t.getAllFormatted().get(0));
    }

    @Test
    void doneOutOfRangeReturnsFalse() {
        TodoList t = new TodoList();
        t.add("a");
        assertFalse(t.markDone(-1));
        assertFalse(t.markDone(1));
    }

    @Test
    void searchFindsBySubstringCaseInsensitive() {
        TodoList t = new TodoList();
        t.add("Buy milk");
        t.add("Call mom");
        t.add("Milkshake");

        List<String> found = t.search("milk");
        assertEquals(2, found.size());
        assertTrue(found.get(0).toLowerCase().contains("milk"));
        assertTrue(found.get(1).toLowerCase().contains("milk"));
    }

    @Test
    void searchEmptyReturnsEmptyList() {
        TodoList t = new TodoList();
        t.add("a");
        assertTrue(t.search("").isEmpty());
        assertTrue(t.search("   ").isEmpty());
        assertTrue(t.search(null).isEmpty());
    }
}
