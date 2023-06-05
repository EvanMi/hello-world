package hello.world.annnotation.meta;

import jakarta.inject.Named;

@Named("bookbook")
public class BookRepository extends BaseSqlRepository{

    private final String name = "book repository ~";

    public String getName() {
        return name;
    }
}
