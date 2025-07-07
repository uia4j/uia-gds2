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
 * Represents a GDSII LAYER record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSLayerRecord
extends GDSRecord {
  /** The layer number */
  private short layer;

  /**
   * Creates a new GDSLayerRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid LAYER record.
   */
  public GDSLayerRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSLayerRecord object.
   *
   * @param   layer  The layer.
   *
   * @throws  GDSRecordException  If the layer is not in the range of 0:255.
   */
  public GDSLayerRecord(short layer)
    throws GDSRecordException {
    setLayer(layer);
    this.rectype = LAYER;
    this.dattype = SHORT_TYPE;
    this.length  = (short)6;
  }

  /**
   * Creates a new GDSLayerRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSLayerRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateShortRec(LAYER);
    this.layer = validate(GDSByteConverter.toShort(data));
  }

  /**
   * Returns the layer.
   *
   * @return  The layer.
   */
  public short getLayer(){return this.layer;}

  /**
   * Sets the layer.
   *
   * @param   layer  The layer.
   *
   * @throws  GDSRecordException  If the layer is not in the range of 0:255.
   */
  public void setLayer(short layer)
    throws GDSRecordException {
    this.layer = validate(layer);
    this.data  = GDSByteConverter.writeShort(layer);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(i18n.i18n_LAYER_TOSTRING),
        layer);
  }

  /**
   * A method to validate the record data
   *
   * @param   layer  The layer number
   *
   * @return  The layer number
   *
   * @throws  GDSRecordException  If the layer number isn't in the rang 0-255
   */
  private short validate(short layer)
    throws GDSRecordException {
    if((layer >= 0) && (layer <= 255)){return layer;}

    throw new GDSRecordException(i18n.getString(i18n.i18n_LAYER_THROW));
  }
} // end class GDSLayerRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
