package uia.gds.builder;

import java.io.File;

import org.junit.Test;

import com.ohrasys.cad.gds.GDSOutputStream;

public class GDSFileBuilderTest {

    @Test
    public void test() throws Exception {
        GDSFileBuilder builder = new GDSFileBuilder(new GDSOutputStream(new File("d:\\temp\\gdstest.gds")));
        builder.begin();
        try (StructureBuilder struBuilder = builder.beginStructure("WAFER")) {
            struBuilder.writeElement(new BoundaryBuilder(-10, -10, 10, 10, (short) 2));
            struBuilder.writeElement(new BoundaryBuilder(-20, -20, 20, 20, (short) 3));
        }
        builder.end();
    }
}
