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
 * Represents a GDSII PRESENTATION record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSPresentationRecord
extends GDSRecord {
  /** Indicates font type 0. */
  public static final int FONT0 = 0;

  /** Indicates font type 1. */
  public static final int FONT1 = 1;

  /** Indicates font type 2. */
  public static final int FONT2 = 2;

  /** Indicates font type 3. */
  public static final int FONT3 = 3;

  /** Indicates top justification. */
  public static final int TOP = 0;

  /** Indicates middle justification. */
  public static final int MIDDLE = 1;

  /** Indicates bottom justification. */
  public static final int BOTTOM = 2;

  /** Indicates left justification. */
  public static final int LEFT = 0;

  /** Indicates center justification. */
  public static final int CENTER = 1;

  /** Indicates right justification. */
  public static final int RIGHT = 2;

  /** The font number.  Must be in the range of FONT0-FONT3 */
  private int font;

  /**
   * The horizontal justification value.  Must be one of LEFT, CENTER or RIGHT
   */
  private int hJustification;

  /** The vertical justification value.  Must be one of TOP, MIDDLE or BOTTOM */
  private int vJustification;

  /**
   * Creates a new GDSPresentationRecord object from an existing record..
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid PRESENTATION
   *                              record.
   */
  public GDSPresentationRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSPresentationRecord object.
   *
   * @param   font   The font.
   * @param   vjust  The vertical justification.
   * @param   hjust  The horizontal justification.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSPresentationRecord(int font, int vjust, int hjust)
    throws GDSRecordException {
    this.font           = validateFont(font);
    this.vJustification = validateJustification(vjust);
    this.hJustification = validateJustification(hjust);
    this.rectype        = PRESENTATION;
    this.dattype        = BIT_ARRAY_TYPE;
    this.length         = (short)6;
    this.data           = updateData();
  }

  /**
   * Creates a new GDSPresentationRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSPresentationRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateBitarrayRec(PRESENTATION, (short)0x3f);

    byte msb = data[1];
    this.font           = ((msb >> 4) & 0x03);
    this.vJustification = ((msb >> 2) & 0x03);
    this.hJustification = (msb & 0x03);
  }

  /**
   * Returns the font.
   *
   * @return  One of FONT0, FONT1, FONT2 or FONT3.
   */
  public int getFont(){return this.font;}

  /**
   * Returns the horizontal justification.
   *
   * @return  One of LEFT, CENTER or RIGHT.
   */
  public int getHJustification(){return this.hJustification;}

  /**
   * Returns the vertical justification.
   *
   * @return  One of TOP, MIDDLE or BOTTOM.
   */
  public int getVJustification(){return this.vJustification;}

  /**
   * Sets the font.
   *
   * @param   font  The font.
   *
   * @throws  GDSRecordException  If the font is not one of FONT0, FONT1, FONT2
   *                              or FONT3.
   */
  public void setFont(int font)
    throws GDSRecordException {
    this.font = validateFont(font);
    this.data = updateData();
  }

  /**
   * Sets the horizontal justification.
   *
   * @param   hJustification  The justification.
   *
   * @throws  GDSRecordException  If the justification is not one of LEFT,
   *                              CENTER or RIGHT.
   */
  public void setHJustification(int hJustification)
    throws GDSRecordException {
    this.hJustification = validateJustification(hJustification);
    this.data           = updateData();
  }

  /**
   * Sets the vertical justification.
   *
   * @param   vJustification  The justification.
   *
   * @throws  GDSRecordException  If the justification is not one of TOP, MIDDLE
   *                              or BOTTOM.
   */
  public void setVJustification(int vJustification)
    throws GDSRecordException {
    this.vJustification = validateJustification(vJustification);
    this.data           = updateData();
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    String result;

    switch(vJustification) {
      case 0:
        result = GDSStringUtil.sprintf(i18n.getString(
              i18n.i18n_PRESENTATION_TOSTRING1), font);

        break;

      case 1:
        result = GDSStringUtil.sprintf(i18n.getString(
              i18n.i18n_PRESENTATION_TOSTRING2), font);

        break;

      case 2:
        result = GDSStringUtil.sprintf(i18n.getString(
              i18n.i18n_PRESENTATION_TOSTRING3), font);

        break;

      default:
        result = GDSStringUtil.sprintf(i18n.getString(
              i18n.i18n_PRESENTATION_TOSTRING4), font);
    }

    switch(hJustification) {
      case 0:
        result += i18n.getString(i18n.i18n_PRESENTATION_TOSTRING5);

        break;

      case 1:
        result += i18n.getString(i18n.i18n_PRESENTATION_TOSTRING6);

        break;

      case 2:
        result += i18n.getString(i18n.i18n_PRESENTATION_TOSTRING7);

        break;

      default:
        result += i18n.getString(i18n.i18n_PRESENTATION_TOSTRING8);
    }

    return result;
  } // end method toString

  /**
   * A method to udpate the byte data of the record
   *
   * @return  The updated byte data of the record
   */
  private byte[] updateData() {
    short result = (short)0x00;
    result = (short)(result | hJustification);
    result = (short)(result | (vJustification << 2));
    result = (short)(result | (font << 4));

    return GDSByteConverter.writeShort(result);
  }

  /**
   * A method to validate the font number
   *
   * @param   font  The font number to validate
   *
   * @return  The font number
   *
   * @throws  GDSRecordException  If the value is not in the range FONT0-FONT3
   */
  private int validateFont(int font)
    throws GDSRecordException {
    if((font >= FONT0) && (font <= FONT3)){return font;}

    throw new GDSRecordException(i18n.getString(
        i18n.i18n_PRESENTATION_THROW1));
  }

  /**
   * A method to validate the horizontal/vertical justification value
   *
   * @param   just  The justification value to validate
   *
   * @return  The justification value
   *
   * @throws  GDSRecordException  If the value isn't one of LEFT, CENTER, RIGHT
   *                              for horizontal justification or TOP, MIDDLE,
   *                              BOTTOM for vertical justification.
   */
  private int validateJustification(int just)
    throws GDSRecordException {
    if((just >= LEFT) && (just <= RIGHT)){return just;}

    throw new GDSRecordException(i18n.getString(
        i18n.i18n_PRESENTATION_THROW2));
  }
} // end class GDSPresentationRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
