package com.youngmanster.collection.mvp.presenter.wechat.okhttpcache;

import com.youngmanster.collection.been.Result;
import com.youngmanster.collection.been.wechat.WeChatNews;
import com.youngmanster.collection.http.ApiClient;
import com.youngmanster.collection.http.ApiUrl;
import com.youngmanster.collection.mvp.contract.wechat.okhttpcache.WeChatNBANewsContract;
import com.youngmanster.collectionlibrary.db.DataManager;
import com.youngmanster.collectionlibrary.network.RequestBuilder;
import com.youngmanster.collectionlibrary.network.rx.RxObservableListener;

import java.util.List;


/**
 * Created by yangyan
 * on 2018/3/21.
 */

public class WeChatNBANewsPresenter extends WeChatNBANewsContract.Presenter {
    @Override
    public void requestNBANews(int page, int num) {

        RequestBuilder<Result<List<WeChatNews>>> resultRequestBuilder = new RequestBuilder<>(new RxObservableListener<Result<List<WeChatNews>>>(mView) {
            @Override
            public void onNext(Result<List<WeChatNews>> result) {
                mView.refreshUI(result.getNewslist());
            }
        });

        resultRequestBuilder
                .setUrl(ApiUrl.URL_WETCHAT_NBA_NEWS)
                .setRequestParam(ApiClient.getRequiredBaseParam())
                .setTransformClass(WeChatNews.class)
                .setParam("page",page)
                .setParam("num",num);

        rxManager.addObserver(DataManager.getInstance(DataManager.DataType.RETROFIT).httpRequest(resultRequestBuilder));
    }
}
