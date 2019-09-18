/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.common;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.lagosfx.control.MessageBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.view.save.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Report Helpers
 *
 * @author Cem Ikta
 */
public class ReportHelpers {

    public ReportHelpers() {
    }

    /**
     * Jasper Report show report viewer or print report with print dialog.
     *
     * @param reportName  report file name
     * @param reportTitle report title
     * @param query       report's query
     * @param preview     preview as jasper report viewer or print dialog
     * @param mapParams   report parameters
     * @param connection  db connection for report
     */
    public static void report(String reportName, String reportTitle, String query, boolean preview,
                              HashMap<String, Object> mapParams, Connection connection) {
        Statement stmt = null;
        ResultSet resultset = null;
        try {
            stmt = connection.createStatement();
            resultset = stmt.executeQuery(query);
        } catch (SQLException ex) {
            MessageBox.get()
                    .contentText(I18n.COMMON.getString("reports.query.error"))
                    .showError(ex);
            return;
        }

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport("reports/" + reportName,
                    mapParams, new JRResultSetDataSource(resultset));
            if (preview) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(reportTitle);
                jasperViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jasperViewer.setExtendedState(6);
                jasperViewer.setZoomRatio(1);
                jasperViewer.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
                setSaveContributors(jasperViewer);
                jasperViewer.setVisible(true);
            } else {
                JasperPrintManager.printReport(jasperPrint, true);
            }
        } catch (JRException ex) {
            MessageBox.get()
                    .contentText(I18n.COMMON.getString("reports.runtime.error"))
                    .showError(ex);
        }
    }

    /**
     * Hack the jasper viewer save file filters
     *
     * @param jasperViewer jasper viewer instance
     */
    private static void setSaveContributors(JasperViewer jasperViewer) {
        Field jrViewerField;

        try {
            jrViewerField = jasperViewer.getClass().getDeclaredField("viewer");
            jrViewerField.setAccessible(true);
            JRViewer jrViewer = (JRViewer) jrViewerField.get(jasperViewer);
            JRSaveContributor[] contributors = jrViewer.getSaveContributors();

            for (JRSaveContributor saveContributor : contributors) {
                if (!(saveContributor instanceof JRPdfSaveContributor ||
                        saveContributor instanceof JRRtfSaveContributor ||
                        saveContributor instanceof JRHtmlSaveContributor ||
                        saveContributor instanceof JRSingleSheetXlsSaveContributor ||
                        saveContributor instanceof JRCsvSaveContributor )) {

                    jrViewer.removeSaveContributor(saveContributor);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
