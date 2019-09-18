/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import com.lagossoftware.ibbfx.app.I18n;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * MessageBox control
 *
 * @author Cem Ikta
 */
public final class MessageBox {

    private Alert.AlertType type;
    private String title;
    private String headerText;
    private String contentText;
    private Throwable exception;
    private Set<ButtonType> actions = new LinkedHashSet<>();


    /**
     * Creates new instance of MessageBox.
     *
     * @return message box.
     */
    public static MessageBox get() {
        return new MessageBox();
    }

    /**
     * Title property
     *
     * @param title The title.
     * @return message box.
     */
    public MessageBox title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Header text property.
     *
     * @param headerText The header text.
     * @return message box.
     */
    public MessageBox headerText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    /**
     * Content text property.
     *
     * @param contentText The content text.
     * @return message box.
     */
    public MessageBox contentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    /**
     * Shows information message box.
     */
    public void showInformation() {
        this.type = Alert.AlertType.INFORMATION;
        show();
    }

    /**
     * Shows warning message box.
     */
    public void showWarning(){
        this.type = Alert.AlertType.WARNING;
        show();
    }

    /**
     * Shows error message box.
     */
    public void showError(){
        this.type = Alert.AlertType.ERROR;
        show();
    }

    /**
     * Shows error message box with exception.
     */
    public void showError(Throwable exception){
        this.type = Alert.AlertType.ERROR;
        this.exception = exception;
        show();
    }

    /**
     * Shows confirmation message box with yes, no, cancel actions.
     *
     * @return result as optional clicked action.
     */
    public Optional<ButtonType> showConfirmation(){
        return showConfirmation(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    }

    /**
     * Shows confirmation message box with specified actions.
     *
     * @param actions The confirmation actions.
     * @return result as optional clicked action.
     */
    public Optional<ButtonType> showConfirmation(ButtonType... actions){
        this.type = Alert.AlertType.CONFIRMATION;
        this.actions.clear();
        this.actions.addAll(Arrays.asList(actions));
        return confirmation();
    }

    private void show() {
        Alert alert = createAlert();

        if (type == Alert.AlertType.ERROR && exception != null) {
            // TODO layout bug with expandable pane only on Linux?
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            String exceptionText = sw.toString();
            pw.close();

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setVisible(false);
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(textArea, 0, 0);

            alert.getDialogPane().setExpandableContent(expContent);
        }

        alert.showAndWait();
    }

    private Optional<ButtonType> confirmation() {
        Alert alert = createAlert();
        alert.getButtonTypes().setAll(actions);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    private Alert createAlert() {
        Alert alert = new Alert(type);

        if (StringUtils.isEmpty(title)) {
            switch (type) {
                case NONE: {
                    break;
                }
                case INFORMATION: {
                    title = I18n.CONTROL.getString("messageBox.information.title");
                    break;
                }
                case WARNING: {
                    title = I18n.CONTROL.getString("messageBox.warning.title");
                    break;
                }
                case ERROR: {
                    title = I18n.CONTROL.getString("messageBox.error.title");
                    break;
                }
                case CONFIRMATION: {
                    title = I18n.CONTROL.getString("messageBox.confirmation.title");
                    break;
                }
            }
        }

        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

}