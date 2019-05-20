package data;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;


public class MorphiaDAO<T> {

    public <T> Key<T> save(Datastore datastore, T t) {
        return datastore.save(t);
    }
}
