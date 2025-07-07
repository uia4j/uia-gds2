package uia.gds.builder;

import java.awt.Rectangle;

import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;

public interface GraphBuilder {

    public Rectangle getRect();

    public GDSRecord[] records() throws GDSRecordException;
}
