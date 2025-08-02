package org.acme.application.domain.output;

import java.util.List;

public class PaginationOutput<T> {
    private List<T> items;
    private long totalItems;
    private int totalPages;
    private int currentPage;
    private int pageSize;

    public PaginationOutput(List<T> items, long totalItems, int totalPages, int currentPage, int pageSize) {
        this.items = items;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public List<T> getItems() { return items; }
    public long getTotalItems() { return totalItems; }
    public int getTotalPages() { return totalPages; }
    public int getCurrentPage() { return currentPage; }
    public int getPageSize() { return pageSize; }
}
