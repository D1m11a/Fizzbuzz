package FizzBuzzMultithreaded;
import java.util.concurrent.*;

public class FizzBuzzMultithreaded {
    private final int n;
    private int current = 1;
    private final Semaphore semaphore = new Semaphore(1);

    public FizzBuzzMultithreaded(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 3 == 0 && i % 5 != 0) {
                semaphore.acquire();
                System.out.println("fizz");
                current++;
                semaphore.release();
            }
        }
    }

    public void buzz() throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0 && i % 5 == 0) {
                semaphore.acquire();
                System.out.println("buzz");
                current++;
                semaphore.release();
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            semaphore.acquire();
            System.out.println("fizzbuzz");
            current++;
            semaphore.release();
        }
    }

    public void number() throws InterruptedException {
        while (current <= n) {
            semaphore.acquire();
            if (current % 3 != 0 && current % 5 != 0) {
                System.out.println(current);
                current++;
            }
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        int n = 15;
        FizzBuzzMultithreaded fizzBuzz = new FizzBuzzMultithreaded(n);

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
    }
}
