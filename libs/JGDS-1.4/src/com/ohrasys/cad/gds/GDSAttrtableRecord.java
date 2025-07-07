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
 * Represents a GDSII ATTRTABLE record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.10 $
 * @since    1.5
 */
public class GDSAttrtableRecord
extends GDSRecord {
  /** The name of the attribute table */
  private String attrtable;

  /**
   * Creates a new GDSAttrtableRecord object base on an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid ATTRTABLE record.
   */
  public GDSAttrtableRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSAttrtableRecord object.
   *
   * @param   attrtable  The name of the attribute definithion file. The
   *                     filename can include directory specifiers separated by
   *                     "/" and an extension separated by ".".
   *
   * @throws  GDSRecordException  If the filename doesn't meet the naming
   *                              requirements.
   */
  public GDSAttrtableRecord(String attrtable)
    throws GDSRecordException {
    setAttrtable(attrtable);
    this.rectype = ATTRTABLE;
    this.dattype = STRING_TYPE;
  }

  /**
   * Creates a new GDSAttrtableRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSAttrtableRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateStringRec(ATTRTABLE, 44, 2);
    this.attrtable = GDSSpecificDataConverter.toJavaString(data);
  }

  /**
   * Returns the attribute filename.
   *
   * @return  The current attribute filename.
   */
  public String getAttrtable(){return new String(attrtable);}

  /**
   * Sets the attribute filename.
   *
   * @param   attrtable  The attribute filename.
   *
   * @throws  GDSRecordException  If the filename doesn't meet the naming
   *                              requirements.
   */
  public void setAttrtable(String attrtable)
    throws GDSRecordException {
    if((attrtable == null) || (attrtable.length() < 1) ||
        (attrtable.length() > 44)) {
      throw new GDSRecordException(i18n.getString(
          i18n.i18n_ATTRTBL_THROW));
    }

    this.attrtable = new String(attrtable);
    this.data      = GDSSpecificDataConverter.writeJavaString(attrtable);
    this.length    = (short)(data.length + 4);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_ATTRTBL_TOSTRING), attrtable);
  }
} // end class GDSAttrtableRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
