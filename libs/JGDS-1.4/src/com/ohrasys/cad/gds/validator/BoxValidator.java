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
import com.ohrasys.cad.gds.dao.*;

import java.util.*;

/**
 * A Bachus Naur test for the GDSII &lt;box&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.13 $
 * @since    1.5
 */
public class BoxValidator
extends BNFRequiredTest {
  /**
   * Creates a new BoxValidator object.
   *
   * @throws  BNFTestException  If an error in object creation occurs
   */
  public BoxValidator()
    throws BNFTestException{super(buildTests());}

  /**
   * A method to retrieve data collected during the evaluation of this test
   *
   * @return  A list containing the data collected by this test during
   *          evaluation
   */
  public Object collect() {
    List<Box>    result   = new ArrayList<Box>();
    List<Object> retValue = (List<Object>)super.collect();
    retValue = GDSSpecificDataConverter.flattenList(new ArrayList<Object>(),
        retValue);
    Box box = new Box();
    for(int i = 0;i < retValue.size();i++) {
      GDSRecord rec = (GDSRecord)((BNFTestableObject)retValue.get(i))
          .getData();
      switch(rec.getRectype()) {
        case GDSRecord.ELFLAGS:
          box.setExternal(((GDSElflagsRecord)rec).isExternal());
          box.setTemplate(((GDSElflagsRecord)rec).isTemplate());
          break;

        case GDSRecord.PLEX:
          box.setGroup(((GDSPlexRecord)rec).getPlex());
          break;

        case GDSRecord.LAYER:
          box.setLayer(((GDSLayerRecord)rec).getLayer());
          break;

        case GDSRecord.BOXTYPE:
          box.setDatatype(((GDSBoxtypeRecord)rec).getBoxtype());
          break;

        case GDSRecord.XY:
          box.setPoints(((GDSXyRecord)rec).getXy());
          break;

        default:
          break;
      }
    } // end for
    result.add(box);

    return result;
  } // end method collect

  /**
   * Returns a string representation of this validator
   *
   * @return  The physical address of this object
   */
  public String toString(){return super.toString();}

  /**
   * The list of child tests that comprise the BoxValidator
   *
   * @return  A list of tests that comprise the Box element
   *
   * @throws  BNFTestException  If an error in test list creation occurs
   */
  private static BNFTestImplementor[] buildTests()
    throws BNFTestException{return new BNFTestImplementor[]{
      new BNFNoFallthruTest(GDSRecord.BOX),
      new BNFOptionalTest(
        new BNFTestImplementor[]{new BNFNoFallthruTest(GDSRecord.ELFLAGS)}),
      new BNFOptionalTest(
        new BNFTestImplementor[]{new BNFNoFallthruTest(GDSRecord.PLEX)}),
      new BNFNoFallthruTest(GDSRecord.LAYER),
      new BNFNoFallthruTest(GDSRecord.BOXTYPE),
      new BNFNoFallthruTest(GDSRecord.XY)
    };}
} // end class BoxValidator


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
