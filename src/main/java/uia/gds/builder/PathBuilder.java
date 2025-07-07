package uia.gds.builder;

import java.awt.Point;
import java.awt.Rectangle;

import com.ohrasys.cad.gds.GDSDatatypeRecord;
import com.ohrasys.cad.gds.GDSEndelRecord;
import com.ohrasys.cad.gds.GDSLayerRecord;
import com.ohrasys.cad.gds.GDSPathRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSWidthRecord;
import com.ohrasys.cad.gds.GDSXyRecord;
import com.ohrasys.cad.gds.dao.Path;

/**
 * https://jupiter.math.nycu.edu.tw/~weng/courses/IC_2007/PROJECT_NCTU_MATH/CELL_LAYOUT/The%20GDSII%20Stream%20Format.htm
 *
 * PATH
 * [ELFLAGS]
 * [PLEX]
 * LAYER
 * DATATYPE
 * [PATHTYPE]
 * [WIDTH]
 * [BGNEXTN]
 * [ENDEXTN]
 * XY
 *
 * @author ks026400
 *
 */
public class PathBuilder implements GraphBuilder {

    private Point[] pts;

    private short layer;

    private Integer width;

    private Rectangle rect;

    public PathBuilder(Path path) {
        this.pts = path.getPoints();
        this.layer = (short) path.getLayer();
    }

    /**
     * Create a path.
     *
     * @param pts
     * @param layer
     */
    public PathBuilder(Point[] pts, short layer) {
        this.pts = pts;
        this.layer = layer;
        int minX = pts[0].x;
        int minY = pts[0].y;
        int maxX = pts[0].x;
        int maxY = pts[0].y;
        for (int i = 1; i < pts.length; i++) {
            minX = Math.min(minX, pts[i].x);
            minY = Math.min(minY, pts[i].y);
            maxX = Math.max(maxX, pts[i].x);
            maxY = Math.max(maxY, pts[i].y);
        }
        this.rect = new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

    /**
     * Creates a circular path.
     * @param cx
     * @param cy
     * @param width
     * @param layer
     */
    public PathBuilder(int cx, int cy, int width, short layer) {
        this.pts = new Point[1];
        this.pts[0] = new Point(cx, cy);
        this.layer = layer;
        this.width = width;
        this.rect = new Rectangle(cx - width / 2, cy - width / 2, width, width);
    }

    @Override
    public Rectangle getRect() {
        return this.rect;
    }

    @Override
    public GDSRecord[] records() throws GDSRecordException {
        if (this.width == null) {
            return new GDSRecord[] {
                    new GDSPathRecord(),
                    new GDSLayerRecord(this.layer),
                    new GDSDatatypeRecord(GDSRecord.SHORT_TYPE),
                    new GDSXyRecord(this.pts),
                    new GDSEndelRecord()
            };
        }
        else {
            return new GDSRecord[] {
                    new GDSPathRecord(),
                    new GDSLayerRecord(this.layer),
                    new GDSDatatypeRecord(GDSRecord.SHORT_TYPE),
                    new GDSWidthRecord(this.width),
                    new GDSXyRecord(this.pts),
                    new GDSEndelRecord()
            };

        }
    }
}
