package xyz.iiemyewrs.www.technica.instagram;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Developer: Rohit Tayal
 * Package : xyz.iiemyewrs.www.ic_insta.instagram
 */

public class From {

    @Expose
    private String username;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @Expose
    private String id;
    @SerializedName("full_name")
    @Expose
    private String fullName;

    /**
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The profilePicture
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * 
     * @param profilePicture
     *     The profile_picture
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName
     *     The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
