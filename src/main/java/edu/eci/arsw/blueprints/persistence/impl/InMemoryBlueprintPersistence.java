/*
 * Este es un componente de persistencia de Blueprints en memoria que implementa la interfaz BlueprintsPersistence.
 * Utiliza un mapa para almacenar los Blueprints en memoria.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementación de la interfaz BlueprintsPersistence que almacena Blueprints en memoria.
 */
@Component
@Qualifier("Memory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final ConcurrentHashMap<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        Point[] pts = new Point[]{new Point(140, 140), new Point(115, 115)};
        Point[] pts2 =new Point[]{new Point(110, 130),new Point(125, 120)};
        Point[] pts3 =new Point[]{new Point(110, 220),new Point(135, 130)};
        Blueprint bp=new Blueprint("autor_1", "bps_1",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        Blueprint bp2 =new Blueprint("autor_2", "bps_2",pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        Blueprint bp3 =new Blueprint("autor_3", "bps_3",pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("El Blueprint proporcionado ya existe: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> prints = new HashSet<>();
        for (Tuple<String, String> tuple : this.blueprints.keySet()) {
            if (tuple.o1.equals(author)) {
                prints.add(blueprints.get(tuple));
            }
        }
        return prints;
    }

    @Override
    public Set<Blueprint> getBluePrints() throws BlueprintPersistenceException, BlueprintNotFoundException {
        Set<Blueprint> prints = new HashSet<>();
        for (Tuple<String, String> tuple : this.blueprints.keySet()) {
            prints.add(blueprints.get(tuple));
        }
        return prints;
    }
}
