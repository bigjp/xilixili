package com.xilixili.pojo;

import lombok.Data;

@Data
public class EsSearchVideo {
    private String esIndex;
    private String esType;
    private String esContent;
    private String queryName;
    private String esOrderName;
    private Integer currentPage;
    private Integer rowsPage;
    private String[] sourceNames;
}
