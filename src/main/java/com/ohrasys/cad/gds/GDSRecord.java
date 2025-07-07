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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This class represents a generic GDSII stream record.  A stream record is a
 * byte stream that is divided into four parts.
 *
 * <ol>
 *   <li>Size field - The first two bytes form an integer that declares the
 *     total number of bytes in the record.</li>
 *   <li>Record type field - The third byte specifies the record type</li>
 *   <li>Data type field - The fourth byte specifies the data type</li>
 *   <li>Data field - The remaining bytes are record data</li>
 * </ol>
 *
 * The records of the stream are normally divided in 2048 byte physical blocks.
 * Records can overlap block boundaries; A record is not required to be wholly
 * contained in a single physical block. Two consecutive zero bytes are a null
 * word.  Null words can be used to fill the space between the last record of a
 * library and the end of its physical block. All GDSII records must be an even
 * multiple of bytes. If a record contains ASCII string data and the ASCII
 * string is an odd number of bytes, the last character is a null character.
 * Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.10 $
 * @since    1.5
 */
public class GDSRecord {

    /**
     * Indicates a record has no data associated with it.  Records with this
     * datatype should have a record size of 4 bytes.
     */
    public static final byte NO_DATA_TYPE = 0x00;

    /** Indicates that record data is a two byte bit array. */
    public static final byte BIT_ARRAY_TYPE = 0x01;

    /** Indicates the record data type is two byte signed integer. */
    public static final byte SHORT_TYPE = 0x02;

    /** Indicates the record data type is four byte signed integer. */
    public static final byte INT_TYPE = 0x03;

    /** Indicates the record data is four byte floating point. */
    public static final byte FLOAT_TYPE = 0x04;

    /** Indicates the record data is eight byte floating point. */
    public static final byte DOUBLE_TYPE = 0x05;

    /**
     * Indicates the record data is ASCII string data.  If the string contains an
     * odd number of characters, a null character is appended.  The record byte
     * count includes this null character.
     */
    public static final byte STRING_TYPE = 0x06;

    /** NULL record type */
    public static final byte NULL = -0x01;

    /** HEADER record type */
    public static final byte HEADER = 0x00;

    /** BGNLIB record type */
    public static final byte BGNLIB = 0x01;

    /** LIBNAME record type */
    public static final byte LIBNAME = 0x02;

    /** UNITS record type */
    public static final byte UNITS = 0x03;

    /** ENDLIB record type */
    public static final byte ENDLIB = 0x04;

    /** BGNSTR record type */
    public static final byte BGNSTR = 0x05;

    /** STRNAME record type */
    public static final byte STRNAME = 0x06;

    /** ENDSTR record type */
    public static final byte ENDSTR = 0x07;

    /** BOUNDARY record type */
    public static final byte BOUNDARY = 0x08;

    /** PATH record type */
    public static final byte PATH = 0x09;

    /** SREF record type */
    public static final byte SREF = 0x0A;

    /** AREF record type */
    public static final byte AREF = 0x0B;

    /** TEXT record type */
    public static final byte TEXT = 0x0C;

    /** LAYER record type */
    public static final byte LAYER = 0x0D;

    /** DATATYPE record type */
    public static final byte DATATYPE = 0x0E;

    /** WIDTH record type */
    public static final byte WIDTH = 0x0F;

    /** XY record type */
    public static final byte XY = 0x10;

    /** ENDEL record type */
    public static final byte ENDEL = 0x11;

    /** SNAME record type */
    public static final byte SNAME = 0x12;

    /** COLROW record type */
    public static final byte COLROW = 0x13;

    /** TEXTNODE record type */
    public static final byte TEXTNODE = 0x14;

    /** NODE record type */
    public static final byte NODE = 0x15;

    /** TEXTTYPE record type */
    public static final byte TEXTTYPE = 0x16;

    /** PRESENTATION record type */
    public static final byte PRESENTATION = 0x17;

    /** SPACING record type */
    public static final byte SPACING = 0x18;

    /** STRING record type */
    public static final byte STRING = 0x19;

    /** STRANS record type */
    public static final byte STRANS = 0x1A;

    /** MAG record type */
    public static final byte MAG = 0x1B;

    /** ANGLE record type */
    public static final byte ANGLE = 0x1C;

    /** UINTEGER record type */
    public static final byte UINTEGER = 0x1D;

    /** USTRING record type */
    public static final byte USTRING = 0x1E;

    /** REFLIBS record type */
    public static final byte REFLIBS = 0x1F;

    /** FONTS record type */
    public static final byte FONTS = 0x20;

    /** PATHTYPE record type */
    public static final byte PATHTYPE = 0x21;

