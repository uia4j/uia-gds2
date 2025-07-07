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

/**
 * Create a specific GDSII record based on the record type of the GDSRecord
 * argument.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSRecordFactory {
  /**
   * Expands an unidentified GDSRecord to a specifc type of GDSII record based
   * on the record type.
   *
   * @param   rec  The record to expand.
   *
   * @return  A specific GDSII record with of the appropriate type.
   *
   * @throws  GDSRecordException  If the record is not a valid GDSII record.
   */
  public static GDSRecord expandRecord(GDSRecord rec)
    throws GDSRecordException {
    GDSRecord result = rec;

    switch(rec.getRectype()) {
      case 0x00:
        result = new GDSHeaderRecord(rec);

        break;

      case 0x01:
        result = new GDSBgnlibRecord(rec);

        break;

      case 0x02:
        result = new GDSLibnameRecord(rec);

        break;

      case 0x03:
        result = new GDSUnitsRecord(rec);

        break;

      case 0x04:
        result = new GDSEndlibRecord(rec);

        break;

      case 0x05:
        result = new GDSBgnstrRecord(rec);

        break;

      case 0x06:
        result = new GDSStrnameRecord(rec);

        break;

      case 0x07:
        result = new GDSEndstrRecord(rec);

        break;

      case 0x08:
        result = new GDSBoundaryRecord(rec);

        break;

      case 0x09:
        result = new GDSPathRecord(rec);

        break;

      case 0x0A:
        result = new GDSSrefRecord(rec);

        break;

      case 0x0B:
        result = new GDSArefRecord(rec);

        break;

      case 0x0C:
        result = new GDSTextRecord(rec);

        break;

      case 0x0D:
        result = new GDSLayerRecord(rec);

        break;

      case 0x0E:
        result = new GDSDatatypeRecord(rec);

        break;

      case 0x0F:
        result = new GDSWidthRecord(rec);

        break;

      case 0x10:
        result = new GDSXyRecord(rec);

        break;

      case 0x11:
        result = new GDSEndelRecord(rec);

        break;

      case 0x12:
        result = new GDSSnameRecord(rec);

        break;

      case 0x13:
        result = new GDSColrowRecord(rec);

        break;

      case 0x14:
        result = new GDSTextnodeRecord(rec);

        break;

      case 0x15:
        result = new GDSNodeRecord(rec);

        break;

      case 0x16:
        result = new GDSTexttypeRecord(rec);

        break;

      case 0x17:
        result = new GDSPresentationRecord(rec);

        break;

      case 0x18:
        result = new GDSSpacingRecord(rec);

        break;

      case 0x19:
        result = new GDSStringRecord(rec);

        break;

      case 0x1A:
        result = new GDSStransRecord(rec);

        break;

      case 0x1B:
        result = new GDSMagRecord(rec);

        break;

      case 0x1C:
        result = new GDSAngleRecord(rec);

        break;

      case 0x1D:
        result = new GDSUintegerRecord(rec);

        break;

      case 0x1E:
        result = new GDSUstringRecord(rec);

        break;

      case 0x1F:
        result = new GDSReflibsRecord(rec);

        break;

      case 0x20:
        result = new GDSFontsRecord(rec);

        break;

      case 0x21:
        result = new GDSPathtypeRecord(rec);

        break;

      case 0x22:
        result = new GDSGenerationsRecord(rec);

        break;

      case 0x23:
        result = new GDSAttrtableRecord(rec);

        break;

      case 0x24:
        result = new GDSStyptableRecord(rec);

        break;

      case 0x25:
        result = new GDSStrtypeRecord(rec);

        break;

      case 0x26:
        result = new GDSElflagsRecord(rec);

        break;

      case 0x27:
        result = new GDSElkeyRecord(rec);

        break;

      case 0x28:
        result = new GDSLinktypeRecord(rec);

        break;

      case 0x29:
        result = new GDSLinkkeysRecord(rec);

        break;

      case 0x2A:
        result = new GDSNodetypeRecord(rec);

        break;

      case 0x2B:
        result = new GDSPropattrRecord(rec);

        break;

      case 0x2C:
        result = new GDSPropvalueRecord(rec);

        break;

      case 0x2D:
        result = new GDSBoxRecord(rec);

        break;

      case 0x2E:
        result = new GDSBoxtypeRecord(rec);

        break;

      case 0x2F:
        result = new GDSPlexRecord(rec);

        break;

      case 0x30:
        result = new GDSBgnextnRecord(rec);

        break;

      case 0x31:
        result = new GDSEndextnRecord(rec);

        break;

      case 0x32:
        result = new GDSTapenumRecord(rec);

        break;

      case 0x33:
        result = new GDSTapecodeRecord(rec);

        break;

      case 0x34:
        result = new GDSStrclassRecord(rec);

        break;

      case 0x35:
        result = new GDSReservedRecord(rec);

        break;

      case 0x36:
        result = new GDSFormatRecord(rec);

        break;

      case 0x37:
        result = new GDSMaskRecord(rec);

        break;

      case 0x38:
        result = new GDSEndmasksRecord(rec);

        break;

      case 0x39:
        result = new GDSLibdirsizeRecord(rec);

        break;

      case 0x3A:
        result = new GDSSrfnameRecord(rec);

        break;

      case 0x3B:
        result = new GDSLibsecurRecord(rec);

        break;

      case 0x3C:
        result = new GDSBorderRecord(rec);

        break;

      case 0x3D:
        result = new GDSSoftfenceRecord(rec);

        break;

      case 0x3E:
        result = new GDSHardfenceRecord(rec);

        break;

      case 0x3F:
        result = new GDSSoftwireRecord(rec);

        break;

      case 0x40:
        result = new GDSHardwireRecord(rec);

        break;

      case 0x41:
        result = new GDSPathportRecord(rec);

        break;

      case 0x42:
        result = new GDSNodeportRecord(rec);

        break;

      case 0x43:
        result = new GDSUserconstraintRecord(rec);

        break;

      case 0x44:
        result = new GDSSpacererrorRecord(rec);

        break;

      case 0x45:
        result = new GDSContactRecord(rec);

        break;

      default:
        result = new GDSNullRecord();

        break;
    }

    return result;
  } // end method expandRecord

  /**
   * Returns a string representation of this class
   *
   * @return  The physical address of this instance
   */
  public String toString(){return super.toString();}
} // end class GDSRecordFactory


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
