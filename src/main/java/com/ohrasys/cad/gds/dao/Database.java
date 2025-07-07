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

package com.ohrasys.cad.gds.dao;
import com.ohrasys.cad.gds.*;

import java.util.*;

/**
 * A database data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $, $Date: 2005/05/18 07:11:09 $
 */
public class Database
implements InfoProvider {
  /** The resource bundle */
  private static final String bundle =
    "com/ohrasys/cad/gds/dao/JGDSDAOProperties" /* NOI18N */;

  /** Holds value of property accessControls. */
  private GDSAccessControl accessControls[];

  /** Holds value of property accessed. */
  private Date accessed;

  /** Holds value of property attributeFile. */
  private String attributeFile;

  /** Holds value of property dirSize. */
  private int dirSize;

  /** Holds value of property fonts. */
  private String fonts[];

  /** Holds value of property format. */
  private Format format;

  /** Holds value of property generations. */
  private int generations;

  /** The resource bundle */
  private ResourceBundle i18n;

  /** Holds value of property metersPerDBU. */
  private double metersPerDBU;

  /** Holds value of property modified. */
  private Date modified;

  /** Holds value of property name. */
  private String name;

  /** Holds value of property refLibs. */
  private String refLibs[];

  /** Holds value of property spacingRulesFile. */
  private String spacingRulesFile;

  /** Holds value of property structures. */
  private Structure structures[];

  /** Holds value of property userUnitsPerDBU. */
  private double userUnitsPerDBU;

  /** Holds value of property version. */
  private int version;

  /**
   * Creates a new Database object.
   */
  public Database(){i18n = ResourceBundle.getBundle(bundle);}

  /**
   * Getter for property accessControls.
   *
   * @return  Value of property accessControls.
   */
  public GDSAccessControl[] getAccessControls(){return this.accessControls;}

  /**
   * Getter for property accessed.
   *
   * @return  Value of property accessed.
   */
  public Date getAccessed(){return this.accessed;}

  /**
   * Getter for property attributeFile.
   *
   * @return  Value of property attributeFile.
   */
  public String getAttributeFile(){return this.attributeFile;}

  /**
   * Getter for property dirSize.
   *
   * @return  Value of property dirSize.
   */
  public int getDirSize(){return this.dirSize;}

  /**
   * Getter for property fonts.
   *
   * @return  Value of property fonts.
   */
  public String[] getFonts(){return this.fonts;}

  /**
   * Getter for property format.
   *
   * @return  Value of property format.
   */
  public Format getFormat(){return this.format;}

  /**
   * Getter for property generations.
   *
   * @return  Value of property generations.
   */
  public int getGenerations(){return this.generations;}

  /**
   * Returns a textual representation of the Database data
   *
   * @return  A textual representation of the Database data
   */
  public String getInfo() {
    String result = new String();
    result += String.format(i18n.getString("I18N_DB_NAME" /*NOI18N*/), name);
    result += String.format(i18n.getString("I18N_DB_UUPERDBU" /*NOI18N*/),
        userUnitsPerDBU);
    result += String.format(i18n.getString("I18N_DB_METERSPERDBU" /*NOI18N*/),
        metersPerDBU);
    result += String.format(i18n.getString("I18N_DB_VERSION" /*NOI18N*/),
        version);
    result += String.format(i18n.getString("I18N_DB_MODIFIED" /*NOI18N*/),
        modified.toString());
    result += String.format(i18n.getString("I18N_DB_ACCESSED" /*NOI18N*/),
        accessed.toString());
    if(accessControls != null) {
      result += String.format(i18n.getString("I18N_DB_ACCSCTL" /*NOI18N*/),
          accessControls.length);
    }
    if(attributeFile != null) {
      result += String.format(i18n.getString("I18N_DB_AFILE" /*NOI18N*/),
          attributeFile);
    }
    if(dirSize > 0) {
      result += String.format(i18n.getString("I18N_DB_DIRSIZE" /*NOI18N*/),
          dirSize);
    }
    if(fonts != null) {
      result += String.format(i18n.getString("I18N_DB_FONT" /*NOI18N*/),
          fonts.length);
    }
    if(format != null){result += format.getInfo();}
    if(generations > 0) {
      result += String.format(i18n.getString("I18N_DB_GEN" /*NOI18N*/),
          generations);
    }
    if(refLibs != null) {
      result += String.format(i18n.getString("I18N_DB_REFLIB" /*NOI18N*/),
          refLibs.length);
    }
    if(spacingRulesFile != null) {
      result += String.format(i18n.getString("I18N_DB_SRF" /*NOI18N*/),
          spacingRulesFile);
    }
    if(structures != null) {
      result += String.format(i18n.getString("I18N_DB_STRUCT" /*NOI18N*/),
          structures.length);
    }

    return result;
  } // end method getInfo

  /**
   * Getter for property metersPerDBU.
   *
   * @return  Value of property metersPerDBU.
   */
  public double getMetersPerDBU(){return this.metersPerDBU;}

  /**
   * Getter for property modified.
   *
   * @return  Value of property modified.
   */
  public Date getModified(){return this.modified;}

  /**
   * Getter for property name.
   *
   * @return  Value of property name.
   */
  public String getName(){return this.name;}

  /**
   * Getter for property refLibs.
   *
   * @return  Value of property refLibs.
   */
  public String[] getRefLibs(){return this.refLibs;}

  /**
   * Getter for property spacingRulesFile.
   *
   * @return  Value of property spacingRulesFile.
   */
  public String getSpacingRulesFile(){return this.spacingRulesFile;}

  /**
   * Getter for property structures.
   *
   * @return  Value of property structures.
   */
  public Structure[] getStructures(){return this.structures;}

  /**
   * Getter for property userUnitsPerDBU.
   *
   * @return  Value of property userUnitsPerDBU.
   */
  public double getUserUnitsPerDBU(){return this.userUnitsPerDBU;}

  /**
   * Getter for property version.
   *
   * @return  Value of property version.
   */
  public int getVersion(){return this.version;}

  /**
   * Setter for property accessControls.
   *
   * @param  accessControls  New value of property accessControls.
   */
  public void setAccessControls(GDSAccessControl accessControls[]) {
    this.accessControls = accessControls;
  }

  /**
   * Setter for property accessed.
   *
   * @param  accessed  New value of property accessed.
   */
  public void setAccessed(Date accessed){this.accessed = accessed;}

  /**
   * Setter for property attributeFile.
   *
   * @param  attributeFile  New value of property attributeFile.
   */
  public void setAttributeFile(String attributeFile) {
    this.attributeFile = attributeFile;
  }

  /**
   * Setter for property dirSize.
   *
   * @param  dirSize  New value of property dirSize.
   */
  public void setDirSize(int dirSize){this.dirSize = dirSize;}

  /**
   * Setter for property fonts.
   *
   * @param  fonts  New value of property fonts.
   */
  public void setFonts(String fonts[]){this.fonts = fonts;}

  /**
   * Setter for property format.
   *
   * @param  format  New value of property format.
   */
  public void setFormat(Format format){this.format = format;}

  /**
   * Setter for property generations.
   *
   * @param  generations  New value of property generations.
   */
  public void setGenerations(int generations){this.generations = generations;}

  /**
   * Setter for property metersPerDBU.
   *
   * @param  metersPerDBU  New value of property metersPerDBU.
   */
  public void setMetersPerDBU(double metersPerDBU) {
    this.metersPerDBU = metersPerDBU;
  }

  /**
   * Setter for property modified.
   *
   * @param  modified  New value of property modified.
   */
  public void setModified(Date modified){this.modified = modified;}

  /**
   * Setter for property name.
   *
   * @param  name  New value of property name.
   */
  public void setName(String name){this.name = name;}

  /**
   * Setter for property refLibs.
   *
   * @param  refLibs  New value of property refLibs.
   */
  public void setRefLibs(String refLibs[]){this.refLibs = refLibs;}

  /**
   * Setter for property spacingRulesFile.
   *
   * @param  spacingRulesFile  New value of property spacingRulesFile.
   */
  public void setSpacingRulesFile(String spacingRulesFile) {
    this.spacingRulesFile = spacingRulesFile;
  }

  /**
   * Setter for property structures.
   *
   * @param  structures  New value of property structures.
   */
  public void setStructures(Structure structures[]) {
    this.structures = structures;
  }

  /**
   * Setter for property userUnitsPerDBU.
   *
   * @param  userUnitsPerDBU  New value of property userUnitsPerDBU.
   */
  public void setUserUnitsPerDBU(double userUnitsPerDBU) {
    this.userUnitsPerDBU = userUnitsPerDBU;
  }

  /**
   * Setter for property version.
   *
   * @param  version  New value of property version.
   */
  public void setVersion(int version){this.version = version;}

  /**
   * Returns a string representation of the Database
   *
   * @return  A string representation of the Database
   */
  public String toString(){return getName();}
} // end class Database


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
