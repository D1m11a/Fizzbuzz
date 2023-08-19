package FizzBuzz;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz {

    private final int n;
    private volatile int currentNumber;
    private final BlockingQueue<String> queue;

    public FizzBuzz(int n) {
        this.n = n;
        this.currentNumber = 1;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void fizz() throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                queue.put("fizz");
            }
        }
    }

    public void buzz() throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                queue.put("buzz");
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            queue.put("fizzbuzz");
        }
    }

    public void number() throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                queue.put(Integer.toString(currentNumber));
            }
            currentNumber++;

        }
    }

    public static void main(String[] args) {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (fizzBuzz.currentNumber <= n) {
            try {
                System.out.println(fizzBuzz.queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}