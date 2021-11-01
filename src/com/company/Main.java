package com.company;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Task task = new Task();

    Thread producer = new Thread(task::produce);
    Thread summer = new Thread(task::sum);
    Thread squarer = new Thread(task::square);

    producer.start();
    summer.start();
    squarer.start();

    producer.join();
    summer.join();
    squarer.join();
  }
}
