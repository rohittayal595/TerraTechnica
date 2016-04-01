package xyz.iiemyewrs.www.technica.instagram;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Developer: Rohit Tayal
 * Package : xyz.iiemyewrs.www.ic_insta.instagram
 */

public class Images {

    @SerializedName("low_resolution")
    @Expose
    private LowResolution lowResolution;
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("standard_resolution")
    @Expose
    private StandardResolution standardResolution;

    /**
     * @return The lowResolution
     */
    public LowResolution getLowResolution() {
        return lowResolution;
    }

    /**
     * @param lowResolution The low_resolution
     */
    public void setLowResolution(LowResolution lowResolution) {
        this.lowResolution = lowResolution;
    }

    /**
     * @return The thumbnail
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail The thumbnail
     */
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return The standardResolution
     */
    public StandardResolution getStandardResolution() {
        return standardResolution;
    }

    /**
     * @param standardResolution The standard_resolution
     */
    public void setStandardResolution(StandardResolution standardResolution) {
        this.standardResolution = standardResolution;
    }

}
