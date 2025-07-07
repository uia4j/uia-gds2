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
 * Represents a GDSII PROPATTR record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSPropattrRecord
extends GDSRecord {
  /** The property attribute */
  private short propattr;

  /**
   * Creates a new GDSPropattrRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid PROPATTR record.
   */
  public GDSPropattrRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSPropattrRecord object.
   *
   * @param   propattr  The attribute.
   *
   * @throws  GDSRecordException  If the attribute is not in the range 1:127.
   */
  public GDSPropattrRecord(short propattr)
    throws GDSRecordException {
    setPropattr(propattr);
    this.rectype = PROPATTR;
    this.dattype = SHORT_TYPE;
    this.length  = (short)6;
  }

  /**
   * Creates a new GDSPropattrRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSPropattrRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateShortRec(PROPATTR);
    this.propattr = validate(GDSByteConverter.toShort(data));
  }

  /**
   * Returns the property attribute.
   *
   * @return  The property attribute.
   */
  public short getPropattr(){return this.propattr;}

  /**
   * Sets the property attribute.
   *
   * @param   propattr  The attribute.
   *
   * @throws  GDSRecordException  If the attribute is not in the range 1:127.
   */
  public void setPropattr(short propattr)
    throws GDSRecordException {
    this.propattr = validate(propattr);
    this.data     = GDSByteConverter.writeShort(propattr);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_PROPATTR_TOSTRING), propattr);
  }

  /**
   * A method to validate the property attribute value
   *
   * @param   propattr  The property attribute value to validate
   *
   * @return  The property attribute
   *
   * @throws  GDSRecordException  If the value is not in the range 1-127
   */
  private short validate(short propattr)
    throws GDSRecordException {
    if((propattr >= 1) && (propattr <= 127)){return propattr;}

    throw new GDSRecordException(i18n.getString(i18n.i18n_PROPATTR_THROW));
  }
} // end class GDSPropattrRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
