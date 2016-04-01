package xyz.iiemyewrs.www.technica.instagram;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Rohit Tayal
 * Package : xyz.iiemyewrs.www.ic_insta.instagram
 */

public class InstaFeed {

    @Expose
    private Pagination pagination;
    @Expose
    private Meta meta;
    @Expose
    private List<InstagramDatum> data = new ArrayList<>();

    /**
     * @return The pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * @param pagination The pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     * @return The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * @param meta The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * @return The data
     */
    public List<InstagramDatum> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<InstagramDatum> data) {
        this.data = data;
    }

}
