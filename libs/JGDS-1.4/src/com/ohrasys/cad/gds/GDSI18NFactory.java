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

package com.ohrasys.cad.gds;

import java.util.*;

/**
 * A storage class for internationalization constants.  These constants
 * correspond to resource bundle keys that can be used to retrieve localized
 * application messages.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.10 $, $Date: 2005/03/12 04:39:52 $
 */
final class GDSI18NFactory {
  /** The resource bundle path */
  protected static final String res =
    "com/ohrasys/cad/gds/GDSProperties" /*NOI18N*/;

  /** The resource bundle */
  protected static final ResourceBundle resources = ResourceBundle.getBundle(
      res);

  /** The ACCESS CONTROL <code>toString()</code> message key */
  protected static final String i18n_ACCS_CTL = "I18N_ACCS_CTL" /*NOI18N*/;

  /** The CONTACT <code>toString()</code> message key */
  protected static final String i18n_CONTACT_TOSTRING =
    "I18N_CONTACT_TOSTRING" /*NOI18N*/;

  /** The DATATYPE <code>GDSException</code> message key */
  protected static final String i18n_DATATYPE_THROW =
    "I18N_DATATYPE_THROW" /*NOI18N*/;

  /** The DATATYPE <code>toString()</code> message key */
  protected static final String i18n_DATATYPE_TOSTRING =
    "I18N_DATATYPE_TOSTRING" /*NOI18N*/;

  /** The ELFLAGS <code>toString()</code> message key */
  protected static final String i18n_ELFLAGS_TOSTRING =
    "I18N_ELFLAGS_TOSTRING" /*NOI18N*/;

  /** The ELKEY <code>GDSException</code> message key */
  protected static final String i18n_ELKEY_CTOR = "I18N_ELKEY_CTOR" /*NOI18N*/;

  /** The ELKEY <code>toString()</code> message key */
  protected static final String i18n_ELKEY_TOSTRING =
    "I18N_ELKEY_TOSTRING" /*NOI18N*/;

  /** The ENDEL <code>toString()</code> message key */
  protected static final String i18n_ENDEL_TOSTRING =
    "I18N_ENDEL_TOSTRING" /*NOI18N*/;

  /** The ENDEXTN <code>toString()</code> message key */
  protected static final String i18n_ENDEXTN_TOSTRING =
    "I18N_ENDEXTN_TOSTRING" /*NOI18N*/;

  /** The ENDLIB <code>toString()</code> message key */
  protected static final String i18n_ENDLIB_TOSTRING =
    "I18N_ENDLIB_TOSTRING" /*NOI18N*/;

  /** The ENDMASKS <code>toString()</code> message key */
  protected static final String i18n_ENDMASKS_TOSTRING =
    "I18N_ENDMASKS_TOSTRING" /*NOI18N*/;

  /** The ENDSTR <code>toString()</code> message key */
  protected static final String i18n_ENDSTR_TOSTRING =
    "I18N_ENDSTR_TOSTRING" /*NOI18N*/;

  /** The prefix portion of the FONTS <code>toString()</code> message key */
  protected static final String i18n_FONTS_TOSTRING1 =
    "I18N_FONTS_TOSTRING1" /*NOI18N*/;

  /** The predicate portion of the FONTS <code>toString()</code> message key */
  protected static final String i18n_FONTS_TOSTRING2 =
    "I18N_FONTS_TOSTRING2" /*NOI18N*/;

  /** The FONTS <code>GDSException</code> message key */
  protected static final String i18n_FONTS_THROW1 =
    "I18N_FONTS_THROW1" /*NOI18N*/;

  /** The FONTS <code>GDSException</code> message key */
  protected static final String i18n_FONTS_THROW2 =
    "I18N_FONTS_THROW2" /*NOI18N*/;

  /** The FONTS <code>GDSException</code> message key */
  protected static final String i18n_FONTS_THROW3 =
    "I18N_FONTS_THROW3" /*NOI18N*/;

  /** The FORMAT <code>toString()</code> message key */
  protected static final String i18n_FORMAT_TOSTRING1 =
    "I18N_FORMAT_TOSTRING1" /*NOI18N*/;

