package uia.gds.builder;

import java.io.IOException;
import java.util.Date;

import com.ohrasys.cad.gds.GDSBgnlibRecord;
import com.ohrasys.cad.gds.GDSBgnstrRecord;
import com.ohrasys.cad.gds.GDSEndlibRecord;
import com.ohrasys.cad.gds.GDSHeaderRecord;
import com.ohrasys.cad.gds.GDSLibnameRecord;
import com.ohrasys.cad.gds.GDSOutputStream;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSRecordException;
import com.ohrasys.cad.gds.GDSStrnameRecord;
import com.ohrasys.cad.gds.GDSUnitsRecord;

/**
 *
 * The actual stream will be like:
 *
 * <PRE>
 * HEADER
 * BGNLIB
 * [LIBDIRSIZE]
 * [SRFNAME]
 * [LIBSECUR]
 * LIBNAME
 * [REFLIBS]
 * [FONTS]
 * [ATTRTABLE]
 * [GENERATIONS]
 * [FORMAT | FORMAT {MASK}+ ENDMASKS]
 * UNITS
 * [{BGNSTR STRNAME [STRCLASS] [{<element>}+] ENDSTR}+]
 * ENDLIB
 * </PRE>
 *
 * An element portion of a stream file:
 * <PRE>
 * BOUNDARY | PATH | SREF | AREF | TEXT | NODE | BOX
 * [{PROPATTR PROPVALUE}+]
 * ENDEL
 * </PRE>
 *
 * @author ks026400
 *
 */
public class GDSFileBuilder {

    private short version;

    private Date modified;

    private Date access;

    private String libName;

    private double userUnits;

    private double meters;

    private GDSOutputStream gos;

    public GDSFileBuilder(GDSOutputStream gos) {
        this.version = 600;
        this.modified = new Date();
        this.libName = "LIB";
        this.access = this.modified;
        this.userUnits = 0.001;
        this.meters = 0.000000001;

        this.gos = gos;
    }

    public GDSFileBuilder begin() throws IOException, GDSRecordException {
        this.gos.writeRecord(new GDSHeaderRecord(this.version));
        this.gos.writeRecord(new GDSBgnlibRecord(this.modified, this.access));
        this.gos.writeRecord(new GDSLibnameRecord(this.libName));
        this.gos.writeRecord(new GDSUnitsRecord(this.userUnits, this.meters));
        return this;
    }

    public StructureBuilder beginStructure(String name) throws IOException, GDSRecordException {
        this.gos.writeRecord(new GDSBgnstrRecord(this.modified, this.access));
        this.gos.writeRecord(new GDSStrnameRecord(name));
        return new StructureBuilder(this.gos);
    }

    public GDSFileBuilder writeElement(ElementBuilder builder) throws IOException, GDSRecordException {
        for (GDSRecord rec : builder.records()) {
            this.gos.writeRecord(rec);
        }
        return this;
    }

    public GDSFileBuilder end() throws IOException, GDSRecordException {
        this.gos.writeRecord(new GDSEndlibRecord());
        return this;
    }
}