    /** GENERATIONS record type */
    public static final byte GENERATIONS = 0x22;

    /** ATTRTABLE record type */
    public static final byte ATTRTABLE = 0x23;

    /** STYPTABLE record type */
    public static final byte STYPTABLE = 0x24;

    /** STRTYPE record type */
    public static final byte STRTYPE = 0x25;

    /** ELFLAGS record type */
    public static final byte ELFLAGS = 0x26;

    /** ELKEY record type */
    public static final byte ELKEY = 0x27;

    /** LINKTYPE record type */
    public static final byte LINKTYPE = 0x28;

    /** LINKKEYS record type */
    public static final byte LINKKEYS = 0x29;

    /** NODETYPE record type */
    public static final byte NODETYPE = 0x2A;

    /** PROPATTR record type */
    public static final byte PROPATTR = 0x2B;

    /** PROPVALUE record type */
    public static final byte PROPVALUE = 0x2C;

    /** BOX record type */
    public static final byte BOX = 0x2D;

    /** BOXTYPE record type */
    public static final byte BOXTYPE = 0x2E;

    /** PLEX record type */
    public static final byte PLEX = 0x2F;

    /** BGNEXTN record type */
    public static final byte BGNEXTN = 0x30;

    /** ENDEXTN record type */
    public static final byte ENDEXTN = 0x31;

    /** TAPENUM record type */
    public static final byte TAPENUM = 0x32;

    /** TAPECODE record type */
    public static final byte TAPECODE = 0x33;

    /** STRCLASS record type */
    public static final byte STRCLASS = 0x34;

    /** RESERVED record type */
    public static final byte RESERVED = 0x35;

    /** FORMAT record type */
    public static final byte FORMAT = 0x36;

    /** MASK record type */
    public static final byte MASK = 0x37;

    /** ENDMASKS record type */
    public static final byte ENDMASKS = 0x38;

    /** LIBDIRSIZE record type */
    public static final byte LIBDIRSIZE = 0x39;

    /** SRFNAME record type */
    public static final byte SRFNAME = 0x3A;

    /** LIBSECUR record type */
    public static final byte LIBSECUR = 0x3B;

    /** BORDER record type */
    public static final byte BORDER = 0x3C;

    /** SOFTFENCE record type */
    public static final byte SOFTFENCE = 0x3D;

    /** HARDFENCE record type */
    public static final byte HARDFENCE = 0x3E;

    /** SOFTWIRE record type */
    public static final byte SOFTWIRE = 0x3F;

    /** HARDWIRE record type */
    public static final byte HARDWIRE = 0x40;

    /** PATHPORT record type */
    public static final byte PATHPORT = 0x41;

    /** NODEPORT record type */
    public static final byte NODEPORT = 0x42;

    /** USERCONSTRAINT record type */
    public static final byte USERCONSTRAINT = 0x43;

    /** SPACER_ERROR record type */
    public static final byte SPACER_ERROR = 0x44;

    /** CONTACT record type */
    public static final byte CONTACT = 0x45;

    /** PATH element identifier */
    public static final int PATH_ELEM_TYPE = 0;

    /** BOUNDARY element identifier */
    public static final int BOUNDARY_ELEM_TYPE = 1;

    /** TEXT element identifier */
    public static final int TEXT_ELEM_TYPE = 2;

    /** CONTACT element identifier */
    public static final int CONTACT_ELEM_TYPE = 3;

    /** SREF element identifier */
    public static final int SREF_ELEM_TYPE = 4;

    /** NODE element identifier */
    public static final int NODE_ELEM_TYPE = 5;

    /** BOX element identifier */
    public static final int BOX_ELEM_TYPE = 6;

    /** AREF element identifier */
    public static final int AREF_ELEM_TYPE = 7;

    /** The maximum number of data bytes a record may contain */
    public static final int MAX_REC_LEN = Short.MAX_VALUE - 4;

    /** Kyle K. Lin */
    protected static final String validChars = "abcdefghijklmnopqrstuvwxyz0123456789?_$*-" /*NOI18N*/;

    /** The record data. */
    protected byte data[];

    /** The data type of the record. */
    protected byte dattype;

    /** The internationalized message factory */
    protected GDSI18NFactory i18n;

    /** The sum total length of all record fields. */
    protected short length;

    /** The record type. */
    protected byte rectype;

    /**
     * Creates a new NULL GDSRecord object.
     *
     * @throws  GDSRecordException  If there is an error creating the record
     */
    public GDSRecord() throws GDSRecordException {
        this((short) 0, NULL, NO_DATA_TYPE, new byte[0]);
    }

