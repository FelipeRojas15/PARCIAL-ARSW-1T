package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@Service
public class PrimeServiceStub implements PrimeService {

    /**
     * The Primos.
     */
    CopyOnWriteArrayList<FoundPrime> listaPrimos;

    /**
     * Instantiates a new Prime service stub.
     */
    public PrimeServiceStub() {
        this.listaPrimos = new CopyOnWriteArrayList<>();
        listaPrimos.add(new FoundPrime("Juan", "2"));
        listaPrimos.add(new FoundPrime("Felipe", "3"));

    }
    @Override
    public void addFoundPrime( FoundPrime foundPrime ) throws Exception {
        for(FoundPrime f:listaPrimos) {
            if(f.getPrime().equals(foundPrime.getPrime())){
                throw new Exception("Primo ya existente");
            }
        }
        listaPrimos.add(foundPrime);
    }

    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        return listaPrimos;
    }

    @Override
    public FoundPrime getPrime( String prime ) throws Exception {
        FoundPrime primo=null;
        busqueda:
        for(FoundPrime i:listaPrimos) {
            if(i.getPrime().equals(prime)){
                primo=i;
                break busqueda;
            }
        }
        if(primo==null){
            throw new Exception("Primo no encontrado");
        }
        return primo;
    }
}
