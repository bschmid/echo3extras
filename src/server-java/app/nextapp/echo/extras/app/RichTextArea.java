/* 
 * This file is part of the Echo Extras Project.
 * Copyright (C) 2005-2009 NextApp, Inc.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

package nextapp.echo.extras.app;

import nextapp.echo.app.Border;
import nextapp.echo.app.Component;
import nextapp.echo.app.event.DocumentEvent;
import nextapp.echo.app.event.DocumentListener;
import nextapp.echo.app.text.Document;
import nextapp.echo.app.text.StringDocument;

/**
 * RichTextArea component: a rich text user input field which allows the user to
 * select text styles, and insert/manipulate objects such as links, images, enumerated
 * lists, or tables.  This component may not contain children.
 * 
 * Security warning: HTML input provided by this component should be considered
 * potentially malicious. Directly rendering the HTML entered by one user to
 * other users of a multi-user application without first "cleaning" it could be
 * disastrous to the other users. For example, a user could potentially embed
 * JavaScript code in URLs that would execute in the other users' browsers. Any
 * cleaning operations must be performed on the client that will render such
 * HTML (not the client sending it) or on a central trusted server.
 */
public class RichTextArea extends Component {

    public static final String ICON_ALIGNMENT = "alignment";
    public static final String ICON_ALIGNMENT_CENTER = "alignmentCenter";
    public static final String ICON_ALIGNMENT_JUSTIFY = "alignmentJustify";
    public static final String ICON_ALIGNMENT_LEFT = "alignmentLeft";
    public static final String ICON_ALIGNMENT_RIGHT = "alignmentRight";
    public static final String ICON_BACKGROUND = "background";
    public static final String ICON_BOLD = "bold";
    public static final String ICON_BULLETED_LIST = "bulletedList";
    public static final String ICON_CANCEL = "cancel";
    public static final String ICON_COPY = "copy";
    public static final String ICON_CUT = "cut";
    public static final String ICON_DELETE = "delete";
    public static final String ICON_FOREGROUND = "foreground";
    public static final String ICON_FORMAT_PARAGRAPH_STYLE = "paragraphStyle";
    public static final String ICON_FORMAT_TEXT_STYLE = "textStyle";
    public static final String ICON_HORIZONTAL_RULE = "horizontalRule";
    public static final String ICON_HYPERLINK = "hyperlink";
    public static final String ICON_IMAGE = "image";
    public static final String ICON_INDENT = "indent";
    public static final String ICON_ITALIC = "italic";
    public static final String ICON_NUMBERED_LIST = "numberedList";
    public static final String ICON_OK = "ok";
    public static final String ICON_OUTDENT = "outdent";
    public static final String ICON_PARAGRAPH_STYLE_HEADING_1 = "styleH1";
    public static final String ICON_PARAGRAPH_STYLE_HEADING_2 = "styleH2";
    public static final String ICON_PARAGRAPH_STYLE_HEADING_3 = "styleH3";
    public static final String ICON_PARAGRAPH_STYLE_HEADING_4 = "styleH4";
    public static final String ICON_PARAGRAPH_STYLE_HEADING_5 = "styleH5";
    public static final String ICON_PARAGRAPH_STYLE_HEADING_6 = "styleH6";
    public static final String ICON_PARAGRAPH_STYLE_NORMAL = "styleNormal";
    public static final String ICON_PARAGRAPH_STYLE_PREFORMATTED = "stylePreformatted";
    public static final String ICON_PASTE = "paste";
    public static final String ICON_PLAIN_TEXT = "plainText";
    public static final String ICON_REDO = "redo";
    public static final String ICON_SELECT_ALL = "selectAll";
    public static final String ICON_STRIKETHROUGH = "strikethrough";
    public static final String ICON_SUBSCRIPT = "subscript";
    public static final String ICON_SUPERSCRIPT = "superscript";
    public static final String ICON_TABLE = "table";
    public static final String ICON_UNDERLINE = "underline";
    public static final String ICON_UNDO = "undo";

    public static final String DOCUMENT_CHANGED_PROPERTY = "document";

    public static final String PROPERTY_BORDER = "border";
    public static final String PROPERTY_CONTROL_PANE_BUTTON_STYLE_NAME = "controlPaneButtonStyleName";
    public static final String PROPERTY_CONTROL_PANE_ROW_STYLE_NAME = "controlPaneRowStyleName";

    public static final String PROPERTY_CONTROL_PANE_SPLIT_PANE_STYLE_NAME = "controlPaneSplitPaneStyleName";
    public static final String PROPERTY_ICONS = "icons";
    public static final String PROPERTY_MENU_STYLE_NAME = "menuStyleName";
    public static final String PROPERTY_TOOLBAR_BUTTON_STYLE_NAME = "toolbarButtonStyleName";
    public static final String PROPERTY_TOOLBAR_PANEL_STYLE_NAME = "toolbarPanelStyleName";
    public static final String PROPERTY_WINDOW_PANE_STYLE_NAME = "windowPaneStyleName";
    public static final String TEXT_CHANGED_PROPERTY = "text";
    private Document document;
    
