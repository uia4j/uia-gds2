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

public class BNFTestableObjectTest
  extends TestCase {
  private String            className;
  private int               numTests = 1000;
  private BNFTestableObject object;
  private Random            random;

  public BNFTestableObjectTest(String testName) {
    super(testName);
    object                           = new BNFTestableObject((Integer)null) {
        public int getToken() {return ((Integer)getData()).intValue();}
      };
    className                        = object.getClass().getSimpleName();
    int seed = new Random().nextInt();
    System.out.println("Random seed for test is " + seed);
    random                           = new Random(seed);
  }

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite(BNFTestableObjectTest.class);
    return suite;
  }

  public void testEquals() {
    int               token  = random.nextInt();
    BNFTestableObject tester = new BNFTestableObject(new Integer(token)) {
        public int getToken() {return ((Integer)getData()).intValue();}
      };
    object.setData(new Integer(token));
    assertTrue(tester.equals(object));
    object.setData(new Integer(Math.abs(token) - 1));
    assertFalse(tester.equals(object));
    assertFalse(tester.equals(new ArrayList()));
  }

  public void testGetToken() {
    for(int i = 0; i < numTests; i++) {
      Integer integer = new Integer(random.nextInt());
      object.setData(integer);
      assertEquals(object.getToken(), integer.intValue());
    }
  }

  public void testHashCode() {
    object.setData(new Integer(0));
    assertTrue(object.hashCode() == object.getData().hashCode());
  }

  public void testSetGetData() {
    for(int i = 0; i < numTests; i++) {
      Integer integer = new Integer(random.nextInt());
      object.setData(integer);
      assertEquals(object.getData(), integer);
    }
  }

  public void testToString() {
    object.setData(new Integer(0));
    try {
      assertTrue(object.toString().equals(object.getData().toString()));
    } catch(Exception ex) {
      fail(ex.getMessage());
    }
  }
}

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
