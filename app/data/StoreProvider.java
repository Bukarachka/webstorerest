package data;

import data.config.StagingConnectionConfig;
import org.mongodb.morphia.Datastore;

import javax.inject.Provider;


public class StoreProvider implements Provider<Datastore> {
    @Override
    public Datastore get() {
        return new Connection().store(new StagingConnectionConfig());
    }
}