    /**
     * Local listener to monitor changes to document.
     */
    private DocumentListener documentListener = new DocumentListener() {

        /** Serial Version UID. */
        private static final long serialVersionUID = 20070101L;
    
        /**
         * @see nextapp.echo.app.event.DocumentListener#documentUpdate(nextapp.echo.app.event.DocumentEvent)
         */
        public void documentUpdate(DocumentEvent e) {
            firePropertyChange(TEXT_CHANGED_PROPERTY, null, ((Document) e.getSource()).getText());
        }
    };
    
    /**
     * Creates a new <code>RichTextArea</code> with an empty 
     * <code>StringDocument</code> as its model.
     */
    public RichTextArea() {
        this(new StringDocument());
    }

    /**
     * Creates a new <code>RichTextArea</code> with the specified
     * <code>Document</code> as its model.
     * 
     * @param document the desired model
     */
    public RichTextArea(Document document) {
        super();
        setDocument(document);
    }

    /**
     * Returns the border surrounding the text entry area.
     * 
     * @return the border
     */
    public Border getBorder() {
        return (Border) get(PROPERTY_BORDER);
    }
    
    public String getControlPaneButtonStyleName() {
        return (String) get(PROPERTY_CONTROL_PANE_BUTTON_STYLE_NAME);
    }
    
    public String getControlPaneRowStyleName() {
        return (String) get(PROPERTY_CONTROL_PANE_ROW_STYLE_NAME);
    }
    
    public String getControlPaneSplitPaneStyleName() {
        return (String) get(PROPERTY_CONTROL_PANE_SPLIT_PANE_STYLE_NAME);
    }
    
    /**
     * Returns the model associated with this <code>RichTextArea</code>.
     * 
     * @return the model
     */
    public Document getDocument() {
        return document;
    }
    
    public IconSet getIcons() {
        return (IconSet) get(PROPERTY_ICONS);
    }
    
    public String getMenuStyleName() {
        return (String) get(PROPERTY_MENU_STYLE_NAME);
    }
    
    /**
     * Returns the text contained in the <code>Document</code> model of
     * this rich text area.
     * Note that this HTML is not "cleaned" in any way, and could potentially
     * contain malicious code.
     * 
     * @return the text contained in the document
     */
    public String getText() {
        return document.getText();
    }
    
    public String getToolbarButtonStyleName() {
        return (String) get(PROPERTY_TOOLBAR_BUTTON_STYLE_NAME);
    }
    
    public String getToolbarPanelStyleName() {
        return (String) get(PROPERTY_TOOLBAR_PANEL_STYLE_NAME);
    }
    
    public String getWindowPaneStyleName() {
        return (String) get(PROPERTY_WINDOW_PANE_STYLE_NAME);
    }
    
    /**
     * @see nextapp.echo.app.Component#processInput(java.lang.String, java.lang.Object)
     */
    public void processInput(String inputName, Object inputValue) {
        super.processInput(inputName, inputValue);
        
        if (TEXT_CHANGED_PROPERTY.equals(inputName)) {
            setText((String) inputValue);
        }
    }
    
    /**
     * Sets the border displayed around the text entry area.
     * 
     * @param newValue the new border
     */
    public void setBorder(Border newValue) {
        set(PROPERTY_BORDER, newValue);
    }

    public void setControlPaneButtonStyleName(String newValue) {
        set(PROPERTY_CONTROL_PANE_BUTTON_STYLE_NAME, newValue);
    }

    public void setControlPaneRowStyleName(String newValue) {
        set(PROPERTY_CONTROL_PANE_ROW_STYLE_NAME, newValue);
    }
    
    public void setControlPaneSplitPaneStyleName(String newValue) {
        set(PROPERTY_CONTROL_PANE_SPLIT_PANE_STYLE_NAME, newValue);
    }
    
    /**
     * Sets the model associated with this <code>RichTextArea</code>.
     * 
     * @param newValue the new model (may not be null)
     */
    public void setDocument(Document newValue) {
        if (newValue == null) {
            throw new IllegalArgumentException("Document may not be null.");
        }
        Document oldValue = getDocument();
        if (oldValue != null) {
            oldValue.removeDocumentListener(documentListener);
        }
        newValue.addDocumentListener(documentListener);
        document = newValue;
    }
    
    public void setIcons(IconSet newValue) {
        set(PROPERTY_ICONS, newValue);
    }
    
    public void setMenuStyleName(String newValue) {
        set(PROPERTY_MENU_STYLE_NAME, newValue);
    }
    
    /**
     * Sets the text of document model of this rich text area.
     * 
     * @param newValue the new text
     */
    public void setText(String newValue) {
        getDocument().setText(newValue);
    }
    
    public void setToolbarButtonStyleName(String newValue) {
        set(PROPERTY_TOOLBAR_BUTTON_STYLE_NAME, newValue);
    }

    public void setToolbarPanelStyleName(String newValue) {
        set(PROPERTY_TOOLBAR_PANEL_STYLE_NAME, newValue);
    }

    public void setWindowPaneStyleName(String newValue) {
        set(PROPERTY_WINDOW_PANE_STYLE_NAME, newValue);
    }
}
