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
import java.util.*;

/**
 * A structure data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.6 $, $Date: 2005/05/18 07:11:09 $
 */
public class Structure
implements InfoProvider {
  /** The resource bundle */
  private static final String bundle =
    "com/ohrasys/cad/gds/dao/JGDSDAOProperties" /* NOI18N */;

  /** Holds value of property accessed. */
  private Date accessed;

  /** Holds value of property elements. */
  private Element elements[];

  /** The resource bundle */
  private ResourceBundle i18n;

  /** Holds value of property modified. */
  private Date modified;

  /** Holds value of property name. */
  private String name;

  /** Holds value of property structureClass. */
  private int structureClass;

  /**
   * Creates a new Structure object.
   */
  public Structure(){this(new String());}

  /**
   * Creates a new Structure object.
   *
   * @param  name  The name of the structure
   */
  public Structure(String name){this(name, new Element[0]);}

  /**
   * Creates a new Structure object.
   *
   * @param  name      The name of the structure
   * @param  elements  The elements that define the structure
   */
  public Structure(String name, Element elements[]) {
    this(name, new Date(System.currentTimeMillis()),
      new Date(System.currentTimeMillis()), elements);
  }

  /**
   * Creates a new Structure object.
   *
   * @param  name      The name of the strucuture
   * @param  accessed  The date the structure was last accessed
   * @param  modified  The date the structure was last modified
   * @param  elements  The elements that define the structure
   */
  public Structure(String name, Date accessed, Date modified,
      Element elements[]){this(name, accessed, modified, 0, elements);}

  /**
   * Creates a new Structure object.
   *
   * @param  name            The name of the structure
   * @param  accessed        The date the structure was last accessed
   * @param  modified        The data the structure was last modified
   * @param  structureClass  The object group the structure belongs to
   * @param  elements        The elements that define the structure
   */
  public Structure(String name, Date accessed, Date modified,
      int structureClass, Element elements[]) {
    setName(name);
    setAccessed(accessed);
    setModified(modified);
    setStructureClass(structureClass);
    setElements(elements);
    i18n = ResourceBundle.getBundle(bundle);
  }

  /**
   * Getter for property accessed.
   *
   * @return  Value of property accessed.
   */
  public Date getAccessed(){return this.accessed;}

  /**
   * Setter for the elements property
   *
   * @return  Value of the property elements
   */
  public Element[] getElements(){return this.elements;}

  /**
   * Returns a textual representation of the structure
   *
   * @return  A textual representation of the structure
   */
  public String getInfo() {
    String result = new String();
    result += String.format(i18n.getString("I18N_STRUCT_NAME" /*NOI18N*/),
        name);
    result += String.format(i18n.getString("I18N_STRUCT_MOD" /*NOI18N*/),
        modified.toString());
    result += String.format(i18n.getString("I18N_STRUCT_ACCESSED" /*NOI18N*/),
        accessed.toString());
    if(elements != null) {
      result += String.format(i18n.getString("I18N_STRUCT_COUNT" /*NOI18N*/),
          elements.length);
    }
    result += String.format(i18n.getString("I18N_STRUCT_CLASS" /*NOI18N*/),
        structureClass);

    return result;
  }

  /**
   * Getter for property modified.
   *
   * @return  Value of property modified.
   */
  public Date getModified(){return this.modified;}

  /**
   * Getter for property name.
   *
   * @return  Value of property name.
   */
  public String getName(){return this.name;}

  /**
   * Getter for property structureClass.
   *
   * @return  Value of property structureClass.
   */
  public int getStructureClass(){return this.structureClass;}

  /**
   * Setter for property accessed.
   *
   * @param  accessed  New value of property accessed.
   */
  public void setAccessed(Date accessed){this.accessed = accessed;}

  /**
   * Setter for the elements property
   *
   * @param  elements  The value of the elements property
   */
  public void setElements(Element elements[]){this.elements = elements;}

  /**
   * Setter for property modified.
   *
   * @param  modified  New value of property modified.
   */
  public void setModified(Date modified){this.modified = modified;}

  /**
   * Setter for property name.
   *
   * @param  name  New value of property name.
   */
  public void setName(String name){this.name = name;}

  /**
   * Setter for property structureClass.
   *
   * @param  structureClass  New value of property structureClass.
   */
  public void setStructureClass(int structureClass) {
    this.structureClass = structureClass;
  }

  /**
   * Returns a string representation of the structure
   *
   * @return  A string representation of the structure
   */
  public String toString(){return getName();}
} // end class Structure


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