  /** The FORMAT <code>toString()</code> message key */
  protected static final String i18n_FORMAT_TOSTRING2 =
    "I18N_FORMAT_TOSTRING2" /*NOI18N*/;

  /** The FORMAT <code>toString()</code> message key */
  protected static final String i18n_FORMAT_TOSTRING3 =
    "I18N_FORMAT_TOSTRING3" /*NOI18N*/;

  /** The FORMAT <code>toString()</code> message key */
  protected static final String i18n_FORMAT_TOSTRING4 =
    "I18N_FORMAT_TOSTRING4" /*NOI18N*/;

  /** The FORMAT <code>toString()</code> message key */
  protected static final String i18n_FORMAT_TOSTRING5 =
    "I18N_FORMAT_TOSTRING5" /*NOI18N*/;

  /** The FORMAT <code>GDSException()</code> message key */
  protected static final String i18n_FORMAT_THROW =
    "I18N_FORMAT_THROW" /*NOI18N*/;

  /** The GENERATIONS <code>toString()</code> message key */
  protected static final String i18n_GENERATIONS_TOSTRING =
    "I18N_GENERATIONS_TOSTRING" /*NOI18N*/;

  /** The GENERATIONS <code>GDSException</code> message key */
  protected static final String i18n_GENERATIONS_THROW =
    "I18N_GENERATIONS_THROW" /*NOI18N*/;

  /** The HARDFENCE <code>toString()</code> message key */
  protected static final String i18n_HARDFENCE_TOSTRING =
    "I18N_HARDFENCE_TOSTRING" /*NOI18N*/;

  /** The HARDWIRE <code>toString()</code> message key */
  protected static final String i18n_HARDWIRE_TOSTRING =
    "I18N_HARDWIRE_TOSTRING" /*NOI18N*/;

  /** The HEADER <code>toString()</code> message key */
  protected static final String i18n_HEADER_TOSTRING =
    "I18N_HEADER_TOSTRING" /*NOI18N*/;

  /** The HEADER <code>toString()</code> message key */
  protected static final String i18n_ANGLE_TOSTRING =
    "I18N_ANGLE_TOSTRING" /*NOI18N*/;

  /** "The AREF <code>toString()</code> message key" */
  protected static final String i18n_AREF_TOSTRING =
    "I18N_AREF_TOSTRING" /*NOI18N*/;

  /** "The ATTRTABLE <code>toString()</code> message key" */
  protected static final String i18n_ATTRTBL_TOSTRING =
    "I18N_ATTRTBL_TOSTRING" /*NOI18N*/;

  /** "The BGNEXTN <code>toString()</code> message key" */
  protected static final String i18n_BGNEXTN_TOSTRING =
    "I18N_BGNEXTN_TOSTRING" /*NOI18N*/;

  /** "The ATTRTABLE <code>GDSException</code> message key" */
  protected static final String i18n_ATTRTBL_THROW =
    "I18N_ATTRTBL_THROW" /*NOI18N*/;

  /** "The BGNLIB <code>toString()</code> message key" */
  protected static final String i18n_BGNLIB_TOSTRING =
    "I18N_BGNLIB_TOSTRING" /*NOI18N*/;

  /** "The BGNSTR <code>toString()</code> message key" */
  protected static final String i18n_BGNSTR_TOSTRING =
    "I18N_BGNSTR_TOSTRING" /*NOI18N*/;

  /** "The BORDER <code>toString()</code> message key" */
  protected static final String i18n_BORDER_TOSTRING =
    "I18N_BORDER_TOSTRING" /*NOI18N*/;

  /** "The BOUNDARY <code>toString()</code> message key" */
  protected static final String i18n_BOUNDARY_TOSTRING =
    "I18N_BOUNDARY_TOSTRING" /*NOI18N*/;

  /** "The BOX <code>toString()</code> message key" */
  protected static final String i18n_BOX_TOSTRING =
    "I18N_BOX_TOSTRING" /*NOI18N*/;

  /** "The BOXTYPE <code>toString()</code> message key" */
  protected static final String i18n_BOXTYPE_TOSTRING =
    "I18N_BOXTYPE_TOSTRING" /*NOI18N*/;

