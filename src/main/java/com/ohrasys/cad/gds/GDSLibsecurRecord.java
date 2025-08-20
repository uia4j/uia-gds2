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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a GDSII LIBSECUR record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.9 $
 * @since    1.5
 */
public class GDSLibsecurRecord extends GDSRecord {

    /** The list of access control elements */
    private List<GDSAccessControl> libsecur = new ArrayList<GDSAccessControl>();

    /**
     * Creates a new GDSLibsecurRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid LIBSECUR record.
     */
    public GDSLibsecurRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSLibsecurRecord object.
     *
     * @param   list  The access control list.
     *
     * @throws  GDSRecordException  If the list doesn't contain between 1 and 32
     *                              members.
     */
    public GDSLibsecurRecord(GDSAccessControl list[]) throws GDSRecordException {
        setLibsecur(list);
        this.rectype = LIBSECUR;
        this.dattype = SHORT_TYPE;
    }

    /**
     * Creates a new GDSLibsecurRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSLibsecurRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateShortRec(LIBSECUR, 192, 6);

        if ((data.length % 6) != 0) {
            throw new GDSRecordException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_LIBSECUR_THROW));
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(data);

        for (int i = 0; i < (data.length / 6); i++) {
            short group = GDSByteConverter.readShort(bais);
            short user = GDSByteConverter.readShort(bais);
            short access = GDSByteConverter.readShort(bais);
            GDSAccessControl elem = new GDSAccessControl(group, user, access);

            if (!this.libsecur.contains(elem)) {
                this.libsecur.add(elem);
            }
        }
    }

    /**
     * Returns a copy of the access control list.
     *
     * @return  A copy of the access control list.
     */
    public GDSAccessControl[] getLibsecur() {
        GDSAccessControl result[] = new GDSAccessControl[this.libsecur.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = new GDSAccessControl(this.libsecur.get(i));
        }

        return result;
    }

    /**
     * Sets the access control list for the library.
     *
     * @param   libsecur  The access control list.
     *
     * @throws  GDSRecordException  If the list doesn't contain between 1 and 32
     *                              members.
     */
    public void setLibsecur(GDSAccessControl libsecur[]) throws GDSRecordException {
        if ((libsecur.length < 1) || (libsecur.length > 32)) {
            throw new GDSRecordException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_LIBSECUR_THROW));
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.libsecur.clear();

        for (int i = 0; i < libsecur.length; i++) {
            if (!this.libsecur.contains(libsecur[i])) {
                this.libsecur.add(libsecur[i]);
                baos = GDSByteConverter.fromShort(libsecur[i].getGroup(), baos);
                baos = GDSByteConverter.fromShort(libsecur[i].getUser(), baos);
                baos = GDSByteConverter.fromShort(libsecur[i].getAccess(),
                        baos);
            }
        }

        this.data = baos.toByteArray();
        this.length = (short) (this.data.length + 4);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        String result = GDSI18NFactory.getString(GDSI18NFactory.i18n_LIBSECUR_TOSTRING1);

        for (int i = 0; i < this.libsecur.size(); i++) {
            result += GDSStringUtil.sprintf(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_LIBSECUR_TOSTRING2), i,
                    this.libsecur.get(i).toString());
        }

        return result;
    }
} // end class GDSLibsecurRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
