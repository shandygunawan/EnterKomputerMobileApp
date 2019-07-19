package com.shandy.enterkomputermobileapp.presentation

open class BasePresenter<T: BaseView> {

    var mView: T? = null

    fun attachView(view: T){
        mView = view
    }

    fun detachView(){
        mView = null
    }

    fun isViewAttached(): Boolean = (mView != null)
}