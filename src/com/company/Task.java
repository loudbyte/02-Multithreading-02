package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class Task {

  private static final List<Integer> list = new ArrayList<>();

  public void produce() {
    try {
      while (true) {
        synchronized (this) {
          Random random = new Random();
          int randomInt = random.ints(1, 10).findFirst().getAsInt();
          randomInt = randomInt > 0 ? randomInt : -randomInt;
          list.add(randomInt);
          notify();
          Thread.sleep(1000);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void sum() {
    try {
      while (true) {
        synchronized (this) {
          while (list.size() == 0) {
            wait();
          }

          long sum = 0;
          for (Integer value : list) {
            sum = sum + value;
          }
          System.out.println("Sum = " + sum);
          notify();
          Thread.sleep(1000);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void square() {
    try {
      while (true) {
        synchronized (this) {
          while (list.size() == 0) {
            wait();
          }

          Iterator<Integer> iterator = list.iterator();
          Long sumOfSquares = 0L;
          while (iterator.hasNext()) {
            Integer val1 = iterator.next();
            if (iterator.hasNext()) {
              Integer val2 = iterator.next();
              sumOfSquares += ((long) val1 * val2);
            }
          }
          double sqrt = Math.sqrt(sumOfSquares);
          System.out.println("Sqrt = " + sqrt);
          notify();
          Thread.sleep(1000);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
