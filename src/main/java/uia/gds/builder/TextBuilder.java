package uia.gds.builder;

import java.awt.Point;

import com.ohrasys.cad.gds.GDSEndelRecord;
import com.ohrasys.cad.gds.GDSLayerRecord;
import com.ohrasys.cad.gds.GDSPresentationRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSStringRecord;
import com.ohrasys.cad.gds.GDSTextRecord;
import com.ohrasys.cad.gds.GDSTexttypeRecord;
import com.ohrasys.cad.gds.GDSXyRecord;

/**
 * TEXT
 * [ELFLAGS]
 * [PLEX]
 * LAYER
 * TEXTTYPE
 * [PRESENTATION]
 * [PATHTYPE]
 * [WIDTH]
 * [STRANS [MAG] [ANGLE]]
 * XY
 * STRING
 *
 * @author ks026400
 *
 */
public class TextBuilder implements ElementBuilder {

    private String text;

    private Point[] points;

    private short layer;

    public TextBuilder(String text, Point point, short layer) {
        this.text = text;
        this.points = new Point[] { point };
        this.layer = layer;
    }

    public TextBuilder(String text, int x, int y, short layer, int scale) {
        this.text = text;
        this.points = new Point[] { new Point(x * scale, y * scale) };
        this.layer = layer;
    }

    @Override
    public GDSRecord[] records() throws GDSRecordException {
        return new GDSRecord[] {
                new GDSTextRecord(),
                new GDSLayerRecord(this.layer),
                new GDSTexttypeRecord(GDSRecord.TEXTTYPE),
                new GDSPresentationRecord(3, 0, 0),
                new GDSXyRecord(this.points),
                new GDSStringRecord(this.text),
                new GDSEndelRecord()
        };
    }
}
