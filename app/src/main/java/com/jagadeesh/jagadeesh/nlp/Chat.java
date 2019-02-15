package com.jagadeesh.jagadeesh.nlp;

import java.util.concurrent.ExecutionException;

public class Chat {
    private String sender;
    private String receiver;

    public Chat(){}
    public Chat(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    private String message;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }
    public String getT(){
        //  final TextView textView = (TextView) findViewById(R.id.textview);

        GoogleTranslate googleTranslate = new GoogleTranslate();


        String result = "";
        try {
            result += googleTranslate.execute(message,"te").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        // textView.setText(result );

        return result;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
