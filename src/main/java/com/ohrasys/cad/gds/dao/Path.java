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
 * A path data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.6 $, $Date: 2005/05/18 07:11:09 $
 */
public class Path
extends GeometryElement
implements InfoProvider {
  /** Holds value of property endExtension. */
  private int endExtension;

  /** Holds value of property pathtype. */
  private int pathtype;

  /** Holds value of property startExtension. */
  private int startExtension;

  /** Holds value of property width. */
  private int width;

  /**
   * Creates a new Path object.
   */
  public Path() {
    super();
    setPathtype(0);
    setWidth(1);
    setStartExtension(0);
    setEndExtension(0);
  }

  /**
   * Creates a new Path object.
   *
   * @param  layer   The layer of the path
   * @param  width   The width of the path
   * @param  points  The points defining the path
   */
  public Path(int layer, int width, Point points[]) {
    this(false, false, 0, layer, 0, 0, width, 0, 0, points);
  }

  /**
   * Creates a new Path object.
   *
   * @param  external        The path is external data
   * @param  template        The path is template data
   * @param  group           The object group of the path
   * @param  layer           The layer of the path
   * @param  datatype        The datatype of the path
   * @param  pathtype        The pathtype of the path
   * @param  width           The width of the path
   * @param  startExtension  The start extension of the path
   * @param  endExtension    The end extension of the path
   * @param  points          The points defining the path
   */
  public Path(boolean external, boolean template, int group, int layer,
      int datatype, int pathtype, int width, int startExtension,
      int endExtension, Point points[]) {
    super(external, template, group, layer, datatype, points);
    setPathtype(pathtype);
    setWidth(width);
    setStartExtension(startExtension);
    setEndExtension(endExtension);
  }

  /**
   * Getter for property endExtension.
   *
   * @return  Value of property endExtension.
   */
  public int getEndExtension(){return this.endExtension;}

  /**
   * Returns a textual representation of the path
   *
   * @return  A textual representation of the path
   */
  public String getInfo() {
    String result = super.getInfo();
    result += String.format(i18n.getString("I18N_PATH_END" /*NOI18N*/),
        getEndExtension());
    result += String.format(i18n.getString("I18N_PATH_START" /*NOI18N*/),
        getStartExtension());
    result += String.format(i18n.getString("I18N_PATH_TYPE" /*NOI18N*/),
        getPathtype());
    result += String.format(i18n.getString("I18N_PATH_WIDTH" /*NOI18N*/),
        getWidth());

    return result;
  }

  /**
   * Getter for property pathtype.
   *
   * @return  Value of property pathtype.
   */
  public int getPathtype(){return this.pathtype;}

  /**
   * Getter for property startExtension.
   *
   * @return  Value of property startExtension.
   */
  public int getStartExtension(){return this.startExtension;}

  /**
   * Getter for property width.
   *
   * @return  Value of property width.
   */
  public int getWidth(){return this.width;}

  /**
   * Setter for property endExtension.
   *
   * @param  endExtension  New value of property endExtension.
   */
  public void setEndExtension(int endExtension) {
    this.endExtension = endExtension;
  }

  /**
   * Setter for property pathtype.
   *
   * @param  pathtype  New value of property pathtype.
   */
  public void setPathtype(int pathtype){this.pathtype = pathtype;}

  /**
   * Setter for property startExtension.
   *
   * @param  startExtension  New value of property startExtension.
   */
  public void setStartExtension(int startExtension) {
    this.startExtension = startExtension;
  }

  /**
   * Setter for property width.
   *
   * @param  width  New value of property width.
   */
  public void setWidth(int width){this.width = width;}

  /**
   * Returns a string representation of the path
   *
   * @return  A string representation of the path
   */
  public String toString(){return i18n.getString("I18N_PATH" /*NOI18N*/);}
} // end class Path


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
