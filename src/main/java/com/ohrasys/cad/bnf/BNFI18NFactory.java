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

import java.util.ResourceBundle;

/**
 * The internationalized string factory
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.12 $, $Date: 2005/03/31 22:13:16 $
 */
final class BNFI18NFactory {

    /** The location of the resource bundle */
    protected static final String res = "com/ohrasys/cad/bnf/BNFProperties" /*NOI18N*/;

    /** The resource bundle */
    protected static final ResourceBundle bundle = ResourceBundle.getBundle(
            res);

    /** The child tests localized message */
    protected static final String i18n_CHLD_TSTS = "I18N_CHLD_TSTS" /*NOI18N*/;

    /** The first fallthru localized message */
    protected static final String i18n_FIRST_FTHRU = "I18N_FIRST_FTHRU" /*NOI18N*/;

    /** The no fallthru localized message */
    protected static final String i18n_NO_FTHRU = "I18N_NO_FTHRU" /*NOI18N*/;

    /** The requirement localized message */
    protected static final String i18n_RQMT = "I18N_RQMNT" /*NOI18N*/;

    /** The test required localized message */
    protected static final String i18n_TST_REQD = "I18N_TST_RQD" /*NOI18N*/;

    /**
     * A method to retrieve a localized text message from the resource
     * bundle.
     *
     * @param   key  The key for the message to retrieve
     *
     * @return  The localized message
     */
    public static String getString(String key) {
        return bundle.getString(key);
    }

    /**
     * Returns a text representation of this object
     *
     * @return  A text representation of this object
     */
    @Override
    public String toString() {
        return super.toString();
    }
} // end class BNFI18NFactory

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
