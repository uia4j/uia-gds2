package uia.gds.builder;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.simplify.TopologyPreservingSimplifier;

import com.ohrasys.cad.bnf.BNFTestResult;
import com.ohrasys.cad.bnf.BNFTestableObject;
import com.ohrasys.cad.gds.GDSBgnstrRecord;
import com.ohrasys.cad.gds.GDSBoundaryRecord;
import com.ohrasys.cad.gds.GDSBoxRecord;
import com.ohrasys.cad.gds.GDSEndelRecord;
import com.ohrasys.cad.gds.GDSEndstrRecord;
import com.ohrasys.cad.gds.GDSInputStream;
import com.ohrasys.cad.gds.GDSLibnameRecord;
import com.ohrasys.cad.gds.GDSOutputStream;
import com.ohrasys.cad.gds.GDSPathRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSSnameRecord;
import com.ohrasys.cad.gds.GDSSrefRecord;
import com.ohrasys.cad.gds.GDSStrnameRecord;
import com.ohrasys.cad.gds.dao.ArrayReference;
import com.ohrasys.cad.gds.dao.Boundary;
import com.ohrasys.cad.gds.dao.Box;
import com.ohrasys.cad.gds.dao.Database;
import com.ohrasys.cad.gds.dao.Element;
import com.ohrasys.cad.gds.dao.Node;
import com.ohrasys.cad.gds.dao.Path;
import com.ohrasys.cad.gds.dao.Structure;
import com.ohrasys.cad.gds.dao.StructureReference;
import com.ohrasys.cad.gds.dao.Text;
import com.ohrasys.cad.gds.validator.DatabaseValidator;

public class GDSFileReaderTest {

    private Map<String, Handler> handles;

    private Map<Integer, List<Element>> layerElems;

    private List<Geometry> gs;

    private GeometryFactory gf;

    public GDSFileReaderTest() {
        this.handles = new TreeMap<>();
        // reference elements
        this.handles.put(ArrayReference.class.getName(), this::arrayRef);
        this.handles.put(StructureReference.class.getName(), this::structureRef);

        // geometry elements
        this.handles.put(Boundary.class.getName(), this::boundary);
        this.handles.put(Box.class.getName(), this::box);
        this.handles.put(Node.class.getName(), this::node);
        this.handles.put(Path.class.getName(), this::path);
        this.handles.put(Text.class.getName(), this::text);

        this.layerElems = new TreeMap<>();
        this.gf = new GeometryFactory();
        this.gs = new ArrayList<>();
    }

    @Test
    public void test() throws Exception {
        test("examples/32_new_61.gds");
        System.out.println(this.gs.size());

        MultiPolygon mp = new MultiPolygon(this.gs.toArray(new Polygon[0]), this.gf);

        /**
        Polygon p = (Polygon) mp.union();
        System.out.println(p.getNumInteriorRing());
        for (int i = 0; i < p.getNumInteriorRing(); i++) {
            LinearRing r = p.getInteriorRingN(i);
            // Polygon mark = this.gf.createPolygon(r.getCoordinates());
            System.out.println(i + ":" + r.getCoordinates().length);
        }
         */

        GDSFileBuilder builder = new GDSFileBuilder(new GDSOutputStream(new File("d:\\temp\\merge61.gds")));
        builder.begin();
        try (StructureBuilder struBuilder = builder.beginStructure("C32")) {
            Polygon p = (Polygon) mp.union();

            // shape
            struBuilder.writeElement(
                    new BoundaryBuilder(
                            convert(p.getExteriorRing().getCoordinates()),
                            (short) 2));
            // hole
            for (int i = 0; i < p.getNumInteriorRing(); i++) {
                LinearRing r = p.getInteriorRingN(i);
                struBuilder.writeElement(
                        new BoundaryBuilder(
                                convert(r.getCoordinates()),
                                (short) 3));
            }
        }
        builder.end();
    }

    private void test(String filePath) throws Exception {
        DatabaseValidator dv = load(filePath);

        @SuppressWarnings("unchecked")
        List<Database> dbs = (List<Database>) dv.collect();
        Database db = dbs.get(0);
        for (Structure st : db.getStructures()) {
            System.out.println("gds> struct> " + st.getName());
            for (Element elem : st.getElements()) {
                this.handles.get(elem.getClass().getName()).run(elem);
            }
        }
    }

