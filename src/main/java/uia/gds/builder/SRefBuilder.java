package uia.gds.builder;

import java.awt.Point;

import com.ohrasys.cad.gds.GDSEndelRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSSnameRecord;
import com.ohrasys.cad.gds.GDSSrefRecord;
import com.ohrasys.cad.gds.GDSXyRecord;

/**
 * SREF
 * [ELFLAGS]
 * [PLEX]
 * SNAME
 * [STRANS [MAG] [ANGLE]]
 * XY
 *
 * @author ks026400
 *
 */
public class SRefBuilder implements ElementBuilder {

    private String name;

    private Point[] points;

    public SRefBuilder(String name) {
        this.name = name;
        this.points = new Point[0];
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point[] getPoints() {
        return this.points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    @Override
    public GDSRecord[] records() throws GDSRecordException {
        return new GDSRecord[] {
                new GDSSrefRecord(),
                new GDSSnameRecord(this.name),
                new GDSXyRecord(this.points),
                new GDSEndelRecord(),
        };
    }
}