    /**
     * Creates a new GDSRecord object from an existing record.
     *
     * @param   rec  The record to duplicate
     *
     * @throws  GDSRecordException  If the record is malformed
     */
    public GDSRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSRecord object.
     *
     * @param   length   The total record length
     * @param   rectype  The record type
     * @param   dattype  The datatype
     * @param   data     The record data
     *
     * @throws  GDSRecordException  If any of the input parameters would create a
     *                              malformed record
     */
    public GDSRecord(short length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        if ((data == null) || ((data.length % 2) != 0)) {
            throw new GDSRecordException(this.i18n.getString(
                    this.i18n.i18n_RECORD_THROW1));
        }

        if ((rectype != NULL) && (length != (data.length + 4))) {
            throw new GDSRecordException(GDSStringUtil.sprintf(
                    this.i18n.getString(this.i18n.i18n_RECORD_THROW2),
                    (data.length + 4)));
        }

        if ((rectype < -1) || (rectype > CONTACT)) {
            throw new GDSRecordException(GDSStringUtil.sprintf(
                    this.i18n.getString(this.i18n.i18n_RECORD_THROW3), rectype));
        }

        if ((dattype < 0) || (dattype > 6)) {
            throw new GDSRecordException(GDSStringUtil.sprintf(
                    this.i18n.getString(this.i18n.i18n_RECORD_THROW4), dattype));
        }

        this.data = data;
        this.length = length;
        this.rectype = rectype;
        this.dattype = dattype;
    } // end ctor GDSRecord

    /**
     * Returns a copy of the data
     *
     * @return  The record data
     */
    public byte[] getData() {
        byte result[] = new byte[this.data.length];
        System.arraycopy(this.data, 0, result, 0, this.data.length);

        return result;
    }

    /**
     * The datatype of the record
     *
     * @return  Record datatype
     */
    public byte getDattype() {
        return this.dattype;
    }

    /**
     * Return the total record length
     *
     * @return  length
     */
    public short getLength() {
        return this.length;
    }

    /**
     * Return the record type
     *
     * @return  Record type
     */
    public byte getRectype() {
        return this.rectype;
    }

    /**
     * Creates a byte array representation of this gds record suitable for
     * consumption by a stream reader/writer.
     *
     * @return  A byte array representation of the record
     */
    public byte[] toBytes() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        GDSByteConverter.fromShort(this.length, result);
        GDSByteConverter.fromByte(this.rectype, result);
        GDSByteConverter.fromByte(this.dattype, result);

        try {
            result.write(this.data);
        }
        catch (IOException e) {
            System.err.println(this.i18n.getString(this.i18n.i18n_RECORD_THROW5));

            return new byte[2];
        }

