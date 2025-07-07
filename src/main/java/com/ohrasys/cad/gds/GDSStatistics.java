/* Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA. */

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
  public GDSStatistics(){clear();}

  /** Clears the current statistics. */
  public void clear() {
    total          = 0;
    angle          = 0;
    aref           = 0;
    attrtable      = 0;
    bgnextn        = 0;
    bgnlib         = 0;
    bgnstr         = 0;
    border         = 0;
    boundary       = 0;
    box            = 0;
    boxtype        = 0;
    colrow         = 0;
    contact        = 0;
    datatype       = 0;
    elflags        = 0;
    elkey          = 0;
    endel          = 0;
    endextn        = 0;
    endlib         = 0;
    endmasks       = 0;
    endstr         = 0;
    fonts          = 0;
    format         = 0;
    generations    = 0;
    hardfence      = 0;
    hardwire       = 0;
    header         = 0;
    layer          = 0;
    libdirsize     = 0;
    libname        = 0;
    libsecur       = 0;
    linkkeys       = 0;
    linktype       = 0;
    mag            = 0;
    mask           = 0;
    node           = 0;
    nodeport       = 0;
    nodetype       = 0;
    nullrec        = 0;
    path           = 0;
    pathport       = 0;
    pathtype       = 0;
    plex           = 0;
    presentation   = 0;
    propattr       = 0;
    propvalue      = 0;
    reflibs        = 0;
    reserved       = 0;
    sname          = 0;
    softfence      = 0;
    softwire       = 0;
    spacererror    = 0;
    spacing        = 0;
    sref           = 0;
    srfname        = 0;
    strans         = 0;
    strclass       = 0;
    string         = 0;
    strname        = 0;
    strtype        = 0;
    styptable      = 0;
    tapecode       = 0;
    tapenum        = 0;
    text           = 0;
    textnode       = 0;
    texttype       = 0;
    uinteger       = 0;
    units          = 0;
    userconstraint = 0;
    ustring        = 0;
    width          = 0;
    xy             = 0;
    nodata_type    = 0;
    null_type      = 0;
    bitarray_type  = 0;
    short_type     = 0;
    int_type       = 0;
    float_type     = 0;
    double_type    = 0;
    string_type    = 0;
  } // end method clear

  /**
   * Records statistics for the specified record.
   *
   * @param  rec  The record to process.
   */
  public void record(GDSRecord rec) {
    total++;

    switch(rec.getRectype()) {
      case 0x00:
        header++;

        break;

      case 0x01:
        bgnlib++;

        break;

      case 0x02:
        libname++;

        break;

      case 0x03:
        units++;

        break;

      case 0x04:
        endlib++;

        break;

      case 0x05:
        bgnstr++;

        break;

      case 0x06:
        strname++;

        break;

      case 0x07:
        endstr++;

        break;

      case 0x08:
        boundary++;

        break;

      case 0x09:
        path++;

        break;

      case 0x0A:
        sref++;

        break;

      case 0x0B:
        aref++;

        break;

      case 0x0C:
        text++;

        break;

      case 0x0D:
        layer++;

        break;

      case 0x0E:
        datatype++;

        break;

      case 0x0F:
        width++;

        break;

      case 0x10:
        xy++;

        break;

      case 0x11:
        endel++;

        break;

      case 0x12:
        sname++;

        break;

      case 0x13:
        colrow++;

        break;

      case 0x14:
        textnode++;

        break;

      case 0x15:
        node++;

        break;

      case 0x16:
        texttype++;

        break;

      case 0x17:
        presentation++;

        break;

      case 0x18:
        spacing++;

        break;

      case 0x19:
        string++;

        break;

      case 0x1A:
        strans++;

        break;

      case 0x1B:
        mag++;

        break;

      case 0x1C:
        angle++;

        break;

      case 0x1D:
        uinteger++;

        break;

      case 0x1E:
        ustring++;

        break;

      case 0x1F:
        reflibs++;

        break;

      case 0x20:
        fonts++;

        break;

      case 0x21:
        pathtype++;

        break;

      case 0x22:
        generations++;

        break;

      case 0x23:
        attrtable++;

        break;

      case 0x24:
        styptable++;

        break;

      case 0x25:
        strtype++;

        break;

      case 0x26:
        elflags++;

        break;

      case 0x27:
        elkey++;

        break;

      case 0x28:
        linktype++;

        break;

      case 0x29:
        linkkeys++;

        break;

      case 0x2A:
        nodetype++;

        break;

      case 0x2B:
        propattr++;

        break;

      case 0x2C:
        propvalue++;

        break;

      case 0x2D:
        box++;

        break;

      case 0x2E:
        boxtype++;

        break;

      case 0x2F:
        plex++;

        break;

      case 0x30:
        bgnextn++;

        break;

      case 0x31:
        endextn++;

        break;

      case 0x32:
        tapenum++;

        break;

      case 0x33:
        tapecode++;

        break;

      case 0x34:
        strclass++;

        break;

      case 0x35:
        reserved++;

        break;

      case 0x36:
        format++;

        break;

      case 0x37:
        mask++;

        break;

      case 0x38:
        endmasks++;

        break;

      case 0x39:
        libdirsize++;

        break;

      case 0x3A:
        srfname++;

        break;

      case 0x3B:
        libsecur++;

        break;

      case 0x3C:
        border++;

        break;

      case 0x3D:
        softfence++;

        break;

      case 0x3E:
        hardfence++;

        break;

      case 0x3F:
        softwire++;

        break;

      case 0x40:
        hardwire++;

        break;

      case 0x41:
        pathport++;

        break;

      case 0x42:
        nodeport++;

        break;

      case 0x43:
        userconstraint++;

        break;

      case 0x44:
        spacererror++;

        break;

      case 0x45:
        contact++;

        break;

      default:
        nullrec++;

        break;
    }

    switch(rec.getDattype()) {
      case 0x00:
        nodata_type++;

        break;

      case 0x01:
        bitarray_type++;

        break;

      case 0x02:
        short_type++;

        break;

      case 0x03:
        int_type++;

        break;

      case 0x04:
        float_type++;

        break;

      case 0x05:
        double_type++;

        break;

      case 0x06:
        string_type++;

        break;
    }
  } // end method record

  /**
   * A description of the GDSII database statistics.
   *
   * @return  A string representation of the GDSII database statistics.
   */
  public String toString() {
    String result = new String(
        GDSStringUtil.sprintf(i18n.getString(
            i18n.i18n_STATS_TOSTRING1), total) + "\n" /*NOI18N*/);
    result += i18n.getString(i18n.i18n_STATS_TOSTRING2) + "\n" /*NOI18N*/;
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("ANGLE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + angle + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("AREF" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + aref + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("ATTRTABLE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + attrtable + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("BGNEXTN" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + bgnextn + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("BGNLIB" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + bgnlib + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("BGNSTR" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + bgnstr + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("BORDER" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + border + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("BOUNDARY" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + boundary + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("BOX" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + box + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("BOXTYPE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + boxtype + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("COLROW" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + colrow + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("CONTACT" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + contact + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("DATATYPE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + datatype + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("ELFLAGS" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + elflags + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("ELKEY" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + elkey + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("ENDEL" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + endel + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("ENDEXTN" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + endextn + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("ENDLIB" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + endlib + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("ENDMASKS" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + endmasks + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("ENDSTR" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + endstr + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("FONTS" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + fonts + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("FORMAT" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + format + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("GENERATIONS" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + generations + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("HARDFENCE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + hardfence + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("HARDWIRE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + hardwire + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("HEADER" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + header + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("LAYER" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + layer + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("LIBDIRSIZE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + libdirsize + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("LIBNAME" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + libname + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("LIBSECUR" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + libsecur + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("LINKKEYS" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + linkkeys + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("LINKTYPE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + linktype + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("MAG" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + mag + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("MASK" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + mask + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("NODE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + node + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("NODEPORT" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + nodeport + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("NODETYPE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + nodetype + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("NULL" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + nullrec + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("PATH" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + path + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("PATHPORT" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + pathport + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("PATHTYPE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + pathtype + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("PLEX" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + plex + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("PRESENTATION" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + presentation + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("PROPATTR" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + propattr + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("PROPVALUE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + propvalue + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("REFLIBS" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + reflibs + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("RESERVED" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + reserved + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("SNAME" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + sname + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("SOFTFENCE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + softfence + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("SOFTWIRE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + softwire + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("SPACERERROR" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + spacererror + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("SPACING" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + spacing + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("SREF" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + sref + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("SRFNAME" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + srfname + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("STRANS" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + strans + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("STRCLASS" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + strclass + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("STRING" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + string + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("STRNAME" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + strname + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("STRTYPE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + strtype + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("STYPTABLE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + styptable + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("TAPECODE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + tapecode + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("TAPENUM" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + tapenum + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("TEXT" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + text + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("TEXTNODE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + textnode + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("TEXTTYPE" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + texttype + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("UINTEGER" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + uinteger + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("UNITS" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + units + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("USERCONSTRAINT" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + userconstraint + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("USTRING" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + ustring + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("WIDTH" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + width + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("XY" /*NOI18N*/, 16) +
        ":  " /*NOI18N*/ + xy + "\n" /*NOI18N*/);
    result += i18n.getString(i18n.i18n_STATS_TOSTRING3) + "\n" /*NOI18N*/;
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("NULL DATA RECORD" /*NOI18N*/, 18) +
        ":  " /*NOI18N*/ + nodata_type + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("BIT ARRAY RECORD" /*NOI18N*/, 18) +
        ":  " /*NOI18N*/ + bitarray_type + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("SHORT RECORD" /*NOI18N*/, 18) +
        ":  " /*NOI18N*/ + short_type + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("INT RECORD" /*NOI18N*/, 18) +
        ":  " /*NOI18N*/ + int_type + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("FLOAT RECORD" /*NOI18N*/, 18) +
        ":  " /*NOI18N*/ + float_type + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("DOUBLE RECORD" /*NOI18N*/, 18) +
        ":  " /*NOI18N*/ + double_type + "\n" /*NOI18N*/);
    result += ("\t" /*NOI18N*/ +
        GDSStringUtil.fixLength("STRING RECORD" /*NOI18N*/, 18) +
        ":  " /*NOI18N*/ + string_type + "\n" /*NOI18N*/);

    return result;
  } // end method toString
} // end class GDSStatistics


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
