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
import java.util.*;

/**
 * An interface that all BNF test implementations must implement.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.20 $, $Date: 2005/04/01 06:21:53 $
 */
public interface BNFTestImplementor {
  /**
   * A method to retrieve information collected during the evaluation of
   * the test.
   *
   * @return  A list containing the data collected during the evaluation of
   *          the test.
   */
  public Object collect();

  /** A method used to reset the test state */
  public void reset();

  /**
   * A method to turn data collection on and off
   *
   * @param  isCollecting  true if the test should collect data as it
   *                       evaluates
   */
  public void setCollecting(boolean isCollecting);

  /**
   * A method to evaluate the test using the supplied testable object
   *
   * @param   obj  The BNFTestableObject from which to extract the token
   *               used to evaluate the test
   *
   * @return  A BNFTestResult object representing the completion status of
   *          the test
   */
  public BNFTestResult test(BNFTestableObject obj);
} // end interface BNFTestImplementor


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