    private void testStream(String filePath) throws Exception {
        try (GDSInputStream gdsin = new GDSInputStream(filePath)) {
            GDSRecord record = null;
            String space = "";
            while ((record = gdsin.readRecord()) != null) {
                if (record instanceof GDSBgnstrRecord) {
                    System.out.println("BGNSTR");
                    space += "  ";
                }
                else if (record instanceof GDSEndstrRecord) {
                    System.out.println("ENDSTR");
                    space = space.substring(0, space.length() - 2);
                }
                else if (record instanceof GDSBoundaryRecord) {
                    System.out.println(space + record);
                    space += "  ";
                }
                else if (record instanceof GDSPathRecord) {
                    System.out.println(space + record);
                    space += "  ";
                }
                else if (record instanceof GDSBoxRecord) {
                    System.out.println(space + record);
                    space += "  ";
                }
                else if (record instanceof GDSEndelRecord) {
                    space = space.substring(0, space.length() - 2);
                    System.out.println(space + record);
                }
                else if (record instanceof GDSStrnameRecord) {
                    System.out.println(space + record);
                }
                else if (record instanceof GDSSrefRecord) {
                    System.out.println(space + record);
                }
                else if (record instanceof GDSSnameRecord) {
                    System.out.println(space + record);
                }
                else if (record instanceof GDSLibnameRecord) {
                    GDSLibnameRecord header = (GDSLibnameRecord) record;
                    System.out.println("LIBNAME - " + header.getLibname());
                }
                else {
                    System.out.println(space + record.toString());
                }
            }
        }
    }

    private DatabaseValidator load(String filePath) throws Exception {
        DatabaseValidator dv = new DatabaseValidator();
        dv.reset();
        dv.setCollecting(true);

        try (GDSInputStream gdsin = new GDSInputStream(new File(filePath))) {
            GDSRecord record = null;
            while ((record = gdsin.readRecord()) != null) {
                try {
                    BNFTestResult testR = dv.test(new BNFTestableObjectImpl(record));
                    if (testR.isFailed()) {
                        throw new GDSRecordException(record + " failed");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    throw new GDSRecordException(e.getMessage());
                }
            }
        }

        return dv;
    }

    private Point[] convert(Coordinate[] cs) {
        cs = TopologyPreservingSimplifier.simplify(this.gf.createPolygon(cs), 0.1).getCoordinates();
        System.out.println(cs.length);
        Point[] pts = new Point[cs.length];
        for (int i = 0; i < cs.length; i++) {
            pts[i] = new Point((int) cs[i].getX(), (int) cs[i].getY());
        }
        return pts;
    }

    private void arrayRef(Element elem) {
        ArrayReference arrR = (ArrayReference) elem;
        System.out.printf("gds> arrRef> %s, %s\n",
                arrR.getName(),
                arrR.getInfo());
    }

    private void structureRef(Element elem) {
        StructureReference stR = (StructureReference) elem;
        System.out.printf("gds> structRef> %s, %s\n",
                stR.getName(),
                stR.getInfo());
    }

    private void boundary(Element elem) {
        Boundary boundary = (Boundary) elem;
        System.out.printf("gds> layer> %2s> boundary, pts=%s\n",
                boundary.getLayer(),
                boundary.getPoints().length);

        Coordinate[] shell = new Coordinate[boundary.getPoints().length];
        for (int i = 0; i < shell.length; i++) {
            shell[i] = new Coordinate(boundary.getPoints()[i].getX(), boundary.getPoints()[i].getY());
        }
        this.gs.add(this.gf.createPolygon(shell));

    }

    private void box(Element elem) {
        Box box = (Box) elem;
        System.out.printf("gds> layer> %s> box\n",
                box.getLayer());
    }

    private void node(Element elem) {
        Node node = (Node) elem;
        System.out.printf("gds> layer> %s> node\n",
                node.getLayer());
    }

    private void path(Element elem) {
        Path path = (Path) elem;
        if (path.getPoints().length == 1) {
            System.out.printf("gds> layer> %2s> circle, c=(%s,%s), r=%s\n",
                    path.getLayer(),
                    path.getPoints()[0].x,
                    path.getPoints()[0].y,
                    path.getWidth());
        }
        else {
            System.out.printf("gds> layer> %2s> path, pts=%s\n",
                    path.getLayer(),
                    path.getPoints().length);
        }
    }

    private void text(Element elem) {
        Text text = (Text) elem;
        System.out.printf("gds> layer> %2s> text, value=%s\n",
                text.getLayer(),
                text.getValue());
    }

    protected static String toString(Point[] pts) {
        String desc = "";
        for (Point pt : pts) {
            desc += String.format("%s,%s;", pt.x, pt.y);
        }
        return desc;
    }

    public static interface Handler {

        public void run(Element elem);
    }

    public static class BNFTestableObjectImpl extends BNFTestableObject {

        BNFTestableObjectImpl(GDSRecord record) {
            super(record);
        }

        @Override
        public int getToken() {
            return ((GDSRecord) getData()).getRectype();
        }
    }
}
