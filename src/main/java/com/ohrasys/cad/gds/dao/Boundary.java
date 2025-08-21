/*
 * Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package com.ohrasys.cad.gds.dao;

import java.awt.Point;

/**
 * Boundary Element Data Access Object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.6 $, $Date: 2005/05/18 07:11:08 $
 */
public class Boundary extends GeometryElement implements InfoProvider {

    /**
     * Creates a new instance of Boundary
     */
    public Boundary() {
        super();
    }

    /**
     * Creates a new Boundary object.
     *
     * @param  layer   The layer of the boundary
     * @param  points  The points defining the boundary
     */
    public Boundary(int layer, Point points[]) {
        this(false, false, 0, layer, 0, points);
    }

    /**
     * Creates a new Boundary object.
     *
     * @param  external  Indicates the data is external
     * @param  template  Indicates the data is a template
     * @param  group     The object group the boundary belongs to
     * @param  layer     The layer of the boundary
     * @param  datatype  The data type of the boundary
     * @param  points    The points defining the boundary
     */
    public Boundary(boolean external, boolean template, int group, int layer, int datatype, Point points[]) {
        super(external, template, group, layer, datatype, points);
    }

    /**
     * Returns a string representation of the object
     *
     * @return  A string representation of the object
     */
    @Override
    public String toString() {
        return this.i18n.getString("I18N_BOUNDARY" /*NOI18N*/);
    }
} // end class Boundary

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
