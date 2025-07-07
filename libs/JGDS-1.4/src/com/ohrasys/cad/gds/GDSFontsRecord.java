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
 * Represents a GDSII FONTS record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSFontsRecord
extends GDSRecord {
  /** The list of fonts used in the library */
  private String fonts[];

  /**
   * Creates a new GDSFontsRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid FONTS record.
   */
  public GDSFontsRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSFontsRecord object.
   *
   * @param   fonts  The font mapping filenames.
   *
   * @throws  GDSRecordException  If there are less than one or more than 4 font
   *                              mapping files.
   */
  public GDSFontsRecord(String fonts[])
    throws GDSRecordException {
    setFonts(fonts);
    this.rectype = FONTS;
    this.dattype = STRING_TYPE;
    this.length  = (short)180;
  }

  /**
   * Creates a new GDSFontsRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSFontsRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateStringRec(FONTS, 4 * 44);
    this.fonts = new String[4];

    byte register[] = new byte[44];

    for(int i = 0;i < 4;i++) {
      System.arraycopy(data, i * 44, register, 0, 44);
      this.fonts[i] = GDSSpecificDataConverter.toJavaString(register);
    }

    validate(this.fonts);
  }

  /**
   * Returns the font mapping file names.
   *
   * @return  A String array containing the names of the font mapping files.
   */
  public String[] getFonts() {
    String result[] = new String[fonts.length];
    System.arraycopy(fonts, 0, result, 0, fonts.length);

    return result;
  }

  /**
   * Sets the font mapping filenames.
   *
   * @param   fonts  The font mapping filenames.
   *
   * @throws  GDSRecordException  If there are less than one or more than 4 font
   *                              mapping files.
   */
  public void setFonts(String fonts[])
    throws GDSRecordException {
    this.fonts = new String[validate(fonts).length];
    System.arraycopy(fonts, 0, this.fonts, 0, fonts.length);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    for(int i = 0;i < fonts.length;i++) {
      baos = GDSSpecificDataConverter.fromJavaString(fonts[i], 44, baos);
    }

    this.data = baos.toByteArray();
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    String result = i18n.getString(i18n.i18n_FONTS_TOSTRING1);

    for(int i = 0;i < fonts.length;i++) {
      result += GDSStringUtil.sprintf(i18n.getString(
            i18n.i18n_FONTS_TOSTRING2), i, fonts[i]);
    }

    return result;
  }

  /**
   * A method to validate the record data
   *
   * @param   fonts  The list of fonts
   *
   * @return  The list of fonts
   *
   * @throws  GDSRecordException  If the list doesn't contain 0 or 4 fonts
   *                              strings each of 44 bytes in length
   */
  private String[] validate(String fonts[])
    throws GDSRecordException {
    boolean noneDefined = true;

    if((fonts == null) || (fonts.length != 4)) {
      throw new GDSRecordException(i18n.getString(
          i18n.i18n_FONTS_THROW1));
    }

    for(int i = 0;i < fonts.length;i++) {
      if(fonts[i].length() > 0){noneDefined = false;}

      if(fonts[i].length() > 44) {
        throw new GDSRecordException(i18n.getString(
            i18n.i18n_FONTS_THROW2));
      }
    }

    if(noneDefined) {
      throw new GDSRecordException(i18n.getString(
          i18n.i18n_FONTS_THROW3));
    }

    return fonts;
  }
} // end class GDSFontsRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
