// package cn.mark.thread;
//
// /**
//  * 线程通信模拟生产者和消费者
//  * 生产者和消费者可能带来的问题：
//  * 1.可能生产速度大于消费速度，出现消息丢失。
//  * 2.可能消费的速度大于生产的速度，出现重复消费消息或者错误消息。
//  * 所以需要采用线程通信。
//  * wait用法注意：
//  * 1.必须在同步方法或者同步代码块中
//  * 2.采用循环判断，防止虚假唤醒。
//  *
//  * @author Mark
//  * @create 2020/3/12
//  */
// public class ProductorAndComsumerDemo {
//
//     public static void main(String[] args) {
//         Clerk clerk = new Clerk();
//         Productor productor = new Productor(clerk);
//         Comsumer comsumer = new Comsumer(clerk);
//         new Thread(productor, "生产者A").start();
//         new Thread(productor, "生产者B").start();
//         new Thread(comsumer, "消费者C").start();
//         new Thread(comsumer, "消费者D").start();
//     }
// }
//
// //生产者
// class Productor implements Runnable {
//
//     private Clerk clerk;
//
//     public Productor(Clerk clerk) {
//         this.clerk = clerk;
//     }
//
//     @Override
//     public void run() {
//         for (int i = 0; i < 20; i++) {
//             try {
//                 Thread.sleep(200);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//             clerk.set();
//         }
//     }
// }
//
// //消费者
// class Comsumer implements Runnable {
//
//     private Clerk clerk;
//
//     public Comsumer(Clerk clerk) {
//         this.clerk = clerk;
//     }
//
//     @Override
//     public void run() {
//         for (int i = 0; i < 20; i++) {
//             clerk.get();
//         }
//     }
// }
//
// //店员
// class Clerk {
//
//     private int productNum = 0;
//
//     //进货
//     public synchronized void set() {
//         while (productNum >= 10) {//采用while，而不是if,防止线程虚假唤醒
//             System.out.println("货已满~");
//             try {
//                 this.wait();
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }
//         System.out.println(Thread.currentThread().getName() + " ：" + (++productNum));
//         this.notifyAll();
//     }
//
//     //取货
//     public synchronized void get() {
//         while (productNum <= 0) {//采用while，而不是if,防止线程虚假唤醒
//             System.out.println("缺货~");
//             try {
//                 this.wait();
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }
//         System.out.println(Thread.currentThread().getName() + " ：" + (--productNum));
//         this.notifyAll();
//     }
//
//
// }