  /** "The BOXTYPE <code>GDSException</code> message key" */
  protected static final String i18n_BOXTYPE_THROW =
    "I18N_BOXTYPE_THROW" /*NOI18N*/;

  /** "The GDSSpecificDataConverter <code>GDSException</code> message key" */
  protected static final String i18n_1TOBYTE_THROW =
    "I18N_1TOBYTE_THROW" /*NOI18N*/;

  /** "The GDSSpecificDataConverter <code>GDSException</code> message key" */
  protected static final String i18n_2TOBYTE_THROW =
    "I18N_2TOBYTE_THROW" /*NOI18N*/;

  /** "The GDSSpecificDataConverter <code>GDSException</code> message key" */
  protected static final String i18n_4TOBYTE_THROW =
    "I18N_4TOBYTE_THROW" /*NOI18N*/;

  /** "The GDSSpecificDataConverter <code>GDSException</code> message key" */
  protected static final String i18n_8TOBYTE_THROW =
    "I18N_8TOBYTE_THROW" /*NOI18N*/;

  /** "The COLROW <code>GDSException</code> message key" */
  protected static final String i18n_COLROW_THROW =
    "I18N_COLROW_THROW" /*NOI18N*/;

  /** "The COLROW <code>toString()</code> message key" */
  protected static final String i18n_COLROW_TOSTRING =
    "I18N_COLROW_TOSTRING" /*NOI18N*/;

  /** "The LAYER <code>toString()</code> message key" */
  protected static final String i18n_LAYER_TOSTRING =
    "I18N_LAYER_TOSTRING" /*NOI18N*/;

  /** "The LAYER <code>GDSException</code> message key" */
  protected static final String i18n_LAYER_THROW =
    "I18N_LAYER_THROW" /*NOI18N*/;

  /** "The LIBDIRSIZE <code>toString()</code> message key" */
  protected static final String i18n_LIBDIRSIZE_TOSTRING =
    "I18N_LIBDIRSIZE_TOSTRING" /*NOI18N*/;

  /** "The LIBDIRSIZE <code>GDSException</code> message key" */
  protected static final String i18n_LIBDIRSIZE_THROW =
    "I18N_LIBDIRSIZE_THROW" /*NOI18N*/;

  /** "The LIBNAME <code>toString()</code> message key" */
  protected static final String i18n_LIBNAME_TOSTRING =
    "I18N_LIBNAME_TOSTRING" /*NOI18N*/;

  /** "The LIBNAME <code>GDSException</code> message key" */
  protected static final String i18n_LIBNAME_VALID =
    "I18N_LIBNAME_VALID" /*NOI18N*/;

  /** "The LIBNAME <code>GDSException</code> message key" */
  protected static final String i18n_LIBNAME_THROW =
    "I18N_LIBNAME_THROW" /*NOI18N*/;

  /** "The LIBSECUR <code>GDSException</code> message key" */
  protected static final String i18n_LIBSECUR_THROW =
    "I18N_LIBSECUR_THROW" /*NOI18N*/;

  /** "The LIBSECUR <code>toString()</code> message key" */
  protected static final String i18n_LIBSECUR_TOSTRING1 =
    "I18N_LIBSECUR_TOSTRING1" /*NOI18N*/;

  /** "The LIBSECUR <code>toString()</code> message key" */
  protected static final String i18n_LIBSECUR_TOSTRING2 =
    "I18N_LIBSECUR_TOSTRING2" /*NOI18N*/;

  /** "The LINKKEYS <code>GDSException</code> message key" */
  protected static final String i18n_LINKKEYS_THROW =
    "I18N_LINKKEYS_THROW" /*NOI18N*/;

  /** "The LINKKEYS <code>toString()</code> message key" */
  protected static final String i18n_LINKKEYS_TOSTRING =
    "I18N_LINKKEYS_TOSTRING" /*NOI18N*/;

  /** "The LINKTYPE <code>toString()</code> message key" */
  protected static final String i18n_LINKTYPE_TOSTRING =
    "I18N_LINKTYPE_TOSTRING" /*NOI18N*/;

