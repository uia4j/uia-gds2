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
 * Represents a GDSII LIBNAME record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSLibnameRecord extends GDSRecord {

    /** The library name */
    private String libname;

    /**
     * Creates a new GDSLibnameRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid LIBNAME record.
     */
    public GDSLibnameRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSLibnameRecord object.
     *
     * @param   libname  The library name.
     *
     * @throws  GDSRecordException  If the library name isn't composed of
     *                              [A-Z,a-z,0-9,.,-,_].
     */
    public GDSLibnameRecord(String libname) throws GDSRecordException {
        setLibname(libname);
        this.rectype = LIBNAME;
        this.dattype = STRING_TYPE;
    }

    /**
     * Creates a new GDSLibnameRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSLibnameRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateStringRec(LIBNAME, 256, 2);
        this.libname = validate(GDSSpecificDataConverter.toJavaString(data));
    }

    /**
     * Returns the library name.
     *
     * @return  The library name.
     */
    public String getLibname() {
        return new String(this.libname);
    }

    /**
     * Sets the library name.
     *
     * @param   libname  The library name.
     *
     * @throws  GDSRecordException  If the library name isn't composed of
     *                              [A-Z,a-z,0-9,.,-,_].
     */
    public void setLibname(String libname) throws GDSRecordException {
        this.libname = validate(new String(libname));
        this.data = GDSSpecificDataConverter.writeJavaString(libname);
        this.length = (short) (this.data.length + 4);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(
                GDSI18NFactory.i18n_LIBNAME_TOSTRING), this.libname);
    }

    /**
     * A method to validate the library name
     *
     * @param   libname  The library name to validate
     *
     * @return  The library name
     *
     * @throws  GDSRecordException  If the library name is null, has a length
     *                              outside the range 1-256 or has illegal
     *                              characters in it
     */
    private String validate(String libname) throws GDSRecordException {
        String validchars = GDSI18NFactory.getString(GDSI18NFactory.i18n_LIBNAME_VALID);

        if ((libname != null) &&
                GDSStringUtil.consistsOf(libname.toLowerCase(), validchars) &&
                (libname.length() <= 256) && (libname.length() >= 1)) {
            return libname;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_LIBNAME_THROW));
    }
} // end class GDSLibnameRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
