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

package com.ohrasys.cad.gds;

/**
 * Used to identify exceptions in the GDSParser object.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.5 $
 * @since    1.5
 */
public class GDSParserException extends java.lang.Exception {

    /**
     *
     */
    private static final long serialVersionUID = -1519989052725585699L;

    /**
     * Creates a new ParserException object.
     */
    public GDSParserException() {
    }

    /**
     * Creates a new ParserException object.
     *
     * @param  msg  Error message
     */
    public GDSParserException(String msg) {
        super(msg);
    }

    /**
     * Returns a string representation of this exception
     *
     * @return  A descriptive message of the exception
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