  /** "The LINKTYPE <code>GDSException</code> message key" */
  protected static final String i18n_LINKTYPE_THROW =
    "I18N_LINKTYPE_THROW" /*NOI18N*/;

  /** "The MAG <code>toString()</code> message key" */
  protected static final String i18n_MAG_TOSTRING =
    "I18N_MAG_TOSTRING" /*NOI18N*/;

  /** "The MASK <code>toString()</code> message key" */
  protected static final String i18n_MASK_TOSTRING =
    "I18N_MASK_TOSTRING" /*NOI18N*/;

  /** "The MASK <code>GDSException</code> message key" */
  protected static final String i18n_MASK_THROW = "I18N_MASK_THROW" /*NOI18N*/;

  /** "The NODE <code>toString()</code> message key" */
  protected static final String i18n_NODE_TOSTRING =
    "I18N_NODE_TOSTRING" /*NOI18N*/;

  /** "The NODEPORT <code>toString()</code> message key" */
  protected static final String i18n_NODEPORT_TOSTRING =
    "I18N_NODEPORT_TOSTRING" /*NOI18N*/;

  /** "The NODETYPE <code>toString()</code> message key" */
  protected static final String i18n_NODETYPE_TOSTRING =
    "I18N_NODETYPE_TOSTRING" /*NOI18N*/;

  /** "The NODETYPE <code>GDSException</code> message key" */
  protected static final String i18n_NODETYPE_THROW =
    "I18N_NODETYPE_THROW" /*NOI18N*/;

  /** "The NULL <code>toString()</code> message key" */
  protected static final String i18n_NULL_TOSTRING =
    "I18N_NULL_TOSTRING" /*NOI18N*/;

  /** "The GDSParser <code>GDSException</code> message key" */
  protected static final String i18n_GDSPARSER_THROW1 =
    "I18N_GDSPARSER_THROW1" /*NOI18N*/;

  /** "The GDSParser <code>GDSException</code> message key" */
  protected static final String i18n_GDSPARSER_THROW2 =
    "I18N_GDSPARSER_THROW2" /*NOI18N*/;

  /** "The GDSParser <code>toString()</code> message key" */
  protected static final String i18n_GDSPARSER_TOSTRING =
    "I18N_GDSPARSER_TOSTRING" /*NOI18N*/;

  /** "The PATH <code>toString()</code> message key" */
  protected static final String i18n_PATH_TOSTRING =
    "I18N_PATH_TOSTRING" /*NOI18N*/;

  /** "The PATHPORT <code>toString()</code> message key" */
  protected static final String i18n_PATHPORT_TOSTRING =
    "I18N_PATHPORT_TOSTRING" /*NOI18N*/;

  /** "The PATHTYPE <code>toString()</code> message key" */
  protected static final String i18n_PATHTYPE_TOSTRING1 =
    "I18N_PATHTYPE_TOSTRING1" /*NOI18N*/;

  /** "The PATHTYPE <code>toString()</code> message key" */
  protected static final String i18n_PATHTYPE_TOSTRING2 =
    "I18N_PATHTYPE_TOSTRING2" /*NOI18N*/;

  /** "The PATHTYPE <code>toString()</code> message key" */
  protected static final String i18n_PATHTYPE_TOSTRING3 =
    "I18N_PATHTYPE_TOSTRING3" /*NOI18N*/;

  /** "The PATHTYPE <code>toString()</code> message key" */
  protected static final String i18n_PATHTYPE_TOSTRING4 =
    "I18N_PATHTYPE_TOSTRING4" /*NOI18N*/;

  /** "The PATHTYPE <code>toString()</code> message key" */
  protected static final String i18n_PATHTYPE_TOSTRING5 =
    "I18N_PATHTYPE_TOSTRING5" /*NOI18N*/;

  /** "The PATHTYPE <code>GDSException</code> message key" */
  protected static final String i18n_PATHTYPE_THROW =
    "I18N_PATHTYPE_THROW" /*NOI18N*/;

  /** "The PLEX <code>toString()</code> message key" */
  protected static final String i18n_PLEX_TOSTRING =
    "I18N_PLEX_TOSTRING" /*NOI18N*/;

