package com.example.ams.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.ams.entities.commande;

public interface commandeRepository  extends CrudRepository<commande, Long> {

}
