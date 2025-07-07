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
import junit.framework.TestCase;

public class BnfSuite
  extends TestCase {
  public BnfSuite(String testName) {super(testName);}

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite("BnfSuite");
    suite.addTest(com.ohrasys.cad.bnf.BNFAbstractTestTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFI18NFactoryTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFNoFallthruTestTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFOneOfManyRequiredTestTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFOneOrMoreOptionalTestTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFOneOrMoreRequiredTestTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFOptionalTestTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFRequiredTestTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFTestExceptionTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFTestResultTest.suite());
    suite.addTest(com.ohrasys.cad.bnf.BNFTestableObjectTest.suite());
    return suite;
  }

  protected void setUp()
    throws java.lang.Exception {}

  protected void tearDown()
    throws java.lang.Exception {}
}

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
