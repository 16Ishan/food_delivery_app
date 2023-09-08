package com.delivery.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public abstract class PagedResult<T> {
    @NonNull public List<T> list;
    @NonNull public PageInfo pageInfo;
}
