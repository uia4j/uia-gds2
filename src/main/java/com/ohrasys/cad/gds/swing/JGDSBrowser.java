/*
 * Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package com.ohrasys.cad.gds.swing;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.ohrasys.cad.gds.GDSParser;
import com.ohrasys.cad.gds.dao.Database;
import com.ohrasys.cad.gds.dao.InfoProvider;

/**
 * A lightweight GDSII browser Swing component.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.20 $, $Date: 2005/05/18 18:22:38 $
 */
public class JGDSBrowser extends JComponent implements TreeSelectionListener {

    /**
     *
     */
    private static final long serialVersionUID = 4365989705416348804L;

    /** Indicates the control panel should be embedded within the component */
    public static final int EXPLICIT_CONTROL = 0;

    /** Indicates the control panel is available via a popup menu */
    public static final int IMPLICIT_CONTROL = 1;

    /** Indicates component control is available only via programmatic means */
    public static final int PROGRAMMATIC_CONTROL = 2;

    /** The resource bundle */
    private static final String bundle = "com/ohrasys/cad/gds/swing/JGDSSwingProperties" /* NOI18N */;

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
    private JGDSBrowserControlPanel controlPanel;

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

    /** List of records retrieved from the GDS file */
    private java.util.List records;

    /** The split planes between the tree and text areas */
    private JSplitPane split;

    /** The text area used to display database info */
    private JTextPane textArea;

    /** The text area used to display parser information */
    private JGDSBrowserTree tree;

    /** The top of the GDS browser tree */
    private DefaultMutableTreeNode treeTop;

    /** The thread used to populate the browser tree */
    private Thread updater;

    /**
     * Creates a new JGDSParser object with control panel embedded in the
     * component.
     */
    public JGDSBrowser() {
        this(null, null, EXPLICIT_CONTROL);
    }

    /**
     * Creates a new JGDSParser object.
     *
     * @param  controlMode  One of EXPLICIT_CONTROL, IMPLICIT_CONTROL or
     *                      PROGRAMMATIC_CONTROL indicating whether the components
     *                      controls are available as an embedded control panel, a
     *                      popup control panel, or only via program control.
     */
    public JGDSBrowser(int controlMode) {
        this(null, null, controlMode);
    }

    /**
     * Creates a new JGDSParser object with control panel embedded in the
     * component.
     *
     * @param  gdsfile  The initial GDS file to parse.
     */
    public JGDSBrowser(String gdsfile) {
        this(gdsfile, null, EXPLICIT_CONTROL);
    }

    /**
     * Creates a new JGDSParser object.
     *
     * @param  gdsfile      The initial GDS file to parse.
     * @param  controlMode  One of EXPLICIT_CONTROL, IMPLICIT_CONTROL or
     *                      PROGRAMMATIC_CONTROL indicating whether the components
     *                      controls are available as an embedded control panel, a
     *                      popup control panel, or only via program control.
     */
    public JGDSBrowser(String gdsfile, int controlMode) {
        this(gdsfile, null, controlMode);
    }

