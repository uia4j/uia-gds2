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

package com.ohrasys.cad.gds.dao;

import java.awt.*;

/**
 * A structure reference data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $, $Date: 2005/05/18 07:11:09 $
 */
public class StructureReference
extends ReferenceElement
implements InfoProvider {
  /**
   * Creates a new StructureReference object.
   */
  public StructureReference(){super();}

  /**
   * Creates a new StructureReference object.
   *
   * @param  name    The name of the structure reference
   * @param  points  The points that define the structure reference
   */
  public StructureReference(String name, Point points[]) {
    this(name, new Transform(), points);
  }

  /**
   * Creates a new StructureReference object.
   *
   * @param  name       The name of the strucutre reference
   * @param  transform  The transform of the structure reference
   * @param  points     The points defining the structure refernce
   */
  public StructureReference(String name, Transform transform, Point points[]) {
    this(false, false, 0, name, transform, points);
  }

  /**
   * Creates a new instance of StrucureReference
   *
   * @param  external   The structure reference is external data
   * @param  template   The structure reference is template data
   * @param  group      The object group the structure reference belongs to
   * @param  name       The name of the structure reference
   * @param  transform  The transform of the structure reference
   * @param  points     The points defining the strucutre reference
   */

  public StructureReference(boolean external, boolean template, int group,
      String name, Transform transform, Point points[]) {
    super(external, template, group, name, transform, points);
  }

  /**
   * Returns a textual representation of the structure reference
   *
   * @return  A textual representation of the structure reference
   */
  public String getInfo(){return super.getInfo();}

  /**
   * Returns a string representation of the structure reference
   *
   * @return  A string representation of the structure reference
   */
  public String toString(){return getName();}
} // end class StructureReference


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
