package com.alwan.cimbTest.BlogApps.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddBlogRequest implements Serializable {
    private String title;
    private String content;
    private String category;
}
