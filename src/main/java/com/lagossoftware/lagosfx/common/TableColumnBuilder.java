/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.common;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Table Column Builder.
 *
 * @author Cem Ikta
 */
public class TableColumnBuilder {
    
    protected TableColumnBuilder() {
    }

    /**
     * Creates new @see {@link TableColumn<S, T>}
     * 
     * @param <S> pojo class type
     * @param <T> column data type
     * @param fieldName field name property
     * @param title title property
     * @param prefWidth preferred width property
     * @return table column 
     */
    public static <S, T> TableColumn<S, T> create(String fieldName, 
            String title, double prefWidth) {
        
        return create(fieldName, title, prefWidth, true, true, true, true);
    }
    
    /**
     * Creates new @see {@link TableColumn<S, T>}
     * 
     * @param <S> pojo class type
     * @param <T> column data type
     * @param fieldName field name property
     * @param title title property
     * @param prefWidth preferred width property
     * @param hasCellValueFactory cell value factory column property
     * @param sortable sortable property
     * @param resizable resizable property 
     * @param visible visible property
     * @return table column 
     */
    public static <S, T> TableColumn<S, T> create(String fieldName, String title,
            double prefWidth, boolean hasCellValueFactory, boolean sortable, 
            boolean resizable, boolean visible) {
        
        TableColumn<S, T> column = new TableColumn<>(title);
        if (hasCellValueFactory) {
            column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
        }
        column.setPrefWidth(prefWidth);
        column.setSortable(sortable);
        column.setResizable(resizable);
        column.setVisible(visible);
        
        return column;
    }

}