  /** "The PLEX <code>GDSException</code> message key" */
  protected static final String i18n_PLEX_THROW = "I18N_PLEX_THROW" /*NOI18N*/;

  /** "The PRESENTATION <code>toString()</code> message key" */
  protected static final String i18n_PRESENTATION_TOSTRING1 =
    "I18N_PRESENTATION_TOSTRING1" /*NOI18N*/;

  /** "The PRESENTATION <code>toString()</code> message key" */
  protected static final String i18n_PRESENTATION_TOSTRING2 =
    "I18N_PRESENTATION_TOSTRING2" /*NOI18N*/;

  /** "The PRESENTATION <code>toString()</code> message key" */
  protected static final String i18n_PRESENTATION_TOSTRING3 =
    "I18N_PRESENTATION_TOSTRING3" /*NOI18N*/;

  /** "The PRESENTATION <code>toString()</code> message key" */
  protected static final String i18n_PRESENTATION_TOSTRING4 =
    "I18N_PRESENTATION_TOSTRING4" /*NOI18N*/;

  /** "The PRESENTATION <code>toString()</code> message key" */
  protected static final String i18n_PRESENTATION_TOSTRING5 =
    "I18N_PRESENTATION_TOSTRING5" /*NOI18N*/;

  /** "The PRESENTATION <code>toString()</code> message key" */
  protected static final String i18n_PRESENTATION_TOSTRING6 =
    "I18N_PRESENTATION_TOSTRING6" /*NOI18N*/;

  /** "The PRESENTATION <code>toString()</code> message key" */
  protected static final String i18n_PRESENTATION_TOSTRING7 =
    "I18N_PRESENTATION_TOSTRING7" /*NOI18N*/;

  /** "The PRESENTATION <code>toString()</code> message key" */
  protected static final String i18n_PRESENTATION_TOSTRING8 =
    "I18N_PRESENTATION_TOSTRING8" /*NOI18N*/;

  /** "The PRESENTATION <code>GDSException</code> message key" */
  protected static final String i18n_PRESENTATION_THROW1 =
    "I18N_PRESENTATION_THROW1" /*NOI18N*/;

  /** "The PRESENTATION <code>GDSException</code> message key" */
  protected static final String i18n_PRESENTATION_THROW2 =
    "I18N_PRESENTATION_THROW2" /*NOI18N*/;

  /** "The PROPATTR <code>toString()</code> message key" */
  protected static final String i18n_PROPATTR_TOSTRING =
    "I18N_PROPATTR_TOSTRING" /*NOI18N*/;

  /** "The PROPATTR <code>GDSException</code> message key" */
  protected static final String i18n_PROPATTR_THROW =
    "I18N_PROPATTR_THROW" /*NOI18N*/;

  /** "The PROPVALUE <code>toString()</code> message key" */
  protected static final String i18n_PROPVALUE_TOSTRING =
    "I18N_PROPVALUE_TOSTRING" /*NOI18N*/;

  /** "The PROPVALUE <code>GDSException</code> message key" */
  protected static final String i18n_PROPVALUE_THROW =
    "I18N_PROPVALUE_THROW" /*NOI18N*/;

  /** "The GDSReader <code>GDSException</code> message key" */
  protected static final String i18n_GDSREADER_THROW1 =
    "I18N_GDSREADER_THROW1" /*NOI18N*/;

  /** "The GDSReader <code>GDSException</code> message key" */
  protected static final String i18n_GDSREADER_THROW2 =
    "I18N_GDSREADER_THROW2" /*NOI18N*/;

  /** "The GDSReader <code>GDSException</code> message key" */
  protected static final String i18n_GDSREADER_THROW3 =
    "I18N_GDSREADER_THROW3" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW1 =
    "I18N_RECORD_THROW1" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW2 =
    "I18N_RECORD_THROW2" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW3 =
    "I18N_RECORD_THROW3" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW4 =
    "I18N_RECORD_THROW4" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW5 =
    "I18N_RECORD_THROW5" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW6 =
    "I18N_RECORD_THROW6" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW7 =
    "I18N_RECORD_THROW7" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW8 =
    "I18N_RECORD_THROW8" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW9 =
    "I18N_RECORD_THROW9" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW10 =
    "I18N_RECORD_THROW10" /*NOI18N*/;

