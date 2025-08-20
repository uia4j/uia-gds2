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
 * Represents a GDSII STRANS record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSStransRecord extends GDSRecord {

    /** Indicates the angle is absolute */
    private boolean absAngle;

    /** Indicates the magnification is absolute */
    private boolean absMag;

    /** Indicates the element is mirrored about the x-axis */
    private boolean mirroredX;

    /**
     * Creates a new GDSStransRecord object from and existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid STRANS record.
     */
    public GDSStransRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSStransRecord object.
     *
     * @param   isMirroredX  true if the element is mirrored.
     * @param   isAbsMag     true if the magnification is absolute.
     * @param   isAbsAngle   true if the angle is absolute.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSStransRecord(boolean isMirroredX, boolean isAbsMag, boolean isAbsAngle) throws GDSRecordException {
        this.mirroredX = isMirroredX;
        this.absAngle = isAbsAngle;
        this.absMag = isAbsMag;
        this.rectype = STRANS;
        this.dattype = BIT_ARRAY_TYPE;
        this.length = (short) 6;
        this.data = updateData();
    }

    /**
     * Creates a new GDSStransRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSStransRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateBitarrayRec(STRANS, (short) 0x8007);

        byte msb = data[1];
        byte lsb = data[0];
        this.mirroredX = (((lsb >> 7) & 0x01) == 0x01);
        this.absMag = (((msb >> 2) & 0x01) == 0x01);
        this.absAngle = (((msb >> 1) & 0x01) == 0x01);
    }

    /**
     * Returns true if the angle is absolute.
     *
     * @return  true if the angle is absolute.
     */
    public boolean isAbsAngle() {
        return this.absAngle;
    }

    /**
     * Returns true if the magnification is absolute.
     *
     * @return  true if the magnification is absolute.
     */
    public boolean isAbsMag() {
        return this.absMag;
    }

    /**
     * Returns true if the element is mirrored.
     *
     * @return  true if the element is mirrored.
     */
    public boolean isMirroredX() {
        return this.mirroredX;
    }

    /**
     * Flags the element as having an absolute rotation angle.
     *
     * @param  isAbsAngle  true if the angle is absolute.
     */
    public void setAbsAngle(boolean isAbsAngle) {
        this.absAngle = isAbsAngle;
        this.data = updateData();
    }

    /**
     * Flags the element as having absolute magnification.
     *
     * @param  isAbsMag  true if the magnification is absolute.
     */
    public void setAbsMag(boolean isAbsMag) {
        this.absMag = isAbsMag;
        this.data = updateData();
    }

    /**
     * Flags the element as mirrored.
     *
     * @param  isMirrored  true if the element is mirrored.
     */
    public void setMirroredX(boolean isMirrored) {
        this.mirroredX = isMirrored;
        this.data = updateData();
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        String result = GDSI18NFactory.getString(GDSI18NFactory.i18n_STRANS_TOSTRING);
        result += (this.mirroredX
                ? " MirroredX:" /*NOI18N*/
                : " UnmirroredX:" /*NOI18N*/);
        result += (this.absMag
                ? "AbsMagnification:" /*NOI18N*/
                : "RelMagnification:" /*NOI18N*/);
        result += (this.absAngle ? "AbsAngle" /*NOI18N*/ : "RelAngle" /*NOI18N*/);

        return result;
    }

    /**
     * A method to update the byte data of the record
     *
     * @return  The updated byte data
     */
    private byte[] updateData() {
        short result = (short) 0x00;

        if (this.mirroredX) {
            result = (short) (result | 0x8000);
        }

        if (this.absMag) {
            result = (short) (result | 0x0004);
        }

        if (this.absAngle) {
            result = (short) (result | 0x0002);
        }

        return GDSByteConverter.writeShort(result);
    }
} // end class GDSStransRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
