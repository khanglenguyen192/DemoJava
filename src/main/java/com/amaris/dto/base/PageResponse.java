package com.amaris.dto.base;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
public class PageResponse<T> {
    private int pageNo;
    private int pageSize;
    private int pageTotal;
    private List<T> repsonseData;

    public PageResponse(List<T> data, Pageable page, int pageTotal)
    {
        this.repsonseData = data;
        this.pageNo = page.getPageNumber();
        this.pageSize = page.getPageSize();
        this.pageTotal = pageTotal;
    }
}
