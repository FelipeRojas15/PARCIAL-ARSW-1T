package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class PrimeFinder extends Thread{

    private BigInteger a;
    private BigInteger b;
    private PrimesResultSet prs;
    private int numHilos=0;
    private boolean running;
    //private int numeroHilos=4;
    public PrimeFinder(BigInteger a, BigInteger b, PrimesResultSet prs){
        this.a=a;
        this.b = b;
        this.prs = prs;
        this.numHilos = numHilos;
        System.out.println("Valor Inicial "+a + " Valor Final: "+ b );
        running=false;
    }
    @Override
    public void run () {
        MathUtilities mt = new MathUtilities();
        AtomicInteger itCount = new AtomicInteger(0);
        BigInteger i = a;

        while (i.compareTo(b) <= 0) {
            synchronized (this){
                while(!isRunning()){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


            itCount.getAndIncrement();
            //System.out.println(prs.getPrimes());
            if (mt.isPrime(i)) {
                //System.out.println(i+"..");
                prs.addPrime(i);
            }
            i = i.add(BigInteger.ONE);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void sleep(){
        this.running = false;

    }
    public synchronized void wakeUp(){
        this.running = true;
        notify();

    }

}




