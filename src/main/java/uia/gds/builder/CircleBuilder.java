package uia.gds.builder;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.ohrasys.cad.gds.GDSDatatypeRecord;
import com.ohrasys.cad.gds.GDSEndelRecord;
import com.ohrasys.cad.gds.GDSLayerRecord;
import com.ohrasys.cad.gds.GDSPathRecord;
import com.ohrasys.cad.gds.GDSPathtypeRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSWidthRecord;
import com.ohrasys.cad.gds.GDSXyRecord;

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
public class CircleBuilder implements GraphBuilder {

    private final Point center;

    private final int radius;

    private final short layer;

    private final boolean fill;

    private final Rectangle rect;

    public CircleBuilder(int cx, int cy, int radius, boolean fill, short layer) {
        this.center = new Point(cx, cy);
        this.radius = radius;
        this.rect = new Rectangle(
                cx - radius,
                cy - radius,
                2 * radius,
                2 * radius);
        this.fill = fill;
        this.layer = layer;
    }

    public Point getCenter() {
        return this.center;
    }

    @Override
    public Rectangle getRect() {
        return this.rect;
    }

    @Override
    public GDSRecord[] records() throws GDSRecordException {
        if (this.fill) {
            return new GDSRecord[] {
                    new GDSPathRecord(),
                    new GDSLayerRecord(this.layer),
                    new GDSDatatypeRecord(GDSRecord.INT_TYPE),
                    new GDSPathtypeRecord(GDSPathtypeRecord.ROUND),
                    new GDSWidthRecord(this.radius * 2),
                    new GDSXyRecord(new Point[] { this.center }),
                    new GDSEndelRecord()
            };
        }

        List<Point> pts = new ArrayList<>();
        for (int a = 0; a < 360; a++) {
            double rad = Math.toRadians(a);
            double sin = Math.abs(a) == 180 ? 0 : Math.sin(rad);
            double cos = Math.abs(a) == 90 || Math.abs(a) == 270 ? 0 : Math.cos(rad);
            int vx = (int) (this.radius * cos);
            int vy = (int) (this.radius * sin);
            pts.add(new Point(this.center.x + vx, this.center.y + vy));
        }
        return new GDSRecord[] {
                new GDSPathRecord(),
                new GDSLayerRecord(this.layer),
                new GDSDatatypeRecord(GDSRecord.INT_TYPE),
                new GDSPathtypeRecord(GDSPathtypeRecord.VARIABLE),
                new GDSXyRecord(pts.toArray(new Point[0])),
                new GDSEndelRecord()
        };
    }

    @Override
    public String toString() {
        return getRect().toString();
    }
}