    /**
     * Creates a new JGDSParser object with control panel embedded in the
     * component.
     *
     * @param  gdsfile  The inital GDS file to parse.
     * @param  logfile  The log file.
     */
    public JGDSBrowser(String gdsfile, String logfile) {
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
    public JGDSBrowser(String gdsfile, String logfile, int controlMode) {
        if ((EXPLICIT_CONTROL > controlMode) ||
                (controlMode > PROGRAMMATIC_CONTROL)) {
            throw new IllegalArgumentException();
        }

        this.controlMode = controlMode;
        this.i18n = ResourceBundle.getBundle(bundle);
        initComponents();
        setGds(gdsfile);
        setLog(logfile);
        this.listenerList = null;
    }

    /**
     * Registers ActionListener to receive events.
     *
     * @param  listener  The listener to register.
     */
    public synchronized void addActionListener(ActionListener listener) {
        if (this.listenerList == null) {
            this.listenerList = new EventListenerList();
        }

        this.listenerList.add(ActionListener.class, listener);
    }

    /**
     * Returns the current GDS file location
     *
     * @return  The current GDS file location
     */
    public String getGds() {
        return this.controlPanel.gdsField.getText();
    }

    /**
     * Returns the current log file location
     *
     * @return  The current log file location
     */
    public String getLog() {
        return this.controlPanel.logField.getText();
    }

    /** Interrupts the parser */
    public void interruptParser() {
        this.controlPanel.exitButton.doClick();
    }

    /** Parses the currently specified GDS file set using the setGds method. */
    public void parseDesign() {
        this.controlPanel.parseButton.doClick();
    }

    /**
     * Removes ActionListener from the list of listeners.
     *
     * @param  listener  The listener to remove.
     */
    public synchronized void removeActionListener(ActionListener listener) {
        this.listenerList.remove(ActionListener.class, listener);
    }

    /**
     * Sets the GDS file to parse
     *
     * @param  gds  The path to the GDS file to parse.
     */
    public void setGds(String gds) {
        this.controlPanel.gdsField.setText(gds);
        fireActionListenerActionPerformed(new ActionEvent(this, 0, null));
    }

    /**
     * Sets the logfile for the parser.  If null, the standard output is used.
     *
     * @param  log  The path to the log file.
     */
    public void setLog(String log) {
        this.controlPanel.logField.setText(log);
        fireActionListenerActionPerformed(new ActionEvent(this, 0, null));
    }

    /**
     * Returns a string representation of the object
     *
     * @return  The physical address of the instance
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Method used to update the text area in response to a tree event
     *
     * @param  evt  The tree event initiating the change
     */
    @Override
    public void valueChanged(TreeSelectionEvent evt) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.tree
                .getLastSelectedPathComponent();
        if (node == null) {
            return;
        }
        Object obj = node.getUserObject();
        if ((obj != null) && (obj instanceof InfoProvider)) {
            this.textArea.setText(((InfoProvider) obj).getInfo());
        }
    }

