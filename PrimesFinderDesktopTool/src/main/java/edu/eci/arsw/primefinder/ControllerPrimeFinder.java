package edu.eci.arsw.primefinder;

import java.math.BigInteger;
import java.util.ArrayList;

public class ControllerPrimeFinder {
    private BigInteger a;
    private BigInteger b;
    private PrimesResultSet prs;
    private BigInteger numHilos;
    ArrayList<PrimeFinder> hilosList = new ArrayList<PrimeFinder>();
    public ControllerPrimeFinder(BigInteger _a, BigInteger _b, PrimesResultSet prs, BigInteger numHilos){

        this.a=_a;
        this.b =_b;
        this.prs = prs;
        this.numHilos = numHilos;
        dataDivision();
    }
    public  void dataDivision(){
        BigInteger rango = b.divide(numHilos);


        BigInteger bigRango = rango;
        //System.out.println(a+".."+rango);

        //hilosList.add(hilis);
        for (int i=0; i<numHilos.intValue();i++){
            PrimeFinder hilos;

            hilos = new PrimeFinder(a,bigRango,prs);
            a= a.add(rango);
            bigRango= bigRango.add(rango);
            hilosList.add(hilos);

        }
        for(PrimeFinder hilo:hilosList){
            hilo.start();
        }
        for(PrimeFinder hilo: hilosList){
            hilo.wakeUp();
        }



    }




}