  /** "The RECORD <code>GDSException</code> message key" */
  protected static final String i18n_RECORD_THROW11 =
    "I18N_RECORD_THROW11" /*NOI18N*/;

  /** "The REFLIBS <code>GDSException</code> message key" */
  protected static final String i18n_REFLIBS_THROW1 =
    "I18N_REFLIBS_THROW1" /*NOI18N*/;

  /** "The REFLIBS <code>GDSException</code> message key" */
  protected static final String i18n_REFLIBS_THROW2 =
    "I18N_REFLIBS_THROW2" /*NOI18N*/;

  /** "The REFLIBS <code>GDSException</code> message key" */
  protected static final String i18n_REFLIBS_THROW3 =
    "I18N_REFLIBS_THROW3" /*NOI18N*/;

  /** "The REFLIBS <code>GDSException</code> message key" */
  protected static final String i18n_REFLIBS_THROW4 =
    "I18N_REFLIBS_THROW4" /*NOI18N*/;

  /** "The REFLIBS <code>toString()</code> message key" */
  protected static final String i18n_REFLIBS_TOSTRING1 =
    "I18N_REFLIBS_TOSTRING1" /*NOI18N*/;

  /** "The REFLIBS <code>toString()</code> message key" */
  protected static final String i18n_REFLIBS_TOSTRING2 =
    "I18N_REFLIBS_TOSTRING2" /*NOI18N*/;

  /** "The RESERVED <code>GDSException</code> message key" */
  protected static final String i18n_RESERVED_THROW =
    "I18N_RESERVED_THROW" /*NOI18N*/;

  /** "The RESERVED <code>toString()</code> message key" */
  protected static final String i18n_RESERVED_TOSTRING =
    "I18N_RESERVED_TOSTRING" /*NOI18N*/;

  /** "The SNAME <code>toString()</code> message key" */
  protected static final String i18n_SNAME_TOSTRING =
    "I18N_SNAME_TOSTRING" /*NOI18N*/;

  /** "The SNAME <code>GDSException</code> message key" */
  protected static final String i18n_SNAME_THROW1 =
    "I18N_SNAME_THROW1" /*NOI18N*/;

  /** "The SNAME <code>GDSException</code> message key" */
  protected static final String i18n_SNAME_THROW2 =
    "I18N_SNAME_THROW2" /*NOI18N*/;

  /** "The SOFTFENCE <code>toString()</code> message key" */
  protected static final String i18n_SOFTFENCE_TOSTRING =
    "I18N_SOFTFENCE_TOSTRING" /*NOI18N*/;

  /** "The SOFTWIRE <code>toString()</code> message key" */
  protected static final String i18n_SOFTWIRE_TOSTRING =
    "I18N_SOFTWIRE_TOSTRING" /*NOI18N*/;

  /** "The SPACERERROR <code>toString()</code> message key" */
  protected static final String i18n_SPACERERROR_TOSTRING =
    "I18N_SPACERERROR_TOSTRING" /*NOI18N*/;

  /** "The SPACING <code>GDSException</code> message key" */
  protected static final String i18n_SPACING_THROW =
    "I18N_SPACING_THROW" /*NOI18N*/;

  /** "The SPACING <code>toString()</code> message key" */
  protected static final String i18n_SPACING_TOSTRING =
    "I18N_SPACING_TOSTRING" /*NOI18N*/;

  /** "The GDSSpecificDataConverter <code>GDSException</code> message key" */
  protected static final String i18n_DATACONVERT_THROW1 =
    "I18N_DATACONVERT_THROW1" /*NOI18N*/;

  /** "The GDSSpecificDataConverter <code>GDSException</code> message key" */
  protected static final String i18n_DATACONVERT_THROW2 =
    "I18N_DATACONVERT_THROW2" /*NOI18N*/;

  /** "The GDSSpecificDataConverter <code>GDSException</code> message key" */
  protected static final String i18n_DATACONVERT_THROW3 =
    "I18N_DATACONVERT_THROW3" /*NOI18N*/;

