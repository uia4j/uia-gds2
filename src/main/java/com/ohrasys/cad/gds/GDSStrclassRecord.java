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

import java.io.*;

/**
 * Represents a GDSII STRCLASS record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSStrclassRecord
extends GDSRecord {
  /** The record value */
  private short strclass;

  /**
   * Creates a new GDSStrclassRecord object.
   *
   * @throws  GDSRecordException  If thre record is malformed.
   */
  public GDSStrclassRecord()
    throws GDSRecordException {
    this.strclass = (short)0x0000;
    this.rectype  = STRCLASS;
    this.dattype  = BIT_ARRAY_TYPE;
    this.length   = (short)6;
  }

  /**
   * Creates a new GDSStrclassRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid STRCLASS record.
   */
  public GDSStrclassRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSStrclassRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSStrclassRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateBitarrayRec(STRCLASS, (short)0xFFFF);
    this.strclass = GDSByteConverter.toShort(data);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_STRCLASS_TOSTRING), strclass);
  }
} // end class GDSStrclassRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
