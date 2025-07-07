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
 * A reference element data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.6 $, $Date: 2005/05/18 07:11:09 $
 */
public class ReferenceElement
extends Element
implements InfoProvider {
  /** Holds value of property name. */
  private String name;

  /** Holds value of property transform. */
  private Transform transform;

  /**
   * Creates a new ReferenceElement object.
   */
  public ReferenceElement() {
    super();
    setName(new String());
    setTransform(new Transform());
  }

  /**
   * Creates a new instance of ReferenceElement
   *
   * @param  external   The reference element is external data
   * @param  template   The reference element is template data
   * @param  group      The object group the reference element belongs to
   * @param  name       The name of the reference element
   * @param  transform  The transform of the reference element
   * @param  points     The points that define the reference element
   */
  public ReferenceElement(boolean external, boolean template, int group,
      String name, Transform transform, Point points[]) {
    super(external, template, group, points);
    setName(name);
    setTransform(transform);
  }

  /**
   * Returns a textual representation of the reference element
   *
   * @return  A textual representation of the reference element
   */
  public String getInfo() {
    String result = new String();
    result += i18n.getString("I18N_REF_SPEC" /*NOI18N*/);
    result += super.getInfo();
    result += String.format(i18n.getString("I18N_REF_NAME" /*NOI18N*/),
        getName());
    if(getTransform() != null){result += getTransform().getInfo();}

    return result;
  }

  /**
   * Getter for property name.
   *
   * @return  Value of property name.
   */
  public String getName(){return this.name;}

  /**
   * Getter for property transform.
   *
   * @return  Value of property transform.
   */
  public Transform getTransform(){return this.transform;}

  /**
   * Setter for property name.
   *
   * @param  name  New value of property name.
   */
  public void setName(String name){this.name = name;}

  /**
   * Setter for property transform.
   *
   * @param  transform  New value of property transform.
   */
  public void setTransform(Transform transform){this.transform = transform;}

  /**
   * Returns a string representation of the reference element
   *
   * @return  A string representation of the reference element
   */
  public String toString(){return i18n.getString("I18N_REF_EL" /*NOI18N*/);}
} // end class ReferenceElement


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
