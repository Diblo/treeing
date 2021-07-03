package org.xeustechnologies.treeing;

import java.util.*;

public class CrawlQueue<E> {
    private final LinkedList<E> tasks = new LinkedList<>();
    private final ArrayList<E> deposed_tasks = new ArrayList<>();
    private final LinkedList<E> done_tasks = new LinkedList<>();

    public synchronized void put(E o) {
        if (!tasks.contains(o) && !deposed_tasks.contains(o) && !done_tasks.contains(o)) {
            tasks.push(o);
        }

        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (tasks.size() == 0) {
            if ( isAllDone() ) {
                throw new InterruptedException();
            }

            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }

        E o = tasks.pop();
        deposed_tasks.add(o);

        notifyAll();

        return o;
    }

    public synchronized void done(E o) {
        int index = deposed_tasks.indexOf(o);
        if ( index != -1 ) {
            deposed_tasks.remove(index);

            done_tasks.push(o);
        }

        notifyAll();
    }

    public boolean isAllDone() {
        return tasks.size() == 0 && deposed_tasks.size() == 0 && done_tasks.size() > 0;
    }
}
