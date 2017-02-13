package com.ajaystha.recyclerview_infinitescroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ajayshrestha on 2/12/17.
 */

public abstract class InfiniteScrollRecyclerView extends RecyclerView.OnScrollListener {


    private LinearLayoutManager mLayoutManager;

    private int mTotalPreviousItemCount = 0; //Default

    private int mThreshold = 5; //Default

    private int mFirstVisibleItemPosition;
    private int mLastVisibleItemPosition;

    private int mFirstCompletelyVisibleItemPosition;
    private int mLastCompletelyVisibleItemPosition;

    private int mCurrentVisibleItemPosition;


    private int mTotalItemCount;
    private int mVisibleItemCount;

    private boolean isFetching;

    private int mCurrentPage;

    private boolean isScrollingUp = false;
    private boolean isPreviouslyScrolled = false;


    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = (LinearLayoutManager) layoutManager;
    }

    /**
     * threshold value - visible item before fetching more item
     *
     * @param thresholdValue
     */
    public void setThresholdValue(int thresholdValue) {
        this.mThreshold = thresholdValue;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        mFirstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
        mLastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();

        mFirstCompletelyVisibleItemPosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
        mLastCompletelyVisibleItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition();

        mTotalItemCount = mLayoutManager.getItemCount();
        mVisibleItemCount = mLastVisibleItemPosition - mFirstVisibleItemPosition + 1;


        /********************* Fetching more items *************************/

        if (isFetching) {
            if (mTotalItemCount > mTotalPreviousItemCount) {
                isFetching = false;
                mTotalPreviousItemCount = mTotalItemCount;
            }
        }

        if (!isFetching) {
            if (mTotalItemCount - mVisibleItemCount < mFirstVisibleItemPosition + mThreshold) {
                onFetchMoreItem(mCurrentPage++);
                isFetching = true;
            }
        }

        /********************* Reach Top / Bottom *************************/

        if (mLayoutManager.findViewByPosition(mFirstCompletelyVisibleItemPosition).getTop() == 0
                && mFirstCompletelyVisibleItemPosition == 0) {
            onReachTop();
        } else if (mLastCompletelyVisibleItemPosition == mTotalItemCount - 1) {
            onReachBottom();
        }

        /********************* Scroll Up / Down *************************/

        if (mCurrentVisibleItemPosition < mFirstVisibleItemPosition) {
            isScrollingUp = true;
        } else if (mCurrentVisibleItemPosition > mFirstVisibleItemPosition) {
            isScrollingUp = false;
        }
        mCurrentVisibleItemPosition = mFirstVisibleItemPosition;


        //stop firing all the time. Only fired if scroll changed
        if (isPreviouslyScrolled != isScrollingUp) {

            if (!isScrollingUp) onScrollDown();
            else onScrollUp();

            isPreviouslyScrolled = isScrollingUp;
        }


    }

    /**
     * fetch more data based on page number
     *
     * @param currentPage
     */
    protected abstract void onFetchMoreItem(int currentPage);

    /**
     * This method fires when reach to top
     */
    protected void onReachTop() {
    }

    /**
     * This method fires when reach to bottom
     */
    protected void onReachBottom() {
    }

    /**
     * This method fires while scrolling up
     */
    protected void onScrollUp() {

    }

    /**
     * This method fires while scrolling down
     */
    protected void onScrollDown() {

    }
}
