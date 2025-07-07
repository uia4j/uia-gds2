package uia.gds.builder;

import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;

public interface ElementBuilder {

    public GDSRecord[] records() throws GDSRecordException;
}
