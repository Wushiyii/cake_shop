//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.common.response;

import java.util.List;

public class TableResultResponse<T> extends BaseResponse {
    TableResultResponse<T>.TableData<T> data;

    public TableResultResponse(long total, List<T> rows) {
        this.data = new TableResultResponse.TableData(total, rows);
    }

    public TableResultResponse() {
        this.data = new TableResultResponse.TableData();
    }

    TableResultResponse<T> total(long total) {
        this.data.setTotal(total);
        return this;
    }

    TableResultResponse<T> rows(List<T> rows) {
        this.data.setRows(rows);
        return this;
    }

    public TableResultResponse<T>.TableData<T> getData() {
        return this.data;
    }

    public void setData(TableResultResponse<T>.TableData<T> data) {
        this.data = data;
    }

    public class TableData<T> {
        long total;
        List<T> rows;

        public TableData(long total, List<T> rows) {
            this.total = total;
            this.rows = rows;
        }

        public TableData() {
        }

        public long getTotal() {
            return this.total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public List<T> getRows() {
            return this.rows;
        }

        public void setRows(List<T> rows) {
            this.rows = rows;
        }
    }
}
