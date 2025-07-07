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
import java.util.*;

/**
 * A box element data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.6 $, $Date: 2005/05/18 07:11:09 $
 */
public class Box
extends GeometryElement
implements InfoProvider {
  /**
   * Creates a new instance of Boundary
   */
  public Box(){super();}

  /**
   * Creates a new Box object.
   *
   * @param  layer   The layer of the box
   * @param  points  The points defining the box
   */
  public Box(int layer, Point points[]) {
    this(false, false, 0, layer, 0, points);
  }

  /**
   * Creates a new Box object.
   *
   * @param  external  The box is external data
   * @param  template  The box is template data
   * @param  group     The object group the box belongs to
   * @param  layer     The layer of the box
   * @param  datatype  The data type of the box
   * @param  points    The points defining the box
   */
  public Box(boolean external, boolean template, int group, int layer,
      int datatype, Point points[]) {
    super(external, template, group, layer, datatype, points);
  }

  /**
   * Returns a string representation of the box
   *
   * @return  A string representation of the box
   */
  public String toString(){return i18n.getString("I18N_BOX" /*NOI18N*/);}
} // end class Box


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
