package org.example;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<String> items = new ArrayList<>();
    private final List<Boolean> done = new ArrayList<>();

    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                items.add(item);
                done.add(false);
            }
        }
    }

    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            done.remove(index);
            return true;
        }
        return false;
    }

    public void clear() {
        items.clear();
        done.clear();
    }

    public boolean markDone(int index) {
        if (index >= 0 && index < items.size()) {
            done.set(index, true);
            return true;
        }
        return false;
    }

    public List<String> search(String query) {
        String q = (query == null) ? "" : query.trim().toLowerCase();
        List<String> result = new ArrayList<>();
        if (q.isEmpty()) return result;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).toLowerCase().contains(q)) {
                result.add(formatItem(i));
            }
        }
        return result;
    }

    public List<String> getAllFormatted() {
        List<String> out = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            out.add(formatItem(i));
        }
        return out;
    }

    public List<String> getAll() {
        return new ArrayList<>(items);
    }

    public boolean isDone(int index) {
        if (index >= 0 && index < done.size()) {
            return done.get(index);
        }
        return false;
    }

    public int size() {
        return items.size();
    }

    private String formatItem(int index) {
        return String.format("%s %s", done.get(index) ? "[x]" : "[ ]", items.get(index));
    }
}
