/*
 * Copyright (C) 2015 Tomás Ruiz-López.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.truizlop.sectionedrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

/**
 * An extension to RecyclerView.Adapter to provide sections with headers and footers to a
 * RecyclerView. Each section can have an arbitrary number of items.
 *
 * @param <H> Class extending RecyclerView.ViewHolder to hold and bind the header view
 * @param <VH> Class extending RecyclerView.ViewHolder to hold and bind the items view
 * @param <F> Class extending RecyclerView.ViewHolder to hold and bind the footer view
 */
public abstract class SectionedRecyclerViewAdapter<H extends RecyclerView.ViewHolder,
        VH extends RecyclerView.ViewHolder,
        F extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG = "SectionedRecyclerViewAdapter";

    //无参数构造.
    public SectionedRecyclerViewAdapter() {
        super();
        //注册数据观察者.
        registerAdapterDataObserver(new SectionDataObserver());
        Log.i(TAG, "SectionedRecyclerViewAdapter(): 无参构造");
    }

    /**
     *当条目添加到recyclerView中执行此方法.当设置适配器后会执行一次该方法.
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        setupIndices();
        Log.i(TAG, "onAttachedToRecyclerView: ");
    }

    /**
     * Returns the sum of number of items for each section plus headers and footers if they
     * are provided.
     * 决定条目总数量.
     */
    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: ");
        return count;
    }

    /**
     * 决定创建条目的类型以及视觉样式
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        RecyclerView.ViewHolder viewHolder;
        if(isSectionHeaderViewType(viewType)){
            //若是头条目类型,则创建头视图
            viewHolder = onCreateSectionHeaderViewHolder(parent, viewType);
        }else if(isSectionFooterViewType(viewType)){
            //若是脚条目类型,则创建脚视图
            viewHolder = onCreateSectionFooterViewHolder(parent, viewType);
        }else{
            //否则创建普通视图.
            viewHolder = onCreateItemViewHolder(parent, viewType);
        }
        return viewHolder;
    }

    /**
     * 数据适配,数据填充,根据不同view，绑定不同数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        //
        int section = sectionForPosition[position];
        Log.i(TAG, "section: ***"+section);

        //
        int index = positionWithinSection[position];
        Log.i(TAG, "index: ***"+index);

        if(isSectionHeaderPosition(position)){
            //若是头条目,则填充头视图数据
            onBindSectionHeaderViewHolder((H) holder, section);
        }else if(isSectionFooterPosition(position)){
            //若是脚条目,则填充脚视图数据
            onBindSectionFooterViewHolder((F) holder, section);
        }else{
            //否则普通条目,则填充普通条目数据
            onBindItemViewHolder((VH) holder, section, index);
        }
    }

    /**
     * 重写此方法,决定条目返回类型.
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Log.i(TAG, "getItemViewType: ");
        if(sectionForPosition == null){
            setupIndices();
        }

        int section = sectionForPosition[position];
        int index = positionWithinSection[position];

        if(isSectionHeaderPosition(position)){
            return getSectionHeaderViewType(section);
        }else if(isSectionFooterPosition(position)){
            return getSectionFooterViewType(section);
        }else{
            return getSectionItemViewType(section, index);
        }

    }


    //定义头条目类型
    protected static final int TYPE_SECTION_HEADER = -1;
    //定义脚条目类型
    protected static final int TYPE_SECTION_FOOTER = -2;
    //定义正常条目类型
    protected static final int TYPE_ITEM = -3;

    //定义头部件数组容器,放置所有的头部件位置.
    private int[] sectionForPosition = null;
    //条目在部件组中的位置,
    private int[] positionWithinSection = null;

    //定义是否是头的数组
    private boolean[] isHeader = null;
    //定义是否是脚的数组
    private boolean[] isFooter = null;
    //定义条目数量.
    private int count = 0;

    //位置索引初始化.
    private void setupIndices(){
        Log.i(TAG, "setupIndices: ");
        //计算总条目数量,包含头,正常,脚所有条目
        count = countItems();
        //创建辅助容器数组.
        allocateAuxiliaryArrays(count);
        //计算索引
        precomputeIndices();
    }

    private int countItems() {
        int count = 0;

        //获取头部件的数量
        int sections = getSectionCount();

        for(int i = 0; i < sections; i++){
            //部件数+部件下条目数+部件下脚条目数
            //多个累加!!!
            count += 1 + getItemCountForSection(i) + (hasFooterInSection(i) ? 1 : 0);
        }
        return count;
    }

    //计算放置
    private void precomputeIndices(){
        //获取头部件数量.
        int sections = getSectionCount();
        int index = 0;

        for(int i = 0; i < sections; i++){
            setPrecomputedItem(index, true, false, i, 0);
            index++;

            for(int j = 0; j < getItemCountForSection(i); j++){
                setPrecomputedItem(index, false, false, i, j);
                index++;
            }

            if(hasFooterInSection(i)){
                setPrecomputedItem(index, false, true, i, 0);
                index++;
            }
        }
    }

    private void allocateAuxiliaryArrays(int count) {
        sectionForPosition = new int[count];
        positionWithinSection = new int[count];
        isHeader = new boolean[count];
        isFooter = new boolean[count];
    }

    private void setPrecomputedItem(int index, boolean isHeader, boolean isFooter, int section, int position) {
        this.isHeader[index] = isHeader;
        this.isFooter[index] = isFooter;
        sectionForPosition[index] = section;
        positionWithinSection[index] = position;
    }

    protected int getSectionHeaderViewType(int section){
        return TYPE_SECTION_HEADER;
    }

    protected int getSectionFooterViewType(int section){
        return TYPE_SECTION_FOOTER;
    }

    protected int getSectionItemViewType(int section, int position){
        return TYPE_ITEM;
    }

    /**
     * Returns true if the argument position corresponds to a header
     */
    public boolean isSectionHeaderPosition(int position){
        if(isHeader == null){
            setupIndices();
        }
        return isHeader[position];
    }

    /**
     * Returns true if the argument position corresponds to a footer
     */
    public boolean isSectionFooterPosition(int position){
        if(isFooter == null){
            setupIndices();
        }
        return isFooter[position];
    }

    //判断是否是头条目
    protected boolean isSectionHeaderViewType(int viewType){
        return viewType == TYPE_SECTION_HEADER;
    }

    //判断是否是脚条目
    protected boolean isSectionFooterViewType(int viewType){
        return viewType == TYPE_SECTION_FOOTER;
    }

    /**
     * Returns the number of sections in the RecyclerView
     *返回头部件的数量
     */
    protected abstract int getSectionCount();

    /**
     * Returns the number of items for a given section
     * 返回指定位置处头部件下的普通条目数量
     */
    protected abstract int getItemCountForSection(int section);

    /**
     * Returns true if a given section should have a footer
     * 指定头部件下是否含有脚条目
     */
    protected abstract boolean hasFooterInSection(int section);

    /**
     * Creates a ViewHolder of class H for a Header
     * 创建头视图条目对应的ViewHolder
     */
    protected abstract H  onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType);

    /**
     * Creates a ViewHolder of class F for a Footer
     * 创建脚视图条目对应的ViewHolder
     */
    protected abstract F  onCreateSectionFooterViewHolder(ViewGroup parent, int viewType);

    /**
     * Creates a ViewHolder of class VH for an Item
     * 创建普通视图条目对应的ViewHolder
     */
    protected abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    /**
     * Binds data to the header view of a given section
     */
    protected abstract void onBindSectionHeaderViewHolder(H holder, int section);

    /**
     * Binds data to the footer view of a given section
     */
    protected abstract void onBindSectionFooterViewHolder(F holder, int section);

    /**
     * Binds data to the item view for a given position within a section
     */
    protected abstract void onBindItemViewHolder(VH holder, int section, int position);

    //数据观察者
    class SectionDataObserver extends RecyclerView.AdapterDataObserver{
        @Override
        public void onChanged() {//数据变化执行此方法.
            setupIndices();
        }
    }
}
