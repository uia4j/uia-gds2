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
 * A BachusNaur test for the GDSII &lt;format&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.12 $
 * @since    1.5
 */
public class FormatValidator
extends BNFRequiredTest {
  /**
   * Creates a new FormatValidator object.
   *
   * @throws  BNFTestException  If an error in object creation occurs
   */
  public FormatValidator()
    throws BNFTestException {
    super(
      new BNFTestImplementor[]{new BNFOneOfManyRequiredTest(
          new BNFTestImplementor[]{
            new ExtendedFormatBodyValidator(),
            new BasicFormatBodyValidator()
          })});
  }

  /**
   * A method to collect Format data during parsing
   *
   * @return  A list containing a Format object
   */
  public Object collect() {
    List<Format> result   = new ArrayList<Format>();
    List<Object> retValue = (List<Object>)super.collect();
    retValue = GDSSpecificDataConverter.flattenList(new ArrayList<Object>(),
        retValue);
    List<String> masks  = null;
    Format       format = new Format();
    for(int i = 0;i < retValue.size();i++) {
      GDSRecord rec = (GDSRecord)((BNFTestableObject)retValue.get(i))
          .getData();
      switch(rec.getRectype()) {
        case GDSRecord.FORMAT:
          format.setType(((GDSFormatRecord)rec).getFormat());
          break;

        case GDSRecord.MASK:
          if(masks == null){masks = new ArrayList<String>();}
          masks.add(((GDSMaskRecord)rec).getMask());
          break;

        default:
          break;
      }
    }
    format.setMasks(masks.toArray(new String[masks.size()]));
    result.add(format);

    return result;
  } // end method collect

  /**
   * Returns a string representation of this validator
   *
   * @return  The physical address of this object
   */
  public String toString(){return super.toString();}
} // end class FormatValidator


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
