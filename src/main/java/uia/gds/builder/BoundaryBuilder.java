package uia.gds.builder;

import java.awt.Point;
import java.awt.Rectangle;

import com.ohrasys.cad.gds.GDSBoundaryRecord;
import com.ohrasys.cad.gds.GDSDatatypeRecord;
import com.ohrasys.cad.gds.GDSEndelRecord;
import com.ohrasys.cad.gds.GDSLayerRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSXyRecord;
import com.ohrasys.cad.gds.dao.Path;

/**
 * BOUNDARY
 * [ELFLAGS]
 * [PLEX]
 * LAYER
 * DATATYPE
 * XY
 *
 * @author ks026400
 *
 */
public class BoundaryBuilder implements GraphBuilder {

    private Point[] pts;

    private short layer;

    private Rectangle rect;

    public BoundaryBuilder(Path path) {
        this(path.getPoints(), (short) path.getLayer());

    }

    public BoundaryBuilder(Point[] pts, short layer) {
        this.pts = pts;
        this.layer = layer;

        double minX = pts[0].getX();
        double minY = pts[0].getY();
        double maxX = minX;
        double maxY = minY;
        for (int i = 1; i < pts.length; i++) {
            minX = Math.min(minX, pts[i].getX());
            minY = Math.min(minY, pts[i].getY());
            maxX = Math.min(maxX, pts[i].getX());
            maxY = Math.min(maxY, pts[i].getY());
        }
        this.rect = new Rectangle((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY));
    }

    public BoundaryBuilder(int minX, int minY, int maxX, int maxY, short layer) {
        this.pts = new Point[] {
                new Point(minX, minY),
                new Point(maxX, minY),
                new Point(maxX, maxY),
                new Point(minX, maxY)
        };
        this.layer = layer;

        this.rect = new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

    @Override
    public Rectangle getRect() {
        return this.rect;
    }

    @Override
    public GDSRecord[] records() throws GDSRecordException {
        return new GDSRecord[] {
                new GDSBoundaryRecord(),
                new GDSLayerRecord(this.layer),
                new GDSDatatypeRecord(GDSRecord.INT_TYPE),
                new GDSXyRecord(this.pts),
                new GDSEndelRecord()
        };
    }

    @Override
    public String toString() {
        return getRect().toString();
    }
}
