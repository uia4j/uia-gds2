package uia.gds.builder;

import java.awt.Point;

import com.ohrasys.cad.gds.GDSArefRecord;
import com.ohrasys.cad.gds.GDSColrowRecord;
import com.ohrasys.cad.gds.GDSEndelRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSSnameRecord;
import com.ohrasys.cad.gds.GDSXyRecord;

/**
 * AREF
 * [ELFLAGS]
 * [PLEX]
 * SNAME
 * [STRANS [MAG] [ANGLE]]
 * COLROW
 * XY
 *
 * @author ks026400
 *
 */
public class ARefBuilder implements ElementBuilder {

    private String name;

    private short col;

    private short row;

    private Point[] points;

    public ARefBuilder(String name) throws GDSRecordException {
        this.name = name;
        this.points = new Point[0];
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getCol() {
        return this.col;
    }

    public void setCol(short col) {
        this.col = col;
    }

    public short getRow() {
        return this.row;
    }

    public void setRow(short row) {
        this.row = row;
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
                new GDSArefRecord(),
                new GDSSnameRecord(this.name),
                new GDSColrowRecord(this.col, this.row),
                new GDSXyRecord(this.points),
                new GDSEndelRecord()
        };
    }
}
