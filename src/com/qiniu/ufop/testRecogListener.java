package com.qiniu;

import java.io.OutputStream;

public class testRecogListener implements RecognizerListener{
    private StringBuffer mResult = new StringBuffer();
    private OutputStream os;

    public testRecogListener() {
    }

    public testRecogListener(StringBuffer sb){
        mResult = sb;

    }
    public StringBuffer getmResult() {
        return mResult;
    }
    @Override
    public void onVolumeChanged(int i) {

    }
    @Override
    public void onBeginOfSpeech() {

    }
    @Override
    public void onEndOfSpeech() {

    }
    @Override
    public void onResult(RecognizerResult result, boolean islast) {
        System.out.println("onResult enter");
        if (result == null) {
            return;
        }
        mResult.append(result.getResultString());

        if (islast) {
            System.out.println("[UFOP] 输出结果" + mResult.toString());


        }
    }

    @Override
    public void onError(SpeechError error) {

        System.err.println("[UFOP] **********************" + error.getErrorCode()
                + "*************"+ error.getMessage());

    }

    @Override
    public void onEvent(int i, int i1, int i2, String s) {

    }
}
