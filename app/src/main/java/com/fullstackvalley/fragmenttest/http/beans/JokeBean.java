package com.fullstackvalley.fragmenttest.http.beans;

import java.util.List;

public class JokeBean {

    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"笑话1","hashId":"1321asdAgy76gmo","unixtime":1223,"updatetime":"213123123"},{"content":"asdasd","hashId":"asdad78234hifiu","unixtime":312313123,"updatetime":"12321313"},{"content":"asdasd","hashId":"asdad78234hifiu","unixtime":312313123,"updatetime":"12321313"},{"content":"asdasd","hashId":"asdad78234hifiu","unixtime":312313123,"updatetime":"12321313"}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content : 笑话1
             * hashId : 1321asdAgy76gmo
             * unixtime : 1223
             * updatetime : 213123123
             */

            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
