package com.gordarg.sariab.Views;

import com.gordarg.sariab.Models.Post;

public interface IDetailsView {
    void onDownloadFoodMeta(Post food);
    void onDownloadFoodMetaFailed(String Message);
}
