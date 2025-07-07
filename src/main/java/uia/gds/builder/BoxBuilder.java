package uia.gds.builder;

import java.awt.Point;
import java.awt.Rectangle;

import com.ohrasys.cad.gds.GDSBoxRecord;
import com.ohrasys.cad.gds.GDSBoxtypeRecord;
import com.ohrasys.cad.gds.GDSEndelRecord;
import com.ohrasys.cad.gds.GDSLayerRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSXyRecord;

/**
 * BOX
 * [ELFLAGS]
 * [PLEX]
 * LAYER
 * BOXTYPE
 * XY
 *
 * @author ks026400
 *
 */
public class BoxBuilder implements GraphBuilder {

    private Point[] points;

    private Rectangle rect;

    private short layer;

    public BoxBuilder(int x, int y, int w, int h, short layer) {
        this.points = new Point[] {
                new Point(x, y),
                new Point(x + w, y),
                new Point(x + w, y + h),
                new Point(x, y + h)
        };
        this.rect = new Rectangle(x, y, w, h);
        this.layer = layer;
    }

    public BoxBuilder(int x, int y, int w, int h, short layer, int scale) {
        this.points = new Point[] {
                new Point(x * scale, y * scale),
                new Point((x + w) * scale, y * scale),
                new Point((x + w) * scale, (y + h) * scale),
                new Point(x * scale, (y + h) * scale)
        };
        this.rect = new Rectangle(x * scale, y * scale, (x + w) * scale, (y + h) * scale);
        this.layer = layer;
    }

    @Override
    public Rectangle getRect() {
        return this.rect;
    }

    @Override
    public GDSRecord[] records() throws GDSRecordException {
        return new GDSRecord[] {
                new GDSBoxRecord(),
                new GDSLayerRecord(this.layer),
                new GDSBoxtypeRecord(GDSRecord.INT_TYPE),
                new GDSXyRecord(this.points),
                new GDSEndelRecord()
        };
    }

    @Override
    public String toString() {
        return getRect().toString();
    }

}
