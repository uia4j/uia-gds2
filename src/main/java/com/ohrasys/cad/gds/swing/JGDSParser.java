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

package com.ohrasys.cad.gds.swing;

import com.ohrasys.cad.gds.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 * A lightweight GDSII parser Swing component.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.24 $, $Date: 2005/05/18 18:22:38 $
 */
public class JGDSParser
extends JComponent {
  /** Indicates the control panel should be embedded within the component */
  public static final int EXPLICIT_CONTROL = 0;

  /** Indicates the control panel is available via a popup menu */
  public static final int IMPLICIT_CONTROL = 1;

  /** Indicates component control is available only via programmatic means */
  public static final int PROGRAMMATIC_CONTROL = 2;

  /** The resource bundle */
  private static final String bundle =
    "com/ohrasys/cad/gds/swing/JGDSSwingProperties" /* NOI18N */;

  /** The thread used to update the component text area */
  private Thread consumer;

  /**
   * A popup menu for displaying the control panel when in IMPLICIT_CONTROL mode
   */
  private JPopupMenu controlMenu;

  /**
   * Indicates by what mode JGDSParser is controlled.  Must be one of
   * EXPLICIT_CONTROL where the control panel is embedded in the component,
   * IMPLICIT_CONTROL where the control panel is available via a popup menu,
   * PROGRAMMATIC_CONTROL where the control of JGDSParser must be done using
   * programmatic methods.
   */
  private int controlMode;

  /**
   * The control panel containing pointers to the GDS and log files as well as
   * buttons to begin and interrupt parsing
   */
  private JGDSParserControlPanel controlPanel;

  /** The frame used to display the control panel when in IMPLICIT_MODE */
  private JFrame controlPanelFrame;

  /** The resource bundle */
  private ResourceBundle i18n;

  /**
   * The piped input stream used to redirect parser data to the component text
   * area
   */
  private PipedInputStream in;

  /**
   * The list of ActionListeners that are listening to events from this
   * component
   */
  private EventListenerList listenerList;

  /**
   * The piped output stream used to redirect parser data to the component text
   * area
   */
  private PipedOutputStream out;

  /** The GDS file parser */
  private GDSParser parser;

  /** The worker thread used to execute the parser */
  private Thread producer;

  /** The text area used to display parser information */
  private JEditorPane textPane;

  /**
   * Creates a new JGDSParser object with control panel embedded in the
   * component.
   */
  public JGDSParser(){this(null, null, EXPLICIT_CONTROL);}

  /**
   * Creates a new JGDSParser object.
   *
   * @param  controlMode  One of EXPLICIT_CONTROL, IMPLICIT_CONTROL or
   *                      PROGRAMMATIC_CONTROL indicating whether the components
   *                      controls are available as an embedded control panel, a
   *                      popup control panel, or only via program control.
   */
  public JGDSParser(int controlMode){this(null, null, controlMode);}

  /**
   * Creates a new JGDSParser object with control panel embedded in the
   * component.
   *
   * @param  gdsfile  The initial GDS file to parse.
   */
  public JGDSParser(String gdsfile){this(gdsfile, null, EXPLICIT_CONTROL);}

  /**
   * Creates a new JGDSParser object.
   *
   * @param  gdsfile      The initial GDS file to parse.
   * @param  controlMode  One of EXPLICIT_CONTROL, IMPLICIT_CONTROL or
   *                      PROGRAMMATIC_CONTROL indicating whether the components
   *                      controls are available as an embedded control panel, a
   *                      popup control panel, or only via program control.
   */
  public JGDSParser(String gdsfile, int controlMode) {
    this(gdsfile, null, controlMode);
  }

  /**
   * Creates a new JGDSParser object with control panel embedded in the
   * component.
   *
   * @param  gdsfile  The inital GDS file to parse.
   * @param  logfile  The log file.
   */
  public JGDSParser(String gdsfile, String logfile) {
    this(gdsfile, logfile, EXPLICIT_CONTROL);
  }

  /**
   * Creates a new JGDSParser object.
   *
   * @param   gdsfile      The GDS file to parse.
   * @param   logfile      The log file to use.
   * @param   controlMode  One of EXPLICIT_CONTROL, IMPLICIT_CONTROL or
   *                       PROGRAMMATIC_CONTROL indicating whether the
   *                       components controls are available as an embedded
   *                       control panel, a popup control panel, or only via
   *                       program control.
   *
   * @throws  IllegalArgumentException  If controlMode is not an allowed value.
   */
  public JGDSParser(String gdsfile, String logfile, int controlMode) {
    if((EXPLICIT_CONTROL > controlMode) ||
        (controlMode > PROGRAMMATIC_CONTROL)) {
      throw new IllegalArgumentException();
    }

    this.controlMode = controlMode;
    i18n             = ResourceBundle.getBundle(bundle);
    initComponents();
    setGds(gdsfile);
    setLog(logfile);
    listenerList = null;
  }

  /**
   * Registers ActionListener to receive events.
   *
   * @param  listener  The listener to register.
   */
  public synchronized void addActionListener(ActionListener listener) {
    if(listenerList == null){listenerList = new EventListenerList();}

    listenerList.add(ActionListener.class, listener);
  }

  /**
   * Returns the current GDS file location
   *
   * @return  The current GDS file location
   */
  public String getGds(){return controlPanel.gdsField.getText();}

  /**
   * Returns the current log file location
   *
   * @return  The current log file location
   */
  public String getLog(){return controlPanel.logField.getText();}

  /** Interrupts the parser */
  public void interruptParser(){controlPanel.exitButton.doClick();}

  /** Parses the currently specified GDS file set using the setGds method. */
  public void parseDesign(){controlPanel.parseButton.doClick();}

  /**
   * Removes ActionListener from the list of listeners.
   *
   * @param  listener  The listener to remove.
   */
  public synchronized void removeActionListener(ActionListener listener) {
    listenerList.remove(ActionListener.class, listener);
  }

  /**
   * Sets the GDS file to parse
   *
   * @param  gds  The path to the GDS file to parse.
   */
  public void setGds(String gds) {
    controlPanel.gdsField.setText(gds);
    fireActionListenerActionPerformed(new ActionEvent(this, 0, null));
  }

  /**
   * Sets the logfile for the parser.  If null, the standard output is used.
   *
   * @param  log  The path to the log file.
   */
  public void setLog(String log) {
    controlPanel.logField.setText(log);
    fireActionListenerActionPerformed(new ActionEvent(this, 0, null));
  }

  /**
   * Returns a string representation of the object
   *
   * @return  The physical address of the instance
   */
  public String toString(){return super.toString();}

  /**
   * Displays a message dialog if the component is visible
   *
   * @param  message  The message to display
   */
  private void displayDialog(String message) {
    if(isVisible()) {
      final String msg = message;
      new Thread(new Runnable() {
          public void run(){JOptionPane.showMessageDialog(null, msg);}
        }).start();
    }
  }

  /**
   * Notify listeners that an action has occured
   *
   * @param  event  The event representing the action
   */
  private void fireActionListenerActionPerformed(ActionEvent event) {
    if(listenerList == null){return;}

    Object listeners[] = listenerList.getListenerList();

    for(int i = listeners.length - 2;i >= 0;i -= 2) {
      if(listeners[i] == ActionListener.class) {
        ((ActionListener)listeners[i + 1]).actionPerformed(event);
      }
    }
  }

  /** A method to initialize the sub-components of JGDSParser */
  private void initComponents() {
    controlPanel         = new JGDSParserControlPanel();
    textPane             = new JEditorPane();
    textPane.setEditable(false);
    textPane.setFont(new Font("Monospaced" /* NOI18N */, Font.PLAIN,
        textPane.getFont().getSize()));

    JScrollPane scroller = new JScrollPane(textPane);
    setLayout(new BorderLayout());
    add(scroller, BorderLayout.CENTER);

    switch(controlMode) {
      case (EXPLICIT_CONTROL):
        add(controlPanel, BorderLayout.SOUTH);

        break;

      case (IMPLICIT_CONTROL):
        textPane.setToolTipText(i18n.getString(
            "JGDSPARSER_RIGHT_CLICK" /* NOI18N */));
        textPane.addMouseListener(new JGDSParserPaneMouseAdapter());

        break;

      default:
        controlPanel.setVisible(false);
    }
  } // end method initComponents

  /**
   * A method to insert a text message into the message area of JGDSParser
   *
   * @param  msg  The message to insert
   */
  private void insertMessage(String msg) {
    int offset = textPane.getDocument().getEndPosition().getOffset();

    try {
      textPane.getDocument().insertString(offset, msg, null);
    } catch(BadLocationException e) {
      /* can't do anything now */
    }
  }

  /**
   * A method for processing incoming control panel events
   *
   * @param  evt  The event to process
   */
  private void processControlEvent(AWTEvent evt) {
    Object       src     = evt.getSource();
    final String gdsfile = controlPanel.gdsField.getText();
    final String logfile = controlPanel.logField.getText();

    if(src.equals(controlPanel.parseButton)) {
      if((producer != null) || (consumer != null)) {
        displayDialog(i18n.getString(
            "JGDSPARSER_RUNNING" /* NOI18N */));
      } else {
        textPane.setDocument(new DefaultEditorKit().createDefaultDocument());
        producer       = new Thread(new Runnable() {
              public void run() {
                /* Build the output stream */
                out = new PipedOutputStream();

                synchronized(consumer) {
                  consumer.notifyAll();
                }

                /* Wait for the consumer to notify us that the input stream has been created */
                synchronized(producer) {
                  while(in == null) {
                    try {
                      producer.wait();
                    } catch(InterruptedException ex) {
                      ex.printStackTrace();

                      break;
                    }
                  }
                }

                /* It's now safe to parse the design */
                parser = new GDSParser();
                parser.parseDesign(new File(gdsfile), out);

                /* Wait for the consumer to notify us that processing is done */
                synchronized(producer) {
                  while(in != null) {
                    try {
                      producer.wait();
                    } catch(InterruptedException ex) {
                      ex.printStackTrace();

                      break;
                    }
                  }
                }

                /* Close the and delete the output stream */
                out = null;

                synchronized(consumer) {
                  consumer.notifyAll();
                }

                /* Wait until the consumer exits */
                synchronized(producer) {
                  while(consumer != null) {
                    try {
                      producer.wait();
                    } catch(InterruptedException ex) {
                      ex.printStackTrace();

                      break;
                    }
                  }
                }

                /* Delete ourself */
                producer = null;
              } // end method run
            });
        consumer = new Thread(new Runnable() {
              public void run() {
                /* Wait for the output stream */
                synchronized(consumer) {
                  while(out == null) {
                    try {
                      consumer.wait();
                    } catch(InterruptedException ex) {
                      ex.printStackTrace();

                      break;
                    }
                  }
                }

                /* Create the input stream */
                try {
                  in = new PipedInputStream(out);
                } catch(IOException ex) {
                  ex.printStackTrace();
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(
                      in));
                PrintWriter    fw = null;

                if((logfile != null) &&
                    (logfile.trim().length() > 0)) {
                  try {
                    fw = new PrintWriter(new File(
                          logfile));
                  } catch(IOException ex) {
                    /* can't do anything now */
                  }
                } else if(!isVisible()){fw = new PrintWriter(System.out);}

                /* Notify the producer that the input stream is ready */
                synchronized(producer) {
                  producer.notifyAll();
                }

                /* Read parser data until the end of stream is reached */
                String line = null;

                try {
                  while((line = br.readLine()) != null) {
                    insertMessage(line + "\n" /* NOI18N */);

                    if(fw != null){fw.print(line + "\n" /* NOI18N */);}
                  }
                } catch(IOException ex) {
                  /* can't do anything now */
                }

                /* Close and delete the input streams */
                try {
                  if(fw != null){fw.close();}

                  br.close();
                  in.close();
                } catch(IOException ex) {
                  ex.printStackTrace();
                }

                in = null;

                /* Notify the producer that all the data has been read */
                synchronized(producer) {
                  producer.notifyAll();
                }

                /* Wait until the output stream is deleted */
                synchronized(consumer) {
                  while(out != null) {
                    try {
                      consumer.wait();
                    } catch(InterruptedException ex) {
                      ex.printStackTrace();

                      break;
                    }
                  }
                }

                consumer = null;

                /* Delete our self and notify the producer that were finished */
                synchronized(producer) {
                  producer.notifyAll();
                }
              } // end method run
            });
        producer.start();
        consumer.start();
      } // end if
    } else if(src.equals(controlPanel.exitButton)) {
      if(producer != null) {
        producer.interrupt();

        try {
          producer.join();
        } catch(InterruptedException ex) {
          /* can't do anything now */
        }
      }
    }
  } // end method processControlEvent

  /**
   * The control panel class used to set the GDS and log file names as well as
   * beginning and interrupting parsing
   *
   * @author   $Author: tvaline $
   * @version  $Revision: 1.24 $, $Date: 2005/05/18 18:22:38 $
   */
  private final class JGDSParserControlPanel
  extends JPanel {
    /** The button used to interrupt parsing */
    private JButton exitButton;

    /** The file chooser used to select the GDS and log files */
    private JFileChooser fc;

    /** The file filter used to filter in only GDS files */
    private JGDSParserFileFilter ff;

    /**
     * The frame used to display the controll panel when in IMPLICIT_CONTROL
     * mode
     */
    private JFrame frame;

    /** The button used to select the GDS file using the file chooser */
    private JButton gdsButton;

    /** The text field containing the GDS file name */
    private JTextField gdsField;

    /** The label for the GDS text field */
    private JLabel gdsLabel;

    /** The button used to select the log file using the file chooser */
    private JButton logButton;

    /** The text field containing the log file name */
    private JTextField logField;

    /** The label for the log file text field */
    private JLabel logLabel;

    /** The button used to begin parsing */
    private JButton parseButton;

    /**
     * Creates a new JGDSParserControlPanel object.
     */
    private JGDSParserControlPanel() {
      super();
      initComponents();
    }

    /**
     * Returns a string representation of the object
     *
     * @return  The physical address of the instance
     */
    public String toString(){return super.toString();}

    /** A method to initialize the sub-components of the control panel */
    private void initComponents() {
      GridBagConstraints gridBagConstraints;
      ff          = new JGDSParserFileFilter(
          i18n.getString("JGDSPARSER_GDSFILES" /* NOI18N */) +
          "(*.gds, *.gds2, *.db, *.sf)" /* NOI18N */,
          new String[]{
            "gds2" /* NOI18N */,
            "db" /* NOI18N */,
            "gds" /* NOI18N */,
            "sf" /* NOI18N */
          }, true);
      gdsLabel    = new JLabel();
      gdsField    = new JTextField();
      gdsButton   = new JButton();
      logLabel    = new JLabel();
      logField    = new JTextField();
      logButton   = new JButton();
      parseButton = new JButton();
      exitButton  = new JButton();
      setLayout(new GridBagLayout());
      gdsLabel.setText(i18n.getString("JGDSPARSER_GDS" /* NOI18N */));
      gridBagConstraints         = new GridBagConstraints();
      gridBagConstraints.fill    = GridBagConstraints.HORIZONTAL;
      gridBagConstraints.insets  = new Insets(10, 10, 0, 0);
      gridBagConstraints.anchor  = GridBagConstraints.NORTHWEST;
      gridBagConstraints.weightx = 1.0;
      add(gdsLabel, gridBagConstraints);
      gdsField.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            processControlEvent(evt);
          }
        });
      gdsField.addFocusListener(new FocusAdapter() {
          public void focusLost(FocusEvent evt){processControlEvent(evt);}
        });
      gdsField.setColumns(48);
      gdsField.setToolTipText(i18n.getString(
          "JGDSPARSER_ENT_GDS" /* NOI18N */));
      gridBagConstraints           = new GridBagConstraints();
      gridBagConstraints.gridx     = 0;
      gridBagConstraints.gridy     = 1;
      gridBagConstraints.gridwidth = 7;
      gridBagConstraints.fill      = GridBagConstraints.BOTH;
      gridBagConstraints.insets    = new Insets(0, 10, 0, 0);
      gridBagConstraints.anchor    = GridBagConstraints.NORTHWEST;
      gridBagConstraints.weightx   = 0.7;
      add(gdsField, gridBagConstraints);
      gdsButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            if(fc == null) {
              fc = new JFileChooser();
              fc.setFileSelectionMode(fc.FILES_ONLY);
            }

            fc.addChoosableFileFilter(ff);
            fc.setAcceptAllFileFilterUsed(true);
            fc.setFileFilter(ff);

            int result = fc.showDialog(null,
                i18n.getString(
                  "JGDSPARSER_SEL_GDS" /* NOI18N */));

            if(result == fc.APPROVE_OPTION) {
              gdsField.setText(fc.getSelectedFile().getAbsolutePath());
              processControlEvent(evt);
            }
          }
        });
      gdsButton.setText("..." /*NOI18N*/);
      gdsButton.setToolTipText(i18n.getString(
          "JGDSPARSER_BROWSE_GDS" /* NOI18N */));
      gridBagConstraints        = new GridBagConstraints();
      gridBagConstraints.gridx  = 7;
      gridBagConstraints.gridy  = 1;
      gridBagConstraints.insets = new Insets(0, 0, 0, 10);
      gridBagConstraints.anchor = GridBagConstraints.WEST;
      add(gdsButton, gridBagConstraints);
      logLabel.setText(i18n.getString("JGDSPARSER_LOG" /* NOI18N */));
      gridBagConstraints         = new GridBagConstraints();
      gridBagConstraints.gridx   = 0;
      gridBagConstraints.gridy   = 2;
      gridBagConstraints.fill    = GridBagConstraints.HORIZONTAL;
      gridBagConstraints.insets  = new Insets(0, 10, 0, 0);
      gridBagConstraints.anchor  = GridBagConstraints.NORTHWEST;
      gridBagConstraints.weightx = 1.0;
      add(logLabel, gridBagConstraints);
      logField.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            processControlEvent(evt);
          }
        });
      logField.addFocusListener(new FocusAdapter() {
          public void focusLost(FocusEvent evt){processControlEvent(evt);}
        });
      logField.setColumns(48);
      logField.setToolTipText(i18n.getString(
          "JGDSPARSER_ENT_LOG" /* NOI18N */));
      gridBagConstraints           = new GridBagConstraints();
      gridBagConstraints.gridx     = 0;
      gridBagConstraints.gridy     = 3;
      gridBagConstraints.gridwidth = 7;
      gridBagConstraints.fill      = GridBagConstraints.BOTH;
      gridBagConstraints.insets    = new Insets(0, 10, 10, 0);
      gridBagConstraints.anchor    = GridBagConstraints.NORTHWEST;
      gridBagConstraints.weightx   = 1.0;
      add(logField, gridBagConstraints);
      logButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            if(fc == null) {
              fc = new JFileChooser();
              fc.setFileSelectionMode(fc.FILES_ONLY);
            }

            fc.removeChoosableFileFilter(ff);
            fc.setAcceptAllFileFilterUsed(true);

            int result = fc.showDialog(null,
                i18n.getString(
                  "JGDSPARSER_SEL_LOG" /* NOI18N */));

            if(result == fc.APPROVE_OPTION) {
              logField.setText(fc.getSelectedFile().getAbsolutePath());
              processControlEvent(evt);
            }
          }
        });
      logButton.setText("..." /*NOI18N*/);
      logButton.setToolTipText(i18n.getString(
          "JGDSPARSER_BROWSE_LOG" /* NOI18N */));
      gridBagConstraints        = new GridBagConstraints();
      gridBagConstraints.gridx  = 7;
      gridBagConstraints.gridy  = 3;
      gridBagConstraints.insets = new Insets(0, 0, 10, 10);
      gridBagConstraints.anchor = GridBagConstraints.WEST;
      add(logButton, gridBagConstraints);
      parseButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            processControlEvent(evt);
          }
        });
      exitButton.setMnemonic(KeyEvent.VK_T);
      exitButton.setText(i18n.getString("JGDSPARSER_STOP" /* NOI18N */));
      exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
      exitButton.setToolTipText(i18n.getString(
          "JGDSPARSER_CANCEL" /* NOI18N */));
      gridBagConstraints        = new GridBagConstraints();
      gridBagConstraints.gridx  = 8;
      gridBagConstraints.gridy  = 3;
      gridBagConstraints.insets = new Insets(0, 10, 10, 10);
      gridBagConstraints.anchor = GridBagConstraints.WEST;
      gridBagConstraints.fill   = GridBagConstraints.HORIZONTAL;
      add(exitButton, gridBagConstraints);
      parseButton.setMnemonic(KeyEvent.VK_S);
      parseButton.setText(i18n.getString(
          "JGDSPARSER_START" /* NOI18N */));
      parseButton.setHorizontalTextPosition(SwingConstants.CENTER);
      parseButton.setToolTipText(i18n.getString(
          "JGDSPARSER_PARSE" /* NOI18N */));
      gridBagConstraints        = new GridBagConstraints();
      gridBagConstraints.gridx  = 8;
      gridBagConstraints.gridy  = 1;
      gridBagConstraints.insets = new Insets(0, 10, 0, 10);
      gridBagConstraints.anchor = GridBagConstraints.WEST;
      gridBagConstraints.fill   = GridBagConstraints.HORIZONTAL;
      add(parseButton, gridBagConstraints);
      exitButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            processControlEvent(evt);
          }
        });
    } // end method initComponents
  } // end class JGDSParserControlPanel

  /**
   * A JGDSParser specialization of the FileFilter interface.  Used to select
   * only GDS file extensions
   *
   * @author   $Author: tvaline $
   * @version  $Revision: 1.24 $, $Date: 2005/05/18 18:22:38 $
   */
  private final class JGDSParserFileFilter
  extends javax.swing.filechooser.FileFilter {
    /** A flag indicating if directories are selectable */
    private boolean allowDirs;

    /** A list of selectable file extensions */
    private String allowed[];

    /** A description of the file filter */
    private String description;

    /**
     * Creates a new JGDSParserFileFilter object.
     *
     * @param  desc       The file filter description
     * @param  allowed    A String array of allowable file extensions
     * @param  allowDirs  True if directories are allowed to be selected
     */
    private JGDSParserFileFilter(String desc, String allowed[],
        boolean allowDirs) {
      this.description = desc;
      this.allowed     = allowed;
      this.allowDirs   = allowDirs;
    }

    /**
     * A method to determine if the filter should pass a file based on the
     * filtering criteria
     *
     * @param   file  The file to test
     *
     * @return  True if the extension of the file is one of the allowable
     *          extensions
     */
    public boolean accept(File file) {
      if(file.isDirectory()) {
        if(allowDirs){return true;}
        else{return false;}
      }

      String extension = getExtension(file);

      if(extension != null) {
        for(int i = 0;i < allowed.length;i++) {
          if(extension.equals(allowed[i])){return true;}
        }

        return false;
      }

      return false;
    }

    /**
     * Returns a description of the file filter
     *
     * @return  A description of the file filter
     */
    public String getDescription(){return description;}

    /**
     * Returns a string representation of the file filter
     *
     * @return  The physical address of the file filter instance
     */
    public String toString(){return super.toString();}

    /**
     * A method to determine the extension of a file
     *
     * @param   file  The file to test
     *
     * @return  The extension of the file
     */
    private String getExtension(File file) {
      String path = file.getPath();

      return path.substring(path.lastIndexOf('.') + 1);
    }
  } // end class JGDSParserFileFilter

  /**
   * A JGDSParser specialization of the MouseListener interface
   *
   * @author   $Author: tvaline $
   * @version  $Revision: 1.24 $, $Date: 2005/05/18 18:22:38 $
   */
  private final class JGDSParserPaneMouseAdapter
  extends MouseAdapter {
    /**
     * A method that is executed when the mouse is pressed
     *
     * @param  evt  An event representing the mouse button press
     */
    public void mousePressed(MouseEvent evt){showPopup(evt);}

    /**
     * A method that is executed when the mouse is released
     *
     * @param  evt  An event representing the mouse button release
     */
    public void mouseReleased(MouseEvent evt){showPopup(evt);}

    /**
     * Returns a string representation of the object
     *
     * @return  The physical address of the instance
     */
    public String toString(){return super.toString();}

    /**
     * A method to determine if a popup menu should be displayed
     *
     * @param  evt  The mouse event to evaluate
     */
    private void showPopup(MouseEvent evt) {
      if(controlMenu == null) {
        controlMenu = new JPopupMenu();

        JMenuItem item = controlMenu.add(i18n.getString(
              "JGDSPARSER_OPEN_MENU" /* NOI18N */));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              if(controlPanelFrame == null) {
                controlPanelFrame = new JFrame(i18n.getString(
                      "JGDSPARSER_OPTIONS" /* NOI18N */));
                controlPanelFrame.setLocationByPlatform(true);
                controlPanelFrame.getContentPane().add(
                  controlPanel);
                controlPanelFrame.pack();
                controlPanelFrame.setDefaultCloseOperation(
                  JFrame.HIDE_ON_CLOSE);
                controlPanelFrame.setResizable(false);
              } else{controlPanelFrame.setLocationByPlatform(false);}

              controlPanelFrame.setVisible(true);
            }
          });
      }

      if(evt.isPopupTrigger()) {
        controlMenu.show(evt.getComponent(), evt.getX(), evt.getY());
      }
    } // end method showPopup
  } // end class JGDSParserPaneMouseAdapter
} // end class JGDSParser


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
