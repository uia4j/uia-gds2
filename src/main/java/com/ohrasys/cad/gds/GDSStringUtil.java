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
import java.util.*;

/**
 * String utility class.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.12 $
 * @since    1.5
 */
public class GDSStringUtil {
  /**
   * Tests to see if a string consists solely of specified characters.
   *
   * @param   data   The string to test.
   * @param   chars  A string of valid characters.
   *
   * @return  true if the string is a subset of the specified characters.
   */
  public static boolean consistsOf(String data, String chars) {
    for(int i = 0;i < data.length();i++) {
      char    c  = data.charAt(i);
      boolean ok = true;

      for(int j = 0;j < chars.length();j++) {
        if(chars.indexOf(c) < 0) {
          ok = false;

          break;
        }
      }

      if(!ok){return false;}
    }

    return true;
  }

  /**
   * Pads a string with blank spaces up to a specified length.
   *
   * @param   data    The string to extend.
   * @param   length  The required length of the string.
   *
   * @return  A new copy of the original string padded with spaces so that it's
   *          length is that specified.
   */
  public static String fixLength(String data, int length) {
    String result = data;

    if(result.length() < length) {
      for(int i = data.length();i < length;i++) {
        result += " " /*NOI18N*/;
      }
    } else{result = data.substring(0, length - 1);}

    return result;
  }

  /**
   * Returns a string consisting of a repeated pattern of the specified
   * separator up to the specified length.
   *
   * @param   sep  The separator to repeat.
   * @param   len  The maximum length of the result.
   *
   * @return  A new string of the repeated specified pattern of specified
   *          length.
   */
  public static String getSeparator(String sep, int len) {
    String result = new String();

    for(int i = 0;i < len;i++) {
      result += sep.charAt(i % sep.length());
    }

    return result;
  }

  /**
   * A <code>sprintf</code> method implementation.
   *
   * @param   format  The format control string
   * @param   args    The format token values
   *
   * @return  The corresponding formatted string
   */
  public static String sprintf(String format, Object... args) {
    return String.format(format, args);
  }

  /**
   * Returns a string representation of this class
   *
   * @return  The physical address of this instance
   */
  public String toString(){return super.toString();}
} // end class GDSStringUtil


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
