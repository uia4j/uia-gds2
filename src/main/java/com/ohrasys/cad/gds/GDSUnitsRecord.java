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
import java.text.*;

/**
 * Represents a GDSII UNITS record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.9 $
 * @since    1.5
 */
public class GDSUnitsRecord
extends GDSRecord {
  /** The number of meters per database unit */
  private double metersperdbu;

  /** The number of user units per database unit */
  private double uuperdbu;

  /**
   * Creates a new GDSUnitsRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid UNITS record.
   */
  public GDSUnitsRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSUnitsRecord object.
   *
   * @param   uuperdbu      The number of user units per database unit.
   * @param   metersperdbu  The number of meters per database unit.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSUnitsRecord(double uuperdbu, double metersperdbu)
    throws GDSRecordException {
    setUuperdbu(uuperdbu);
    setMetersperdbu(metersperdbu);
    this.rectype = UNITS;
    this.dattype = DOUBLE_TYPE;
    this.length  = (short)20;
    this.data    = updateData();
  }

  /**
   * Creates a new GDSUnitsRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSUnitsRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateDoubleRec(UNITS, 16);

    ByteArrayInputStream bais = new ByteArrayInputStream(data);
    this.uuperdbu     = GDSSpecificDataConverter.readDouble(bais);
    this.metersperdbu = GDSSpecificDataConverter.readDouble(bais);
    this.data         = updateData();
  }

  /**
   * Returns the meters per database unit.
   *
   * @return  The meters per database unit.
   */
  public double getMetersperdbu(){return this.metersperdbu;}

  /**
   * Returns the user units per database unit.
   *
   * @return  The user units per database unit.
   */
  public double getUuperdbu(){return this.uuperdbu;}

  /**
   * Sets the meters per database unit.
   *
   * @param  metersperdbu  The meters per database unit.
   */
  public void setMetersperdbu(double metersperdbu) {
    this.metersperdbu = metersperdbu;
    this.data         = updateData();
  }

  /**
   * Sets the user units per database units.
   *
   * @param  uuperdbu  The user units per database units.
   */
  public void setUuperdbu(double uuperdbu) {
    this.uuperdbu = uuperdbu;
    this.data     = updateData();
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    String result = new String();
    result += GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_UNITS_TOSTRING1), uuperdbu);
    result += GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_UNITS_TOSTRING2), metersperdbu);
    result += GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_UNITS_TOSTRING3), (metersperdbu / uuperdbu));

    return result;
  }

  /**
   * A method to update the byte data of the record
   *
   * @return  The updated byte data of the record
   */
  private byte[] updateData() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos = GDSSpecificDataConverter.fromJavaDouble(uuperdbu, baos);
    baos = GDSSpecificDataConverter.fromJavaDouble(metersperdbu, baos);

    return baos.toByteArray();
  }
} // end class GDSUnitsRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
