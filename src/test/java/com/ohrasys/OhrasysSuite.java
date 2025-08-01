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
package com.ohrasys;
import junit.framework.*;
import junit.framework.TestCase;

public class OhrasysSuite
  extends TestCase {
  public OhrasysSuite(String testName) {super(testName);}

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite("OhrasysSuite");
    suite.addTest(com.ohrasys.cad.CadSuite.suite());
    return suite;
  }

  protected void setUp()
    throws java.lang.Exception {}

  protected void tearDown()
    throws java.lang.Exception {}
}

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
