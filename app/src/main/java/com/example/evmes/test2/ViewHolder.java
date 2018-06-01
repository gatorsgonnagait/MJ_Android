package com.example.evmes.test2;

import android.widget.ImageView;

public class ViewHolder {
    ImageView imageView;
    String txtTitle;
    String url;

    public ViewHolder(String txtTitle, String url) {

        this.txtTitle = txtTitle;
        this.url = url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
