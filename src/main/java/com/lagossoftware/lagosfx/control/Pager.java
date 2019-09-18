/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Pager control
 *
 * @author Cem Ikta
 */
public class Pager extends HBox {

    private Button btnFirst;
    private Button btnPrevious;
    private Label lblPages;
    private Button btnNext;
    private Button btnLast;
    private Button btnRefresh;
    private Label lblRecordsFound;

    private int rowCount = 0;
    private int pageCount = 0;
    private int itemsPerPage;
    private int currentPageIndex = 1;
    private PagerRefreshCallback pagerRefreshCallback;

    private static final int DEFAULT_ITEMS_PER_PAGE = 20;

    public Pager() {
        this(DEFAULT_ITEMS_PER_PAGE);
    }
    
    /**
     * Pager with items per page.
     *
     * @param itemsPerPage items per page for pager
     */
    public Pager(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        buildView();
    }

    private void buildView() {
        btnFirst = ViewHelpers.createIconButton(Images.FIRST_16);
        btnFirst.setTooltip(new Tooltip(I18n.COMMON.getString("pager.hint.firstPage")));
        btnFirst.getStyleClass().add("left-pill");
        btnFirst.setOnAction(( ActionEvent e) -> {
            onFirstPage();
        });

        btnPrevious = ViewHelpers.createIconButton(Images.PREVIOUS_16);
        btnPrevious.setTooltip(new Tooltip(I18n.COMMON.getString("pager.hint.previousPage")));
        btnPrevious.getStyleClass().add("center-pill");
        btnPrevious.setOnAction(( ActionEvent e) -> {
            onPreviousPage();
        });
        
        lblPages = new Label();
        lblPages.getStyleClass().addAll("button", "center-pill");

        btnNext = ViewHelpers.createIconButton(Images.NEXT_16);
        btnNext.setTooltip(new Tooltip(I18n.COMMON.getString("pager.hint.nextPage")));
        btnNext.getStyleClass().add("center-pill");
        btnNext.setOnAction(( ActionEvent e) -> {
            onNextPage();
        });

        btnLast = ViewHelpers.createIconButton(Images.LAST_16);
        btnLast.setTooltip(new Tooltip(I18n.COMMON.getString("pager.hint.lastPage")));
        btnLast.getStyleClass().add("right-pill");
        btnLast.setOnAction(( ActionEvent e) -> {
            onLastPage();
        });

        btnRefresh = ViewHelpers.createIconButton(Images.REFRESH_16);
        btnRefresh.setTooltip(new Tooltip(I18n.COMMON.getString("pager.hint.refresh")));
        btnRefresh.setOnAction((ActionEvent e) -> {
            callRefreshCallback();
        });
        HBox.setMargin(btnRefresh, new Insets(0, 10, 0, 10));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        lblRecordsFound = new Label();
        HBox.setMargin(lblRecordsFound, new Insets(5, 0, 0, 0));

        getChildren().addAll(btnFirst, btnPrevious, lblPages,
                btnNext, btnLast, btnRefresh, spacer, lblRecordsFound);
    }

    /**
     * Pager first page action.
     */
    private void onFirstPage() {
        if (getCurrentPageIndex() > 1) {
            setCurrentPageIndex(1);
        }
    }

    /**
     * Pager previous page action.
     */
    private void onPreviousPage() {
        if (getCurrentPageIndex() > 1) {
            setCurrentPageIndex(getCurrentPageIndex() - 1);
        }
    }

    /**
     * Pager next page action.
     */
    private void onNextPage() {
        if (getCurrentPageIndex() < getPageCount()) {
            setCurrentPageIndex(getCurrentPageIndex() + 1);
        }
    }

    /**
     * Pager last page action.
     */
    private void onLastPage() {
        if (getCurrentPageIndex() < getPageCount()) {
            setCurrentPageIndex(getPageCount());
        }
    }
    
    /**
     * Gets current page index of pager
     * 
     * @return current page index
     */
    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    /**
     * Sets current page index of pager.
     * 
     * @param currentPageIndex index value to set
     */
    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
        callRefreshCallback();
    }
    
    private void callRefreshCallback() {
        if (pagerRefreshCallback != null) {
            pagerRefreshCallback.onRefresh();
        }
    }

    /**
     * Gets row count for pager.
     *
     * @return row count of pager.
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Sets row count for pager.
     *
     * @param rowCount row count of pager.
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        
        pageCount = (int) Math.ceil((double) (rowCount / itemsPerPage));
        double remainder = (rowCount % itemsPerPage);

        if (remainder > 0) {
            pageCount += 1;
        }

        setPagerValues();
    }

    /**
     * Gets page count for pager.
     *
     * @return page count of pager.
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Sets pager label values. 
     */
    private void setPagerValues() {
        lblPages.setText(I18n.COMMON.getString("pager.page", 
                getCurrentPageIndex(), getPageCount()));
        
        if (getRowCount() > 1) {
            lblRecordsFound.setText(I18n.COMMON.getString("pager.recordsFound", 
                    getRowCount()));
        } else {
            lblRecordsFound.setText(I18n.COMMON.getString("pager.recordFound", 
                    getRowCount()));
        }
    }
    
    /**
     * Gets refresh callback of pager control
     * 
     * @return pager refresh callback
     */
    public PagerRefreshCallback getRefreshCallback() {
        return pagerRefreshCallback;
    }

    /**
     * Sets refresh callback of pager control
     * 
     * @param pagerRefreshCallback pager refresh callback 
     */
    public void setRefreshCallback(PagerRefreshCallback pagerRefreshCallback) {
        this.pagerRefreshCallback = pagerRefreshCallback;
    }
    
    /**
     * Gets start position of pager for service query.
     * 
     * @return pager start position 
     */
    public int getStartPosition() {
        int start = ((itemsPerPage * getCurrentPageIndex()) - itemsPerPage);
        return start;
    }

    /**
     * Gets end position of pager for service query.
     * 
     * @return pager end position
     */
    public int getEndPosition() {
	int end = getStartPosition() + itemsPerPage;
        return end;
    }

}