    /**
     * Displays a message dialog if the component is visible
     *
     * @param  message  The message to display
     */
    private void displayDialog(String message) {
        if (isVisible()) {
            final String msg = message;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, msg);
                }
            }).start();
        }
    }

    /**
     * Notify listeners that an action has occured
     *
     * @param  event  The event representing the action
     */
    private void fireActionListenerActionPerformed(ActionEvent event) {
        if (this.listenerList == null) {
            return;
        }

        Object listeners[] = this.listenerList.getListenerList();

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ActionListener.class) {
                ((ActionListener) listeners[i + 1]).actionPerformed(event);
            }
        }
    }

    /** A method to initialize the sub-components of JGDSParser */
    private void initComponents() {
        this.controlPanel = new JGDSBrowserControlPanel();
        this.tree = new JGDSBrowserTree();
        this.tree.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
                this.tree.getBackground()));
        this.tree.addTreeSelectionListener(this);
        this.textArea = new JTextPane();
        JScrollPane scroller = new JScrollPane(this.tree);
        JScrollPane textScroll = new JScrollPane(this.textArea);
        this.split = new JSplitPane();
        this.split.setOneTouchExpandable(true);
        this.split.add(scroller, this.split.LEFT, 0);
        this.split.add(textScroll, this.split.RIGHT, 1);
        setLayout(new BorderLayout());
        add(this.split, BorderLayout.CENTER);
        switch (this.controlMode) {
            case (EXPLICIT_CONTROL):
                add(this.controlPanel, BorderLayout.SOUTH);
                break;

            case (IMPLICIT_CONTROL):
                ToolTipManager.sharedInstance().registerComponent(this.tree);
                this.tree.addMouseListener(new JGDSBrowserPaneMouseAdapter());
                break;

            default:
                this.controlPanel.setVisible(false);
        }
    } // end method initComponents

    /**
     * A method for processing incoming control panel events
     *
     * @param  evt  The event to process
     */
    private void processControlEvent(AWTEvent evt) {
        Object src = evt.getSource();
        final String gdsfile = this.controlPanel.gdsField.getText();
        final String logfile = this.controlPanel.logField.getText();
        if (src.equals(this.controlPanel.parseButton)) {
            if ((this.producer != null) || (this.consumer != null)) {
                displayDialog(this.i18n.getString(
                        "JGDSPARSER_RUNNING" /* NOI18N */));
            }
            else {
                this.records = null;
                this.producer = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        /* Build the output stream */
                        JGDSBrowser.this.out = new PipedOutputStream();

                        synchronized (JGDSBrowser.this.consumer) {
                            JGDSBrowser.this.consumer.notifyAll();
                        }

                        /* Wait for the consumer to notify us that the input stream has been created */
                        synchronized (JGDSBrowser.this.producer) {
                            while (JGDSBrowser.this.in == null) {
                                try {
                                    JGDSBrowser.this.producer.wait();
                                }
                                catch (InterruptedException ex) {
                                    ex.printStackTrace();

                                    break;
                                }
                            }
                        }

                        /* It's now safe to parse the design */
                        JGDSBrowser.this.parser = new GDSParser();
                        JGDSBrowser.this.parser.setCollecting(true);
                        JGDSBrowser.this.records = JGDSBrowser.this.parser.parseDesign(new File(gdsfile), JGDSBrowser.this.out);
                        synchronized (JGDSBrowser.this.updater) {
                            if (JGDSBrowser.this.records == null) {
                                JGDSBrowser.this.updater.interrupt();
                            }
                            else {
                                JGDSBrowser.this.updater.notifyAll();
                            }
                        }

                        /* Wait for the consumer to notify us that processing is done */
                        synchronized (JGDSBrowser.this.producer) {
                            while (JGDSBrowser.this.in != null) {
                                try {
                                    JGDSBrowser.this.producer.wait();
                                }
                                catch (InterruptedException ex) {
                                    ex.printStackTrace();

                                    break;
                                }
                            }
                        }

                        /* Close the and delete the output stream */
                        JGDSBrowser.this.out = null;

                        synchronized (JGDSBrowser.this.consumer) {
                            JGDSBrowser.this.consumer.notifyAll();
                        }

                        /* Wait until the consumer exits */
                        synchronized (JGDSBrowser.this.producer) {
                            while (JGDSBrowser.this.consumer != null) {
                                try {
                                    JGDSBrowser.this.producer.wait();
                                }
                                catch (InterruptedException ex) {
                                    ex.printStackTrace();

                                    break;
                                }
                            }
                        }

                        /* Delete ourself */
                        JGDSBrowser.this.producer = null;
                    } // end method run
                });
                this.consumer = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        /* Wait for the output stream */
                        synchronized (JGDSBrowser.this.consumer) {
                            while (JGDSBrowser.this.out == null) {
                                try {
                                    JGDSBrowser.this.consumer.wait();
                                }
                                catch (InterruptedException ex) {
                                    ex.printStackTrace();

                                    break;
                                }
                            }
                        }

                        /* Create the input stream */
                        try {
                            JGDSBrowser.this.in = new PipedInputStream(JGDSBrowser.this.out);
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        BufferedReader br = new BufferedReader(new InputStreamReader(
                                JGDSBrowser.this.in));
                        PrintWriter fw = null;

                        if ((logfile != null) &&
                                (logfile.trim().length() > 0)) {
                            try {
                                fw = new PrintWriter(new File(
                                        logfile));
                            }
                            catch (IOException ex) {
                                /* can't do anything now */
                            }
                        }

                        /* Notify the producer that the input stream is ready */
                        synchronized (JGDSBrowser.this.producer) {
                            JGDSBrowser.this.producer.notifyAll();
                        }

                        /* Read parser data until the end of stream is reached */
                        String line = null;

                        try {
                            while ((line = br.readLine()) != null) {
                                if (fw != null) {
                                    fw.print(line + "\n" /* NOI18N */);
                                }
                            }
                        }
                        catch (IOException ex) {
                            /* can't do anything now */
                        }

                        /* Close and delete the input streams */
                        try {
                            if (fw != null) {
                                fw.close();
                            }

                            br.close();
                            JGDSBrowser.this.in.close();
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        JGDSBrowser.this.in = null;

                        /* Notify the producer that all the data has been read */
                        synchronized (JGDSBrowser.this.producer) {
                            JGDSBrowser.this.producer.notifyAll();
                        }

                        /* Wait until the output stream is deleted */
                        synchronized (JGDSBrowser.this.consumer) {
                            while (JGDSBrowser.this.out != null) {
                                try {
                                    JGDSBrowser.this.consumer.wait();
                                }
                                catch (InterruptedException ex) {
                                    ex.printStackTrace();

                                    break;
                                }
                            }
                        }

                        JGDSBrowser.this.consumer = null;

                        /* Delete our self and notify the producer that were finished */
                        synchronized (JGDSBrowser.this.producer) {
                            JGDSBrowser.this.producer.notifyAll();
                        }
                    } // end method run
                });
                this.updater = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        JGDSBrowser.this.treeTop = new DefaultMutableTreeNode(JGDSBrowser.this.i18n.getString(
                                "JGDSBROWSER_PARSING" /*NOI18N*/));
                        JGDSBrowser.this.tree.setModel(new DefaultTreeModel(JGDSBrowser.this.treeTop));
                        synchronized (JGDSBrowser.this.updater) {
                            while (JGDSBrowser.this.records == null) {
                                try {
                                    JGDSBrowser.this.updater.wait();
                                }
                                catch (InterruptedException ex) {
                                    break;
                                }
                            }
                        }
                        JGDSBrowser.this.treeTop = new DefaultMutableTreeNode();
                        if (JGDSBrowser.this.records != null) {
                            JGDSBrowser.this.tree.buildTree((Database) JGDSBrowser.this.records.get(0));
                        }
                        else {
                            JGDSBrowser.this.tree.setModel(new DefaultTreeModel(
                                    new DefaultMutableTreeNode()));
                        }
                    }
                });
                this.producer.start();
                this.consumer.start();
                this.updater.start();
            } // end if
        }
        else if (src.equals(this.controlPanel.exitButton)) {
            if (this.producer != null) {
                this.producer.interrupt();

                try {
                    this.producer.join();
                }
                catch (InterruptedException ex) {
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
     * @version  $Revision: 1.20 $, $Date: 2005/05/18 18:22:38 $
     */
    private final class JGDSBrowserControlPanel extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = -1624540739696826874L;

        /** The button used to interrupt parsing */
        private JButton exitButton;

        /** The file chooser used to select the GDS and log files */
        private JFileChooser fc;

        /** The file filter used to filter in only GDS files */
        private JGDSBrowserFileFilter ff;

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
        private JGDSBrowserControlPanel() {
            super();
            initComponents();
        }

        /**
         * Returns a string representation of the object
         *
         * @return  The physical address of the instance
         */
        @Override
        public String toString() {
            return super.toString();
        }

        /** A method to initialize the sub-components of the control panel */
        private void initComponents() {
            GridBagConstraints gridBagConstraints;
            this.ff = new JGDSBrowserFileFilter(
                    JGDSBrowser.this.i18n.getString("JGDSPARSER_GDSFILES" /* NOI18N */) +
                            "(*.gds, *.gds2, *.db, *.sf)" /* NOI18N */,
                    new String[] {
                            "gds2" /* NOI18N */,
                            "db" /* NOI18N */,
                            "gds" /* NOI18N */,
                            "sf" /* NOI18N */
                    }, true);
            this.gdsLabel = new JLabel();
            this.gdsField = new JTextField();
            this.gdsButton = new JButton();
            this.logLabel = new JLabel();
            this.logField = new JTextField();
            this.logButton = new JButton();
            this.parseButton = new JButton();
            this.exitButton = new JButton();
            setLayout(new GridBagLayout());
            this.gdsLabel.setText(JGDSBrowser.this.i18n.getString("JGDSPARSER_GDS" /* NOI18N */));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints.insets = new Insets(10, 10, 0, 0);
            gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            add(this.gdsLabel, gridBagConstraints);
            this.gdsField.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    processControlEvent(evt);
                }
            });
            this.gdsField.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent evt) {
                    processControlEvent(evt);
                }
            });
            this.gdsField.setColumns(48);
            this.gdsField.setToolTipText(JGDSBrowser.this.i18n.getString(
                    "JGDSPARSER_ENT_GDS" /* NOI18N */));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 7;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.insets = new Insets(0, 10, 0, 0);
            gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 0.7;
            add(this.gdsField, gridBagConstraints);
            this.gdsButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (JGDSBrowserControlPanel.this.fc == null) {
                        JGDSBrowserControlPanel.this.fc = new JFileChooser();
                        JGDSBrowserControlPanel.this.fc.setFileSelectionMode(JGDSBrowserControlPanel.this.fc.FILES_ONLY);
                    }

                    JGDSBrowserControlPanel.this.fc.addChoosableFileFilter(JGDSBrowserControlPanel.this.ff);
                    JGDSBrowserControlPanel.this.fc.setAcceptAllFileFilterUsed(true);
                    JGDSBrowserControlPanel.this.fc.setFileFilter(JGDSBrowserControlPanel.this.ff);

                    int result = JGDSBrowserControlPanel.this.fc.showDialog(null,
                            JGDSBrowser.this.i18n.getString(
                                    "JGDSPARSER_SEL_GDS" /* NOI18N */));

                    if (result == JGDSBrowserControlPanel.this.fc.APPROVE_OPTION) {
                        JGDSBrowserControlPanel.this.gdsField.setText(JGDSBrowserControlPanel.this.fc.getSelectedFile().getAbsolutePath());
                        processControlEvent(evt);
                    }
                }
            });
            this.gdsButton.setText("..." /*NOI18N*/);
            this.gdsButton.setToolTipText(JGDSBrowser.this.i18n.getString(
                    "JGDSPARSER_BROWSE_GDS" /* NOI18N */));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new Insets(0, 0, 0, 10);
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            add(this.gdsButton, gridBagConstraints);
            this.logLabel.setText(JGDSBrowser.this.i18n.getString("JGDSPARSER_LOG" /* NOI18N */));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints.insets = new Insets(0, 10, 0, 0);
            gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            add(this.logLabel, gridBagConstraints);
            this.logField.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    processControlEvent(evt);
                }
            });
            this.logField.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent evt) {
                    processControlEvent(evt);
                }
            });
            this.logField.setColumns(48);
            this.logField.setToolTipText(JGDSBrowser.this.i18n.getString(
                    "JGDSPARSER_ENT_LOG" /* NOI18N */));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = 7;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.insets = new Insets(0, 10, 10, 0);
            gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            add(this.logField, gridBagConstraints);
            this.logButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (JGDSBrowserControlPanel.this.fc == null) {
                        JGDSBrowserControlPanel.this.fc = new JFileChooser();
                        JGDSBrowserControlPanel.this.fc.setFileSelectionMode(JGDSBrowserControlPanel.this.fc.FILES_ONLY);
                    }

                    JGDSBrowserControlPanel.this.fc.removeChoosableFileFilter(JGDSBrowserControlPanel.this.ff);
                    JGDSBrowserControlPanel.this.fc.setAcceptAllFileFilterUsed(true);

                    int result = JGDSBrowserControlPanel.this.fc.showDialog(null,
                            JGDSBrowser.this.i18n.getString(
                                    "JGDSPARSER_SEL_LOG" /* NOI18N */));

                    if (result == JGDSBrowserControlPanel.this.fc.APPROVE_OPTION) {
                        JGDSBrowserControlPanel.this.logField.setText(JGDSBrowserControlPanel.this.fc.getSelectedFile().getAbsolutePath());
                        processControlEvent(evt);
                    }
                }
            });
            this.logButton.setText("..." /*NOI18N*/);
            this.logButton.setToolTipText(JGDSBrowser.this.i18n.getString(
                    "JGDSPARSER_BROWSE_LOG" /* NOI18N */));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.insets = new Insets(0, 0, 10, 10);
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            add(this.logButton, gridBagConstraints);
            this.parseButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    processControlEvent(evt);
                }
            });
            this.exitButton.setMnemonic(KeyEvent.VK_C);
            this.exitButton.setText(JGDSBrowser.this.i18n.getString("JGDSBROWSER_STOP" /* NOI18N */));
            this.exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
            this.exitButton.setToolTipText(JGDSBrowser.this.i18n.getString(
                    "JGDSBROWSER_CANCEL" /* NOI18N */));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 8;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.insets = new Insets(0, 10, 10, 10);
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            add(this.exitButton, gridBagConstraints);
            this.parseButton.setMnemonic(KeyEvent.VK_L);
            this.parseButton.setText(JGDSBrowser.this.i18n.getString(
                    "JGDSBROWSER_START" /* NOI18N */));
            this.parseButton.setHorizontalTextPosition(SwingConstants.CENTER);
            this.parseButton.setToolTipText(JGDSBrowser.this.i18n.getString(
                    "JGDSBROWSER_LOAD" /* NOI18N */));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 8;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new Insets(0, 10, 0, 10);
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            add(this.parseButton, gridBagConstraints);
            this.exitButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    processControlEvent(evt);
                }
            });
        } // end method initComponents
    } // end class JGDSBrowserControlPanel

    /**
     * A JGDSParser specialization of the FileFilter interface.  Used to select
     * only GDS file extensions
     *
     * @author   $Author: tvaline $
     * @version  $Revision: 1.20 $, $Date: 2005/05/18 18:22:38 $
     */
    private final class JGDSBrowserFileFilter extends javax.swing.filechooser.FileFilter {

        /** A flag indicating if directories are selectable */
        private boolean allowDirs;

        /** A list of selectable file extensions */
        private String allowed[];

        /** A description of the file filter */
        private String description;

        /**
         * Creates a new JGDSBrowserFileFilter object.
         *
         * @param  desc       The file filter description
         * @param  allowed    A String array of allowable file extensions
         * @param  allowDirs  True if directories are allowed to be selected
         */
        private JGDSBrowserFileFilter(String desc, String allowed[], boolean allowDirs) {
            this.description = desc;
            this.allowed = allowed;
            this.allowDirs = allowDirs;
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
        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                if (this.allowDirs) {
                    return true;
                }
                else {
                    return false;
                }
            }

            String extension = getExtension(file);

            if (extension != null) {
                for (int i = 0; i < this.allowed.length; i++) {
                    if (extension.equals(this.allowed[i])) {
                        return true;
                    }
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
        @Override
        public String getDescription() {
            return this.description;
        }

        /**
         * Returns a string representation of the file filter
         *
         * @return  The physical address of the file filter instance
         */
        @Override
        public String toString() {
            return super.toString();
        }

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
    } // end class JGDSBrowserFileFilter

    /**
     * A JGDSParser specialization of the MouseListener interface
     *
     * @author   $Author: tvaline $
     * @version  $Revision: 1.20 $, $Date: 2005/05/18 18:22:38 $
     */
    private final class JGDSBrowserPaneMouseAdapter extends MouseAdapter {

        /**
         * A method that is executed when the mouse is pressed
         *
         * @param  evt  An event representing the mouse button press
         */
        @Override
        public void mousePressed(MouseEvent evt) {
            showPopup(evt);
        }

        /**
         * A method that is executed when the mouse is released
         *
         * @param  evt  An event representing the mouse button release
         */
        @Override
        public void mouseReleased(MouseEvent evt) {
            showPopup(evt);
        }

        /**
         * Returns a string representation of the object
         *
         * @return  The physical address of the instance
         */
        @Override
        public String toString() {
            return super.toString();
        }

        /**
         * A method to determine if a popup menu should be displayed
         *
         * @param  evt  The mouse event to evaluate
         */
        private void showPopup(MouseEvent evt) {
            if (JGDSBrowser.this.controlMenu == null) {
                JGDSBrowser.this.controlMenu = new JPopupMenu();

                JMenuItem item = JGDSBrowser.this.controlMenu.add(JGDSBrowser.this.i18n.getString(
                        "JGDSPARSER_OPEN_MENU" /* NOI18N */));
                item.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (JGDSBrowser.this.controlPanelFrame == null) {
                            JGDSBrowser.this.controlPanelFrame = new JFrame(JGDSBrowser.this.i18n.getString(
                                    "JGDSBROWSER_OPTIONS" /* NOI18N */));
                            JGDSBrowser.this.controlPanelFrame.setLocationByPlatform(true);
                            JGDSBrowser.this.controlPanelFrame.getContentPane().add(
                                    JGDSBrowser.this.controlPanel);
                            JGDSBrowser.this.controlPanelFrame.setDefaultCloseOperation(
                                    JFrame.HIDE_ON_CLOSE);
                            JGDSBrowser.this.controlPanelFrame.setResizable(false);
                        }
                        else {
                            JGDSBrowser.this.controlPanelFrame.setLocationByPlatform(false);
                        }
                        JGDSBrowser.this.controlPanelFrame.pack();
                        JGDSBrowser.this.controlPanelFrame.setVisible(true);
                    }
                });
            }

            if (evt.isPopupTrigger()) {
                JGDSBrowser.this.controlMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        } // end method showPopup
    } // end class JGDSBrowserPaneMouseAdapter
} // end class JGDSBrowser

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
