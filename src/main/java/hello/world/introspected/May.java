package hello.world.introspected;

import io.micronaut.core.annotation.Introspected;

@Introspected
public enum May {
    MAY1("May1"),MAY2("May2"),MAY3("May3");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    May(String name) {
        this.name = name;
    }
}
