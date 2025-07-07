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
 * Geometry element data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.6 $, $Date: 2005/05/18 07:11:09 $
 */
public class GeometryElement
extends Element
implements InfoProvider {
  /** Holds value of property datatype. */
  private int datatype;

  /** Holds value of property layer. */
  private int layer;

  /**
   * Creates a new GeometryElement object.
   */
  public GeometryElement() {
    super();
    setLayer(layer);
    setDatatype(datatype);
  }

  /**
   * Creates a new GeometryElement object.
   *
   * @param  external  The geometry element is external data
   * @param  template  The geometry element is template data
   * @param  group     The object group the geometry element belongs to
   * @param  layer     The layer of the geometry element
   * @param  datatype  The datatype of the geometry element
   * @param  points    The points defining the geometry element
   */
  public GeometryElement(boolean external, boolean template, int group,
      int layer, int datatype, Point points[]) {
    super(external, template, group, points);
    setLayer(layer);
    setDatatype(datatype);
  }

  /**
   * Getter for property datatype.
   *
   * @return  Value of property datatype.
   */
  public int getDatatype(){return this.datatype;}

  /**
   * Returns a textual representation of the geometry element
   *
   * @return  A textual representation of the geometry element
   */
  public String getInfo() {
    String result = new String();
    result += i18n.getString("I18N_GEOM_SPEC" /*NOI18N*/);
    result += super.getInfo();
    result += String.format(i18n.getString("I18N_GEOM_DTYPE" /*NOI18N*/),
        getDatatype());
    result += String.format(i18n.getString("I18N_GEOM_LYR" /*NOI18N*/),
        getLayer());

    return result;
  }

  /**
   * Getter for property layer.
   *
   * @return  Value of property layer.
   */
  public int getLayer(){return this.layer;}

  /**
   * Setter for property datatype.
   *
   * @param  datatype  New value of property datatype.
   */
  public void setDatatype(int datatype){this.datatype = datatype;}

  /**
   * Setter for property layer.
   *
   * @param  layer  New value of property layer.
   */
  public void setLayer(int layer){this.layer = layer;}

  /**
   * Returns a string representation of the geometry element
   *
   * @return  A string representation of the geometry element
   */
  public String toString(){return i18n.getString("I18N_GEOM_EL" /*NOI18N*/);}
} // end class GeometryElement


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
