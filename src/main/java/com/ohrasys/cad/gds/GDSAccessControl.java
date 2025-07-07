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

package com.ohrasys.cad.gds;

/**
 * Represents an access control specifier.  Each access control specifier
 * consists of a group number, a user number and access rights.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.10 $
 * @since    1.5
 */
public class GDSAccessControl {
  /** The value of the access rights */
  private short access;

  /** The group number */
  private short group;

  /** The user id */
  private short user;

  /**
   * Creates a copy of an existing GDSAccessControl object.
   *
   * @param  data  The GDSAccessControl object to copy.
   */
  public GDSAccessControl(GDSAccessControl data) {
    this.group  = data.group;
    this.user   = data.user;
    this.access = data.access;
  }

  /**
   * Creates a new GDSAccessControl object.
   *
   * @param  group   Group number.
   * @param  user    User number.
   * @param  access  Access rights.
   */
  public GDSAccessControl(short group, short user, short access) {
    this.group  = group;
    this.user   = user;
    this.access = access;
  }

  /**
   * Determines if one access control list is equivalent to another.
   *
   * @param   obj  The object to compare to.
   *
   * @return  true if the objects contain the same data.
   */
  public boolean equals(Object obj) {
    if(obj == null){return false;}

    if(this == obj){return true;}

    if((obj instanceof GDSAccessControl) &&
        (((GDSAccessControl)obj).user == this.user) &&
        (((GDSAccessControl)obj).group == this.group) &&
        (((GDSAccessControl)obj).access == this.access)){return true;}

    return false;
  }

  /**
   * Gets the access control permissions.
   *
   * @return  The current access control permissions.
   */
  public short getAccess(){return this.access;}

  /**
   * Gets the group number.
   *
   * @return  The current group number.
   */
  public short getGroup(){return this.group;}

  /**
   * Gets the user number.
   *
   * @return  The current user number.
   */
  public short getUser(){return this.user;}

  /**
   * Generates a hashing code for the access control.
   *
   * @return  A hashcode for the access control.
   */
  public int hashCode(){return (this.user ^ this.group ^ this.access);}

  /**
   * Sets the access control permissions.
   *
   * @param  access  The new access control permissions.
   */
  public void setAccess(short access){this.access = access;}

  /**
   * Sets the group number.
   *
   * @param  group  The new group number.
   */
  public void setGroup(short group){this.group = group;}

  /**
   * Sets the user number.
   *
   * @param  user  The new user number.
   */
  public void setUser(short user){this.user = user;}

  /**
   * Returns description of the access control.
   *
   * @return  A string representation of the access control.
   */
  public String toString() {
    GDSI18NFactory i18n = new GDSI18NFactory();

    return GDSStringUtil.sprintf(i18n.getString(i18n.i18n_ACCS_CTL), group,
        user, access);
  }
} // end class GDSAccessControl


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
