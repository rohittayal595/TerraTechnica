package xyz.iiemyewrs.www.technica.instagram;

import com.google.gson.annotations.Expose;

/**
 * Developer: Rohit Tayal
 * Package : xyz.iiemyewrs.www.ic_insta.instagram
 */

public class Meta {

    @Expose
    private Integer code;

    /**
     * @return The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

}
