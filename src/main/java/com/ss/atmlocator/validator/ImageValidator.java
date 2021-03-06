package com.ss.atmlocator.validator;

import com.ss.atmlocator.utils.Constants;
import com.ss.atmlocator.utils.UploadedFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 *
 */
@Service
public class ImageValidator implements Validator {

    private final int MAX_FILE_SIZE = 716800; //700kb
    private static final List<String> EXTENSIONS = Arrays.asList("jpg", "jpeg", "png","gif");

    @Autowired
    private MessageSource messages;

    /**
     * Uses for checking supported classes
     * @param Clazz validated object
     * @return true if class is supported
     */
    @Override
    public boolean supports(Class<?> Clazz) {
        return UploadedFile.class.isAssignableFrom(Clazz);
    }

    /**
     * Rejects the filed errors into map <b>errors</b>
     * @param object validated image
     * @param errors map of field errors
     */
    @Override
    public void validate(Object object, Errors errors) {
        UploadedFile file = (UploadedFile) object;
        MultipartFile image = file.getFile();
        if (null == image) return;
        if (image.getSize() > MAX_FILE_SIZE) {
            errors.reject(messages.getMessage("file.size.limit", null, Locale.ENGLISH));
            return;
        } else if (!isValidExtension(image)) {
            errors.reject(messages.getMessage("file.extension", null, Locale.ENGLISH));
        }
    }

    /**
     * Returns true if image is valid
     * @param image Multipart file
     * @return
     */
    private boolean isValidExtension(MultipartFile image) {
        String extension = FilenameUtils.getExtension(image.getOriginalFilename());
        return EXTENSIONS.contains(extension);
    }
}
