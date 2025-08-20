/*
 * Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package com.ohrasys.cad.gds;

/**
 * Records statistics for a GDSII database.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSStatistics {

    /** Number of ANGLE records */
    private int angle;

    /** Number of AREF records */
    private int aref;

    /** Number of ATTRTABLE records */
    private int attrtable;

    /** Number of BGNEXTN records */
    private int bgnextn;

    /** Number of BGNLIB records */
    private int bgnlib;

    /** Numbero of BGNSTR records */
    private int bgnstr;

    /** Number of BITARRAY datatype records */
    private int bitarray_type;

    /** Number of BORDER records */
    private int border;

    /** Number of BOUNDARY records */
    private int boundary;

    /** Number of BOX records */
    private int box;

    /** Number of BOXTYPE records */
    private int boxtype;

    /** Number of COLROW records */
    private int colrow;

    /** Number of CONTACT records */
    private int contact;

    /** Number of DATATYPE records */
    private int datatype;

    /** Number of DOUBLE datatype records */
    private int double_type;

    /** Number of ELFLAGS records */
    private int elflags;

    /** Number of ELKEY records */
    private int elkey;

    /** Number of ENDEL records */
    private int endel;

    /** Number of ENDEXTN records */
    private int endextn;

    /** Number of ENDLIB records */
    private int endlib;

    /** Number of ENDMASKS records */
    private int endmasks;

    /** Number of ENDSTR records */
    private int endstr;

    /** Number of FLOAT datatype records */
    private int float_type;

    /** Number of FONTS records */
    private int fonts;

    /** Number of FORMAT records */
    private int format;

    /** Number of GENERATIONS records */
    private int generations;

    /** Number of HARDFENCE records */
    private int hardfence;

    /** Number of HARDWIRE records */
    private int hardwire;

    /** Number of HEADER records */
    private int header;

    /** Internationalized strings factory */
    private GDSI18NFactory i18n;

    /** Number of INT datatype records */
    private int int_type;

    /** Number of LAYER records */
    private int layer;

    /** Number of LIBDIRSIZE records */
    private int libdirsize;

    /** Number of LIBNAME records */
    private int libname;

    /** Number of LIBSECUR records */
    private int libsecur;

    /** Number of LINKKEYS records */
    private int linkkeys;

    /** Number of LINKTYPE records */
    private int linktype;

    /** Number of MAG records */
    private int mag;

    /** Number of MASK records */
    private int mask;

    /** Number of NODATA datatype records */
    private int nodata_type;

    /** Number of NODE records */
    private int node;

    /** Number of NODEPORT records */
    private int nodeport;

    /** Number of NODETYPE records */
    private int nodetype;

    /** Number of NULL datatype records */
    private int null_type;

    /** Number of NULL records */
    private int nullrec;

    /** Number of PATH records */
    private int path;

    /** Number of PATHPORT records */
    private int pathport;

    /** Number or PATHTYPE records */
    private int pathtype;

    /** Number of PLEX records */
    private int plex;

    /** Number of PRESENTATION records */
    private int presentation;

    /** Number of PROPATTR records */
    private int propattr;

    /** Number of PROPVALUE records */
    private int propvalue;

    /** Number of REFLIBS records */
    private int reflibs;

    /** Number of RESERVED records */
    private int reserved;

    /** Number of SHORT datatype records */
    private int short_type;

    /** Number of SNAME records */
    private int sname;

    /** Number of SOFTFENCE records */
    private int softfence;

    /** Number of SOFTWIRE records */
    private int softwire;

    /** Number of SPACERERROR records */
    private int spacererror;

    /** Number of SPACING records */
    private int spacing;

    /** Number of SREF records */
    private int sref;

    /** Number of SRFNAME records */
    private int srfname;

    /** Number of STRANS records */
    private int strans;

    /** Number of STRCLASS records */
    private int strclass;

    /** Number of STRING records */
    private int string;

    /** Number of STRING datatype records */
    private int string_type;

    /** Number of STRNAME records */
    private int strname;

    /** Number of STRTYPE records */
    private int strtype;

    /** Number of STYPTABLE records */
    private int styptable;

    /** Number of TAPECODE records */
    private int tapecode;

    /** Number of TAPENUM records */
    private int tapenum;

    /** Number of TEXT records */
    private int text;

    /** Number of TEXTNODE records */
    private int textnode;

    /** Number of TEXTTYPE records */
    private int texttype;

    /** Total number of records */
    private int total;

    /** Number of UINTEGER records */
    private int uinteger;

    /** Number of UNITS records */
    private int units;

    /** Number of USERCONSTRAINT records */
    private int userconstraint;

    /** Number of USTRING records */
    private int ustring;

    /** Number of WIDTH records */
    private int width;

    /** Number of XY records */
    private int xy;

    /**
     * Creates a new GDSStatistics object.
     */
    public GDSStatistics() {
        clear();
    }

    /** Clears the current statistics. */
    public void clear() {
        this.total = 0;
        this.angle = 0;
        this.aref = 0;
        this.attrtable = 0;
        this.bgnextn = 0;
        this.bgnlib = 0;
        this.bgnstr = 0;
        this.border = 0;
        this.boundary = 0;
        this.box = 0;
        this.boxtype = 0;
        this.colrow = 0;
        this.contact = 0;
        this.datatype = 0;
        this.elflags = 0;
        this.elkey = 0;
        this.endel = 0;
        this.endextn = 0;
        this.endlib = 0;
        this.endmasks = 0;
        this.endstr = 0;
        this.fonts = 0;
        this.format = 0;
        this.generations = 0;
        this.hardfence = 0;
        this.hardwire = 0;
        this.header = 0;
        this.layer = 0;
        this.libdirsize = 0;
        this.libname = 0;
        this.libsecur = 0;
        this.linkkeys = 0;
        this.linktype = 0;
        this.mag = 0;
        this.mask = 0;
        this.node = 0;
        this.nodeport = 0;
        this.nodetype = 0;
        this.nullrec = 0;
        this.path = 0;
        this.pathport = 0;
        this.pathtype = 0;
        this.plex = 0;
        this.presentation = 0;
        this.propattr = 0;
        this.propvalue = 0;
        this.reflibs = 0;
        this.reserved = 0;
        this.sname = 0;
        this.softfence = 0;
        this.softwire = 0;
        this.spacererror = 0;
        this.spacing = 0;
        this.sref = 0;
        this.srfname = 0;
        this.strans = 0;
        this.strclass = 0;
        this.string = 0;
        this.strname = 0;
        this.strtype = 0;
        this.styptable = 0;
        this.tapecode = 0;
        this.tapenum = 0;
        this.text = 0;
        this.textnode = 0;
        this.texttype = 0;
        this.uinteger = 0;
        this.units = 0;
        this.userconstraint = 0;
        this.ustring = 0;
        this.width = 0;
        this.xy = 0;
        this.nodata_type = 0;
        this.null_type = 0;
        this.bitarray_type = 0;
        this.short_type = 0;
        this.int_type = 0;
        this.float_type = 0;
        this.double_type = 0;
        this.string_type = 0;
    } // end method clear

    /**
     * Records statistics for the specified record.
     *
     * @param  rec  The record to process.
     */
    public void record(GDSRecord rec) {
        this.total++;

        switch (rec.getRectype()) {
            case 0x00:
                this.header++;

                break;

            case 0x01:
                this.bgnlib++;

                break;

            case 0x02:
                this.libname++;

                break;

            case 0x03:
                this.units++;

                break;

            case 0x04:
                this.endlib++;

                break;

            case 0x05:
                this.bgnstr++;

                break;

            case 0x06:
                this.strname++;

                break;

            case 0x07:
                this.endstr++;

                break;

            case 0x08:
                this.boundary++;

                break;

            case 0x09:
                this.path++;

                break;

            case 0x0A:
                this.sref++;

                break;

            case 0x0B:
                this.aref++;

                break;

            case 0x0C:
                this.text++;

                break;

            case 0x0D:
                this.layer++;

                break;

            case 0x0E:
                this.datatype++;

                break;

            case 0x0F:
                this.width++;

                break;

            case 0x10:
                this.xy++;

                break;

            case 0x11:
                this.endel++;

                break;

            case 0x12:
                this.sname++;

                break;

            case 0x13:
                this.colrow++;

                break;

            case 0x14:
                this.textnode++;

                break;

            case 0x15:
                this.node++;

                break;

            case 0x16:
                this.texttype++;

                break;

            case 0x17:
                this.presentation++;

                break;

            case 0x18:
                this.spacing++;

                break;

            case 0x19:
                this.string++;

                break;

            case 0x1A:
                this.strans++;

                break;

            case 0x1B:
                this.mag++;

                break;

            case 0x1C:
                this.angle++;

                break;

            case 0x1D:
                this.uinteger++;

                break;

            case 0x1E:
                this.ustring++;

                break;

            case 0x1F:
                this.reflibs++;

                break;

            case 0x20:
                this.fonts++;

                break;

            case 0x21:
                this.pathtype++;

                break;

            case 0x22:
                this.generations++;

                break;

            case 0x23:
                this.attrtable++;

                break;

            case 0x24:
                this.styptable++;

                break;

            case 0x25:
                this.strtype++;

                break;

            case 0x26:
                this.elflags++;

                break;

            case 0x27:
                this.elkey++;

                break;

            case 0x28:
                this.linktype++;

                break;

            case 0x29:
                this.linkkeys++;

                break;

            case 0x2A:
                this.nodetype++;

                break;

            case 0x2B:
                this.propattr++;

                break;

            case 0x2C:
                this.propvalue++;

                break;

            case 0x2D:
                this.box++;

                break;

            case 0x2E:
                this.boxtype++;

                break;

            case 0x2F:
                this.plex++;

                break;

            case 0x30:
                this.bgnextn++;

                break;

            case 0x31:
                this.endextn++;

                break;

            case 0x32:
                this.tapenum++;

                break;

            case 0x33:
                this.tapecode++;

                break;

            case 0x34:
                this.strclass++;

                break;

            case 0x35:
                this.reserved++;

                break;

            case 0x36:
                this.format++;

                break;

            case 0x37:
                this.mask++;

                break;

            case 0x38:
                this.endmasks++;

                break;

            case 0x39:
                this.libdirsize++;

                break;

            case 0x3A:
                this.srfname++;

                break;

            case 0x3B:
                this.libsecur++;

                break;

            case 0x3C:
                this.border++;

                break;

            case 0x3D:
                this.softfence++;

                break;

            case 0x3E:
                this.hardfence++;

                break;

            case 0x3F:
                this.softwire++;

                break;

            case 0x40:
                this.hardwire++;

                break;

            case 0x41:
                this.pathport++;

                break;

            case 0x42:
                this.nodeport++;

                break;

            case 0x43:
                this.userconstraint++;

                break;

            case 0x44:
                this.spacererror++;

                break;

            case 0x45:
                this.contact++;

                break;

            default:
                this.nullrec++;

                break;
        }

        switch (rec.getDattype()) {
            case 0x00:
                this.nodata_type++;

                break;

            case 0x01:
                this.bitarray_type++;

                break;

            case 0x02:
                this.short_type++;

                break;

            case 0x03:
                this.int_type++;

                break;

            case 0x04:
                this.float_type++;

                break;

            case 0x05:
                this.double_type++;

                break;

            case 0x06:
                this.string_type++;

                break;
        }
    } // end method record

    /**
     * A description of the GDSII database statistics.
     *
     * @return  A string representation of the GDSII database statistics.
     */
    @Override
    public String toString() {
        String result = new String(
                GDSStringUtil.sprintf(GDSI18NFactory.getString(
                        GDSI18NFactory.i18n_STATS_TOSTRING1), this.total) + "\n" /*NOI18N*/);
        result += GDSI18NFactory.getString(GDSI18NFactory.i18n_STATS_TOSTRING2) + "\n" /*NOI18N*/;
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("ANGLE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.angle + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("AREF" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.aref + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("ATTRTABLE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.attrtable + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("BGNEXTN" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.bgnextn + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("BGNLIB" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.bgnlib + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("BGNSTR" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.bgnstr + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("BORDER" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.border + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("BOUNDARY" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.boundary + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("BOX" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.box + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("BOXTYPE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.boxtype + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("COLROW" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.colrow + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("CONTACT" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.contact + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("DATATYPE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.datatype + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("ELFLAGS" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.elflags + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("ELKEY" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.elkey + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("ENDEL" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.endel + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("ENDEXTN" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.endextn + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("ENDLIB" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.endlib + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("ENDMASKS" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.endmasks + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("ENDSTR" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.endstr + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("FONTS" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.fonts + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("FORMAT" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.format + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("GENERATIONS" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.generations + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("HARDFENCE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.hardfence + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("HARDWIRE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.hardwire + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("HEADER" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.header + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("LAYER" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.layer + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("LIBDIRSIZE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.libdirsize + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("LIBNAME" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.libname + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("LIBSECUR" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.libsecur + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("LINKKEYS" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.linkkeys + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("LINKTYPE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.linktype + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("MAG" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.mag + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("MASK" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.mask + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("NODE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.node + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("NODEPORT" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.nodeport + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("NODETYPE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.nodetype + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("NULL" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.nullrec + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("PATH" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.path + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("PATHPORT" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.pathport + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("PATHTYPE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.pathtype + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("PLEX" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.plex + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("PRESENTATION" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.presentation + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("PROPATTR" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.propattr + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("PROPVALUE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.propvalue + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("REFLIBS" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.reflibs + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("RESERVED" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.reserved + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("SNAME" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.sname + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("SOFTFENCE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.softfence + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("SOFTWIRE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.softwire + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("SPACERERROR" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.spacererror + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("SPACING" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.spacing + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("SREF" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.sref + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("SRFNAME" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.srfname + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("STRANS" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.strans + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("STRCLASS" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.strclass + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("STRING" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.string + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("STRNAME" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.strname + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("STRTYPE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.strtype + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("STYPTABLE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.styptable + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("TAPECODE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.tapecode + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("TAPENUM" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.tapenum + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("TEXT" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.text + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("TEXTNODE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.textnode + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("TEXTTYPE" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.texttype + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("UINTEGER" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.uinteger + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("UNITS" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.units + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("USERCONSTRAINT" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.userconstraint + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("USTRING" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.ustring + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("WIDTH" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.width + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("XY" /*NOI18N*/, 16) +
                ":  " /*NOI18N*/ + this.xy + "\n" /*NOI18N*/);
        result += GDSI18NFactory.getString(GDSI18NFactory.i18n_STATS_TOSTRING3) + "\n" /*NOI18N*/;
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("NULL DATA RECORD" /*NOI18N*/, 18) +
                ":  " /*NOI18N*/ + this.nodata_type + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("BIT ARRAY RECORD" /*NOI18N*/, 18) +
                ":  " /*NOI18N*/ + this.bitarray_type + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("SHORT RECORD" /*NOI18N*/, 18) +
                ":  " /*NOI18N*/ + this.short_type + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("INT RECORD" /*NOI18N*/, 18) +
                ":  " /*NOI18N*/ + this.int_type + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("FLOAT RECORD" /*NOI18N*/, 18) +
                ":  " /*NOI18N*/ + this.float_type + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("DOUBLE RECORD" /*NOI18N*/, 18) +
                ":  " /*NOI18N*/ + this.double_type + "\n" /*NOI18N*/);
        result += ("\t" /*NOI18N*/ +
                GDSStringUtil.fixLength("STRING RECORD" /*NOI18N*/, 18) +
                ":  " /*NOI18N*/ + this.string_type + "\n" /*NOI18N*/);

        return result;
    } // end method toString
} // end class GDSStatistics

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
