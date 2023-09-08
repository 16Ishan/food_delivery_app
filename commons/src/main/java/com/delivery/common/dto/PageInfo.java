package com.delivery.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class PageInfo {

    private final Long totalCount;
    private final Integer currentPage;
    private final String sortBy;
    private final Integer pageSize;
    private final Integer nextPage;
    private final Integer lastPage;
    private final Integer firstPage;

    public static <T> PageInfo of(Page<T> page) {
        return new PageInfo(
                page.getTotalElements(),
                page.getNumber(),
                page.getSort().toString(),
                page.getContent().size(),
                (page.hasNext()) ? page.getNumber() + 1 : page.getNumber(),
                (page.getTotalPages() > 0) ? page.getTotalPages() - 1 : 0,
                0
        );
    }
}
