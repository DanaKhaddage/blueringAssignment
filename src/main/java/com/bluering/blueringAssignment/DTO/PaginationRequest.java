package com.bluering.blueringAssignment.DTO;

import lombok.Data;
import java.util.List;

@Data
public class PaginationRequest {
    private List<LeaveeDTO> items;
    private long totalItems;
    private int currentPage;
    private int totalPages;
    private int pageSize;

    public PaginationRequest(List<LeaveeDTO> items, long totalItems, int currentPage, int pageSize) {
        this.items = items;
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
    }
}