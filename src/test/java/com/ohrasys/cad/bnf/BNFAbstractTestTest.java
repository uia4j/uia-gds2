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
package com.ohrasys.cad.bnf;
import junit.framework.*;
import java.util.*;

public class BNFAbstractTestTest
  extends TestCase {
  public BNFAbstractTestTest(String testName) {super(testName);}

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite(BNFAbstractTestTest.class);
    return suite;
  }

  public void testCheckTests()
    throws BNFTestException {
    BNFTestImplementor tests[] = null;
    boolean            caught  = false;
    try {
      BNFRequiredTest test = new BNFRequiredTest(tests);
    } catch(BNFTestException ex) {
      caught = true;
    }
    assertTrue(caught);
    tests    = new BNFTestImplementor[] {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1)
      };
    tests[1] = null;
    caught   = false;
    try {
      BNFRequiredTest test = new BNFRequiredTest(tests);
    } catch(BNFTestException ex) {
      caught = true;
    }
  }

  public void testReset()
    throws BNFTestException {
    BNFTestImplementor tests[]             = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1)
      };
    BNFRequiredTest    test                = new BNFRequiredTest(tests);
    tests[0]                               = null;
    ((BNFNoFallthruTest)tests[1]).nextTest = 9;
    assertTrue(test.tests[0] == null);
    test.reset();
    assertTrue(((BNFNoFallthruTest)tests[1]).nextTest == 0);
  }
}

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
