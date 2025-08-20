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
 * Represents a GDSII null word. Please see <a
 * href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal reference</a> for a
 * more complete discussion of the GDSII stream syntax.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.5 $
 * @since    1.5
 */
public class GDSNullRecord extends GDSRecord {

    /**
     * Creates a new GDSNullRecord object.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSNullRecord() throws GDSRecordException {
        super();
    }

    /**
     * Returns a byte array representation of the record.
     *
     * @return  A byte array representation of the record.
     */
    @Override
    public byte[] toBytes() {
        return new byte[2];
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSI18NFactory.getString(GDSI18NFactory.i18n_NULL_TOSTRING);
    }
}
/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
