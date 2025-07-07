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
 * Represents a GDSII BOXTYPE record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSBoxtypeRecord
extends GDSRecord {
  /** The boxtype */
  private short boxtype;

  /**
   * Creates a new GDSBoxtypeRecord object based on an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid BOXTYPE record.
   */
  public GDSBoxtypeRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSBoxtypeRecord object.
   *
   * @param   boxtype  The boxtype.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSBoxtypeRecord(short boxtype)
    throws GDSRecordException {
    setBoxtype(boxtype);
    this.rectype = BOXTYPE;
    this.dattype = SHORT_TYPE;
    this.length  = (short)6;
  }

  /**
   * Creates a new GDSBoxtypeRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSBoxtypeRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateShortRec(BOXTYPE);
    this.boxtype = validate(GDSByteConverter.toShort(data));
  }

  /**
   * Returns the boxtype.
   *
   * @return  The boxtype.
   */
  public short getBoxtype(){return this.boxtype;}

  /**
   * Sets the boxtype.
   *
   * @param   boxtype  The new boxtype.
   *
   * @throws  GDSRecordException  If the boxtype isn't in the range of 0:255.
   */
  public void setBoxtype(short boxtype)
    throws GDSRecordException {
    this.boxtype = validate(boxtype);
    this.data    = GDSByteConverter.writeShort(boxtype);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(i18n.i18n_BOXTYPE_THROW),
        boxtype);
  }

  /**
   * A method to validate the record data
   *
   * @param   boxtype  The boxtype!
   *
   * @return  The boxtype if it is in the range of 0-255
   *
   * @throws  GDSRecordException  If the boxtype is not valid
   */
  private short validate(short boxtype)
    throws GDSRecordException {
    if((boxtype >= 0) && (boxtype <= 255)){return boxtype;}

    throw new GDSRecordException(i18n.getString(i18n.i18n_BOXTYPE_THROW));
  }
} // end class GDSBoxtypeRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
