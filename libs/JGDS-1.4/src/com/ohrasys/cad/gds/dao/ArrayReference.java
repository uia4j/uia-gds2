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
 * Array reference data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.9 $, $Date: 2005/05/18 07:11:08 $
 */
public class ArrayReference
extends ReferenceElement
implements InfoProvider {
  /** Holds value of property columns. */
  private int columns;

  /** Holds value of property rows. */
  private int rows;

  /**
   * The resource bundle
   */
  /**
   * Creates a new ArrayReference object.
   */
  public ArrayReference(){this(new String(), 0, 0, new Point[0]);}

  /**
   * Creates a new ArrayReference object.
   *
   * @param  name     The name of the structure being arrayed
   * @param  columns  The number of columns
   * @param  rows     The number of rows
   * @param  points   The points defining the array boundary
   */
  public ArrayReference(String name, int columns, int rows, Point points[]) {
    this(false, false, 0, name, new Transform(), columns, rows, points);
  }

  /**
   * Creates a new ArrayReference object.
   *
   * @param  external   The structure reference is external data
   * @param  template   The structure reference is template data
   * @param  group      The object group the array belongs to
   * @param  name       The name of the structure being arrayed
   * @param  transform  The transform associated with the array
   * @param  columns    The number of columns
   * @param  rows       The number of rows
   * @param  points     The points defining the array boundary
   */
  public ArrayReference(boolean external, boolean template, int group,
      String name, Transform transform, int columns, int rows,
      Point points[]) {
    super(external, template, group, name, transform, points);
    setColumns(columns);
    setRows(rows);
  }

  /**
   * Getter for property columns.
   *
   * @return  Value of property columns.
   */
  public int getColumns(){return this.columns;}

  /**
   * Returns a textual representation of the array information
   *
   * @return  The textual representation of the array information
   */
  public String getInfo() {
    String result = super.getInfo();
    result += String.format(i18n.getString("I18N_ARYREF_COLS" /*NOI18N*/),
        getColumns());
    result += String.format(i18n.getString("I18N_ARYREF_ROWS" /*NOI18N*/),
        getRows());

    return result;
  }

  /**
   * Getter for property rows.
   *
   * @return  Value of property rows.
   */
  public int getRows(){return this.rows;}

  /**
   * Setter for property columns.
   *
   * @param  columns  New value of property columns.
   */
  public void setColumns(int columns){this.columns = columns;}

  /**
   * Setter for property rows.
   *
   * @param  rows  New value of property rows.
   */
  public void setRows(int rows){this.rows = rows;}

  /**
   * Returns a string representation of the array
   *
   * @return  The name of the structure being arrayed
   */
  public String toString(){return getName();}
} // end class ArrayReference


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
