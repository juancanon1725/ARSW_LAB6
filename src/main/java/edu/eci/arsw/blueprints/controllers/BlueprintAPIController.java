/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bps;

    @GetMapping()
    public ResponseEntity<?> getAllBlueprint() throws ResourceNotFoundException{
        try{
            return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.OK);
        }catch(BlueprintNotFoundException | BlueprintPersistenceException ex){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{author}")
    public ResponseEntity<?> getBlueprintByAuthor(@PathVariable("author") String author) throws ResourceNotFoundException, BlueprintNotFoundException, BlueprintPersistenceException {
        Set<Blueprint> blueprints = bps.getBlueprintsByAuthor(author);
        if (blueprints.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(blueprints, HttpStatus.OK);
    }

    @GetMapping(value="/{author}/{name}")
    public ResponseEntity<?>  getBlueprintByAuthorAndName(@PathVariable("author") String author,@PathVariable("name") String name )throws ResourceNotFoundException, BlueprintNotFoundException{
        try {
            return new ResponseEntity<>(bps.getBlueprint(author,name), HttpStatus.OK);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/addBlueprint", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> postBlueprintResourceHandler(@RequestBody Blueprint bp) {
        ResponseEntity<?> mensaje;
        try {
            bps.addNewBlueprint(bp);
            mensaje = new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException e) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            mensaje = new ResponseEntity<>("El nombre del plano ya existe", HttpStatus.NOT_ACCEPTABLE);
        }
        return mensaje;
    }

    @PutMapping(value = "/{author}/{bpname}")
    public ResponseEntity<?> manejadorPutRecursoBluePrint(@PathVariable String author, @PathVariable String bpname, @RequestBody List<Point> points) {
       ResponseEntity<?> mensaje;
       try {
           bps.updateBluePrint(author, bpname, points);
           Blueprint bp = bps.getBlueprint(author, bpname);
           mensaje = new ResponseEntity<>(bp, HttpStatus.ACCEPTED);
       } catch (BlueprintNotFoundException e) {
           mensaje = new ResponseEntity<>("No existe el plano con el nombre dado", HttpStatus.NOT_FOUND);
       }
       return mensaje;
   }

}

