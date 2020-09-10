package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController
{
    @Autowired
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = GET )
    public List<FoundPrime> getPrimes()
    {
        return primeService.getFoundPrimes();
    }

    @GetMapping("/primes/{prime}")
    public ResponseEntity<?> returnPrime(@PathVariable String prime) {
        try {
            FoundPrime data = primeService.getPrime(prime);

            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("No se encontro el numero", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/primes")
    public ResponseEntity<?> additionPrime(@RequestBody FoundPrime primes) {
        try {
            primeService.addFoundPrime(primes);
            return new ResponseEntity<>(primes, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("No se puede agregar este numero", HttpStatus.CONFLICT);
        }
    }

}
