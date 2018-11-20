package com.osl.exception;

/**
 * 应用程序异常
 */
public class ApplException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8598147433343381457L;

	private String params[];

    private String msgId;

    private String errItems;

    private int andResMsgId;

    public ApplException() {
    }

    /**
     *
     * @param cause
     */
    public ApplException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param msgId
     */
    public ApplException(String msgId) {
        super(msgId);
        this.msgId = msgId;
    }

    /**
     *
     * @param msgId
     * @param params
     */
    public ApplException(String msgId, String... params) {
        super(msgId);
        this.msgId = msgId;
        this.params = params;
    }

    /**
     *
     * @param msgId
     * @param params
     */
    public ApplException(int andResMsgId, String... params) {
        this.andResMsgId = andResMsgId;
        this.params = params;
    }

    /**
     *
     */
    public void setParams(String... params) {
        this.params = params;
    }

    /**
     *
     */
    public String[] getParams() {
        return params;
    }

    /**
     *
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     *
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     *
     */
    public int getAndResMsgId() {
        return andResMsgId;
    }

    public void setErrItems(String errItems) {
        this.errItems = errItems;
    }

    public String getErrItems() {
        return errItems;
    }
}
