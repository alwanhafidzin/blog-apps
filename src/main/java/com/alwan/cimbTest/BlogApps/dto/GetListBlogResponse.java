package com.alwan.cimbTest.BlogApps.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetListBlogResponse implements Serializable {
    private Boolean isSuccess;
    private List<GetBlogResponseDto> data;
    private Long totalPages;
    private Long totalElements;
    private Long numberOfElements;
}