        return result.toByteArray();
    }

    /**
     * Returns a descriptive string representation of the record.
     *
     * @return  The record description
     */
    @Override
    public String toString() {
        return getClass().getName();
    }

    /**
     * Ensures a record is well formed, of the expected record type, of bit array
     * datatype and that no reserved fields are being utilized.
     *
     * @param   rectype    The expected record type
     * @param   validbits  A bit mask identifying valid bit positions by a '1'
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateBitarrayRec(byte rectype, short validbits) throws GDSRecordException {
        validateRec(rectype, BIT_ARRAY_TYPE, 2, 2);

        if ((GDSByteConverter.toShort(this.data) & ~validbits) != 0) {
            throw new GDSRecordException(this.i18n.getString(
                    this.i18n.i18n_RECORD_THROW6));
        }
    }

    /**
     * Ensures a record is well formed, of the expected record type and of double
     * datatype
     *
     * @param   rectype  Expected record type
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateDoubleRec(byte rectype) throws GDSRecordException {
        validateDoubleRec(rectype, 8);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of double
     * datatype and contains len number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   len      Expected number of data bytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateDoubleRec(byte rectype, int len) throws GDSRecordException {
        validateDoubleRec(rectype, len, len);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of double
     * datatype and contains between min and max number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   maxlen   Max number of data bytes
     * @param   minlen   Min number of databytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateDoubleRec(byte rectype, int maxlen, int minlen) throws GDSRecordException {
        if ((this.data.length % 8) != 0) {
            throw new GDSRecordException(this.i18n.getString(
                    this.i18n.i18n_RECORD_THROW7));
        }

        validateRec(rectype, DOUBLE_TYPE, maxlen, minlen);
    }

    /**
     * Ensures a record is well formed, of the expected record type and of float
     * datatype
     *
     * @param   rectype  Expected record type
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateFloatRec(byte rectype) throws GDSRecordException {
        validateFloatRec(rectype, 4);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of float
     * datatype and contains len number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   len      Expected number of data bytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateFloatRec(byte rectype, int len) throws GDSRecordException {
        validateFloatRec(rectype, len, len);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of float
     * datatype and contains between min and max number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   maxlen   Max number of data bytes
     * @param   minlen   Min number of databytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateFloatRec(byte rectype, int maxlen, int minlen) throws GDSRecordException {
        if ((this.data.length % 4) != 0) {
            throw new GDSRecordException(this.i18n.getString(
                    this.i18n.i18n_RECORD_THROW8));
        }

        validateRec(rectype, FLOAT_TYPE, maxlen, minlen);
    }

    /**
     * Ensures a record is well formed, of the expected record type and of int
     * datatype
     *
     * @param   rectype  Expected record type
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateIntRec(byte rectype) throws GDSRecordException {
        validateIntRec(rectype, 4);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of int
     * datatype and contains len number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   len      Expected number of data bytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateIntRec(byte rectype, int len) throws GDSRecordException {
        validateIntRec(rectype, len, len);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of int
     * datatype and contains between min and max number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   maxlen   Max number of data bytes
     * @param   minlen   Min number of databytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateIntRec(byte rectype, int maxlen, int minlen) throws GDSRecordException {
        if ((this.data.length % 4) != 0) {
            throw new GDSRecordException(this.i18n.getString(
                    this.i18n.i18n_RECORD_THROW8));
        }

        validateRec(rectype, INT_TYPE, maxlen, minlen);
    }

    /**
     * Ensures a record is well formed, of the expected record type and of no data
     * type
     *
     * @param   rectype  Expected record type
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateNodataRec(byte rectype) throws GDSRecordException {
        validateRec(rectype, NO_DATA_TYPE, 0, 0);
    }

    /**
     * Ensures a record is well formed, of the expected record type and of short
     * datatype
     *
     * @param   rectype  Expected record type
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateShortRec(byte rectype) throws GDSRecordException {
        validateShortRec(rectype, 2);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of short
     * datatype and contains len number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   len      Expected number of data bytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateShortRec(byte rectype, int len) throws GDSRecordException {
        validateShortRec(rectype, len, len);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of short
     * datatype and contains between min and max number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   maxlen   Max number of data bytes
     * @param   minlen   Min number of databytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateShortRec(byte rectype, int maxlen, int minlen) throws GDSRecordException {
        if ((this.data.length % 2) != 0) {
            throw new GDSRecordException(this.i18n.getString(
                    this.i18n.i18n_RECORD_THROW9));
        }

        validateRec(rectype, SHORT_TYPE, maxlen, minlen);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of string
     * datatype and contains len number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   len      Expected number of data bytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateStringRec(byte rectype, int len) throws GDSRecordException {
        validateStringRec(rectype, len, len);
    }

    /**
     * Ensures a record is well formed, of the expected record type, of string
     * datatype and contains between min and max number of data bytes
     *
     * @param   rectype  Expected record type
     * @param   maxlen   Max number of data bytes
     * @param   minlen   Min number of databytes
     *
     * @throws  GDSRecordException  Thrown if the record is invalid
     */
    protected void validateStringRec(byte rectype, int maxlen, int minlen) throws GDSRecordException {
        if ((this.data.length % 2) != 0) {
            throw new GDSRecordException(this.i18n.getString(
                    this.i18n.i18n_RECORD_THROW9));
        }

        validateRec(rectype, STRING_TYPE, maxlen, minlen);
    }

    /**
     * A method to validate the record data
     *
     * @param   rectype  The record type
     * @param   dattype  The data type
     * @param   maxlen   The maximum record length
     * @param   minlen   The minimum record length
     *
     * @throws  GDSRecordException  If the record is malformed
     */
    private void validateRec(
            byte rectype,
            byte dattype,
            int maxlen,
            int minlen) throws GDSRecordException {
        if ((this.rectype != rectype) || (this.dattype != dattype) ||
                (this.data.length < minlen) || (this.data.length > maxlen)) {
            String rec = Integer.toHexString(rectype);
            String dtp = String.valueOf(dattype);

            if (minlen != maxlen) {
                throw new GDSRecordException(GDSStringUtil.sprintf(
                        this.i18n.getString(this.i18n.i18n_RECORD_THROW10), rec, dtp,
                        (maxlen + 4), (minlen + 4)));
            }
            else {
                throw new GDSRecordException(GDSStringUtil.sprintf(
                        this.i18n.getString(this.i18n.i18n_RECORD_THROW11), rec, dtp,
                        (maxlen + 4)));
            }
        }
    }
} // end class GDSRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
