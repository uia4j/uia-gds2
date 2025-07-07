/* Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA. */

package com.ohrasys.cad.bnf;

/**
 * An exception class for the BNF test package
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.14 $, $Date: 2005/03/31 22:13:17 $
 */
public class BNFTestException
extends Exception {
  /**
   * Creates a new BNFTestException object.
   */
  public BNFTestException(){super();}

  /**
   * Creates a new BNFTestException object.
   *
   * @param  msg  The message to associate with this exception
   */
  public BNFTestException(String msg){super(msg);}

  /**
   * Returns the message associated with this exception
   *
   * @return  The message associated with this exception
   */
  public String toString(){return getMessage();}
}

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