  /** "The GDSSpecificDataConverter <code>GDSException</code> message key" */
  protected static final String i18n_DATACONVERT_THROW4 =
    "I18N_DATACONVERT_THROW4" /*NOI18N*/;

  /** "The GDSSpecificDataConverter <code>GDSException</code> message key" */
  protected static final String i18n_DATACONVERT_THROW5 =
    "I18N_DATACONVERT_THROW5" /*NOI18N*/;

  /** "The SREF <code>toString()</code> message key" */
  protected static final String i18n_SREF_TOSTRING =
    "I18N_SREF_TOSTRING" /*NOI18N*/;

  /** "The SRFNAME <code>toString()</code> message key" */
  protected static final String i18n_SRFNAME_TOSTRING =
    "I18N_SRFNAME_TOSTRING" /*NOI18N*/;

  /** "The SRFNAME <code>GDSException</code> message key" */
  protected static final String i18n_SRFNAME_THROW =
    "I18N_SRFNAME_THROW" /*NOI18N*/;

  /** "The GDSStatistics <code>toString()</code> message key" */
  protected static final String i18n_STATS_TOSTRING1 =
    "I18N_STATS_TOSTRING1" /*NOI18N*/;

  /** "The GDSStatistics <code>toString()</code> message key" */
  protected static final String i18n_STATS_TOSTRING2 =
    "I18N_STATS_TOSTRING2" /*NOI18N*/;

  /** "The GDSStatistics <code>toString()</code> message key" */
  protected static final String i18n_STATS_TOSTRING3 =
    "I18N_STATS_TOSTRING3" /*NOI18N*/;

  /** "The STRANS <code>toString()</code> message key" */
  protected static final String i18n_STRANS_TOSTRING =
    "I18N_STRANS_TOSTRING" /*NOI18N*/;

  /** "The STRCLASS <code>toString()</code> message key" */
  protected static final String i18n_STRCLASS_TOSTRING =
    "I18N_STRCLASS_TOSTRING" /*NOI18N*/;

  /** "The STRING <code>toString()</code> message key" */
  protected static final String i18n_STRING_TOSTRING =
    "I18N_STRING_TOSTRING" /*NOI18N*/;

  /** "The STRING <code>GDSException</code> message key" */
  protected static final String i18n_STRING_THROW =
    "I18N_STRING_THROW" /*NOI18N*/;

  /** "The STRNAME <code>toString()</code> message key" */
  protected static final String i18n_STRNAME_TOSTRING =
    "I18N_STRNAME_TOSTRING" /*NOI18N*/;

  /** "The STRNAME <code>GDSException</code> message key" */
  protected static final String i18n_STRNAME_THROW1 =
    "I18N_STRNAME_THROW1" /*NOI18N*/;

  /** "The STRNAME <code>GDSException</code> message key" */
  protected static final String i18n_STRNAME_THROW2 =
    "I18N_STRNAME_THROW2" /*NOI18N*/;

  /** "The STRTYPE <code>GDSException</code> message key" */
  protected static final String i18n_STRTYPE_THROW =
    "I18N_STRTYPE_THROW" /*NOI18N*/;

  /** "The STRTYPE <code>toString()</code> message key" */
  protected static final String i18n_STRTYPE_TOSTRING =
    "I18N_STRTYPE_TOSTRING" /*NOI18N*/;

  /** "The STYPETABLE <code>GDSException</code> message key" */
  protected static final String i18n_STYPTABLE_THROW =
    "I18N_STYPTABLE_THROW" /*NOI18N*/;

  /** "The STYPETABLE <code>toString()</code> message key" */
  protected static final String i18n_STYPTABLE_TOSTRING =
    "I18N_STYPTABLE_TOSTRING" /*NOI18N*/;

  /** "The TAPECODE <code>GDSException</code> message key" */
  protected static final String i18n_TAPECODE_THROW =
    "I18N_TAPECODE_THROW" /*NOI18N*/;

  /** "The TAPECODE <code>toString()</code> message key" */
  protected static final String i18n_TAPECODE_TOSTRING1 =
    "I18N_TAPECODE_TOSTRING1" /*NOI18N*/;

  /** "The TAPECODE <code>toString()</code> message key" */
  protected static final String i18n_TAPECODE_TOSTRING2 =
    "I18N_TAPECODE_TOSTRING2" /*NOI18N*/;

