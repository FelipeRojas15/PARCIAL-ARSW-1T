package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

	public static void main(String[] args) {

        boolean t=true;
        int maxPrim=1000;

        PrimesResultSet prs=new PrimesResultSet("john");
        ControllerPrimeFinder controlador  = new ControllerPrimeFinder(new BigInteger("1"), new BigInteger("10000"), prs,new BigInteger("4"));


        System.out.println("Prime numbers found:");

        System.out.println(prs.getPrimes());

        while(t) {
            try {
                t = false;
                //check every 10ms if the idle status (10 seconds without mouse
                //activity) was reached.
                Thread.sleep(10);
                if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000) {
                    System.out.println("Stoped");
                    System.out.println("Primes found: " + Integer.toString(prs.getPrimes().size()));
                    System.out.println("Prime numbers found:");
                    System.out.println(prs.getPrimes());
                    for (PrimeFinder p : controlador.hilosList) {
                        p.sleep();
                    }

                } else {
                    //System.out.println("Working...");
                    for (PrimeFinder p : controlador.hilosList) {
                        p.wakeUp();
                    }


                }

                for (PrimeFinder p : controlador.hilosList) {
                    if (p.isAlive()) {
                        t = true;
                        break;
                    }

                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Task Finished...");
        System.out.println("Primes found: "+Integer.toString(prs.getPrimes().size()));
        System.out.println("Primes Found:");
        System.out.println(prs.getPrimes());
        System.exit(-1);


    }
                        
            
            
            
            
}
	



