/*
 * Este paquete pertenece al proyecto de servicios para blueprints.
 */
package edu.eci.arsw.blueprints.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

/**
 * Esta clase representa los servicios relacionados con los blueprints.
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {

    @Autowired
    @Qualifier("Memory")
    BlueprintsPersistence bpp = null;

    /**
     * Agrega un nuevo blueprint.
     *
     * @param bp El blueprint a agregar.
     * @throws BlueprintPersistenceException Si ocurre un error al guardar el blueprint.
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    /**
     * Obtiene todos los blueprints disponibles.
     *
     * @return Un conjunto de todos los blueprints.
     * @throws BlueprintNotFoundException Si no se encuentran blueprints.
     * @throws BlueprintPersistenceException Si ocurre un error en la persistencia de los blueprints.
     */
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException, BlueprintPersistenceException {
        return bpp.getBluePrints();
    }

    /**
     * Obtiene todos los blueprints de un autor específico.
     *
     * @param author El autor de los blueprints.
     * @return Un conjunto de blueprints del autor especificado.
     * @throws BlueprintNotFoundException Si el autor especificado no existe.
     * @throws BlueprintPersistenceException Si ocurre un error en la persistencia de los blueprints.
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException, BlueprintPersistenceException {
        return bpp.getBlueprintsByAuthor(author);
    }

    /**
     * Obtiene un blueprint específico por autor y nombre.
     *
     * @param author El autor del blueprint.
     * @param name El nombre del blueprint.
     * @return El blueprint correspondiente al autor y nombre especificados.
     * @throws BlueprintNotFoundException Si no existe el blueprint especificado.
     */
    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        return bpp.getBlueprint(author, name);
    }

    /**
     * Actualiza un blueprint existente.
     *
     * @param author El autor del blueprint.
     * @param bpname El nombre del blueprint.
     * @param points La nueva lista de puntos.
     * @throws BlueprintNotFoundException Si no existe el blueprint especificado.
     */
    public void updateBluePrint(String author, String bpname, List<Point> points) throws BlueprintNotFoundException {
        Blueprint bp = getBlueprint(author,bpname);
        System.out.println("Before: "+bp.toString());
        bp.setPoints(points);
        System.out.println("After: "+bp.toString());
    }


}