  /** "The TAPENUM <code>toString()</code> message key" */
  protected static final String i18n_TAPENUM_TOSTRING =
    "I18N_TAPENUM_TOSTRING" /*NOI18N*/;

  /** "The TAPENUM <code>GDSException</code> message key" */
  protected static final String i18n_TAPENUM_THROW =
    "I18N_TAPENUM_THROW" /*NOI18N*/;

  /** "The TEXT <code>toString()</code> message key" */
  protected static final String i18n_TEXT_TOSTRING =
    "I18N_TEXT_TOSTRING" /*NOI18N*/;

  /** "The TEXTNODE <code>toString()</code> message key" */
  protected static final String i18n_TEXTNODE_TOSTRING =
    "I18N_TEXTNODE_TOSTRING" /*NOI18N*/;

  /** "The TEXTTYPE <code>toString()</code> message key" */
  protected static final String i18n_TEXTTYPE_TOSTRING =
    "I18N_TEXTTYPE_TOSTRING" /*NOI18N*/;

  /** "The TEXTTYPE <code>GDSException</code> message key" */
  protected static final String i18n_TEXTTYPE_THROW =
    "I18N_TEXTTYPE_THROW" /*NOI18N*/;

  /** "The UINTEGER <code>GDSException</code> message key" */
  protected static final String i18n_UINTEGER_THROW =
    "I18N_UINTEGER_THROW" /*NOI18N*/;

  /** "The UINTEGER <code>toString()</code> message key" */
  protected static final String i18n_UINTEGER_TOSTRING =
    "I18N_UINTEGER_TOSTRING" /*NOI18N*/;

  /** "The UNITS <code>toString()</code> message key" */
  protected static final String i18n_UNITS_TOSTRING1 =
    "I18N_UNITS_TOSTRING1" /*NOI18N*/;

  /** "The UNITS <code>toString()</code> message key" */
  protected static final String i18n_UNITS_TOSTRING2 =
    "I18N_UNITS_TOSTRING2" /*NOI18N*/;

  /** "The UNITS <code>toString()</code> message key" */
  protected static final String i18n_UNITS_TOSTRING3 =
    "I18N_UNITS_TOSTRING3" /*NOI18N*/;

  /** "The USERCONSTRAINT <code>toString()</code> message key" */
  protected static final String i18n_USERCONSTRAINT_TOSTRING =
    "I18N_USERCONSTRAINT_TOSTRING" /*NOI18N*/;

  /** "The USTRING <code>GDSException</code> message key" */
  protected static final String i18n_USTRING_THROW =
    "I18N_USTRING_THROW" /*NOI18N*/;

  /** "The USTRING <code>toString()</code> message key" */
  protected static final String i18n_USTRING_TOSTRING =
    "I18N_USTRING_TOSTRING" /*NOI18N*/;

  /** "The WIDTH <code>toString()</code> message key" */
  protected static final String i18n_WIDTH_TOSTRING =
    "I18N_WIDTH_TOSTRING" /*NOI18N*/;

  /** "The XY <code>GDSException</code> message key" */
  protected static final String i18n_XY_THROW1 = "I18N_XY_THROW1" /*NOI18N*/;

  /** "The XY <code>GDSException</code> message key" */
  protected static final String i18n_XY_THROW2 = "I18N_XY_THROW2" /*NOI18N*/;

  /** "The XY <code>toString()</code> message key" */
  protected static final String i18n_XY_TOSTRING1 =
    "I18N_XY_TOSTRING1" /*NOI18N*/;

  /** "The XY <code>toString()</code> message key" */
  protected static final String i18n_XY_TOSTRING2 =
    "I18N_XY_TOSTRING2" /*NOI18N*/;

  /**
   * Returns the internationalized string for a given key.
   *
   * @param   key  The key for the string.
   *
   * @return  The internationalized string.
   */
  public static String getString(String key){return resources.getString(key);}

  /**
   * Returns a string representation of this class
   *
   * @return  The physical address of this instance
   */
  public String toString(){return super.toString();}
} // end class GDSI18NFactory


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
