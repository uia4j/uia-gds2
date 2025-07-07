package uia.gds.builder;

import java.io.Closeable;
import java.io.IOException;

import com.ohrasys.cad.gds.GDSEndstrRecord;
import com.ohrasys.cad.gds.GDSOutputStream;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;

public class StructureBuilder implements Closeable {

    private GDSOutputStream gos;

    public StructureBuilder(GDSOutputStream gos) {
        this.gos = gos;
    }

    public StructureBuilder writeElement(GraphBuilder builder) throws IOException, GDSRecordException {
        for (GDSRecord rec : builder.records()) {
            this.gos.writeRecord(rec);
        }
        return this;
    }

    @Override
    public void close() throws IOException {
        try {
            this.gos.writeRecord(new GDSEndstrRecord());
        }
        catch (GDSRecordException e) {
            throw new IOException(e);
        }
    }

}
