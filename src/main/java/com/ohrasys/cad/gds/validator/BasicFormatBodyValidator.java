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

package com.ohrasys.cad.gds.validator;

import com.ohrasys.cad.bnf.*;
import com.ohrasys.cad.gds.*;

/**
 * A Bachus Naur test for the basic format body GDSII &lt;format&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.11 $
 * @since    1.5
 */
public class BasicFormatBodyValidator
extends BNFRequiredTest {
  /**
   * Creates a new BasicFormatBodyValidator object.
   *
   * @throws  BNFTestException  If an error in object creation occurs
   */
  public BasicFormatBodyValidator()
    throws BNFTestException{super(buildTests());}

  /**
   * Returns a string representation of this validator
   *
   * @return  The physical address of this object
   */
  public String toString(){return super.toString();}

  /**
   * The list of child tests that comprise the BasicFormatBodyValidator
   *
   * @return  The list of tests that comprise the basic format body element
   *
   * @throws  BNFTestException  If an error in building the test list occurs
   */
  private static BNFTestImplementor[] buildTests()
    throws BNFTestException{return new BNFTestImplementor[]{new BNFNoFallthruTest(
        GDSRecord.FORMAT)};}
} // end class BasicFormatBodyValidator


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
