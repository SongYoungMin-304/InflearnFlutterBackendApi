package com.project.flutterbackendapi.service;

import com.project.flutterbackendapi.model.file.response.FileResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class FileService {

    public List<FileResponseDto> uploadImages(List<MultipartFile> multipartFileList) throws Exception {

        if (!CollectionUtils.isEmpty(multipartFileList)) {

            String currentDate = LocalDate.now().toString();
            String absolutePath = new File("").getAbsolutePath() + File.separator;
            String path = absolutePath + "images" + File.separator + currentDate;

            File file = new File(path);
            if (!file.exists()) {
                boolean created = file.mkdirs();
                if (!created) {
                    throw new IOException("Failed to create directory: " + path);
                }
            }

            for (MultipartFile multipartFile : multipartFileList) {

                String originalFileExtension;

                String contentType = multipartFile.getContentType();

                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                    if (contentType.contains("image/jpg"))
                        originalFileExtension = ".jpg";
                    else if (contentType.contains("image/jpeg"))
                        originalFileExtension = ".jpeg";
                    else if (contentType.contains("image/png"))
                        originalFileExtension = ".png";
                    else  // 다른 확장자일 경우 처리 x
                        break;
                }

                String new_file_name = System.nanoTime() + originalFileExtension;

                FileResponseDto fileDto = FileResponseDto.builder()
                        .origFilename(multipartFile.getOriginalFilename())
                        .fileName(new_file_name)
                        .filePath(path + File.separator + new_file_name)
                        .build();


                // 업로드 한 파일 데이터를 지정한 파일에 저장
                file = new File(absolutePath + path + File.separator + new_file_name);
                multipartFile.transferTo(file);

                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);

            }

        }

        return null;
    }

}

