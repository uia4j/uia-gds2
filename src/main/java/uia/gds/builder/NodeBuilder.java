package uia.gds.builder;

import java.awt.Point;

import com.ohrasys.cad.gds.GDSEndelRecord;
import com.ohrasys.cad.gds.GDSLayerRecord;
import com.ohrasys.cad.gds.GDSNodeRecord;
import com.ohrasys.cad.gds.GDSNodetypeRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSXyRecord;

/**
 * NODE
 * [ELFLAGS]
 * [PLEX]
 * LAYER
 * NODETYPE
 * XY
 *
 * @author ks026400
 *
 */
public class NodeBuilder implements ElementBuilder {

    private Point[] points;

    private short layer;

    public NodeBuilder(Point[] points, short layer) {
        this.points = points;
        this.layer = layer;
    }

    public Point[] getPoints() {
        return this.points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public short getLayer() {
        return this.layer;
    }

    public void setLayer(short layer) {
        this.layer = layer;
    }

    @Override
    public GDSRecord[] records() throws GDSRecordException {
        return new GDSRecord[] {
                new GDSNodeRecord(),
                new GDSLayerRecord(this.layer),
                new GDSNodetypeRecord(GDSRecord.NODETYPE),
                new GDSXyRecord(this.points),
                new GDSEndelRecord()
        };
    }
}
