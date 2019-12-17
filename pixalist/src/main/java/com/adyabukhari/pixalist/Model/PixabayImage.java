package com.adyabukhari.pixalist.Model;

/**
 * Created by Abkhrr on 17/12/2019.
 *
 * Email: bukhariadbuk@gmail.com
 * GitHub: https://github.com/abkhrr
 *
 */

public class PixabayImage {
    private String pageURL;
    private String previewURL;
    private PixabayImageType imageType;

    public PixabayImage(String pageURL, String previewURL, PixabayImageType imageType) {

        this.pageURL = pageURL;
        this.previewURL = previewURL;
        this.imageType = imageType;
    }


    public String getPageURL() {
        return pageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public PixabayImageType getImageTypeType() {
        return imageType;
    }
}
