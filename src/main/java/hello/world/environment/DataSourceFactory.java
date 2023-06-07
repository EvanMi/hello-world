package hello.world.environment;

import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;

import javax.sql.DataSource;
import java.net.URI;

//@Factory
public class DataSourceFactory {

    @EachBean(DataSourceConfiguration.class)// (2)
    DataSource dataSource(DataSourceConfiguration configuration) { // (3)
        URI url = configuration.getUrl();
        return null;
    }
}
