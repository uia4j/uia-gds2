/*
 * Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package com.ohrasys.cad.bnf;

/**
 * An abstract wrapper class used to wrap objects such that a token may be
 * easily retrieved
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.10 $, $Date: 2005/03/31 22:13:17 $
 */
public abstract class BNFTestableObject {

    /** The underlying data object */
    private Object data;

    /**
     * Creates a new BNFTestableObject object.
     *
     * @param  data  The object to wrap
     */
    public BNFTestableObject(Object data) {
        this.data = data;
    }

    /**
     * An abstract method to retrieve a token from the underlying data object
     *
     * @return  An integer token used to represent the object 
     */
    public abstract int getToken();

    /**
     * A method to determine if this object is equivalent to another
     *
     * @param   obj  The object to compare against
     *
     * @return  true if both objects are instances of BNFTestableObjects and
     *          the underlying data objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BNFTestableObject)) {
            return false;
        }
        else {
            return this.data.equals(((BNFTestableObject) obj).getData());
        }
    }

    /**
     * Returns the underlying data object
     *
     * @return  The underlying data object
     */
    public Object getData() {
        return this.data;
    }

    /**
     * Returns a unique hashCode for the object
     *
     * @return  The hash code of the underlying data object
     */
    @Override
    public int hashCode() {
        return this.data.hashCode();
    }

    /**
     * Sets the underlying data
     *
     * @param  obj  The new underlying data object
     */
    public void setData(Object obj) {
        this.data = obj;
    }

    /**
     * Returns a text representation of this object
     *
     * @return  A text representation of the underlying data object
     */
    @Override
    public String toString() {
        return this.data.toString();
    }
} // end class BNFTestableObject

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
