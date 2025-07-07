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

public class BNFI18NFactoryTest
  extends TestCase {
  private BNFI18NFactory i18n = new BNFI18NFactory();

  public BNFI18NFactoryTest(String testName) {super(testName);}

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite(BNFI18NFactoryTest.class);
    return suite;
  }

  public void testGetString() {
    String expectedPropertiesFileName = "com/ohrasys/cad/bnf/BNFProperties";
    assertTrue(i18n.res.equals(expectedPropertiesFileName));
    ResourceBundle bundle =
      ResourceBundle.getBundle(expectedPropertiesFileName);
    assertTrue(i18n.i18n_CHLD_TSTS.equals("I18N_CHLD_TSTS"));
    assertTrue(i18n.i18n_FIRST_FTHRU.equals("I18N_FIRST_FTHRU"));
    assertTrue(i18n.i18n_NO_FTHRU.equals("I18N_NO_FTHRU"));
    assertTrue(i18n.i18n_RQMT.equals("I18N_RQMNT"));
    assertTrue(i18n.i18n_TST_REQD.equals("I18N_TST_RQD"));
    assertTrue(
      i18n.getString(i18n.i18n_TST_REQD).equals(
        bundle.getObject(i18n.i18n_TST_REQD)
        )
      );
  }

  public void testToString() {
    try {
      String expected =
        i18n.getClass().getName() + '@' + Integer.toHexString(i18n.hashCode());
      String actual   = i18n.toString();
      assertTrue(expected.equals(actual));
    } catch(Exception ex) {
      fail("testToString() method failed");
    }
  }
}

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
