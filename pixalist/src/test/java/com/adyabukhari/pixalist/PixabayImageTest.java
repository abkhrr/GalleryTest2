package com.adyabukhari.pixalist;

import com.adyabukhari.pixalist.Model.PixabayImage;
import com.adyabukhari.pixalist.Model.PixabayImageType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PixabayImageTest {
    private PixabayImage pixabayImage;

    @Before
    public void init() {
        pixabayImage = new PixabayImage("http://page.com", "http://preview.com", PixabayImageType.illustration );
    }

    @Test
    public void getPageURL() throws Exception {
        assertEquals(pixabayImage.getPageURL(), "http://page.com");
    }

    @Test
    public void getPreviewURL() throws Exception {
        assertEquals(pixabayImage.getPreviewURL(), "http://preview.com");
    }

    @Test
    public void getType() throws Exception {
        assertEquals(pixabayImage.getImageType(), PixabayImageType.illustration);
    }

}