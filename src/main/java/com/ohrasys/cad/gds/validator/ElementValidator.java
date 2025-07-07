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
 * A Bachus Naur test for a GDSII &lt;element&gt;
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.13 $
 * @since    1.5
 */
public class ElementValidator
extends BNFRequiredTest {
  /**
   * Creates a new ElementValidator object.
   *
   * @throws  BNFTestException  If an error in object creation occurs
   */
  public ElementValidator()
    throws BNFTestException {
    super(

    new BNFTestImplementor[]{
        new ElementBodyValidator(),
        new BNFOneOrMoreOptionalTest(
          new BNFTestImplementor[]{new PropertyValidator()}),
        new BNFNoFallthruTest(GDSRecord.ENDEL)
      });
  }

  /**
   * A method to retrieve data collected during the evaluation of this test
   *
   * @return  A list containing the data collected by this test during
   *          evaluation
   */
  public Object collect() {
    List<Element> result   = new ArrayList<Element>();
    List<Object>  retValue = (List<Object>)super.collect();
    retValue = GDSSpecificDataConverter.flattenList(new ArrayList<Object>(),
        retValue);
    Element        element    = null;
    List<Property> properties = new ArrayList<Property>();
    for(int i = 0;i < retValue.size();i++) {
      if(retValue.get(i) instanceof Element) {
        element = (Element)retValue.get(i);
      } else if(retValue.get(i) instanceof Property) {
        properties.add((Property)retValue.get(i));
      }
    }
    if(element != null) {
      element.setProperties(properties.toArray(new Property[0]));
    }
    result.add(element);

    return result;
  }

  /**
   * Returns a string representation of this validator
   *
   * @return  The physical address of this object
   */
  public String toString(){return super.toString();}
} // end class ElementValidator


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
