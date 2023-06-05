package hello.world.introspected;

import io.micronaut.core.annotation.AccessorsStyle;
import io.micronaut.core.annotation.Introspected;

@Introspected
@AccessorsStyle(readPrefixes = "", writePrefixes = "")
public class PersonPlus {

    private String name;
    private int age;

    public PersonPlus(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public int age() {
        return age;
    }

    public void age(int age) {
        this.age = age;
    }
}