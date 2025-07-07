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
 * A Bachus Naur test for the GDSII &lt;sref&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.14 $
 * @since    1.5
 */
public class SrefValidator
extends BNFRequiredTest {
  /**
   * Creates a new SrefValidator object.
   *
   * @throws  BNFTestException  If an object creation error occurs
   */
  public SrefValidator()
    throws BNFTestException{super(buildTests());}

  /**
   * A method to retrieve data collected during the evaluation of this test
   *
   * @return  A list containing the data collected by this test during
   *          evaluation
   */
  public Object collect() {
    List<StructureReference> result   = new ArrayList<StructureReference>();
    List<Object>             retValue = (List<Object>)super.collect();
    retValue = GDSSpecificDataConverter.flattenList(new ArrayList<Object>(),
        retValue);
    StructureReference sref = new StructureReference();
    for(int i = 0;i < retValue.size();i++) {
      if(retValue.get(i) instanceof Transform) {
        sref.setTransform((Transform)retValue.get(i));
      } else {
        GDSRecord rec = (GDSRecord)((BNFTestableObject)retValue.get(i))
            .getData();
        switch(rec.getRectype()) {
          case GDSRecord.ELFLAGS:
            sref.setExternal(((GDSElflagsRecord)rec).isExternal());
            sref.setTemplate(((GDSElflagsRecord)rec).isTemplate());
            break;

          case GDSRecord.PLEX:
            sref.setGroup(((GDSPlexRecord)rec).getPlex());
            break;

          case GDSRecord.SNAME:
            sref.setName(((GDSSnameRecord)rec).getSname());
            break;

          case GDSRecord.XY:
            sref.setPoints(((GDSXyRecord)rec).getXy());
            break;

          default:
            break;
        }
      }
    } // end for
    result.add(sref);

    return result;
  } // end method collect

  /**
   * Returns a string representation of this validator
   *
   * @return  The physical address of this object
   */
  public String toString(){return super.toString();}

  /**
   * The list of child tests that comprise the SrefValidator
   *
   * @return  A list of tests that comprise the SREF element
   *
   * @throws  BNFTestException  If an error in list creation occurs
   */
  private static BNFTestImplementor[] buildTests()
    throws BNFTestException{return new BNFTestImplementor[]{
      new BNFNoFallthruTest(GDSRecord.SREF),
      new BNFOptionalTest(
        new BNFTestImplementor[]{new BNFNoFallthruTest(GDSRecord.ELFLAGS)}),
      new BNFOptionalTest(
        new BNFTestImplementor[]{new BNFNoFallthruTest(GDSRecord.PLEX)}),
      new BNFNoFallthruTest(GDSRecord.SNAME),
      new BNFOptionalTest(new BNFTestImplementor[]{new StransValidator()}),
      new BNFNoFallthruTest(GDSRecord.XY)
    };}
} // end class SrefValidator


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
