package at.htlhl;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void translate() {
        Field field = Field.EMPTY;
        field.translate(field);
        assertEquals(' ', field.translate(field));

        field = Field.HEAD;
        field.translate(field);
        assertEquals('@', field.translate(field));

        field = Field.BODY;
        field.translate(field);
        assertEquals('*', field.translate(field));

        field = Field.APPLE;
        field.translate(field);
        assertEquals('$', field.translate(field));

        field = Field.STONE;
        field.translate(field);
        assertEquals('#', field.translate(field));
    }
}