N_Super_Queue
=============

Brief Description: 
N-super-queue consists of N linearizable queues. 

Two types of N-super-queue are implemented:

  - The subqueue is a regular sequential queue, protected against concurrent access by using a lock. Uses a native lock     synchronized blocks/methods.
  - The subqueue is a native concurrent queue (ConcurrentLinkedQueue).

Measures the throughput (number of operations enqueue/dequeue per second) of each implementation and includes an excel sheet comparing the throughput as a function of the number of threads. 

Details:
Initially each queue has 10*N objects and the operations are chosen randomly using the Random Java library for equal probability. For at least 5 seconds, the worker threads are run without measurement in order to allow Java's Just-In-Time compiler time to optimize the code. The measurement is run for 2 seconds, while the worker threads each count the number of operations performed. Finally, the number of operations across all threads is divided by the duration of the measurement and printed to the console.

All queues work with integers, but Java generics are utilized. 

The following was run locally and on a 64 core machine.

