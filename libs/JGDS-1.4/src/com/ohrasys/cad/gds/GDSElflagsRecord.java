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
 * Represents a GDSII ELFLAGS record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSElflagsRecord
extends GDSRecord {
  /** A flag indicating element is external */
  private boolean external;

  /** A flag indicating the element is a template */
  private boolean template;

  /**
   * Creates a new GDSElflagsRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid ELFLAGS record.
   */
  public GDSElflagsRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSElflagsRecord object.
   *
   * @param   isTemplate  Flags the data as template data or not.
   * @param   isExternal  Flags the data as external data or not.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSElflagsRecord(boolean isTemplate, boolean isExternal)
    throws GDSRecordException {
    this.template = isTemplate;
    this.external = isExternal;
    this.rectype  = ELFLAGS;
    this.dattype  = BIT_ARRAY_TYPE;
    this.length   = (short)6;
    this.data     = updateData();
  }

  /**
   * Creates a new GDSElflagsRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSElflagsRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateBitarrayRec(ELFLAGS, (short)0x03);
    this.external = (((data[1] >> 1) & 0x01) == 0x01);
    this.template = ((data[1] & 0x01) == 0x01);
  }

  /**
   * Returns whether the data is external data or not.
   *
   * @return  true if the data is external data.
   */
  public boolean isExternal(){return this.external;}

  /**
   * Returns whether the data is template data or not.
   *
   * @return  true if the data is template data.
   */
  public boolean isTemplate(){return this.template;}

  /**
   * Flags the data as external data or not.
   *
   * @param  isExternal  true if the data is external data.
   */
  public void setExternal(boolean isExternal) {
    this.external = isExternal;
    this.data     = updateData();
  }

  /**
   * Flags the data as template data or not.
   *
   * @param  isTemplate  true if the data is template data.
   */
  public void setTemplate(boolean isTemplate) {
    this.template = isTemplate;
    this.data     = updateData();
  }

  /**
   * Retursn a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_ELFLAGS_TOSTRING), template, external);
  }

  /**
   * A method to udpate the record byte data
   *
   * @return  The updated byte data of the record
   */
  private byte[] updateData() {
    short result = (short)0x00;

    if(template){result = (short)(result | 0x0001);}

    if(external){result = (short)(result | 0x0002);}

    return GDSByteConverter.writeShort(result);
  }
} // end class GDSElflagsRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
