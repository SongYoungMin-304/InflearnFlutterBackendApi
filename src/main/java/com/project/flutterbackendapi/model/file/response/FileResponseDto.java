package com.project.flutterbackendapi.model.file.response;

import lombok.Builder;

@Builder
public class FileResponseDto {

    private Long id;

    private String origFilename;

    private String fileName;

    private String filePath;

}
