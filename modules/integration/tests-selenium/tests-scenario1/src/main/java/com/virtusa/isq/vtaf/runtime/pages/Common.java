package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class Common implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum Common {

        win_Index("index=<param_WinNo>"), lbl_NotificationWallMsg("//div[@id='notificationDataDiv']/div/div[2]/div[contains(text(),'<param_Message>')]"), lbl_BuildNotificationMsgForked("//div[@id='notificationDataDiv']/div/div[2]/div[contains(text(),'<param_VersionNumber> forked repo built successfully')]/span[@data-tooltip='Build ID : <param_BuildNumber>']"), lbl_BuildNotificationMsgMaster("//div[@id='notificationDataDiv']/div/div[2]/div[contains(text(),'<param_VersionNumber> master repo built successfully')]/span[@data-tooltip='Build ID : <param_BuildNumber>']"), btn_MinimizeButton("//div[@id='notification-slider-btn']"), ele_SlideBar("//div[@id='notificationWrapper']");

    private String searchPath;
  
    /**
    *  Page Common.
    */
    private Common(final String psearchPath) {
        this.searchPath = psearchPath;
    }
    
    /**
     *  Get search path.
     * @param searchPath search path.
     */
    public final String getSearchPath() {
        return searchPath;
    }
}