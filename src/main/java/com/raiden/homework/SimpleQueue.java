package com.raiden.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: Raiden
 * Date: 2019/6/13
 */

public class SimpleQueue {
    ReentrantLock lock = new ReentrantLock();
    Condition conFull = lock.newCondition();
    Condition conEmpty = lock.newCondition();
    List<Object> queue = new ArrayList<Object>();
    int max = 10;

    public void put(Object o) {
        lock.lock();
        try {
            queue.add(o);
            while (queue.size() >= max) {
                conFull.await();
            }
            if (queue.size() >0)
                conEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public Object take() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                conEmpty.await();
            }
            Object result = queue.get(0);
            queue.remove(0);

            if (queue.size() == max-1) {
                conFull.signal();
            }
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }

    }
}
