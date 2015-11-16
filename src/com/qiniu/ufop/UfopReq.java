package com.qiniu;


public class UfopReq {
    private String cmd;
    private Src src;
    private int mode;
    public static class Src{
        private String url, mimetype, bucket,key;
        private int fsize;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMimetype() {
            return mimetype;
        }

        public void setMimetype(String mimetype) {
            this.mimetype = mimetype;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getFsize() {
            return fsize;
        }

        public void setFsize(int fsize) {
            this.fsize = fsize;
        }
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Src getSrc() {
        return src;
    }

    public void setSrc(Src src) {
        this.src = src;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
